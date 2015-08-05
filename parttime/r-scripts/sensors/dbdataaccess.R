library("RCurl")
library(ggplot2)
library(XML)
library(data.table)
library(plyr)
library(foreach)
library(compiler)
library(fasttime)
library("RMySQL")


connectdb <- function() {
	#print ("Connecting mysql ...")
    #con <- dbConnect(MySQL(), user="root", password="root", dbname="bpl", host="127.0.0.1", port=3306)
	connectMySql <- dbConnect(MySQL(), user="root", password="tbrown", dbname="bpl", host="127.0.0.1", port=3308)
	return (connectMySql)
}
closedb <- function(con) {
	#print("close db connect...")
	dbDisconnect(con)
}


isEmptyTable <- function(dataTable) {
	if (is.null(dataTable) || is.na(any(nrow(dataTable))) || any(nrow(dataTable) == 0) || is.na(any(ncol(dataTable))) || any(ncol(dataTable) == 0))
		return (TRUE)
	return (FALSE)
}

getSensorCatalog<-function() {
  sql <- "select id as 'ID #', sensor_name as 'Sensor Name', sensor_type as 'Sensor Type', floor as 'Floor', device_id as 'Device ID', building as 'Building', campus as 'Campus' from tbl_sensor order by sensor_name, floor"
  print (sql)
  con <- connectdb()
  on.exit(closedb(con))
  rs <- dbGetQuery(con, sql)
  DT<-data.table(rs)
  return (DT)
}

downloadSensorData <- function(fromDate, toDate, sensorName, filt = NULL){
  if (length(sensorName) == 0) {
	  print ("sensor is null")
	  return ()
  }
  con <- connectdb()
  on.exit(closedb(con))

  #print (sprintf("input : fromData = %s, toDate = %s, sensorName = '%s'",toDate, fromDate, sensorName))
  getIDSql <- sprintf("select id from tbl_sensor where sensor_name = '%s'", sensorName)
  #print (getIDSql)
  sensorID <- dbGetQuery(con, getIDSql)
  getDataSql <- NULL
  if (is.null(filt) || filt == ""){
	  getDataSql <- sprintf("select record_time as XTime, value as Value, '%s' as label from tbl_record where record_interval = 15 and sensor_id=%s and record_time >= '%s' and record_time <='%s'", sensorName, sensorID[1][1], fromDate, toDate)
  } else {
	  getDataSql <- sprintf("select record_time as XTime, value as Value, '%s' as label from tbl_record where record_interval = 15 and sensor_id=%s and record_time >= '%s' and record_time <='%s'", sensorName, sensorID[1][1], fromDate, toDate)
      getDataSql <- paste(getDataSql, " and ", filt)
  }
  getDataSql <- paste(getDataSql, " order by record_time")
  #print (getDataSql)
  rs <- dbGetQuery(con, getDataSql)
  DT <- data.table(rs)
  DT$XTime = fastPOSIXct(DT$XTime)
  #print (DT)
  return (DT)
}


plotSensors <- function(fromDate, toDate, sensorlist, filt = NULL){

  if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))
  {
    print (sprintf("bad input : %s > %s",toDate, fromDate))
    return ()
  }
  dplot <- getTable(fromDate, toDate, sensorlist, filt)
  if (length(dplot) == 0) {
	  return ()
  }
  plt <- ggplot(dplot, aes(x=XTime,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
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
  dplot <- downloadSensorData(fromDate, toDate, sensor)
  if (isEmptyTable(dplot)) {
	  print (sprintf("There is no data of %s with time range %s - %s",sensor, toDate, fromDate))
	  return ()
  }
  plt <- ggplot(dplot, aes(x=XTime,y=Value,group=label)) + geom_line(aes(colour=label)) + geom_point()
  return (plt)
}

plotHist<-function(listt,fromDate, toDate, filt){
  name <-""
  values <- NULL
  for (sensorName in listt){
	val <- downloadSensorData(fromDate, toDate, sensorName, filt)[,Value]
	if (isEmptyTable(val)) {
		next
	}
	if (!is.null(filt) || filt != ""){
      print(sprintf("PlotHist %s= [%s]",sensorName,filt))
    }
    if (is.null(values))
      values<-val
    else
      values<-rbind(val,values)
    name <- paste(name,sensorName)
  }
  if(isEmptyTable(values)) {
	  return ()
  }
  r <-range(values)
  f <- ((r[2]-r[1])/100)
  breaks = seq(r[1],r[2], by=f)
  hist(values,breaks=300,main=name,freq=FALSE)
  lines(density(values),col="darkblue")

}

getTable <- function(fromDate, toDate, sensorlist, filt) {
	if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))
	{
		print (sprintf("bad input : %s > %s",toDate, fromDate))
		return ()
	}
	con <- connectdb()
	on.exit(closedb(con))

	dplot <- data.table()

	for (sensor in sensorlist){
		data <- downloadSensorData(fromDate, toDate, sensor, filt)
		#assign(sensor, data, .GlobalEnv)
		if (isEmptyTable(data)) {
			print ("getTable empty result")
			next
		}
		dplot<-rbind(data,dplot)
	}
	return (dplot)
}

getTable2 <- function(fromDate, toDate, sensorlist, xaxis, filt){
	print (sprintf("input : fromData = %s, toDate = %s",toDate, fromDate))
	if (fastPOSIXct(fromDate) > fastPOSIXct(toDate))  {
		print (sprintf("bad input : %s > %s",toDate, fromDate))
		return ()
	}
	if (length(xaxis) == 0) {
		print ("sensor of xaxis is null")
		return ()
	}
	con <- connectdb()
	on.exit(closedb(con))

	getIDSql <- sprintf("select id from tbl_sensor where sensor_name = '%s'", xaxis)
	print (getIDSql)
	xaxisID <- dbGetQuery(con, getIDSql)[1][1]
	tdata <-data.table()
	for (sensor in sensorlist){
		if (length(sensor) == 0) {
			print ("sensor is null")
			next
		}
		getIDSql <- sprintf("select id from tbl_sensor where sensor_name = '%s'", sensor)
		print (getIDSql)
		sensorID <- dbGetQuery(con, getIDSql)[1][1]
		getDataSql <- NULL
		getDataSql <- sprintf("select '%s' as label, t1.record_time as XTime, t1.value as Value, t2.value as XValue from tbl_record as t1, tbl_record as t2 where t1.record_interval = 15 and t1.record_time = t2.record_time and t1.sensor_id=%s and t2.sensor_id=%s and t1.record_time >= '%s' and t1.record_time <='%s'", sensor, sensorID, xaxisID, fromDate, toDate)
		if (!is.null(filt) || filt != ""){
			getDataSql <- paste(getDataSql, " and t1.", filt, sep="")
		}
		getDataSql <- paste(getDataSql, " order by t1.record_time")
		print (getDataSql)
		rs <- dbGetQuery(con, getDataSql)
		DT <- data.table(rs)
		#assign(sensor, DT, .GlobalEnv)
		if (isEmptyTable(DT)) {
			print ("getTable2 empty result")
			next
		}
		tdata<-rbind(DT,tdata)
	}
	tdata$XTime = fastPOSIXct(tdata$XTime)
	print(tdata)
	return (tdata)
}

resetDataC <-function(list){
	for(t in list )
	{
		ll <- grep(t,ls())
		rm(list = ll, envir = globalenv())
		print (sprintf("%s cleared",t))
	}

}


plotArea<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)
  if (isEmptyTable(bdata))
	  return ()
  plt <- qplot(XTime, Value, data=bdata, fill=label, geom="area", main="geom=area")
  plot(plt)
}
plotLine<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)
  if (isEmptyTable(bdata))
	  return ()
  plt <- qplot(XTime, Value, data=bdata, colour=label, geom="line", main="geom=line")
  plot(plt)
}
plotLineStack<-function(fromDate, toDate, sensorlist, filt){
  bdata <- getTable(fromDate, toDate, sensorlist, filt)
  if (isEmptyTable(bdata))
	  return ()
  plt <- qplot(XTime, Value, data=bdata, colour=label, geom="line", position="stack", main="geom=line, position=stack")
  plot(plt)
}

getDisplayTable<-function(fromDate, toDate, sensorlist, xaxis, filt){
  bdata <- getTable2(fromDate, toDate, sensorlist, xaxis, filt)
  print(bdata)
  if (isEmptyTable(bdata)) {
	  return ()
  }
  bdata$XTime<-as.character(bdata$XTime)
  setnames(bdata, "label", "Sensor")
  setnames(bdata, "XTime", "Time")
  setnames(bdata, "XValue", xaxis)
  return(bdata)
}
plotArea2<-function(fromDate, toDate, sensorlist, xaxis, filt){
  bdata <- getTable2(fromDate, toDate, sensorlist, xaxis, filt)
  if (isEmptyTable(bdata))
	  return ()

  plt <- qplot(XValue, Value, data=bdata, colour=label, geom="line", main="geom=line", xlab=xaxis)
  plot(plt)
}

sensorcat <- getSensorCatalog()

fakefunc <- function(a1 = NULL, a2 = NULL, a3 = NULL, a4 = NULL, a5 = NULL) {
	print("This is a fake function!")
}

plotHistC <- cmpfun(plotHist)
plotSensorsC <-cmpfun(plotSensors)
checkRowsC <-cmpfun(fakefunc)
checkNumberRowsC <-cmpfun(fakefunc)
reconnectC <-cmpfun( fakefunc)
checkBoundariesC <-cmpfun(fakefunc)
downloadSensorDataC <-cmpfun(fakefunc)

