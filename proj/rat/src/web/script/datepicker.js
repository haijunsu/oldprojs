var WEEKDAYS    = [0,1,2,3,4,5,6];
var FrameCaller = "self.parent";
var PopupCaller = "self.opener";
var AM_KEYWORD  = "AM";
var PM_KEYWORD  = "PM";
var DEFAULT_DAY_RANGE  = 31;

var frameNextPrevMonYearBgColor          = "#FFFFCC";
var popupNextPrevMonYearBgColor          = "#e0e0e0";
var POPUP_TITLE_STYLE                    = ".title                  { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: black;}";
var POPUP_TODAY_STYLE                    = ".today                  { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: red; font-weight: bold;}";
var POPUP_WEEKDAY_STYLE                  = ".weekday                { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: black;}";
var POPUP_NON_WEEKDAY_STYLE              = ".nonWeekday             { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: darkgreen; background-color: #e0e0e0;}";
var POPUP_PRENEXTMONTH_WEEKDAY_STYLE     = ".preNextMonthWeekday    { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: #CCCC99;}";
var POPUP_PRENEXTMONTH_NON_WEEKDAY_STYLE = ".preNextMonthNonWeekday { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: #CCCC99; background-color: #e0e0e0;}";
var POPUP_WEEKDAY_HEADING_STYLE          = ".weekdayHeading         { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: black;}";
var POPUP_NON_WEEKDAY_HEADING_STYLE      = ".nonWeekdayHeading      { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: darkgreen; background-color: #e0e0e0;}";
var POPUP_STYLES = [POPUP_TITLE_STYLE, POPUP_TODAY_STYLE, POPUP_WEEKDAY_STYLE, POPUP_NON_WEEKDAY_STYLE, POPUP_PRENEXTMONTH_WEEKDAY_STYLE, POPUP_PRENEXTMONTH_NON_WEEKDAY_STYLE,POPUP_WEEKDAY_HEADING_STYLE, POPUP_NON_WEEKDAY_HEADING_STYLE];

var FRAME_TITLE_STYLE                    = ".title                  { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: black;}";
var FRAME_TODAY_STYLE                    = ".today                  { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: red;}";
var FRAME_WEEKDAY_STYLE                  = ".weekday                { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: #0033cc;}";
var FRAME_NON_WEEKDAY_STYLE              = ".nonWeekday             { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: darkgreen;}";
var FRAME_PRENEXTMONTH_WEEKDAY_STYLE     = ".preNextMonthWeekday    { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: #CCCC99;}";
var FRAME_PRENEXTMONTH_NON_WEEKDAY_STYLE = ".preNextMonthNonWeekday { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: #CCCC99;}";
var FRAME_WEEKDAY_HEADING_STYLE          = ".weekdayHeading         { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: black; font-weight: bold;}";
var FRAME_NON_WEEKDAY_HEADING_STYLE      = ".nonWeekdayHeading      { font-family: 'Verdana', 'Arial', 'Helvetica', 'sans-serif'; font-size: 14px; color: darkgreen; font-weight: bold;}";
var FRAME_STYLES = [FRAME_TITLE_STYLE, FRAME_TODAY_STYLE, FRAME_WEEKDAY_STYLE, FRAME_NON_WEEKDAY_STYLE, FRAME_PRENEXTMONTH_WEEKDAY_STYLE, FRAME_PRENEXTMONTH_NON_WEEKDAY_STYLE, FRAME_WEEKDAY_HEADING_STYLE, FRAME_NON_WEEKDAY_HEADING_STYLE];

var WEEKDAY_HEADING_STYLES  = ["nonWeekdayHeading", "weekdayHeading", "weekdayHeading", "weekdayHeading", "weekdayHeading", "weekdayHeading", "nonWeekdayHeading"];
var WEEKDAY_STYLES          = ["nonWeekday", "weekday", "weekday", "weekday", "weekday", "weekday", "nonWeekday"];
var PRENEXT_MONTHDAY_STYLES = ["preNextMonthNonWeekday", "preNextMonthWeekday", "preNextMonthWeekday", "preNextMonthWeekday", "preNextMonthWeekday", "preNextMonthWeekday", "preNextMonthNonWeekday"];
var TODAY_IS_EN_US          = "Today is ";
var TODAY_IS_ZH_TW          = "\u4eca\u5929\u662f";
var TODAY_IS_ZH_CN          = "\u4eca\u5929\u662f";

var ONE_SECOND_LONG_IN_MILLISECOND    = (1000);
var ONE_MINUTE_LONG_IN_MILLISECOND    = (60 * ONE_SECOND_LONG_IN_MILLISECOND);
var ONE_HOUR_LONG_IN_MILLISECOND      = (60 * ONE_MINUTE_LONG_IN_MILLISECOND);
var ONE_DAY_LONG_IN_MILLISECOND       = (24 * ONE_HOUR_LONG_IN_MILLISECOND);
var ONE_DAY_LONG_IN_MINUTE            = (ONE_DAY_LONG_IN_MILLISECOND / ONE_MINUTE_LONG_IN_MILLISECOND);

var gNow = new Date();
var gPopUpWinCal = null;
isNav = (navigator.appName.indexOf("Netscape") != -1) ? true : false;
isIE  = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;

Calendar.LBL_CALENDAR       = "Calendar";
Calendar.LBL_CALENDAR_zh_TW = "\u6708\u66c6";
Calendar.LBL_CALENDAR_zh_CN = "\u6708\u5386";
Calendar.LBL_YEAR_zh_TW     = "\u5e74";
Calendar.LBL_YEAR_zh_CN     = "\u5e74";
Calendar.MONTHS             = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
Calendar.MONTHS_zh_TW       = ["1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"];
Calendar.MONTHS_zh_CN       = ["1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"];
Calendar.FULLWEEKDAYS       = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
Calendar.FULLWEEKDAYS_zh_TW = ["\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"];
Calendar.FULLWEEKDAYS_zh_CN = ["\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"];
Calendar.DOMonth            = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Non-Leap year Month days..
Calendar.lDOMonth           = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Leap year Month days..
Calendar.WEEKDAYS           = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
Calendar.WEEKDAYS_zh_TW     = ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"];
Calendar.WEEKDAYS_zh_CN     = ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"];
Calendar.DAY_zh_TW          = "\u65e5";
Calendar.DAY_zh_CN          = "\u65e5";
Calendar.callerString       = PopupCaller;

function Calendar(p_item, p_item2, p_item2value, p_WinCal, p_month, p_year, p_format, p_locale, p_callerString, p_cbFunc, p_refFromDate, p_refToDate, p_timeDiff, p_fAdjustTimeZone, p_fUseRefDate, p_markedDate) {
    if ((p_month == null) && (p_year == null))  return;

    this.gLocale = p_locale;
    this.gWinCal = p_WinCal;
    if (p_month == null) {
        this.gMonthName = null;
        this.gMonth = null;
        this.gYearly = true;
    } else {
        this.gMonthName = Calendar.get_month(p_month, this.gLocale);
        this.gMonth = new Number(p_month);
        this.gYearly = false;
    }

    this.gYear             = p_year;
    this.gFormat           = p_format;
    this.gBGColor          = "white";
    this.gFGColor          = "black";
    this.gTextColor        = "black";
    this.gReturnItem       = p_item;
    this.gReturnItem2      = p_item2;
    this.gReturnItem2Value = p_item2value;
    this.callerString      = (p_callerString == null || p_callerString == "") ? PopupCaller : p_callerString;
    this.callbakFunc       = (p_cbFunc == null) ? "" : p_cbFunc;
    this.refFromDate       = (p_refFromDate == null) ? "" : p_refFromDate;
    this.refToDate         = (p_refToDate == null) ? "" : p_refToDate;
    this.timeDiff          = (p_timeDiff == null) ? 0 : p_timeDiff;
    this.fAdjustTimeZone   = (p_fAdjustTimeZone == null) ? false : p_fAdjustTimeZone;
    this.fUseRefDate       = (p_fUseRefDate == null) ? false : p_fUseRefDate;
    this.markedDate        = p_markedDate == null ? "" : p_markedDate;
    this.markedDateObj     = getDateFromDateString(this.gFormat, p_markedDate);
    
    if (this.callerString == PopupCaller) {
        this.styleClasses = POPUP_STYLES;
        this.nextPrevMonYearBgColor = popupNextPrevMonYearBgColor;
    } else {
        this.styleClasses = FRAME_STYLES;
        this.nextPrevMonYearBgColor = frameNextPrevMonYearBgColor;
    }
}

Calendar.get_title       = Calendar_get_title;
Calendar.get_year        = Calendar_get_year;
Calendar.get_yearmonth   = Calendar_get_yearmonth;
Calendar.get_yearmonthday= Calendar_get_yearmonthday
Calendar.get_month       = Calendar_get_month;
Calendar.get_weekday     = Calendar_get_weekday;
Calendar.get_fullweekday = Calendar_get_fullweekday;
Calendar.get_daysofmonth = Calendar_get_daysofmonth;
Calendar.calc_month_year = Calendar_calc_month_year;
Calendar.print           = Calendar_print;
Calendar.get_today       = Calendar_get_today;

function Calendar_get_today(aLocale, year, month, day, weekdayNo) {
    var monthname = Calendar.get_month(month, aLocale);
    var today     = Calendar.get_yearmonthday(aLocale, year, monthname, day);
    var weekday   = Calendar.get_fullweekday(weekdayNo, aLocale);
    if (aLocale == "zh_TW") {
        return (TODAY_IS_ZH_TW + today + " " + weekday);
    } else if (aLocale == "zh_CN") {
        return (TODAY_IS_ZH_CN + today + " " + weekday);
    } else{
        return (TODAY_IS_EN_US + weekday + ", " + today);
    }
}

function Calendar_get_month(monthNo, locale) {
    if (locale == "zh_TW") {
        return Calendar.MONTHS_zh_TW[monthNo];
    } else if (locale == "zh_CN") {
        return Calendar.MONTHS_zh_CN[monthNo];
    } else{
        return Calendar.MONTHS[monthNo];
    }
}

function Calendar_get_weekday(weekdayNo, locale) {
    if (locale == "zh_TW") {
        return Calendar.WEEKDAYS_zh_TW[weekdayNo];
    } else if (locale == "zh_CN") {
        return Calendar.WEEKDAYS_zh_CN[weekdayNo];
    } else{
        return Calendar.WEEKDAYS[weekdayNo];
    }
}

function Calendar_get_fullweekday(weekdayNo, locale) {
    if (locale == "zh_TW") {
        return Calendar.FULLWEEKDAYS_zh_TW[weekdayNo];
    } else if (locale == "zh_CN") {
        return Calendar.FULLWEEKDAYS_zh_CN[weekdayNo];
    } else{
        return Calendar.FULLWEEKDAYS[weekdayNo];
    }
}

function Calendar_get_title(locale) {
    if (locale == "zh_TW") {
        return Calendar.LBL_CALENDAR_zh_TW;
    } else if (locale == "zh_CN") {
        return Calendar.LBL_CALENDAR_zh_CN;
    } else{
        return Calendar.LBL_CALENDAR;
    }
}

function Calendar_get_year(locale, year) {
    if (locale == "zh_TW") {
        return (year + " " + Calendar.LBL_YEAR_zh_TW);
    } else if (locale == "zh_CN") {
        return (year + " " + Calendar.LBL_YEAR_zh_CN);
    } else{
        return (year);
    }
}

function getYearMonthAnchorHtml(caller, cbItem, datePattern, year, month, yearMonthString) {
    if (caller != FrameCaller) {
        return ("<TD ALIGN=center class='title'>"+ yearMonthString + "</TD>");
    }

    var pmonthDays     = Calendar_get_daysofmonth(month, year);
    var firstDate      = getDateTimeStringByPattern(datePattern, null, year, (1 + month), 1);
    var lastDate       = getDateTimeStringByPattern(datePattern, null, year, (1 + month), pmonthDays);
    var weekBtnHtml    = "";
    var cbValue        = "month|" + firstDate + "|" + lastDate;
    var onClickHandler = caller + "." + cbItem + "('" + cbValue + "');";
    var weekBtnHtml    = "<TD ALIGN=center class='title' "
                       + "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"\"' "
                       + "style='cursor: pointer; cursor: hand;' onClick=\"" + onClickHandler + "\">"
                       + yearMonthString
                       + "</TD>";
    return weekBtnHtml;
}

function Calendar_get_yearmonth(aLocale, year, monthName, month, caller, cbItem, datePattern) {
    var yearMonth = Calendar_get_yearmonthday(aLocale, year, monthName);
    var yearMonthHtml = getYearMonthAnchorHtml(caller, cbItem, datePattern, year, month, yearMonth);
    return yearMonthHtml;
}

function Calendar_get_yearmonthday(aLocale, year, monthName, day) {
    var yearMonth = "";
    if (aLocale == "zh_TW") {
        yearMonth = (year + Calendar.LBL_YEAR_zh_TW + monthName);
        if (day != null) yearMonth += day + Calendar.DAY_zh_TW;
    } else if (aLocale == "zh_CN") {
        yearMonth = (year + Calendar.LBL_YEAR_zh_CN + monthName);
        if (day != null) yearMonth += day + Calendar.DAY_zh_CN;
    } else{
        yearMonth = (day == null) ? (monthName + " " + year) : (monthName + " " + day + ", " + year);
    }
    return yearMonth;
}

function Calendar_get_daysofmonth(monthNo, p_year) {

    /*
    Check for leap year ..
    1.Years evenly divisible by four are normally leap years, except for...
    2.Years also evenly divisible by 100 are not leap years, except for...
    3.Years also evenly divisible by 400 are leap years.
    */
    if ((p_year % 4) == 0) {
        if ((p_year % 100) == 0 && (p_year % 400) != 0) return Calendar.DOMonth[monthNo];
        return Calendar.lDOMonth[monthNo];
    } else {
        return Calendar.DOMonth[monthNo];
    }
}

function Calendar_calc_month_year(p_Month, p_Year, incr) {
    /*
    Will return an 1-D array with 1st element being the calculated month
    and second being the calculated year
    after applying the month increment/decrement as specified by 'incr' parameter.
    'incr' will normally have 1/-1 to navigate thru the months.
    */
    var ret_arr = new Array();

    if (incr == -1) {
        // B A C K W A R D
        if (p_Month == 0) {
            ret_arr[0] = 11;
            ret_arr[1] = parseInt(p_Year) - 1;
        } else {
            ret_arr[0] = parseInt(p_Month) - 1;
            ret_arr[1] = parseInt(p_Year);
        }
    } else if (incr == 1) {
        // F O R W A R D
        if (p_Month == 11) {
            ret_arr[0] = 0;
            ret_arr[1] = parseInt(p_Year) + 1;
        } else {
            ret_arr[0] = parseInt(p_Month) + 1;
            ret_arr[1] = parseInt(p_Year);
        }
    }

    return ret_arr;
}

function Calendar_print() {
    this.gWinCal.print();
}

function Calendar_calc_month_year(p_Month, p_Year, incr) {
    /*
    Will return an 1-D array with 1st element being the calculated month
    and second being the calculated year
    after applying the month increment/decrement as specified by 'incr' parameter.
    'incr' will normally have 1/-1 to navigate thru the months.
    */
    var ret_arr = new Array();

    if (incr == -1) {
        // B A C K W A R D
        if (p_Month == 0) {
            ret_arr[0] = 11;
            ret_arr[1] = parseInt(p_Year) - 1;
        }
        else {
            ret_arr[0] = parseInt(p_Month) - 1;
            ret_arr[1] = parseInt(p_Year);
        }
    } else if (incr == 1) {
        // F O R W A R D
        if (p_Month == 11) {
            ret_arr[0] = 0;
            ret_arr[1] = parseInt(p_Year) + 1;
        }
        else {
            ret_arr[0] = parseInt(p_Month) + 1;
            ret_arr[1] = parseInt(p_Year);
        }
    }

    return ret_arr;
}

// This is for compatibility with Navigator 3, we have to create and discard one object before the prototype object exists.
new Calendar();

Calendar.prototype.getMonthlyCalendarCode = function() {
    // Begin Table Drawing code here..
    var now          = getAdjustedNowDate(this.timeDiff, this.fAdjustTimeZone);
    var vCode        = "<TABLE BORDER='0' cellpadding='0' cellspacing='1' BGCOLOR='" + this.gBGColor + "' width='100%'>";
    var vHeader_Code = this.cal_header();
    var vData_Code   = this.cal_data(now);
    var vFoot_Code   = this.cal_foot(now);

    vCode = vCode + vHeader_Code + vData_Code;
    vCode = vCode + "</TABLE>" + vFoot_Code;

    return vCode;
}

Calendar.prototype.show = function() {
    var vCode = "";
    this.gWinCal.document.open();

    // Setup the page...

    this.wwrite("<style type='text/css'>\n<!--");
    for (var i = 0; i < this.styleClasses.length; i++) {
        this.wwrite(this.styleClasses[i]);
    }
    this.wwrite("-->\n</style>");

    this.wwrite("<html>");
    this.wwrite("<head><title>" + Calendar.get_title(this.gLocale) + "</title>");
    this.wwrite('<script language=\"JavaScript\">\nvar CALLBACK_ELM1 = \"' + this.gReturnItem + '\"; var CALLBACK_ELM2 = \"' + this.gReturnItem2 + '\";');
    this.wwrite('function mouseOverOutHandler(isOver, elmName, weekIndex) {\nvar elm = document.getElementById(elmName);\nif (elm == null) return;\nelm.style.backgroundColor = (isOver) ? "lightcyan" : "";\nfor (var i = 0; i < 7; i++) {\nvar dayElm = document.getElementById("dayElm" + weekIndex + i);\nif (dayElm == null) continue;\ndayElm.style.backgroundColor = (isOver) ? "lightcyan" : "";\n}\n}\n</script>');
    
    this.wwrite("</head>");
    this.wwrite("<body>");

    if (this.callerString == PopupCaller) this.wwriteA("<BR>");

    // Show navigation buttons
    var prevMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, -1);
    var prevMM     = prevMMYYYY[0];
    var prevYYYY   = prevMMYYYY[1];

    var nextMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, 1);
    var nextMM     = nextMMYYYY[0];
    var nextYYYY   = nextMMYYYY[1];

    var prevYearTip = Calendar.get_year(this.gLocale, (parseInt(this.gYear)-1))+Calendar.get_month(this.gMonth, this.gLocale);
    var prevMonTip  = Calendar.get_month(prevMM, this.gLocale);
    var nextMonTip  = Calendar.get_month(nextMM, this.gLocale);
    var nextYearTip = Calendar.get_year(this.gLocale, (parseInt(this.gYear)+1))+Calendar.get_month(this.gMonth, this.gLocale);
    var height      = (this.callerString == FrameCaller) ? " height='28' " : " ";

    this.wwrite("<TABLE class='title' WIDTH='100%'" + height + "BORDER='0' CELLSPACING='0' CELLPADDING='0' BGCOLOR='" + this.nextPrevMonYearBgColor + "'><TR>");
    this.wwrite("<TD align='center' WIDTH='14%' title='" + prevYearTip + "' " +
        "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"" + this.nextPrevMonYearBgColor + "\"' " +
        "style='cursor: pointer; cursor: hand; color:#8b0000' onClick=\"" +
        this.callerString + ".Build(self, " + 
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', '"+ this.gMonth + "', '" + (parseInt(this.gYear)-1) + "', '" + this.gFormat + "', '" + this.gLocale + "', '" +
        this.callerString + "', '" + this.callbakFunc + "', '" + this.refFromDate + "', '" + this.refToDate + "', " +
        this.timeDiff + ", '" + this.fAdjustTimeZone + "', " + this.fUseRefDate + ", '" + this.markedDate + "')" +
        "\"><<</TD>");  
        
    this.wwrite("<TD align='center' WIDTH='14%' title='" + prevMonTip + "' " +
        "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"" + this.nextPrevMonYearBgColor + "\"' " +
        "style='cursor: pointer; cursor: hand; color:#8b0000' onClick=\"" +
        this.callerString + ".Build(self, " +
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', '" + prevMM + "', '" + prevYYYY + "', '" + this.gFormat + "', '" + this.gLocale + "', '" +
        this.callerString + "', '" + this.callbakFunc + "', '" + this.refFromDate + "', '" + this.refToDate + "', " +
        this.timeDiff + ", '" + this.fAdjustTimeZone + "', " + this.fUseRefDate + ", '" + this.markedDate + "')" +
        "\"><</TD>");
    this.wwrite(Calendar.get_yearmonth(this.gLocale, this.gYear, this.gMonthName, this.gMonth, this.callerString, this.gReturnItem, this.gFormat));
    this.wwrite("<TD align='center' WIDTH='14%' title='" + nextMonTip + "' " +
        "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"" + this.nextPrevMonYearBgColor + "\"' " +
        "style='cursor: pointer; cursor: hand; color:#8b0000' onClick=\"" +
        this.callerString + ".Build(self, " +
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', '" + nextMM + "', '" + nextYYYY + "', '" + this.gFormat + "', '" + this.gLocale + "', '" +
        this.callerString + "', '" + this.callbakFunc + "', '" + this.refFromDate + "', '" + this.refToDate + "', " +
        this.timeDiff + ", '" + this.fAdjustTimeZone + "', " + this.fUseRefDate + ", '" + this.markedDate + "')" +
        "\">></TD>");
    this.wwrite("<TD align='center' WIDTH='14%' title='" + nextYearTip + "' " +
        "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"" + this.nextPrevMonYearBgColor + "\"' " +
        "style='cursor: pointer; cursor: hand; color:#8b0000' onClick=\"" +
        this.callerString + ".Build(self, " +
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', '" + this.gMonth + "', '" + (parseInt(this.gYear)+1) + "', '" + this.gFormat + "', '" + this.gLocale + "', '" +
        this.callerString + "', '" + this.callbakFunc + "', '" + this.refFromDate + "', '" + this.refToDate + "', " +
        this.timeDiff + ", '" + this.fAdjustTimeZone + "', " + this.fUseRefDate + ", '" + this.markedDate + "')" +
        "\">>></TD></TR></TABLE>");

    if (this.callerString == PopupCaller) this.wwrite("<BR>");

    // Get the complete calendar code for the month..
    vCode = this.getMonthlyCalendarCode();
    this.wwrite(vCode);

    this.wwrite("</body></html>");
    //this.wwrite('<script language=\"JavaScript\">window.onblur=close;</script>');
    this.gWinCal.document.close();
}

Calendar.prototype.showY = function() {
    var vCode = "";
    var i;
    var vr, vc, vx, vy;     // Row, Column, X-coord, Y-coord
    var vxf = 285;          // X-Factor
    var vyf = 200;          // Y-Factor
    var vxm = 10;           // X-margin
    var vym;                // Y-margin
    if (isIE)   vym = 75;
    else if (isNav) vym = 25;

    this.gWinCal.document.open();

    this.wwrite("<html>");
    this.wwrite("<head><title>Calendar</title>");
    this.wwrite("<style type='text/css'>\n<!--");
    for (i=0; i<12; i++) {
        vc = i % 3;
        if (i>=0 && i<= 2)  vr = 0;
        if (i>=3 && i<= 5)  vr = 1;
        if (i>=6 && i<= 8)  vr = 2;
        if (i>=9 && i<= 11) vr = 3;

        vx = parseInt(vxf * vc) + vxm;
        vy = parseInt(vyf * vr) + vym;

        this.wwrite(".lclass" + i + " {position:absolute;top:" + vy + ";left:" + vx + ";}");
    }
    this.wwrite("-->\n</style>");
    this.wwrite('<script language=\"JavaScript\">var CALLBACK_ELM1 = \"' + this.gReturnItem + '\"; var CALLBACK_ELM2 = \"' + this.gReturnItem2 + '\";</script>');
    this.wwrite("</head>");

    this.wwrite("<body>");
    this.wwrite("<span class='title'><B>");
    this.wwrite("Year : " + this.gYear);
    this.wwrite("</B></span><BR>");

    // Show navigation buttons
    var prevYYYY = parseInt(this.gYear) - 1;
    var nextYYYY = parseInt(this.gYear) + 1;

    this.wwrite("<TABLE WIDTH='100%' BORDER=0 CELLSPACING=0 CELLPADDING=0 BGCOLOR='#e0e0e0'><TR><TD ALIGN=center>");
    this.wwrite("[<A HREF=\"" +
        "javascript:" + this.callerString + ".Build(self, " +
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', null, '" + prevYYYY + "', '" + this.gFormat + "', '" + this.gLocale + "', '" + this.callerString + "'" +
        ");" +
        "\" alt='Prev Year'><<<\/A>]</TD><TD ALIGN=center>");
    this.wwrite("[<A HREF=\"javascript:window.print();\">Print</A>]</TD><TD ALIGN=center>");
    this.wwrite("[<A HREF=\"" +
        "javascript:" + this.callerString + ".Build(self, " +
        "CALLBACK_ELM1, CALLBACK_ELM2, '" + this.gReturnItem2Value + "', null, '" + nextYYYY + "', '" + this.gFormat + "', '" + this.gLocale + "', '" + this.callerString + "'" +
        ");" +
        "\">>><\/A>]</TD></TR></TABLE><BR>");

    // Get the complete calendar code for each month..
    var j;
    for (i=11; i>=0; i--) {
        if (isIE)
            this.wwrite("<DIV ID=\"layer" + i + "\" CLASS=\"lclass" + i + "\">");
        else if (isNav)
            this.wwrite("<LAYER ID=\"layer" + i + "\" CLASS=\"lclass" + i + "\">");

        this.gMonth = i;
        this.gMonthName = Calendar.get_month(this.gMonth, this.gLocale);
        vCode = this.getMonthlyCalendarCode();
        this.wwrite(this.gMonthName + "/" + this.gYear + "<BR>");
        this.wwrite(vCode);

        if (isIE)
            this.wwrite("</DIV>");
        else if (isNav)
            this.wwrite("</LAYER>");
    }

    this.wwrite("<BR></body></html>");
    this.gWinCal.document.close();
}

Calendar.prototype.wwrite = function(wtext) {
    this.gWinCal.document.writeln(wtext);
}

Calendar.prototype.wwriteA = function(wtext) {
    this.gWinCal.document.write(wtext);
}

Calendar.prototype.cal_header = function() {
    var vCode = "";
    var i;
    var headRowHeight  = (this.callerString == FrameCaller) ? " HEIGHT='28' " :" ";
    var headRowWidth   = (this.callerString == FrameCaller) ? " WIDTH='13%' " :" WIDTH='14%' ";

    vCode = vCode + "<TR>";
    if (this.callerString == FrameCaller) vCode = vCode + "<TD WIDTH='5%'>&nbsp;</TD>";
    for (i = 0; i < WEEKDAYS.length; i++) {
        vCode = vCode + "<TD " + headRowHeight + headRowWidth + " align='center' class='" + WEEKDAY_HEADING_STYLES[i] + "'>" + Calendar.get_weekday(i, this.gLocale) + "</TD>";
    }
    if (this.callerString == FrameCaller) vCode = vCode + "<TD WIDTH='4%'>&nbsp;</TD>";
    vCode = vCode + "</TR>";

    return vCode;
}

function getDayOnClickHandler(caller, cbItem1, cbItem2, cbItem2Value, cbValue, cbFunc) {

    var onClickHandler;
    if (caller == PopupCaller) {
        onClickHandler = caller + ".document." + cbItem1 + ".value='" + cbValue + "';"
                       + caller + ".document." + cbItem1 + ".focus();";

        if (cbItem2 != "" && cbItem2Value == "") {
            onClickHandler += caller + ".document." + cbItem2 + ".value='" + cbValue + "';";
        }

        if (cbFunc != null && cbFunc != "") {
            onClickHandler += caller + "." + cbFunc + "('" + cbValue + "');";
        }
        onClickHandler += "setTimeout('self.close()', 50)";
    } else {
        onClickHandler = caller + "." + cbItem1 + "('" + cbValue + "');";
    }
    return (onClickHandler);
}

function getWeekBtnHtml(caller, firstDate, lastDate, cbItem1, weekIndex) {
    var weekBtnHtml = "";
    var trWeekId    = "weekRow" + weekIndex;
    if (caller == FrameCaller) {
        var cbValue        = "week|" + firstDate + "|" + lastDate;
        var onClickHandler = caller + "." + cbItem1 + "('" + cbValue + "');";
        weekBtnHtml        = "<TD WIDTH='5%' onmouseover='mouseOverOutHandler(true,\"" + trWeekId + "\", " + weekIndex + ")' onmouseout='mouseOverOutHandler(false,\"" + trWeekId + "\", " + weekIndex + ")' "
                           + "style='cursor: pointer; cursor: hand;' onClick=\"" + onClickHandler + "\">"
                           + "->"
                           + "</TD>";
    }
    return weekBtnHtml;
}

function getWeekdayHtml(caller, fUseRefDate, returnDateFormat, rowHeight, rowWidth, styleClass, onClickHandler, day, isReferred, weekIndex, dayIndex, fMarked) {
    var bgcolor     = "";
    var bgcolorAttr = "";
    var weekdayStyle= styleClass
    
    if ((fUseRefDate == true && isReferred == true) || fMarked == true) {
        bgcolor     = "#FFFFCC";
        bgcolorAttr = " bgcolor='" + bgcolor + "' ";
        weekdayStyle= "weekday";
    }
    var weekDayHtml = null;
    var fAccessable = (caller == FrameCaller) || (caller == PopupCaller && isReferred == true);
    if (fAccessable == true) {
        weekDayHtml = "<TD id='dayElm" + weekIndex + dayIndex + "' title='" + returnDateFormat + "' align='center'" + rowHeight + rowWidth + bgcolorAttr + " class='" + weekdayStyle + "' "
                    + "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"" + bgcolor + "\"' "
                    + "style='cursor: pointer; cursor: hand;' onClick=\"" + onClickHandler + "\">"
                    + day
                    + "</TD>";
    } else {
        weekDayHtml = "<TD id='dayElm" + weekIndex + dayIndex + "' title='" + returnDateFormat + "' align='center'" + rowHeight + rowWidth + bgcolorAttr + " class='" + weekdayStyle + "'>"
                    + day
                    + "</TD>";
    }                
    return weekDayHtml;
}
function getDayDiff(fromDateObj, toDateObj){
    if (fromDateObj == null || toDateObj == null) return null;
    var dayDiff  = (toDateObj.getTime() - fromDateObj.getTime()) / ONE_DAY_LONG_IN_MILLISECOND + 1;
    return (dayDiff);
}

function checkThisDayIsRefereed(fcheck, caller, fromDate, toDate, chkeeDate) {
    if (fcheck == false) return ((caller == FrameCaller) ? false : true);
    if (chkeeDate == null) return false;
    if (fromDate == null && toDate == null) return ((caller == FrameCaller) ? true : false);
    return (checkThisDayInRange(fromDate, toDate, chkeeDate));
}

function checkThisDayInRange(fromDate, toDate, chkeeDate, dayRange) {
    if (dayRange != null) toDate = new Date(fromDate.getFullYear(), fromDate.getMonth(), (fromDate.getDate() + dayRange -1));
    if (fromDate != null && toDate == null) return (fromDate.getTime()  <= chkeeDate.getTime());
    if (fromDate == null && toDate != null) return (chkeeDate.getTime() <= toDate.getTime());
    return ((fromDate.getTime() <= chkeeDate.getTime()) && (chkeeDate.getTime() <= toDate.getTime()));
}

Calendar.prototype.cal_data = function(now) {
    var vDate = new Date(now.getTime())
    vDate.setDate(1);
    vDate.setMonth(this.gMonth);
    vDate.setFullYear(this.gYear);

    var vFirstDay  = vDate.getDay();
    var vDay       = 1;
    var vLastDay   = Calendar.get_daysofmonth(this.gMonth, this.gYear);
    var vOnLastDay = 0;
    var vCode      = "";
    var rowHeight  = (this.callerString == FrameCaller) ? " HEIGHT='28' " :" ";
    var rowWidth   = (this.callerString == FrameCaller) ? " WIDTH='13%' " :" WIDTH='14%' ";

    /*
    Get day for the 1st of the requested month/year..
    Place as many blank cells before the 1st day of the month as necessary.
    */

    var prevMMYYYY       = Calendar.calc_month_year(this.gMonth, this.gYear, -1);
    var prevMonthDays    = Calendar.get_daysofmonth(prevMMYYYY[0], prevMMYYYY[1]);
    var weekDayHtml      = "";
    var weekFirstDate    = "";
    var weekLastDate     = "";
    var returnDateFormat = "";
    var onClickHandler   = "";
    var weekIndex        = 0;
    vCode = vCode + "<TR id='weekRow" + weekIndex + "'>";
    var isReferred       = false;
    var refFromDateObj   = getDateFromDateString(this.gFormat, this.refFromDate);
    var refToDateObj     = getDateFromDateString(this.gFormat, this.refToDate);
    var chkeeDateObj     = null;
    var i,j,k,m;
    
    for (i = 0; i < vFirstDay; i++) {
        var crntDay      = prevMonthDays-(vFirstDay-i) + 1;
        returnDateFormat = getDateTimeStringByPattern(this.gFormat, null, prevMMYYYY[1], prevMMYYYY[0]+1, crntDay);
        onClickHandler   = getDayOnClickHandler(this.callerString, this.gReturnItem, this.gReturnItem2, this.gReturnItem2Value, returnDateFormat, this.callbakFunc);
        chkeeDateObj     = getDateFromDateString(this.gFormat, returnDateFormat);
        isReferred       = checkThisDayIsRefereed(this.fUseRefDate, this.callerString, refFromDateObj, refToDateObj, chkeeDateObj);
        weekDayHtml     += getWeekdayHtml(this.callerString, this.fUseRefDate, returnDateFormat, rowHeight, rowWidth, PRENEXT_MONTHDAY_STYLES[i], onClickHandler, crntDay, isReferred, weekIndex, i, checkDatesEqual(this.markedDateObj,chkeeDateObj));
        if (i == 0) weekFirstDate = returnDateFormat;
    }

    // Write rest of the 1st week
    for (j=vFirstDay; j<7; j++) {
        returnDateFormat = this.format_data(vDay);
        onClickHandler   = getDayOnClickHandler(this.callerString, this.gReturnItem, this.gReturnItem2, this.gReturnItem2Value, returnDateFormat, this.callbakFunc);
        chkeeDateObj     = getDateFromDateString(this.gFormat, returnDateFormat);
        isReferred       = checkThisDayIsRefereed(this.fUseRefDate, this.callerString, refFromDateObj, refToDateObj, chkeeDateObj);
        weekDayHtml     += getWeekdayHtml(this.callerString, this.fUseRefDate, returnDateFormat, rowHeight, rowWidth, WEEKDAY_STYLES[j], onClickHandler, this.format_day(vDay, j, now), isReferred, weekIndex, j, checkDatesEqual(this.markedDateObj,chkeeDateObj));
        vDay=vDay + 1;
        if (j == 0) weekFirstDate = returnDateFormat;
        if (j == 6) weekLastDate  = returnDateFormat;
    }

    if (this.callerString == FrameCaller) {
        weekDayHtml = getWeekBtnHtml(this.callerString, weekFirstDate, weekLastDate, this.gReturnItem, weekIndex)
                    + weekDayHtml
                    + "<TD WIDTH='4%'>&nbsp;</TD>";
    }

    vCode = vCode + weekDayHtml + "</TR>";

    // Write the rest of the weeks
    for (k=2; k<7; k++) {
        weekIndex += 1;
        vCode = vCode + "<TR id='weekRow" + weekIndex + "'>";
        weekDayHtml = "";
        for (j=0; j<7; j++) {
            returnDateFormat = this.format_data(vDay);
            onClickHandler   = getDayOnClickHandler(this.callerString, this.gReturnItem, this.gReturnItem2, this.gReturnItem2Value, returnDateFormat, this.callbakFunc);
            chkeeDateObj     = getDateFromDateString(this.gFormat, returnDateFormat);
            isReferred       = checkThisDayIsRefereed(this.fUseRefDate, this.callerString, refFromDateObj, refToDateObj, chkeeDateObj);
            weekDayHtml     += getWeekdayHtml(this.callerString, this.fUseRefDate, returnDateFormat, rowHeight, rowWidth, WEEKDAY_STYLES[j], onClickHandler, this.format_day(vDay, j, now), isReferred, weekIndex, j, checkDatesEqual(this.markedDateObj,chkeeDateObj));
            vDay = vDay + 1;
            if (j == 0) weekFirstDate = returnDateFormat;
            if (j == 6) {
                if (this.callerString == FrameCaller) {
                    weekLastDate = returnDateFormat;
                    weekDayHtml  = getWeekBtnHtml(this.callerString, weekFirstDate, weekLastDate, this.gReturnItem, weekIndex)
                                 + weekDayHtml
                                 + "<TD WIDTH='4%'>&nbsp;</TD>";
                }
                vCode = vCode + weekDayHtml + "</TR>";
            }

            if (vDay > vLastDay) {
                vOnLastDay = 1;
                break;
            }
        }

        if (vOnLastDay == 1) break;
    }

    // Fill up the rest of last week with proper blanks, so that we get proper square blocks

    for (m=1; m<(7-j); m++) {
        if (this.gYearly) {
            //non check yet
            vCode = vCode + "<TD align='right' " + rowHeight + rowWidth + " class='" + PRENEXT_MONTHDAY_STYLES[j+m] + "'>&nbsp;</td>";
        } else {
            var nextMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, 1);
            returnDateFormat = getDateTimeStringByPattern(this.gFormat, null, nextMMYYYY[1], nextMMYYYY[0]+1, m);
            onClickHandler   = getDayOnClickHandler(this.callerString, this.gReturnItem, this.gReturnItem2, this.gReturnItem2Value, returnDateFormat, this.callbakFunc);
            chkeeDateObj     = getDateFromDateString(this.gFormat, returnDateFormat);
            isReferred       = checkThisDayIsRefereed(this.fUseRefDate, this.callerString, refFromDateObj, refToDateObj, chkeeDateObj);
            weekDayHtml     += getWeekdayHtml(this.callerString, this.fUseRefDate, returnDateFormat, rowHeight, rowWidth, PRENEXT_MONTHDAY_STYLES[j+m], onClickHandler, m, isReferred, weekIndex, (j+m), checkDatesEqual(this.markedDateObj,chkeeDateObj));
         }

         if ((j+m) == 6) {
            if (this.callerString == FrameCaller) {
                weekLastDate = returnDateFormat;
                weekDayHtml  = getWeekBtnHtml(this.callerString, weekFirstDate, weekLastDate, this.gReturnItem, weekIndex)
                             + weekDayHtml
                             + "<TD WIDTH='4%'>&nbsp;</TD>";
            }
            vCode = vCode + weekDayHtml + "</TR>";
         }
    }

    return vCode;
}

Calendar.prototype.cal_foot = function(now) {
    if (this.callerString != FrameCaller) return "";
    var vNowDay        = now.getDate();
    var vNowWeekday    = now.getDay();
    var vNowMonth      = now.getMonth();
    var vNowYear       = now.getFullYear();
    var cbToday        = getDateTimeStringByPattern(this.gFormat, null, vNowYear, (1 + vNowMonth), vNowDay);
    var onClickHandler = this.callerString  + "." + this.gReturnItem + "('" + cbToday + "');";
    var vCode = "<TABLE BORDER='0' cellpadding='0' cellspacing='0' width='100%'>"
              + "<TR><TD height='28' align='center' class='today' "
              + "onmouseover='this.style.textDecoration=\"underline\"' onmouseout='this.style.textDecoration=\"none\"' "
              //+ "onmouseover='this.style.backgroundColor=\"lightcyan\"' onmouseout='this.style.backgroundColor=\"\"' "
              + "style='cursor: pointer; cursor: hand;' onClick=\"" + onClickHandler + "\">"
              + Calendar.get_today(this.gLocale, vNowYear, vNowMonth, vNowDay, vNowWeekday)
              + "</TD></TR>";
              + "</TABLE>";

    return vCode;
}

Calendar.prototype.format_day = function(vday, weekno, now) {
    var vNowDay   = now.getDate();
    var vNowMonth = now.getMonth();
    var vNowYear  = now.getFullYear();

    if (vday == vNowDay && this.gMonth == vNowMonth && this.gYear == vNowYear)
        return ("<span class='today'>" + vday + "</span>");
    else
        return (vday);
}

Calendar.prototype.format_data = function(p_day) {
    var vData = getDateTimeStringByPattern(this.gFormat, null, this.gYear, (1 + this.gMonth), p_day);
    return vData;
}

function getDateTimeFormatString(datePattern, timePattern, dateObj) {
    if (dateObj == null) return "";
    return (getDateTimeStringByPattern(datePattern, timePattern,
                    dateObj.getFullYear(),dateObj.getMonth() + 1, dateObj.getDate(),
                    dateObj.getHours(),    dateObj.getMinutes(),   dateObj.getSeconds()));
}

function getDateFormatString(datePattern, dateObj) {
    if (dateObj == null) return "";
    return (getDateTimeStringByPattern(datePattern, null, dateObj.getFullYear(), dateObj.getMonth() + 1, dateObj.getDate()));
}

function getDateTimeStringByPattern(datePattern, timePattern, yyyy, MM, dd, hh, mm, ss) {
    var vData = "";

    MM = (MM.toString().indexOf("*") == -1 && MM.toString().length < 2) ? "0" + MM : MM;
    dd = (dd.toString().indexOf("*") == -1 && dd.toString().length < 2) ? "0" + dd : dd;
    
    if (datePattern != null) {
        switch (datePattern) {
        case "yyyy/MM/dd": vData = yyyy + "/" + MM + "/" + dd;   break;
        case "yyyy/dd/MM": vData = yyyy + "/" + dd + "/" + MM;   break;
        case "MM/dd/yyyy": vData = MM   + "/" + dd + "/" + yyyy; break;
        case "dd/MM/yyyy": vData = dd   + "/" + MM + "/" + yyyy; break;

        case "yyyy-MM-dd": vData = yyyy + "-" + MM + "-" + dd;   break;
        case "yyyy-dd-MM": vData = yyyy + "-" + dd + "-" + MM;   break;
        case "MM-dd-yyyy": vData = MM   + "-" + dd + "-" + yyyy; break;
        case "dd-MM-yyyy": vData = dd   + "-" + MM + "-" + yyyy; break;

        //case "MM-dd-yy": vData = vMonth + "-" + vDD    + "-" + vY2;     break;
        //case "MM/dd/yy": vData = vMonth + "/" + vDD    + "/" + vY2;     break;

        //case "dd/MMM/yyyy": vData = vDD + "/" + vMon + "/" + vY4; break;
        //case "dd/MMM/yy"  : vData = vDD + "/" + vMon + "/" + vY2; break;
        //case "dd-MMM-yyyy": vData = vDD + "-" + vMon + "-" + vY4; break;
        //case "dd-MMM-yy"  : vData = vDD + "-" + vMon + "-" + vY2; break;

        //case "dd/MMMMM/yyyy": vData = vDD + "/" + vFMon + "/" + vY4; break;
        //case "dd/MMMMM/YY"  : vData = vDD + "/" + vFMon + "/" + vY2; break;
        //case "dd-MMMMM-yyyy": vData = vDD + "-" + vFMon + "-" + vY4; break;
        //case "dd-MMMMM-yy"  : vData = vDD + "-" + vFMon + "-" + vY2;  break;

        //case "dd/MM/yy"  : vData = vDD + "/" + vMonth + "/" + vY2; break;
        //case "dd-MM-yy"  : vData = vDD + "-" + vMonth + "-" + vY2;   break;
        default          : vData = yyyy + "/" + MM + "/" + dd; break;
        }
    }

    var timeString        = "";
    var timeStringPrefix  = "";
    var timeStringPostFix = "";

    if (timePattern != null) {
        switch (timePattern) {
        case "a hh:mm:ss":
        case "aa hh:mm:ss":
        case "aaa hh:mm:ss":
            timeStringPostFix = "";
            if (hh == 12) {
                timeStringPrefix = " " + PM_KEYWORD + " ";
            } else if (hh == 0)  {
                timeStringPrefix = " " + AM_KEYWORD + " ";
                hh = 12;
            } else if (hh > 12) {
                timeStringPrefix = " " + PM_KEYWORD + " ";
                hh = hh-12;
            } else if (hh < 12) {
                timeStringPrefix = " " + AM_KEYWORD + " ";
            }
            break;
        case "hh:mm:ss a":
        case "hh:mm:ss aa":
        case "hh:mm:ss aaa":
            timeStringPrefix = " ";
            if (hh == 12) {
                timeStringPostFix = " " + PM_KEYWORD;
            } else if (hh == 0) {
                timeStringPostFix = " " + AM_KEYWORD;
                hh = 12;
            } else if (hh > 12) {
                timeStringPostFix = " " + PM_KEYWORD;
                hh = hh-12;
            } else if (hh < 12) {
                timeStringPostFix = " " + AM_KEYWORD;
            }

            break;
        case "HH:mm:ss"    :
        default            :
            timeStringPrefix  = " "
            timeStringPostFix = "";
        }
        timeString = ((hh<10?("0"+hh):hh) + ":" + (mm<10?("0"+mm):mm) + ":" + (ss<10?("0"+ss):ss));
    }
    return (vData + timeStringPrefix + timeString + timeStringPostFix);
}

function Build(p_WinCal, p_item, p_item2, p_item2Value, p_month, p_year, p_format, p_locale, p_callerString, p_cbFunc, p_refFromDate, p_refEndDate, p_timeDiff, p_fAdjustTimeZone, p_fUseRefDate, p_markedDate) {
    gCal = new Calendar(p_item, p_item2, p_item2Value, p_WinCal, p_month, p_year, p_format, p_locale, p_callerString, p_cbFunc, p_refFromDate, p_refEndDate, p_timeDiff, p_fAdjustTimeZone, p_fUseRefDate, p_markedDate);

    // Choose appropriate show function
    if (gCal.gYearly) {
        gCal.showY();
    } else {
        gCal.show();
    }        
}

/*  p_item          : Return Item.
    p_format        : Date format (yyyy/MM/dd, dd/MM/yy, ...)
    p_locale        : "zh_TW", "en_US"
*/
function show_calendar() {
    var p_item         = arguments[0];
    var p_format       = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    var p_locale       = (arguments[2] == "" || arguments[2] == null) ? "zh_TW" : arguments[2];
    var p_cbFunc       = (arguments[3] == "" || arguments[3] == null) ? null : arguments[3];
    var p_crntDateCtrl = arguments[4];
    var p_fUseRefDate  = (arguments[5] == "" || arguments[5] == null) ? false : arguments[5];
    var p_crntDateStr  = null;
    if (p_crntDateCtrl != null) {
        p_crntDateStr = p_crntDateCtrl.value == null ? p_crntDateCtrl : p_crntDateCtrl.value;
    }
    var crntDateObj    = (p_crntDateStr == null) ? null : getDateFromDateString(p_format, p_crntDateStr);
    var p_crntYear     = null;
    var p_crntMonth    = null;
    
    if (crntDateObj != null) {
        p_crntYear  = new String(crntDateObj.getFullYear());
        p_crntMonth = new String(crntDateObj.getMonth());        
    }
    
    show_calendarEx(p_item, p_format, "", "", p_locale, p_crntYear, p_crntMonth, p_cbFunc, null, null, p_crntDateStr, null, p_fUseRefDate, p_crntDateStr);
}

function show_calendarWithDateRange() {
    var p_item         = arguments[0];
    var p_format       = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    var p_locale       = (arguments[2] == "" || arguments[2] == null) ? "zh_TW" : arguments[2];
    var p_fromDateCtrl = arguments[3];
    var p_dayRange     = (arguments[4] == "" || arguments[4] == null) ? DEFAULT_DAY_RANGE : new Number(arguments[4]);
    var p_crntDateCtrl = arguments[5];
    var fromDateObj    = getDateFromDateString(p_format, p_fromDateCtrl.value);
    var crntDateObj    = (p_crntDateCtrl == null) ? null : getDateFromDateString(p_format, p_crntDateCtrl.value);
    var p_crntYear     = null;
    var p_crntMonth    = null;
    
    if (fromDateObj == null) {
        fromDateObj          = new Date();
        p_fromDateCtrl.value = getDateFormatString(p_format, fromDateObj);
    }
    
    if (crntDateObj != null) {
        p_crntYear  = new String(crntDateObj.getFullYear());
        p_crntMonth = new String(crntDateObj.getMonth());        
    }
    
    if (p_dayRange == null) p_dayRange = DEFAULT_DAY_RANGE;
        
    var toDateObj = new Date(fromDateObj.getFullYear(), fromDateObj.getMonth(), (fromDateObj.getDate() + p_dayRange -1));
    var p_toDate  = getDateFormatString(p_format, toDateObj);
    
    show_calendarEx(p_item, p_format, "", "", p_locale, p_crntYear, p_crntMonth, null, null, null, p_fromDateCtrl.value, p_toDate, true);
}

/*  p_item          : Return Item.
    p_format        : Date format (yyyy/MM/dd, dd/MM/yy, ...)
    p_locale        : "zh_TW", "en_US"
*/
function show_calendar2() {
    p_item              = arguments[0];
    p_format            =(arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    p_locale            =(arguments[2] == "" || arguments[2] == null) ? "zh_TW" : arguments[2];
    
    show_calendarEx(p_item, p_format, "", "", p_locale);
    return (gPopUpWinCal);
}

/*  p_item              : Return Item.
    p_format            : Date format (yyyy/MM/dd, dd/MM/yy, ...)
    p_item2             : copied item
    p_item2Value        : copied item's value (if the value is not empty, the copied item will remain, or it will have the p_item's value
    p_locale            : "zh_TW", "en_US"
    p_year              : 4-digit year
    p_month             : 0-11 for Jan-Dec; 12 for All Months.
*/
function show_calendarEx() {
    p_item            = arguments[0];
    p_format          = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    p_item2           = (arguments[2] == "" || arguments[2] == null) ? "" : arguments[2];
    p_item2Value      = (arguments[3] == "" || arguments[3] == null) ? "" : arguments[3];
    p_locale          = (arguments[4] == "" || arguments[4] == null) ? "en_US" : arguments[4];
    p_cbFunc          = (arguments[7] == "" || arguments[7] == null) ? "" : arguments[7];
    p_timeDiff        = (arguments[8] == "" || arguments[8] == null) ? 0 : arguments[8];
    p_fAdjustTimeZone = (arguments[9] == "" || arguments[9] == null) ? false : arguments[9];
    p_crntTime        = getAdjustedNowDate(p_timeDiff, p_fAdjustTimeZone);
    p_year            = (arguments[5] == "" || arguments[5] == null) ? (new String(p_crntTime.getFullYear().toString())) : arguments[5];
    p_month           = (arguments[6] == "" || arguments[6] == null) ? (new String(p_crntTime.getMonth())) : arguments[6];
    p_refFromDay      = (arguments[10] == "" || arguments[10] == null) ? null : arguments[10];
    p_refToDay        = (arguments[11] == "" || arguments[11] == null) ? null : arguments[11];
    p_fUseRefDate     = (arguments[12] == "" || arguments[12] == null) ? null : arguments[12];
    p_markedDate      = (arguments[13] == "" || arguments[13] == null) ? null : arguments[13];
    
    calendarCloseHandler();
    
    if(window.navigator.userAgent.toLowerCase().indexOf('gecko') != -1) {
        gPopUpWinCal = window.open("blank.html", "Calendar", "width=300, height=300, toolbar=no, menubar=no,scrollbars=no,status=no,resizable=no,top=200,left=200");
    } else {
        gPopUpWinCal = window.open("blank.html", "Calendar", "width=300, height=220, toolbar=no, menubar=no,scrollbars=no,status=no,resizable=no,top=200,left=200");
    }
    p_item2Value = dpTrimString(p_item2Value);
    Build(gPopUpWinCal, p_item, p_item2, p_item2Value, p_month, p_year, p_format, p_locale, PopupCaller, p_cbFunc, p_refFromDay, p_refToDay, p_timeDiff, p_fAdjustTimeZone, p_fUseRefDate, p_markedDate);
    gPopUpWinCal.focus();
    gPopUpWinCal.opener = self;

    self.onunload = calendarCloseHandler;
    if (self.parent != null) self.parent.onunload = calendarCloseHandler;
}

function show_calendarFrame() {
    var p_frameName       = arguments[0];
    var p_parentFunc      = arguments[1];
    var p_format          = (arguments[2]  == "" || arguments[2]  == null) ? "yyyy/MM/dd" : arguments[2];
    var p_locale          = (arguments[3]  == "" || arguments[3]  == null) ? "en_US" : arguments[3];
    var p_refFromDay      = (arguments[4]  == "" || arguments[4]  == null) ? "" : arguments[4];
    var p_refToDay        = (arguments[5]  == "" || arguments[5]  == null) ? p_refFromDay : arguments[5];
    var p_timeDiff        = (arguments[8]  == "" || arguments[8]  == null) ? 0 : arguments[8];
    var p_fAdjustTimeZone = (arguments[9]  == "" || arguments[9]  == null) ? false : arguments[9];
    var p_fUseRefDate     = (arguments[10] == "" || arguments[10] == null) ? false : arguments[10];
    var p_year            = null;
    var p_month           = null;
    var dayArray          = getDateStringToken(p_format, p_refToDay);
    
    if (dayArray == null) {
        var now = getAdjustedNowDate(p_timeDiff, p_fAdjustTimeZone);
        p_year  = (arguments[6] == "" || arguments[6] == null) ? (new String(now.getFullYear().toString())) : arguments[6];
        p_month = (arguments[7] == "" || arguments[7] == null) ? (new String(now.getMonth())) : arguments[7];
    } else {
        p_year  = new Number(dayArray[0]);
        p_month = new Number(dayArray[1]) - 1;
    }
    var winCal = self.frames[p_frameName];

    Build(winCal, p_parentFunc, "", "", p_month, p_year, p_format, p_locale, FrameCaller, null, p_refFromDay, p_refToDay, p_timeDiff, p_fAdjustTimeZone, p_fUseRefDate);
}

function calendarCloseHandler() {
    if (gPopUpWinCal != null) {
        gPopUpWinCal.close();
        gPopUpWinCal = null;
    }
}
/*
Yearly Calendar Code Starts here
*/
function show_yearly_calendar(p_item, p_year, p_format, p_locale) {
    // Load the defaults..
    if (p_year == null || p_year == "")  p_year = new String(gNow.getFullYear().toString());
    if (p_format == null || p_format == "") p_format = "yyyy/MM/dd";

    var vWinCal = window.open("", "Calendar", "scrollbars=yes");
    vWinCal.opener = self;
    gPopUpWinCal = vWinCal;

    Build(vWinCal,p_item, "", "", null, p_year, p_format, p_locale);
}

/*
Check input parameter digStr is digital
*/
function isDigit(digStr) {
  if (digStr.length == 0) return false;
  for ( i = 0 ; i < digStr.length ; i++ ) {
    if ( digStr.charAt(i) > '9' || digStr.charAt(i) < '0' ) return false;
  }

  return true;
}

function getDatePatternDelimitation(pattern) {
    switch (pattern) {
        case "yyyy-dd-MM":
        case "yyyy-MM-dd":
        case "MM-dd-yyyy":
        case "dd-MM-yyyy":return "-";

        case "yyyy/dd/MM":
        case "MM/dd/yyyy":
        case "dd/MM/yyyy":
        case "yyyy/MM/dd":
        default          : return"/"; break;
    }
}

function appendYear(pattern, year, monthDay) {
    switch (pattern) {
        case "yyyy-dd-MM":
        case "yyyy-MM-dd":return (year + "-" + monthDay);

        case "yyyy/dd/MM":
        case "yyyy/MM/dd":return (year + "/" + monthDay);

        case "MM-dd-yyyy":
        case "dd-MM-yyyy":return (monthDay + "-" + year);

        case "MM/dd/yyyy":
        case "dd/MM/yyyy":
        default          :return (monthDay + "/" + year);
    }
}

function getStringToken(delim, str) {
    var token   = new Array();
    var testStr = dpTrimString(str);

    if (delim == null || delim.length == 0) {
        token[0] = testStr;
        return token;
    }

    var delimIndex = testStr.indexOf(delim);
    var tokenIndex = 0;
    if (delimIndex == -1 && testStr.length != 0) token[0] = testStr;

    while(delimIndex != -1) {
        token[tokenIndex] = testStr.substring(0, delimIndex);
        if (delim != " ") token[tokenIndex] = dpTrimString(token[tokenIndex]);
        tokenIndex += 1;
        testStr = testStr.substring(delimIndex + 1);
        delimIndex = testStr.indexOf(delim);
        if (delimIndex == -1) {
            token[tokenIndex] = testStr;
            if (delim != " ") token[tokenIndex] = dpTrimString(token[tokenIndex]);
        }
    }

    return token;
}

function getDateStringToken(pattern, dateStr, fAllowWildCards) {

    var delimitation = getDatePatternDelimitation(pattern);
    var patternToken = getStringToken(delimitation, pattern);
    var dateStrToken = getStringToken(delimitation, dateStr);

    if (patternToken.length != dateStrToken.length) return null;
    if (dateStrToken.length != 3) return null;

    var normalizedToken = new Array();
    var year  = 0;
    var month = 0;
    var day   = 0;
    switch (pattern) {
        case "MM/dd/yyyy":
        case "MM-dd-yyyy": month=dateStrToken[0];day=dateStrToken[1];year=dateStrToken[2];break;
        case "dd/MM/yyyy":
        case "dd-MM-yyyy": day=dateStrToken[0];month=dateStrToken[1];year=dateStrToken[2];break;
        case "yyyy-dd-MM":
        case "yyyy/dd/MM": year=dateStrToken[0];day=dateStrToken[1];month=dateStrToken[2];break;
        case "yyyy/MM/dd":
        case "yyyy-MM-dd":
        default          : year=dateStrToken[0];month=dateStrToken[1];day=dateStrToken[2];break;
    }
    
    if (fAllowWildCards == null || fAllowWildCards == false) {
        if (isDigit(year) == false) return null; //format error
        if (isDigit(month) == false) return null; //format error
        if (isDigit(day) == false) return null; //format error
    }
    
    normalizedToken[0] = year;
    normalizedToken[1] = month;
    normalizedToken[2] = day;
    //alert(normalizedToken);
    return normalizedToken;
}

function getMaxDay(year, month) {
    var maxDay = 0;
    if ((year % 4) == 0) {
        if ((year % 100) == 0 && (year % 400) != 0) maxDay = Calendar.DOMonth[month-1];
        maxDay = Calendar.lDOMonth[month-1];
    } else {
        maxDay = Calendar.DOMonth[month-1];
    }
    //alert("maxDay = " + maxDay);
    return maxDay;
}

function getDateFromDateString(pattern, dateStr) {
    if (dateStr == null || dateStr == "") return null;
    var dateToken = getDateStringToken(pattern, dateStr);
    if (dateToken == null) {
        //alert("format error -" + dateStr);
        return null; //format error
    }
    var year  = dateToken[0];
    var month = dateToken[1];
    var day   = dateToken[2];
    var hours = (arguments[2] == "" || arguments[2] == null) ? 0 : arguments[2];
    var mins  = (arguments[3] == "" || arguments[3] == null) ? 0 : arguments[3];
    var secs  = (arguments[4] == "" || arguments[4] == null) ? 0 : arguments[4];

    //alert("year  = " + year);
    //alert("month = " + month);
    //alert("day   = " + day);

    if (month < 1 || month > 12) {
        //alert("month error -" + month);
        return null; //illegal month
    }
    if (day < 1 || day > getMaxDay(year, month)) {
        //alert("day error -" + day);
        return null; //illegal day
    }
    var chkDate = new Date(year, month-1, day, hours, mins, secs);
    //alert(chkDate);
    return chkDate;
}

//before use the function, should call isValidDateFormat or isValidDateFormatEx
//for fromDateStr and toDateStr
//  argument 0 : from Date presented by a string.
//  argument 1 : to Date presented by a string.
//  argument 2 : the date string pattern (yyyy/MM/dd, yyyy/dd/MM, ...)
function isFromDateBeforeOrEqualToDate(){
    var fromDateStr = arguments[0];
    var toDateStr   = arguments[1]
    var pattern     = (arguments[2] == "" || arguments[2] == null) ? "yyyy/MM/dd" : arguments[2];
    var fromDate    = getDateFromDateString(pattern, fromDateStr);
    var toDate      = getDateFromDateString(pattern, toDateStr);
    var timeDiff    = toDate - fromDate;
    //alert("timeDiff = " + timeDiff);
    return (timeDiff >= 0);
}

function checkDatesEqual(){
    var chkee = arguments[0];
    var chker = arguments[1]
    if (chkee == null || chker == null) return false;
    return ((chkee - chker) == 0);
}

//before use the function, should call isValidDateFormat or isValidDateFormatEx
//for fromDateStr and toDateStr
//  argument 0 : from Date presented by a string.
//  argument 1 : to Date presented by a string.
//  argument 2 : the date string pattern (yyyy/MM/dd, yyyy/dd/MM, ...)
function getTimeDiff(){

    var fromDateStr = arguments[0];
    var toDateStr   = arguments[1]
    var pattern     = (arguments[2] == "" || arguments[2] == null) ? "yyyy/MM/dd" : arguments[2];

    var fromDate = getDateFromDateString(pattern, fromDateStr);
    var toDate   = getDateFromDateString(pattern, toDateStr);
    var timeDiff = toDate - fromDate;
    //alert("timeDiff = " + timeDiff);
    return (timeDiff);
}

//before use the function, should call isValidDateFormat or isValidDateFormatEx
//for fromDateStr and toDateStr
//  argument 0 : from date presented by a string.
//  argument 1 : from hour
//  argument 2 : from minute
//  argument 3 : from second
//  argument 4 : to date presented by a string.
//  argument 5 : to hour
//  argument 6 : to minute
//  argument 7 : to second
//  argument 8 : the date string pattern (yyyy/MM/dd, yyyy/dd/MM, ...)
function isFromDateTimeBeforeToDateTime(){
    var fromDateStr = arguments[0];
    var fromHour    = arguments[1];
    var fromMin     = arguments[2];
    var fromSec     = arguments[3];
    var toDateStr   = arguments[4];
    var toHour      = arguments[5];
    var toMin       = arguments[6];
    var toSec       = arguments[7];
    var pattern     = (arguments[8] == "" || arguments[8] == null) ? "yyyy/MM/dd" : arguments[8];

    var fromDate = getDateFromDateString(pattern, fromDateStr, fromHour, fromMin, fromSec);
    var toDate   = getDateFromDateString(pattern, toDateStr, toHour, toMin, toSec);

    var timeDiff = toDate - fromDate;
    //alert("timeDiff = " + timeDiff);
    return (timeDiff > 0);
}

function getAdjustedNowDate(clockTimeDiff, fAdjustTimeZone, dayOffset) {
    if (clockTimeDiff   == null) clockTimeDiff   = 0;
    if (fAdjustTimeZone == null) fAdjustTimeZone = false;
    if (dayOffset       == null) dayOffset       = 0;

    var now      = new Date();
    var adjustee = fAdjustTimeZone
                 ? new Date(now.getTime() + dayOffset*ONE_DAY_LONG_IN_MILLISECOND + clockTimeDiff + now.getTimezoneOffset()*ONE_MINUTE_LONG_IN_MILLISECOND)
                 : new Date(now.getTime() + dayOffset*ONE_DAY_LONG_IN_MILLISECOND + clockTimeDiff);
    return (adjustee);
}

function getSvrNowDate(svrClientTimeDiff, now) {
    if (svrClientTimeDiff == null) svrClientTimeDiff = 0;
    if (now == null) now = new Date();
    var svrTime = new Date(now.getTime() + svrClientTimeDiff);
    return (svrTime);
}

function getCrntTimeStringByPattern(pattern, svrClientTimeDiff, fAdjustTimeZone) {
    var svrTime = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone);
    return (getDateTimeStringByPattern(pattern, null, svrTime.getFullYear(), (1 + svrTime.getMonth()), svrTime.getDate()));
}

function getFirstWeekdayByPattern(pattern, refDateStr, svrClientTimeDiff, fAdjustTimeZone) {
    var refDate = getDateFromDateString(pattern, refDateStr);
    if (refDate == null) return (null);
    var date = refDate.getDate() - refDate.getDay();
    return (getDateTimeStringByPattern(pattern, null, refDate.getFullYear(), (1 + refDate.getMonth()), date));
}

function getLastWeekdayByPattern(pattern, refDateStr, svrClientTimeDiff, fAdjustTimeZone) {
    var refDate = getDateFromDateString(pattern, refDateStr);
    if (refDate == null) return (null);
    var date = refDate.getDate() + 6-refDate.getDay();
    return (getDateTimeStringByPattern(pattern, null, refDate.getFullYear(), (1 + refDate.getMonth()), date));
}

function getThisFirstWeekdayByPattern(pattern, svrClientTimeDiff, fAdjustTimeZone) {
    var usrLocalNow  = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone);
    var firstWeekday = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone, (-1 * usrLocalNow.getDay()));
    return (getDateTimeStringByPattern(pattern, null, firstWeekday.getFullYear(), (1 + firstWeekday.getMonth()), firstWeekday.getDate()));
}

function getThisLastWeekdayByPattern(pattern, svrClientTimeDiff, fAdjustTimeZone) {
    var usrLocalNow = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone);
    var lastWeekday = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone, (6-usrLocalNow.getDay()));
    return (getDateTimeStringByPattern(pattern, null, lastWeekday.getFullYear(), (1 + lastWeekday.getMonth()), lastWeekday.getDate()));
}

function getLastDayOfThisMonthByPattern(pattern, svrClientTimeDiff, fAdjustTimeZone) {
    var usrLocalNow = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone);
    var lastDay     = Calendar_get_daysofmonth(usrLocalNow.getMonth(), usrLocalNow.getFullYear());
    return (getDateTimeStringByPattern(pattern, null, usrLocalNow.getFullYear(), (1 + usrLocalNow.getMonth()), lastDay));
}

function getFirstDayOfThisMonthByPattern(pattern, svrClientTimeDiff, fAdjustTimeZone) {
    var usrLocalNow = getAdjustedNowDate(svrClientTimeDiff, fAdjustTimeZone);
    return (getDateTimeStringByPattern(pattern, null, usrLocalNow.getFullYear(), (1 + usrLocalNow.getMonth()), 1));
}

function getFirstDayOfMonthByPattern(pattern, refDateStr, svrClientTimeDiff, fAdjustTimeZone) {
    var refDate = getDateFromDateString(pattern, refDateStr);
    if (refDate == null) return (null);
    return (getDateTimeStringByPattern(pattern, null, refDate.getFullYear(), (1 + refDate.getMonth()), 1));
}

function getLastDayOfMonthByPattern(pattern, refDateStr, svrClientTimeDiff, fAdjustTimeZone) {
    var refDate = getDateFromDateString(pattern, refDateStr);
    if (refDate == null) return (null);
    var lastDay = Calendar_get_daysofmonth(refDate.getMonth(), refDate.getFullYear());
    return (getDateTimeStringByPattern(pattern, null, refDate.getFullYear(), (1 + refDate.getMonth()), lastDay));
}

function isValidDateFormatEx2() {
    var dateCtrl = arguments[0];
    var pattern  = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    var dateStr;

    if (dateCtrl.value == null) {
        dateStr = dpTrimString(dateCtrl);
    } else {
        dateCtrl.value = dpTrimString(dateCtrl.value);
        dateStr = dateCtrl.value;
    }

    if (dateStr.length == 0) {
        return false;
    }

    var delim  = getDatePatternDelimitation(pattern);
    var tokens = getStringToken(delim, dateStr);
    var now    = new Date();
    if (tokens.length == 2) {
        dateStr = appendYear(pattern, now.getFullYear(), dateStr);
        if (dateCtrl.value != null) dateCtrl.value = dateStr;
    }

    var result = getDateFromDateString(pattern, dateStr);
    if (result != null && dateCtrl.value != null) {
        dateCtrl.value = dateStr;
    }

    return ( result != null);
}

var DATE_VALUE_VALID      = 0;
var DATE_VALUE_NOT_DIGIT  = -1;
var DATE_VALUE_OVER_LIMIT = -2;
function isValidDateFormatEx3() {
    var dateCtrl = arguments[0];
    var pattern  = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    var dateStr;

    if (dateCtrl.value == null) {
        dateStr = dpTrimString(dateCtrl);
    } else {
        dateCtrl.value = dpTrimString(dateCtrl.value);
        dateStr = dateCtrl.value;
    }

    if (dateStr.length == 0) return DATE_VALUE_NOT_DIGIT;

    var delim  = getDatePatternDelimitation(pattern);
    var tokens = getStringToken(delim, dateStr);
    var now    = new Date();
    if (tokens.length == 2) {
        dateStr = appendYear(pattern, now.getFullYear(), dateStr);
    }

    for (var i = 0; i < tokens.length; i++) {
        if (isDigit(tokens[i]) == false) return DATE_VALUE_NOT_DIGIT;
    }

    var result = getDateFromDateString(pattern, dateStr);
    if (result != null && dateCtrl.value != null) {
        dateCtrl.value = dateStr;
    }

    if (result == null) return DATE_VALUE_OVER_LIMIT;
    return (DATE_VALUE_VALID);
}

// argument 0 : date presented by a string format to be checked by a specified pattern(argument 1).
// argument 1 : the date string pattern (yyyy/MM/dd, yyyy/dd/MM, ...)
// return value true   well-formatted date string
//              false  ill-formatted date string
function isValidDateFormat() {
    var dateStr = arguments[0];
    var pattern = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    return (getDateFromDateString(pattern, dateStr) != null);
}

// argument 0 : date presented by a string format to be checked by a specified pattern(argument 1).
// argument 1 : the date string pattern (yyyy/MM/dd, yyyy/dd/MM, ...)
// return value  0   well-formatted date string
//              -1   format error
//              -2   illegal date
function isValidDateFormatEx() {
    var dateStr        = arguments[0];
    var pattern        = (arguments[1] == "" || arguments[1] == null) ? "yyyy/MM/dd" : arguments[1];
    var fWithWildCards = (arguments[2] == "" || arguments[2] == null) ? false : arguments[2];
    var dateToken      = getDateStringToken(pattern, dateStr, fWithWildCards);
    
    if (dateToken == null) return -1; //format error
    var year  = dateToken[0];
    var month = dateToken[1];
    var day   = dateToken[2];
    
    if (fWithWildCards == false) {
        if (month < 1 || month > 12) return -2; //illegal month
        if (day < 1 || day > getMaxDay(year, month)) return -2; //illegal day
    
        var chkDate = new Date(year, month-1, day);
        if (isNaN(chkDate) == true) return -1; //format error
    } else {
        if (year.indexOf("*") == -1) {
            if (isDigit(year) == false) return -2;
        } else {
            if (isAllWildCards(year) == false)  return -2;
        }
        
        if (month.indexOf("*") == -1) {
            if (month < 1 || month > 12) return -2; //illegal month
        } else {
            if (isAllWildCards(month, 2) == false)  return -2;
        }
        
        if (day.indexOf("*") == -1) {
            if (month.indexOf("*") == -1) {
                if (day < 1 || day > getMaxDay(2000, month)) return -2; //illegal day
            } else {
                if (day < 1 || day > 31) return -2; //illegal day
            }
        } else {
            return -2;
        }
    }
   
    return 0;
}

function isAllWildCards(text, len) {  
    if (text.length == 0) return false;  
    if (text.indexOf("*") == -1) return true;
    if (len != null && text.length != len) return false;
    for (var i = 0 ; i < text.length ; i++ ) {
        if ( text.charAt(i) != '*') return false;
    }
    return true;
}

function dpTrimString(str) {
    str = this != window? this : str;
    return str.replace(/^\s+/g, '').replace(/\s+$/g, '').replace(/\r/g,'').replace(/\n/g,' ');
}

function dpTrimDateString(str) {
    return str.replace(/\s+/g,'');
}

function checkDateFormat() {
    var chkDate          = arguments[0];
    var aDateFormat      = arguments[1];
    var formatErrorMsg   = arguments[2];
    var illegalDateMsg   = arguments[3];
    var isEmptyLegal     = arguments[4];
    var isNormalized     = arguments[5];
    var isAllowWildCards = arguments[6];
    var withWildCards    = false;
    var result = 0;
    
    if (chkDate == null || aDateFormat == null) return false;

    chkDate.value = dpTrimDateString(chkDate.value);
    
    if (isEmptyLegal == null || isEmptyLegal == true) {
        if (chkDate.value == "") return true;
    }
    
    if ((isAllowWildCards != null || isAllowWildCards == true) && chkDate.value.indexOf("*") != -1) {
        withWildCards = true;
    }
    
    var result = isValidDateFormatEx(chkDate.value, aDateFormat, withWildCards);
    
    if (result == -1) {
        if (formatErrorMsg != null && formatErrorMsg != "") {
            alert(formatErrorMsg);
            chkDate.focus();
        }
        return false;
    } else if (result == -2){
        if (illegalDateMsg != null && illegalDateMsg != "") {
            alert(illegalDateMsg);
            chkDate.focus();
        }
        return false;
    }
    
    if (isNormalized != null && isNormalized == true) {
        var tokens = getDateStringToken(aDateFormat, chkDate.value, withWildCards);
        chkDate.value = getDateTimeStringByPattern(aDateFormat, null, tokens[0], tokens[1], tokens[2]);
    }
    return true;
}
