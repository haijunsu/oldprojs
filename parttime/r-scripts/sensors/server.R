source ("dbdataaccess.R")
library("shiny")
library(datasets)

shinyServer( function(input, output, session) {
  observe({
    floor <- input$floor
    isolate({
      output$sensors <-renderUI({
        selectInput("dataset",multiple=TRUE,"Choose one or several sensors :", choices = sensorcat[Floor == floor,`Sensor Name`])
      })
    })
  })

    observe({
      assign("dates",input$dates,.GlobalEnv)
      assign("dataset" ,input$dataset,.GlobalEnv)
      assign("filter",input$filter,.GlobalEnv)
      assign("numberD",input$numberD,.GlobalEnv)
      assign("datefrom",input$datecheck,.GlobalEnv)
    })
	observe({
			clear <- input$clear
			if (clear == 0)
				return (NULL)
			assign("dataset",NULL,.GlobalEnv)
			assign("xaxis",c(),.GlobalEnv)
			output$Xaxis <- renderText({get("xaxis")})
			assign("total",c(),.GlobalEnv)
			output$Selected <- renderText({get("total")})
		})
	observe({
			undo <- input$undo
			if (undo == 0)
				return (NULL)
			toremove <- get("dataset")
			tl <- get("total")
			tl <- tl[tl!=toremove]
			assign("total",tl,.GlobalEnv)
			output$Selected <- renderText({get("total")})
			print(tl)
		})
	observe({
			add <- input$add
			if (add == 0)
				return (NULL)
			set<-get("dataset")
			if (is.null(set))
				return (NULL)
			if (!exists("total"))
				assign("total",c(),.GlobalEnv)
			tl <- unique(c(get("total"),set))
			assign("total",tl,.GlobalEnv)
			output$Selected <- renderText({get("total")})
			print(tl)

		})
    observe({
			addXAxis <- input$addXAxis
			if (addXAxis == 0)
				return (NULL)
			set<-get("dataset")
			if (is.null(set))
				return (NULL)
			#if (!exists("xaxis"))
			assign("xaxis",c(),.GlobalEnv)
			tl <- unique(c(get("xaxis"),set))
			assign("xaxis",tl,.GlobalEnv)
			output$Xaxis <- renderText({get("xaxis")})
			print(tl)

		})
    observe({
      connect <- input$reconnect
      if (connect == 0)
        return (NULL)
      reconnectC()
    })
    observe({
      reset <- input$reset
      if (reset == 0)
        return (NULL)
      resetDataC(get("dataset"))
    })
    observe({
      check <- input$checkdata
      if (check == 0)
        return (NULL)
      sum <- checkNumberRowsC(as.integer(get("numberD")),get("datefrom"))
      #output$checksummary <- renderTable  ({sum})
    })
    observe({
      run <- input$run
      if (run == 0)
        return (NULL)
	  print(dates)
	  data<-get("dataset")
	  if (exists("total")) {
		  data <- get("total")
		  if (length(data) == 0)
			  data<-get("dataset")
	  }
	  xaxisSensor=data[1]
	  if (exists("xaxis")) {
		  xaxisSensor <- get("xaxis")[1]
		  if (length(xaxisSensor) == 0)
			  xaxisSensor<-data[1]
	  }
	  print(data)
	  print(xaxisSensor)
	  output$main_plot <- renderPlot({
        plotSensorsC(dates[1],dates[2], data, filter)

      })
      output$density <- renderPlot({
        plotHistC(data,dates[1],dates[2],filter)
      })
      output$area2<- renderPlot({
			plotArea2(dates[1], dates[2], data,xaxisSensor, filter)
		})
      output$area <- renderPlot({
        plotArea(dates[1], dates[2], data, filter)
      })
      output$line <- renderPlot({
        plotLine(dates[1], dates[2], data, filter)
      })
      output$lineStack <- renderPlot({
        plotLineStack(dates[1], dates[2], data, filter)
      })
      output$data <- renderTable({
        getDisplayTable(dates[1], dates[2], data, xaxisSensor, filter)
      })
      output$summary <- renderPrint({
		if (exists("xaxis")) {
			if (exists("xaxis")) {
				xaxisSensor <- get("xaxis")[1]
				if (isEmptyTable(xaxisSensor)) {
					print(xaxisSensor)
					print(summary(downloadSensorData(dates[1], dates[2], xaxisSensor, filter)))
				}
			}
		}
		for (ss in data){
			print(ss)
			ssData = downloadSensorData(dates[1], dates[2], ss, filter)
			if (isEmptyTable(ssData)) {
				print(sprintf("There is no data of %s with time range %s - %s", ss, dates[1], dates[2]))
			} else {
				print(summary(downloadSensorData(dates[1], dates[2], ss, filter)))
			}
		}
	  })
    })

})
progress <-function (output,session,txt){
  isolate({
    output$busy <-renderText({txt})
    })
}
checkUpdates<-function(){
  reconnectC()
  cat("Check for daily updates\n")
  bulkDownloadC(Sys.Date()-1,Sys.Date())
}
appRun <- function (path){
  browseURL(url="http://localhost:3244",)
  runApp(path,launch.browser=FALSE,port=3244)
}