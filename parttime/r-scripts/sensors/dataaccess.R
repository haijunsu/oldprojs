library("RCurl")
library(ggplot2)
library(XML)
library(data.table)
library(plyr)
library(foreach)
library(compiler)
library(fasttime)

initConnection <- function(){
  curl <- getCurlHandle()
  curlSetOpt(cookiejar="c:/tmp/cookies.txt", curl=curl)
  postForm("http://bpl.cisdd.org/login.php",username="tbrown",password="tbrown", submit="Login", curl=curl)
  return (curl)
}

getCSVList <- function (curl){
  tableurl <- getURL("http://bpl.cisdd.org/reportlist.php",curl = curl)
  header     <- c("#", "College", "Building", "Directory","Filename","Filesize","Datasets","Time Entered")
  colClasses <- c("integer","character","character","character","character","integer","integer","character")

  tableMain<-readHTMLTable(tableurl,header=TRUE,colClasses,trim = TRUE, stringsAsFactors = FALSE)
  DFMain<-data.table(tableMain[[1]])
  return (DFMain)
}

getSensorCatalog<-function(curl) {
  tableurl <- getURL("http://bpl.cisdd.org/sensortable.php?buildingid=1",curl = curl)
  header     <- c("ID #", "Sensor Name", "Sensor Type", "Floor","Device ID","Building","Campus")
  colClasses <- c("integer","character","character","character","integer","character","character")
  tableMain<-readHTMLTable(tableurl,header=TRUE,colClasses,trim = TRUE, stringsAsFactors = FALSE)
  DT<-data.table(tableMain[[1]])
  return (DT)
}

getCSVCatalog<-function(curl, name ){
  tableurl <- getURL("http://bpl.cisdd.org/reportlist.php",curl = curl)
  header     <- c("#", "College", "Building", "Directory","Filename","Filesize","Datasets","Time Entered")
  colClasses <- c("integer","character","character","character","character","integer","integer","character")
  tableMain<-readHTMLTable(tableurl,header=TRUE,colClasses,trim = TRUE, stringsAsFactors = FALSE)
  DFMain<-data.table(tableMain[[1]])

  id<-DFMain[Filename %like% name][,list(`#`)]
  urlreport <- sprintf("http://bpl.cisdd.org/reportdetail.php?reportid=%s",id[1])
  headerReport     <- c("DataSetID", "Sensor Name", "Interval", "StartDate","End Date","# Records")
  colClassesReport <- c("integer","character","integer","character","character","integer")
  tableReport<-readHTMLTable(utableurl2,header=TRUE,colClassesReport ,trim = TRUE, stringsAsFactors = FALSE)
  DS<-data.table(tableReport[[1]])
  return (DS)
}

getSensorData<-function(curl, setId){
  urldetail <- sprintf("http://bpl.cisdd.org/datasetdetail.php?datasetid=%s",setId)
  tabledetailurl = getURL(urldetail ,curl = curl)
  colClassesDetail <- c("character","numeric","character")
  detaildata<-readHTMLTable(tabledetailurl ,header=TRUE,colClassesDetail ,trim = TRUE, stringsAsFactors = FALSE)
  if (is.null(detaildata[[2]])){
    return (NULL)
  }
  DT<-data.table(detaildata[[2]])
  DT$Time = fastPOSIXct(DT$Time)
  return (DT)
}


downloadSensorData <- function(fromDate, toDate, sensorName){

  if (!exists("sensorcat"))
    assign("sensorcat",getSensorCatalog(curl),envir=.GlobalEnv)

  id<-sensorcat[`Sensor Name` == sensorName][,`ID #`]
  if (length(id) == 0)
  {
    print(sprintf("%s not in sensorcat",sensorName))
    return()
  }
  urlsensor <- sprintf("http://bpl.cisdd.org/sensordetail.php?sensorid=%s",id[1])
  tablesensor = getURL(urlsensor ,curl = curl)
  colClassesDetail <- c("integer", "character","character","integer","character","character","integer","character")
  detaildata<-readHTMLTable(tablesensor ,header=TRUE,colClassesDetail ,trim = TRUE, stringsAsFactors = FALSE)
  tdata<-data.table(detaildata[[1]])

  tdatal <- tdata[(fastPOSIXct(`Start Time`) >= fastPOSIXct(fromDate)) & (Interval==15),]
  idlist <- tdatal[(fastPOSIXct(`Start Time`) <= fastPOSIXct(toDate)) & (Interval==15),`Dataset #`]

  if (length(idlist) == 0){
    print ("id list is empty for the time period")
    if (exists(sensorName))
      return()
    else
    {
      print("downloading everything available")
      idlist <-  tdata[Interval==15,`Dataset #`]
    }
  }
  print (idlist)
  for (reportid in idlist){
    print ("collecting reportid #")
    print(reportid)
    t <- getSensorData(curl,reportid)
    print ("check sensor")
    print (sensorName)
    if (is.null(t) || nrow(t) == 0)
    {
      print("empty table !")
      return ()
    }
    if (!exists(sensorName)){
      print ("creating data.table" )
      assign(sensorName,t, envir = .GlobalEnv)
      print(sensorName)
    }
    else {
      dt <- get(sensorName)
      dt <- unique(rbind(dt, t))
      assign(sensorName,dt,envir=.GlobalEnv)
    }
  }
}

checkBoundaries<-function(fromDate, toDate,sensorName){
  if (!exists(sensorName)){
    print (sprintf("download data for sensor: %s",sensorName))
    downloadSensorData (fromDate, toDate, sensorName)
  }
  if (exists(sensorName)){
    minbound <- get(sensorName)[,min(Time)]
    maxbound <- get(sensorName)[,max(Time)]
    if (fastPOSIXct(fromDate)<fastPOSIXct(minbound)){
      print (sprintf("down min for %s until %s",sensorName,minbound))
      downloadSensorData(fromDate, minbound,sensorName)
    }
    if (fastPOSIXct(toDate)>fastPOSIXct(maxbound)){
      print (sprintf("up max for %s",sensorName))
      downloadSensorData(maxbound, toDate, sensorName)
    }
  }
}


plotSensors <- function(fromDate, toDate, sensorlist, filt = NULL){

  if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))
  {
    print (sprintf("bad input : %s > %s",toDate, fromDate))
    return ()
  }
  dplot <-data.table()
  for (sensor in sensorlist){
    if (!exists(sensor)){
      print (sprintf("download data for sensor: %s",sensor))
      downloadSensorDataC (fromDate, toDate, sensor )
    }
    else{
      checkBoundariesC(fromDate,toDate, sensor)
    }
    if (is.null(filt) || filt == ""){
      t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
    }
    else{
      t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
    }

    t[,label:=sensor]
    dplot<-rbind(t,dplot)
  }
  assign("dplot",dplot,envir = .GlobalEnv)
  plt <- ggplot(dplot, aes(x=V1,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
  plot(plt)
  return (plt)
}


plotTable <- function(fromDate, toDate, sensor){
  print (sensor)
  if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))
  {
    print (sprintf("bad input : %s > %s",toDate, fromDate))
    return ()
  }
  dplot <-data.table()

  t <- sensor[(Value > 0 & fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
  t[,label:="sensor"]
  dplot<-rbind(t,dplot)

  assign("dplot",dplot,envir = .GlobalEnv)
  plt <- ggplot(dplot, aes(x=V1,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
  return (plt)
}

bulkDownload<-function(fromDate,toDate){
  if (!exists("sensorcat"))
    assign("sensorcat",getSensorCatalog(curl),envir=.GlobalEnv)
  for (sensor in  sensorcat[,`Sensor Name`]){
    checkBoundaries(fromDate,toDate,sensor)
    save.image()
  }
}
resetDataC <-function(list){
  for(t in list )
  {
    ll <- grep(t,ls())
    rm(list = ll, envir = globalenv())
    print (sprintf("%s cleared",t))
  }

}
plotHist<-function(listt,fromDate, toDate, filt){
  name <-""
  if (is.null(t))
  {
    return (NULL)
  }
  values <- NULL
  for (t in listt){
    if (is.null(filt) || filt == ""){
      val <- get(t)[,Value]
    }
    else{
      print(sprintf("PlotHist %s= [%s]",t,filt))
      val <- get(t)[eval(parse(text=filt)) & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)),Value]
    }
    if (is.null(values))
      values<-val
    else
      values<-rbind(val,values)
    name <- paste(name,t)
  }
  r <-range(values)
  f <- ((r[2]-r[1])/100)
  breaks = seq(r[1],r[2], by=f)
  hist(values,breaks=300,main=name,freq=FALSE)
  lines(density(values),col="darkblue")
  #  curve(dnorm(x, mean=mean(values), sd=sd(values)), add=TRUE, col="green")

}


reconnect<-function(){
  assign("curl",initConnection(),envir=.GlobalEnv)
  rm (sensorcat)
  assign("sensorcat",getSensorCatalog(curl),envir=.GlobalEnv)
  sensorcat <- getSensorCatalog(curl)
}

checkRows<-function(sensor,fromDate,toDate,targetn) {

  n = get(sensor)[ between(Time,fromDate, toDate), .N]
  print(sensor)
  if(n < targetn)
    return (list(sensor, targetn, n))
  else
    return (NULL)
}

checkNumberRows<-function(numberD,upto = NULL){

  res <- data.table(Sensor="",Expected = "", Actual="")
  targetn <- numberD * 4 * 24
  if (is.null(upto))
    upto<-Sys.Date()
  toDate = fastPOSIXct(upto)
  fromDate = fastPOSIXct(upto - numberD)
  for (sensor in sensorcat[,`Sensor Name`])  {
    a = checkRowsC(sensor,fromDate,toDate,targetn)
    if (!is.null(a) ){
      res<-rbind(res,a)
    }

  }
  return(data.frame(res)[-1,])
}

setIndex<-function(sensor) {
  stable <-get(sensor)
  stable$Time = fastPOSIXct(stable$Time)
  setkey(get(sensor),Time,Value)
  stable <- stable[order(Time),]
  assign(sensor,stable,envir = .GlobalEnv)
  print(sensor)
  return (sensor)
}

setIndices<-function(){
  res <- for (sensor in sensorcat[,`Sensor Name`]) {
    setIndex(sensor)
  }
  return(res)
}

reconnect<-function(){
  cat("connection -> curl\n")
  assign("curl", initConnection(), envir=.GlobalEnv)
  cat("sensor catalog  -> sensorcat\n")
  assign("sensorcat",getSensorCatalog(curl),envir=.GlobalEnv)
  cat("done\n")
}
dailyDownload<-function(sensor){
  checkBoundaries(Sys.Date()-1,Sys.Date(),sensor)
}
plotHistC <- cmpfun(plotHist)
plotSensorsC <-cmpfun(plotSensors)
checkRowsC <-cmpfun(checkRows)
checkNumberRowsC <-cmpfun(checkNumberRows)
reconnectC <-cmpfun( reconnect)
checkBoundariesC <-cmpfun(checkBoundaries)
downloadSensorDataC <-cmpfun(downloadSensorData)


getTable <- function(fromDate, toDate, sensorlist, filt){
  if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))  {
    print (sprintf("bad input : %s > %s",toDate, fromDate))
    return ()
  }
  tdata <-data.table()
  for (sensor in sensorlist){
    if (!exists(sensor)){
      print (sprintf("download data for sensor: %s",sensor))
      downloadSensorData (fromDate, toDate, sensor )
    }
    else{
      checkBoundaries(fromDate,toDate, sensor)
    }
	if (is.null(filt) || filt == ""){
		t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
	}
	else{
		t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
	}

    t[,label:=sensor]
    tdata<-rbind(t,tdata)
  }
  return (tdata)
 # t <- get(sensor)[(Value > 0 & as.POSIXct(`Time`) <= as.POSIXct(toDate)) & (as.POSIXct(`Time`) >= as.POSIXct(fromDate)), as.POSIXct(Time),Value]
 # t[,label:="sensor"]
 # dplot<-rbind(t,dplot)

 # assign("dplot",dplot,envir = .GlobalEnv)
 # plt <- ggplot(dplot, aes(x=V1,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
 # return (plt)
}

getTable2 <- function(fromDate, toDate, sensorlist, xaixs, filt){
	if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))  {
		print (sprintf("bad input : %s > %s",toDate, fromDate))
		return ()
	}
	tXaixs = getTable(fromDate, toDate, c(xaixs), filt)
	tx<-tXaixs$Value
	tdata <-data.table()
	for (sensor in sensorlist){
		if (!exists(sensor)){
			print (sprintf("download data for sensor: %s",sensor))
			downloadSensorData (fromDate, toDate, sensor )
		}
		else{
			checkBoundaries(fromDate,toDate, sensor)
		}
		if (is.null(filt) || filt == ""){
			t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
		}
		else{
			t <- get(sensor)[eval(parse(text=filt))  & (fastPOSIXct(`Time`) <= fastPOSIXct(toDate)) & (fastPOSIXct(`Time`) >= fastPOSIXct(fromDate)), fastPOSIXct(Time),Value]
		}
		if (length(t)==0) {
			print("t is empty!!!!")
		} else {
		t[,label:=sensor]
		t[, XValue:=tx]

		#setkey(tdata, c("V1"))
		  print(t)
		  tdata<-rbind(t,tdata)
	  }
	}
	#setnames(tdata, "XValue", xaixs)
   # setnames(tdata, "label", "Sensor")
    #setnames(tdata, "V1", "Time")
	print(tdata)
	return (tdata)
	# t <- get(sensor)[(Value > 0 & as.POSIXct(`Time`) <= as.POSIXct(toDate)) & (as.POSIXct(`Time`) >= as.POSIXct(fromDate)), as.POSIXct(Time),Value]
	# t[,label:="sensor"]
	# dplot<-rbind(t,dplot)

	# assign("dplot",dplot,envir = .GlobalEnv)
	# plt <- ggplot(dplot, aes(x=V1,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
	# return (plt)
}


plotArea<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)

#  ss <- ddply(bdata, "V1", summarise, ssValue=mean(Value), sslabel=label)
#  print(ss)
#  plt <- ggplot(ss, aes(x=V1,y=ssValue, fill=sslabel)) + geom_bar(position="dodge", stat="identity")
#  plt <- ggplot(bdata, aes(x=V1,y=Value, fill=label)) + geom_bar(position="dodge", stat="identity")
#  plt <- ggplot(bdata, aes(x=V1,y=Value, fill=label)) + geom_bar(stat="identity")
  plt <- qplot(V1, Value, data=bdata, fill=label, geom="area", main="geom=area")
  plot(plt)
}
plotLine<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)
  plt <- qplot(V1, Value, data=bdata, colour=label, geom="line", main="geom=line")
  plot(plt)
}
plotLineStack<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)
  plt <- qplot(V1, Value, data=bdata, colour=label, geom="line", position="stack", main="geom=line, position=stack")
  plot(plt)
}

getDisplayTable<-function(fromDate, toDate, sensorlist, xaixs, filt){
  bdata <- getTable2(fromDate, toDate, sensorlist, xaixs, filt)
  print(bdata)
  bdata$V1<-as.character(bdata$V1)
  #colnames(bdata)<-c("Values", "Time", "Sensors")
  setnames(bdata, "label", "Sensor")
  setnames(bdata, "V1", "Time")
  setnames(bdata, "XValue", xaixs)
  bdata <- subset(bdata, select=c(3,2,1,4))
  print(bdata)
  return(bdata)
}
plotArea2<-function(fromDate, toDate, sensorlist, xaixs, filt){
  bdata <- getTable2(fromDate, toDate, sensorlist, xaixs, filt)

  #  ss <- ddply(bdata, "V1", summarise, ssValue=mean(Value), sslabel=label)
  #  print(ss)
  #  plt <- ggplot(ss, aes(x=V1,y=ssValue, fill=sslabel)) + geom_bar(position="dodge", stat="identity")
  #plt <- ggplot(bdata, aes(x=mean(xaixs),y=Value, fill=label)) + geom_bar(position="dodge", stat="identity")
  #  plt <- ggplot(bdata, aes(x=V1,y=Value, fill=label)) + geom_bar(stat="identity")
  #setnames(bdata, xaixs, "VV")
  plt <- qplot(XValue, Value, data=bdata, fill=label, geom="area", main="geom=area", xlab=xaixs)
  plot(plt)
}

curl <- initConnection()
sensorcat <- getSensorCatalog(curl)
