source ("d:/dev/R/shiny/dataaccess.R")
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
    observe({
      assign("dates",input$dates,.GlobalEnv)
      assign("dataset" ,input$dataset,.GlobalEnv)
      assign("filter",input$filter,.GlobalEnv)     
      assign("numberD",input$numberD,.GlobalEnv)   
      assign("datefrom",input$datecheck,.GlobalEnv)     
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
      output$checksummary <- renderTable  ({sum})    
    })
    observe({      
      run <- input$run
      if (run == 0)
        return (NULL)
      print(dates)
      output$main_plot <- renderPlot({               
        plotSensorsC(dates[1],dates[2], dataset, filter)
       
      })     
      output$density <- renderPlot({         
        plotHistC(dataset,dates[1],dates[2],filter)
      })
      output$summary <- renderPrint({       
        summary(get(dataset[1]))
      })
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