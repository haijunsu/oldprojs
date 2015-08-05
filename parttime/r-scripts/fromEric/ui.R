library(shiny)

shinyUI(pageWithSidebar(
  headerPanel("Sensors"),
  sidebarPanel(   
    selectInput("floor", 'Choose a floor', choices = unique(sensorcat[,`Floor`])),
    
    uiOutput("sensors"),
    textInput("filter", a("Filter",href="http://cran.r-project.org/web/packages/data.table/data.table.pdf",target="_blank"),"Value >= 0"),    
    dateRangeInput("dates",label = 'Date range: dd/mm/yy, en, range limit, weekstart=1. Controls start and end of date range input in main panel.',
                   start = Sys.Date()-30, end = Sys.Date()+1,
                   min = Sys.Date()-300, max = Sys.Date()+1,
                   separator = " - ",
                   format = "yyyy-mm-dd", startview = 'month', language = 'en', weekstart = 1),
    
    actionButton("reconnect","Reconnect"),
    actionButton("run","Plot"),
    actionButton("reset","Reset Data"),
    headerPanel("DataCheck"),
    dateInput("datecheck", label="Date Check", value = Sys.Date(),
                min = Sys.Date()-300, max = Sys.Date()+1,   
                format = "yyyy-mm-dd", startview = 'month', language = 'en', weekstart = 1),
                textInput("numberD","Check: Number of days(back)" ,"7"),  
                actionButton("checkdata","check")
  ),
  
  mainPanel(  
    h4("Summary"),
    verbatimTextOutput("summary"),
    h4("Observations"),     
    plotOutput(outputId = "main_plot", width = "100%"),
    plotOutput(outputId = "density", width = "100%"),
    h4("Check results"),
    tableOutput("checksummary")
  )
))
