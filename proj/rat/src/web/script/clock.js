var g_clockTimeDiff;
var g_clockDateFormat;
var g_clockTimeFormat;
var g_clockHtml;
var g_crntClockDateFormat;
var g_agent;
var g_clockAnchorBegin = "<a href='javascript:toggleClockDateFormat();' class='clock'>";
var g_clockAnchorEnd   = "</a>";
var g_timZoneOffset    = null;

var AM_KEYWORD = "AM";
var PM_KEYWORD = "PM";
var ONE_SECOND_LONG_IN_MILLISECOND    = (1000);
var ONE_MINUTE_LONG_IN_MILLISECOND    = (60 * ONE_SECOND_LONG_IN_MILLISECOND);
var ONE_HOUR_LONG_IN_MILLISECOND      = (60 * ONE_MINUTE_LONG_IN_MILLISECOND);
var ONE_DAY_LONG_IN_MILLISECOND       = (24 * ONE_HOUR_LONG_IN_MILLISECOND);
var ONE_DAY_LONG_IN_MINUTE            = (ONE_DAY_LONG_IN_MILLISECOND / ONE_MINUTE_LONG_IN_MILLISECOND);

function toggleClockDateFormat() {
    if (g_crntClockDateFormat == null) {
        g_crntClockDateFormat = g_clockDateFormat;
    } else {
        g_crntClockDateFormat = null;
    }
}

function setClock(dateFormat, timeFormat, svrGMTTimeMilliseconds, clockId, fAdjustTimeZone, timeZoneOffset, disableToggle) {

    var clock = document.getElementById(clockId);
    if (clock == null) return;

    g_agent = getAgent();
    if (g_agent == null) return;

    if (fAdjustTimeZone == null) fAdjustTimeZone = false;
    g_clockTimeDiff       = new Date(svrGMTTimeMilliseconds) - new Date();
    g_clockDateFormat     = dateFormat;
    g_clockTimeFormat     = timeFormat;
    g_clockHtml           = clock;
    g_crntClockDateFormat = dateFormat;
    g_timZoneOffset       = timeZoneOffset == null ? null : timeZoneOffset;
    
    if (disableToggle == false || (g_agent != "e5" && g_agent != "e6" && g_agent != "e7")) {
        g_clockAnchorBegin = "";
        g_clockAnchorEnd   = "";
    }
    runClock(fAdjustTimeZone);
}

function getAgent() {
    var agent = navigator.userAgent;

    if (agent.indexOf("MSIE 7") != -1)    return ("e7");
    if (agent.indexOf("MSIE 6") != -1)    return ("e6");
    if (agent.indexOf("MSIE 5") != -1)    return ("e5");
    if (agent.indexOf("MSIE 4") != -1)    return ("e4");
    if (agent.indexOf("Netscape6") != -1) return ("n6");
    if (agent.indexOf("Gecko") != -1 )    return ("m"); //Mozilla

    return (null);
}

function runClock(fAdjustTimeZone) {

    var runTime    = getAdjustedNowDate(g_clockTimeDiff, fAdjustTimeZone);
    var movingtime = g_clockAnchorBegin
                   + getDateTimeStringByPattern(
                            g_crntClockDateFormat,
                            g_clockTimeFormat,
                            runTime.getFullYear(),
                            runTime.getMonth() + 1,
                            runTime.getDate(),
                            runTime.getHours(),
                            runTime.getMinutes(),
                            runTime.getSeconds())
                   + getTimeZone(g_timZoneOffset)
                   + g_clockAnchorEnd;

    writeToDiv(g_clockHtml, movingtime);
    if (fAdjustTimeZone == true) {
        setTimeout("runClock(true)", 1000);
    } else {
        setTimeout("runClock(false)", 1000);
    }
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

function writeToDiv(divCtrl, text) {
    if (document.layers) {
        divCtrl.document.write(text);
        divCtrl.document.close();
    } else if (document.all) {
        divCtrl.innerHTML = text;
    } else {
        divCtrl.firstChild.nodeValue = text;
    }
}

function getDateTimeStringByPattern(datePattern, timePattern, yyyy, MM, dd, hh, mm, ss) {
    var vData = "";

    MM = (MM.toString().length < 2) ? "0" + MM : MM;
    dd = (dd.toString().length < 2) ? "0" + dd : dd;
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

function getTimeZone(timZoneOffset) {
    var agent = getAgent();
    if (agent == null) return "";
    var timeZone = "";
    if (timZoneOffset != null) {
        if (timZoneOffset == 0) return (" (GMT)");
        var sign   = ""
        var offset = Math.abs(timZoneOffset);
        var hr     = offset / 60;
        var mi     = offset % 60;
        if (timZoneOffset > 0) sign = "+";
        if (timZoneOffset < 0) sign = "-";
        
        timeZone = "GMT" + sign + (hr < 10 ? "0" : "") + hr + ":" + (mi < 10 ? "0" : "") + mi;
        timeZone = " " + "(" + timeZone + ")";
    } else {
        //Only valid for IE 5.0 and above & Nestcape 6.0 & Mozilla
        var usrTime       = new Date();
        var usrTimeArray  = getStringToken(' ', usrTime.toString());
        var timeZoneStr   = usrTimeArray[(agent == 'm' ? 5 : 4)]; //hard-coded
        var UTC_KEY_WORD  = "UTC";
        var utcStartIndex = timeZoneStr.indexOf(UTC_KEY_WORD);
        if (utcStartIndex != -1) {
            timeZone = timeZoneStr.substring(0, utcStartIndex)
                     + "GMT"
                     + timeZoneStr.substring(utcStartIndex + UTC_KEY_WORD.length, timeZoneStr.length);
            timeZone = " " + "(" + timeZone + ")";
        }
    }
    
    return (timeZone);
}

function getStringToken(delim, str) {
    var token   = new Array();
    var testStr = trimString(str);

    if (delim == null || delim.length == 0) {
        token[0] = testStr;
        return token;
    }

    var delimIndex = testStr.indexOf(delim);
    var tokenIndex = 0;
    if (delimIndex == -1 && testStr.length != 0) token[0] = testStr;

    while(delimIndex != -1) {
        token[tokenIndex] = testStr.substring(0, delimIndex);
        if (delim != " ") token[tokenIndex] = trimString(token[tokenIndex]);
        tokenIndex += 1;
        testStr = testStr.substring(delimIndex + 1);
        delimIndex = testStr.indexOf(delim);
        if (delimIndex == -1) {
            token[tokenIndex] = testStr;
            if (delim != " ") token[tokenIndex] = trimString(token[tokenIndex]);
        }
    }

    return token;
}

function trimString (str) {
    str = this != window? this : str;
    return str.replace(/^\s+/g, '').replace(/\s+$/g, '').replace(/\r/g,'').replace(/\n/g,' ');
}
