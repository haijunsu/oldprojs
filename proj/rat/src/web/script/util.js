/**
 * check browser version
 */
var ns4=document.layers
var ie4=document.all
var ns6=document.getElementById&&!document.all

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
  var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
  if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {                                                                                   d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function trimString (str) {
    str = this != window? this : str;
    return str.replace(/^\s+/g, '').replace(/\s+$/g, '').replace(/\r/g,'').replace(/\n/g,' ');
    //return str.replace(/^\s+/g, '').replace(/\s+$/g, '').replace(/\r/g,'').replace(/\n/g,'');
}

function checkStringEmpty (elm, emptyAlertMsg) {
    var value = elm.value;
    value = trimString(value);
    if (value.length == 0) {
        if (emptyAlertMsg != null) {
            alert(emptyAlertMsg);
            elm.focus();
            return false;
        } else {
            return true;
        }
    }
    return true;
}

function checkTextFieldLength(elm, maxBytes, truncatedConfirmMsg, emptyAlertMsg) {
    var value = elm.value;
    var n;

    //if (elm.type.toLowerCase() == 'text'){
    //    value = trimString(value);
    //}
    value = trimString(value);
    if (value.length == 0) {
        if (emptyAlertMsg != null) {
            alert(emptyAlertMsg);
            elm.focus();
            return false;
        } else {
            return true;
        }
    }
    if (maxBytes == -1) return true;
    for(n=0; (n < value.length && maxBytes > 0); n++) {
        var c=value.charCodeAt(n);

        if (c<128) { // 0-127 => 1byte
            maxBytes -= 1;
        } else if((c>127) && (c<2048)) { // 127 -  2047 => 2byte
            maxBytes -= 2;
        } else { // 2048 - 66536 => 3byte
            maxBytes -= 3;
        }
    }

    if ((maxBytes == 0 && (n < value.length)) || maxBytes < 0) {
        if (truncatedConfirmMsg != null && confirm(truncatedConfirmMsg) == false) {
            elm.focus();
            return false;
        }
    }

    elm.value = (maxBytes < 0 && n > 1) ? value.substring(0, n-1) : value.substring(0, n);
    return true;
}

function checkTextFieldLengthEx(elm, maxLength, truncatedConfirmMsg, emptyAlertMsg) {
    var value = trimString(elm.value);
    if (value.length == 0) {
        if (emptyAlertMsg != null) {
            alert(emptyAlertMsg);
            elm.focus();
            return false;
        } else {
            return true;
        }
    }

    if (maxLength == -1) return true;

    if (maxLength < value.length) {
        if (truncatedConfirmMsg != null && confirm(truncatedConfirmMsg) == false) {
            elm.focus();
            return false;
        }
    }

    elm.value = value.substring(0, maxLength);
    return true;
}

function setAllCheckButtons(isChecked, form, checkBtnNamePrefix) {
    if ( form.elements == null ) return;

    for (var i = 0; i< form.elements.length; i++) {
        var elm = form.elements[i];
        if (elm == null || typeof(elm.type) == 'undefined' || elm.type == null) continue;
        if (elm.type.toLowerCase() == "checkbox" && elm.name.indexOf(checkBtnNamePrefix) != -1) {
            elm.checked = isChecked;
        }
    }
}

function isAnyCheckButtonChecked(form, checkBtn1NamePrefix, checkBtn2NamePrefix) {
    if ( form.elements == null ) return true;
    var checkPrefixs = new Array();

    if (checkBtn1NamePrefix != null && checkBtn1NamePrefix.length > 0) {
        checkPrefixs[checkPrefixs.length] = checkBtn1NamePrefix;
    }

    if (checkBtn2NamePrefix != null && checkBtn2NamePrefix.length > 0) {
        checkPrefixs[checkPrefixs.length] = checkBtn2NamePrefix;
    }
    //alert("checkPrefixs.length = " + checkPrefixs.length);
    if (checkPrefixs.length == 0) return true;

    for (var i = 0; i< form.elements.length; i++) {
        var elm = form.elements[i];
        if (elm == null || typeof(elm.type) == 'undefined' || elm.type == null) continue;
        if (elm.type.toLowerCase() != "checkbox") continue;
        for (var j = 0; j < checkPrefixs.length; j++) {
            if (elm.name.indexOf(checkPrefixs[j]) != -1) {
                if (elm.checked == true) return true;
                break;
            }
        }
    }
    //alert("found nothing");
    return false;
}

function isDigit(digStr, fCanEmpty) {
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && digStr.length == 0) return true;
    var result = digStr.match(/[^0-9]/g);
    if (result || !digStr) return false;
    return true;
}

function isDigitEx(elm, fCanEmpty, emptyAlertMsg) {
	var digStr = elm.value;
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && digStr.length == 0) return true;
    var result = digStr.match(/[^0-9]/g);
    if (result || !digStr) {
		alert(emptyAlertMsg);
        elm.focus();
    	return false;
    }
    return true;
}

function isDecimal(elm, fCanEmpty, emptyAlertMsg, errorAlertMsg) {
	var decStr = elm.value;
	var length = decStr.length;
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && length == 0) return true;
    if (length == 0) {
		alert(emptyAlertMsg);
        elm.focus();
    	return false;
    }

    var beginIndex = decStr.indexOf(".");
    var endIndex = decStr.lastIndexOf(".");
    if (beginIndex != -1) {
    	if (beginIndex == endIndex) {
	    	if ( (length - beginIndex) == 3 || (length - beginIndex) == 2) {
				decStr = decStr.replace("." , "");
	    	}
    	}
    }
    if ( !decStr.match(/[^0-9]/g) ) {
		return true;
    }
	alert(errorAlertMsg);
    elm.focus();
    return false;
}

function getInteger(elm) {
	var str = elm.value;
	var ix = str.indexOf(".");
	var position = str.length - ix;
	str = str.replace("." , "");
	if (position == 2 && ix != -1) {
		return str * 10;
	} else if (position == 3 && ix != -1) {
		return str;
	} else {
		return str * 100;
	}
}

function checkFileExtension(elm, exts, emptyAlertMsg) {
	var value = trimString(elm.value);
	var ix = value.lastIndexOf(".");
	if (ix == -1) {
		alert(emptyAlertMsg);
		elm.focus();
		return false;
	}
	var ext = value.substring(ix+1).toLowerCase();
	for (var i = 0; i < exts.length; i++) {
		if(exts[i] == ext){
			return true;
		}
	}
	alert(emptyAlertMsg);
	elm.focus();
	return false;
}

function isAlphabetNumeric(testStr, fCanEmpty) {
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && testStr.length == 0) return true;
    var result = testStr.match(/[^0-9a-zA-Z]/g);
    if (result || !testStr) return false;
    return true;
}

function isJavaScriptLegalId(idStr) {
  if (idStr.length == 0) return false;
  for ( i = 0 ; i < idStr.length ; i++ ) {
    if ( idStr.charAt(i) == '\'' || idStr.charAt(i) == '\"' || idStr.charAt(i) == '\\') return false;
  }

  return true;
}

function setRadioChecked(radios, radioValue) {

    if (typeof(radios) == 'undefined' || radios == null) return;
    if (typeof(radios.length) == 'undefined') { //only one element
        if (radios.type != "radio") return;
        radios.checked = true;
    } else {
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].type != "radio") continue;
            radios[i].checked = (radios[i].value == radioValue) ? true : false;
        }
    }
}

function trimAllInputTextValues(form, inputCtrlNamePrefix) {
    for(var i=0; i< form.elements.length; i++) {
        var control = form.elements[i];
        if (control == null || typeof(control.type) == 'undefined' || control.type == null) continue;
        if (control.type.toLowerCase() != 'text') continue;
        if (inputCtrlNamePrefix != null && control.name.indexOf(inputCtrlNamePrefix) == -1) continue;

        //alert("To trim " + control.name);
        control.value = trimString(control.value);
    }
}

function isEmail(elm) {
    var email;
    if (elm.value == null) { //it's just a variable
        elm = trimString(elm);
        email = elm;
    } else { //it's a control
        elm.value = trimString(elm.value);
        email = elm.value;
    }

    if (email.length == 0) return true;

    var atIndex       = email.indexOf("@");
    var firstDotIndex = email.indexOf(".");
    if (atIndex == -1 || atIndex == 0 || atIndex == (email.length-1)) return false;
    if (firstDotIndex == -1 || firstDotIndex == (email.length-1)) return false;
    return true;
}

function truncateTextField(elm, maxBytes, alertMsg) {
    elm.value = getTruncatedTextField(elm.value, maxBytes, alertMsg);
}

function getTruncatedTextField(rawString, maxBytes, alertMsg) {
    var value = trimString(rawString);
    value = value.replace(/\r\n/g,"\n");
    var n;
    for(n=0; (n < value.length && maxBytes > 0); n++) {
        var c=value.charCodeAt(n);
        if (c<128) { // 0-127 => 1byte
            maxBytes -= 1;
        } else if((c>127) && (c<2048)) { // 128 -  2047 => 2byte
            maxBytes -= 2;
        } else { // 2048 - 66536 => 3byte
            maxBytes -= 3;
        }
    }

    if ((maxBytes == 0 && (n < value.length)) || maxBytes < 0) {
        if (alertMsg != null) alert(alertMsg);
    }

    value = (maxBytes < 0 && n > 1) ? value.substring(0, n-1) : value.substring(0, n);
    return value;
}

function getControlValue(control) {
    if (control.type == 'checkbox' || control.type == 'radio') {
        //alert(control.type + "/" + control.name + "/" + control.value + " = " + control.checked);
        return control.checked;
    } else if (control.type == 'select-multiple') {
        var out = "";
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].selected) {
                out += "|" + control.options[i].value;
            }
        }
        return out;
    } else if (control.type == 'select-one') {
        return control.options.selectedIndex;
    } else {
        return trimString(control.value);
    }
}

function HtmlCtrl(ctrl, value) {
    this.ctrl = ctrl;
    this.defaultValue = value;
}

function getHtmlCtrls(form, elmNamePrefix, toCheckHidden, toCheckMultipleSelect) {
    var pairs = new Array();
    for(var i=0; i< form.elements.length; i++) {
        var control = form.elements[i];
        if (elmNamePrefix != null && control.name.indexOf(elmNamePrefix) == -1) continue;
        if (control.type == 'submit') continue;
        if (control.type == 'hidden'         && (toCheckHidden         == null || toCheckHidden         == false)) continue;
        if (control.type == 'select-multiple'&& (toCheckMultipleSelect == null || toCheckMultipleSelect == false)) continue;
        pairs[pairs.length] = new HtmlCtrl(control, getControlValue(control));
        //alert(control.name + "/" + control.value + "-" + getControlValue(control));
    }
    return pairs;
}

function restoreHtmlCtrls(htmlCtrls) {
    if (htmlCtrls == null || htmlCtrls.length == 0) return;
    for (var i = 0; i < htmlCtrls.length; i++) {
        //alert(htmlCtrls[i].ctrl.name + "/" + htmlCtrls[i].ctrl.value + "-" + htmlCtrls[i].ctrl.value);
        restoreHtmlCtrl(htmlCtrls[i]);
    }
}

function getStringTokens(delim, str) {
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

function restoreHtmlCtrl(htmlCtrl) {
    if (htmlCtrl.ctrl.type == 'checkbox' || htmlCtrl.ctrl.type == 'radio') {
        htmlCtrl.ctrl.checked = htmlCtrl.defaultValue;
    } else if (htmlCtrl.ctrl.type == 'select-multiple') {
        //alert("htmlCtrl.defaultValue = " + htmlCtrl.defaultValue);
        var defaultValues  = getStringTokens("|", htmlCtrl.defaultValue);
        var isCheckedArray = newArray(defaultValues.length, false);
        for(var i=0; i< htmlCtrl.ctrl.options.length; i++) {
            var isFound = false;
            if (defaultValues != null && defaultValues.length > 0) {
                for (var j = 0; j < defaultValues.length; j++) {
                    if (isCheckedArray[j] == true) continue;
                    if (htmlCtrl.ctrl.options[i].value != defaultValues[j]) continue;
                    htmlCtrl.ctrl.options[i].selected = true;
                    isCheckedArray[j] = true;
                    isFound = true;
                    break;
                }
            }
            if (isFound == false) htmlCtrl.ctrl.options[i].selected = false;
        }
    } else if (htmlCtrl.ctrl.type == 'select-one') {
        htmlCtrl.ctrl.options.selectedIndex = htmlCtrl.defaultValue;
    } else {
        htmlCtrl.ctrl.value = htmlCtrl.defaultValue;
    }
}
function calcBar(Rx, Gx, Bx, xRange, yRange, bias) {
    X = 0.412391 * Rx + 0.357584 * Gx + 0.180481 * Bx;
    Y = 0.212639 * Rx + 0.715169 * Gx + 0.072192 * Bx;
    Z = 0.019331 * Rx + 0.119195 * Gx + 0.950532 * Bx;

    x = Math.floor((X/(X+Y+Z)) * xRange) + Math.random() * bias;
    y = Math.floor((Y/(X+Y+Z)) * yRange) + Math.random() * bias;
}

function drawColors(finverse, dx, dy, stepSize, xRange, yRange, bias) {
   zindex = -1;
   for (r=1;r<=255;r+=stepSize) {
       for (g=0;g<=255;g+=stepSize) {
           for (b=0;b<=255;b+=stepSize) {
               calcBar(r,g,b,xRange,yRange,bias);
               document.write("<div style='position:absolute;"
                   + "width:8px;height:6px;z-index:" + zindex + ";"
                   + "background-color:rgb("+r+","+g+","+b+");"
                   + "border:1px solid #808080;"
                   + "left:" + (finverse?(1000 - (x+dx)):(x+dx)) + ";top:" + (y+dy) + "'></div>");
           }
       }
   }
}

function checkEmailAddr(elm, fCanEmpty) {
    var email;
    if (elm.value == null) { //it's just a variable
        elm = trimString(elm);
        email = elm;
    } else { //it's a control
        elm.value = trimString(elm.value);
        email = elm.value;
    }

    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && email.length == 0) return true;

    var data = email.match(/^\S+@\S+\.\S+$/);
    if (!data || !email) return false;
    return true;
}

function forceNumber() { //only for IE 4.0x, 5.0x, 6.0
    var c = String.fromCharCode( event.keyCode );
    if ("0123456789".indexOf(c, 0) < 0) return false;
    return true;
}

function checkHTTPUrl(dstText, fCanEmpty, fCanSSL) {
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && dstText.length == 0) return true;
    dstText = dstText.toLowerCase();
    if (fCanSSL != null && fCanSSL == true) {
        if (fCanEmpty == true && (dstText == "http://" || dstText == "https://")) return true;
        var data1 = dstText.match(/(http|https):\/\/.+/);
        if (!data1 || !dstText) return false;
    } else {
        if (fCanEmpty == true && dstText == "http://") return true;
        var data2 = dstText.match(/http:\/\/.+/);
        if (!data2 || !dstText) return false;
    }
    return true;
}

function checkIP(dstText, fCanEmpty) {
    if (fCanEmpty == null) fCanEmpty = true;
    if (fCanEmpty == true && dstText.length == 0) return true;
    var data = dstText.match(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/);
    if (!data || !dstText) return false;
    var values = getStringTokens(".", dstText);
    for (var i = 0; i < values.length; i++) {
        var chkee = new Number(values[i]);
        if (chkee <= 0 || chkee > 255) return false;
    }
    return true;
}

function isBlankString(testStr) {
    if (testStr.length == 0) return true;
    var result = testStr.match(/[^ ]/g);
    if (result || !testStr) return false;
    return true;
}

function isFormDirty(form, elmNamePrefix, toCheckHidden, toCheckMultipleSelect) {
    var nonDirtyCtrl = null;
    for(var i=0; i<form.elements.length; i++) {
        var control = form.elements[i];
        if (typeof(control.type) == 'undefined' || control.type == null) continue;
        if (elmNamePrefix != null && control.name.indexOf(elmNamePrefix) == -1) continue;
        if (control.type == 'submit') { continue; }
        if (control.type == 'hidden'         && (toCheckHidden == null || toCheckHidden == false)) continue;
        if (control.type == 'select-multiple'&& (toCheckMultipleSelect == null || toCheckMultipleSelect == false)) continue;
        var crntValue    = getControlValue(control);
        var defaultValue = getControlDefaultValue(control);
        if (crntValue != defaultValue) {
            return true;
        }
        if (nonDirtyCtrl == null) nonDirtyCtrl = control;
    }
    if (nonDirtyCtrl != null) nonDirtyCtrl.focus();
    return false;
}

function getControlValue(control) {
    if (control.type == 'checkbox' || control.type == 'radio') {
        //alert(control.type + "/" + control.name + "/" + control.value + " = " + control.checked);
        return control.checked;
    } else if (control.type == 'select-multiple') {
        var out = "";
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].selected) {
                out += "|" + control.options[i].value;
            }
        }
        return out;
    } else if (control.type == 'select-one') {
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].selected) {
                return control.options[i].value;
            }
        }
        return control.options[0].value;
    } else {
        return trimString(control.value);
    }
}

function getControlText(control) {
    if (control.type == 'checkbox' || control.type == 'radio') {
        //alert(control.type + "/" + control.name + "/" + control.value + " = " + control.checked);
        return control.checked;
    } else if (control.type == 'select-multiple') {
        var out = "";
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].selected) {
                out += "|" + control.options[i].text;
            }
        }
        return out;
    } else if (control.type == 'select-one') {
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].selected) {
                return control.options[i].text;
            }
        }
        return control.options[0].value;
    } else {
        return trimString(control.value);
    }
}

function getControlDefaultValue(control) {
    if (control.type == 'checkbox' || control.type == 'radio') {
        return control.defaultChecked;
    } else if (control.type == 'select-multiple') {
        var out = "";
        for(i=0; i<control.options.length; i++) {
            if (control.options[i].defaultSelected) {
                out += "|" + control.options[i].value;
            }
        }
        return out;
    } else if (control.type == 'select-one') {
        for(var i=0; i<control.options.length; i++) {
            if (control.options[i].defaultSelected) {
                return control.options[i].value;
            }
        }
        return control.options[0].value;
    } else {
        return trimString(control.defaultValue);
    }
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

function checkTaiwanZipCode(dstText) {
    var chkee = dstText.match(/^\d{3}$|^\d{5}$/);
    if (!chkee || !dstText) return false;
    return true;
}
function checkEmail(emailStr) {
   if (emailStr.length == 0) {
       return true;
   }
   var emailPat=/^(.+)@(.+)$/;
   var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
   var validChars="\[^\\s" + specialChars + "\]";
   var quotedUser="(\"[^\"]*\")";
   var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
   var atom=validChars + '+';
   var word="(" + atom + "|" + quotedUser + ")";
   var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
   var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
   var matchArray=emailStr.match(emailPat);
   if (matchArray == null) {
       return false;
   }
   var user=matchArray[1];
   var domain=matchArray[2];
   if (user.match(userPat) == null) {
       return false;
   }
   var IPArray = domain.match(ipDomainPat);
   if (IPArray != null) {
       for (var i = 1; i <= 4; i++) {
          if (IPArray[i] > 255) {
             return false;
          }
       }
       return true;
   }
   var domainArray=domain.match(domainPat);
   if (domainArray == null) {
       return false;
   }
   var atomPat=new RegExp(atom,"g");
   var domArr=domain.match(atomPat);
   var len=domArr.length;
   if ((domArr[domArr.length-1].length < 2) ||
       (domArr[domArr.length-1].length > 3)) {
       return false;
   }
   if (len < 2) {
       return false;
   }
   return true;
}

/**
 * equals trim funtion in java
 */
function trim(str){
   return str.replace(/^\s*|\s*$/g,"");
}

/**
 * equals endWith function in java
 */
function endWith(str, str2) {
	if (str.lastIndexOf(str2) == (str.length - str2.length)) {
		return true;
	}
	return false;
}

/**
 * check textArea maxlength. example:
 * <textarea maxlength="1500" onkeyup="return ismaxlength(this)"
 * cols="32" rows="5" name="logNotes" id="logNotes">
 */
function ismaxlength(obj){
var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
if (obj.getAttribute && getStringLengthByBytes(obj.value)>mlength)
obj.value=leftStringByBytes(obj.value,mlength)
}

/**
 * return string bytes count
 */
function getStringLengthByBytes(str) {
    value = trimString(str);
    if (value.length == 0) {
    	return 0;
    }
    intLength = 0;
    for(n=0; n < value.length; n++) {
        var c=value.charCodeAt(n);

        if (c<128) { // 0-127 => 1byte
            intLength += 1;
        } else if((c>127) && (c<2048)) { // 127 -  2047 => 2byte
            intLength += 2;
        } else { // 2048 - 66536 => 3byte
            intLength += 3;
        }
    }
    return intLength;
}

/**
 * return left string bytes
 */
function leftStringByBytes(str, len) {
    value = trimString(str);
	if (value.length < len || len < 0) {
		return value;
	}
	intLength = 0;
	var rtnString = "";
    for(n=0; (n < value.length && intLength < len); n++) {
        var c=value.charCodeAt(n);
       	rtnString += value.charAt(n);
        if (c<128) { // 0-127 => 1byte
            intLength += 1;
        } else if((c>127) && (c<2048)) { // 127 -  2047 => 2byte
            intLength += 2;
        } else { // 2048 - 66536 => 3byte
            intLength += 3;
        }
    }
	return rtnString;
}

/**
 * Add option to SELECT object
 */
function optionAddChildNode(obj, value, display) {
	//duplicate
    var len=obj.length;
    for(var i=0;i<len;i++)
    {
       if (obj.options[i].value==value) {
       		return;
       }
    }

	//add
	obj.options.add(new Option(display, value))
}

/**
 * Remove options from SELECT object
 */
function optionRemoveChildNodes(obj)
{
    var len=obj.length;
    for(var i=len-1;i>=0;i--)
    {
        if(obj.options[i].selected)
        {
            obj.remove(i);
        }
    }
}

/**
 * Select all options on SELECT object
 */
function optionSelectAll(obj)
{
    var len=obj.length;
    for(var i=0;i<len;i++)
    {
        obj.options[i].selected=true;
    }
}

function optionUnSelectAll(obj)
{
    var len=obj.length;
    for(var i=0;i<len;i++)
    {
        obj.options[i].selected=false;
    }
}



/**
 * Remove all options from SELECT object
 */
function optionClearListObj(obj)
{
    var len=obj.length;
    for(var i=0;i<len;i++) obj.remove(0);
}

/**
 * Remove options from SELECT object when user click 'del' key. example:
 * <SELECT NAME="targetSelect" size="10" style="WIDTH: 12em" multiple
 * onkeydown="optionDelete(this)">
 */
function optionDelete(obj) {
	var ev = window.event
	if (ev.keyCode == 46) {
		optionRemoveChildNodes(obj);
		obj.focus();
	}
}

function optionMove(source, target) {
	optionCopy(source, target);
	optionRemoveChildNodes(source);
}

function optionCopy(source, target) {
	var values = getStringTokens("|", getControlValue(source));
	var labels = getStringTokens("|", getControlText(source));
	for (var i=1; i<values.length; i++) {
		optionAddChildNode(target, values[i], labels[i]);
	}
}


/**
 * get object position
 */
function getAbsolutePos(el) {
	var r = { x: el.offsetLeft, y: el.offsetTop };
	if (el.offsetParent) {
		var tmp = getAbsolutePos(el.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y;
	}
	return r;
}

/**
 * get object width
 */
function getWidth(el) {
	return el.offsetWidth
}

/**
 * get object height
 */
function getHeight(el) {
	return el.offsetHeight
}

/**
 * show object tips. example:
 * <div id="tips" style="background-color: #FFFF00;border: 1 solid #000000;position: absolute;visibility: hidden"></div>
 *
 * <SELECT NAME="targetSelect" size="10" style="WIDTH: 12em" multiple
 * onClick="hideTips();" onkeydown="hideTips()"
 * onmouseover="showTips(this, 'test tips', 'middle', 20)" onmouseout="hideTips()">
 */
function showTips(el, value) {
	showTips(el, value, "left", 0);
}

/**
 * show object tips.
 */
function showTips(el, value, align, width) {
    var tip=ns6? document.getElementById("tips") : document.all.tips
    writeToDiv(tip, value)
    if (width == null || width == 0) {
    	width = getWidth(el)
    }
	tip.style.width = width
    var p = getAbsolutePos(el)
	tip.style.left = p.x
    if (align == null) {
    	align = "left"
    }
    if (align.toLowerCase() == "right") {
		tip.style.left = p.x + getWidth(el) - tip.offsetWidth
	}
    if (align.toLowerCase() == "middle" || align.toLowerCase() == "center") {
		tip.style.left = p.x - (tip.offsetWidth - getWidth(el))/2
	}
	tip.style.top = p.y + getHeight(el)
	if (ie4||ns6)
		tip.style.visibility="visible"
	else if (ns4)
		document.tips.visibility="show"
}

/**
 * show object tips.
 */
function hideTips() {
    var tip=ns6? document.getElementById("tips") : document.all.tips
	if (ie4||ns6)
		tip.style.visibility = "hidden"
	else if (ns4)
		document.tips.visibility="hide"
}

/**
 * set cookie
 */
function setCookie (name, value) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) +
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "");
}

/**
 * delete cookie by name
 */
function deleteCookie (name) {
    var exp = new Date();
    exp.setTime (exp.getTime() - 1);
    var cval = getCookie(name);
    document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}

/**
 * clear cookie which name starts with prefix
 */
function clearCookieStartWith(prefix) {
    var temp=document.cookie.split(";");
    var loop3;
    var ts;
    for (loop3 = 0; loop3 < temp.length;loop3++) {
        ts=temp[loop3].split("=")[0];
        if (ts.indexOf(prefix) == 1) {
   			deleteCookie(ts);
        }
    }
}

/**
 * clear cookie
 */
function clearCookie() {
    var temp=document.cookie.split(";");
    var loop3;
    var ts;
    for (loop3=0;loop3<temp.length;loop3++) {
        ts=temp[loop3].split("=")[0];
        deleteCookie(ts);
    }
}
/**
 * get cookie value by offset
 */
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}
/**
 * get cookie value
 */
function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
			if (i == 0) break;
	}
	return null;
}
/**
 * check cookie support
 */
function isCookieEnabled() {
	setCookie("isCookieEnabled", "enabled");
	var value = getCookie("isCookieEnabled");
	if (value != null && value == "enabled") {
		return true;
	}
	return false;
}

function up(obj) {
  var currerntText;
  var nextText;
  var currerntValue;
  var nextValue;

  if(obj.options[obj.options.selectedIndex].index > 0)
  {
    currerntText = obj.options[obj.options.selectedIndex].text;
    nextText = obj.options[obj.options[obj.options.selectedIndex].index-1].text;
    obj.options[obj.options.selectedIndex].text =  nextText;
    obj.options[obj.options[obj.options.selectedIndex].index-1].text = currerntText;

    currerntValue = obj.options[obj.options.selectedIndex].value;
    nextValue = obj.options[obj.options[obj.options.selectedIndex].index-1].value;
    obj.options[obj.options.selectedIndex].value =  nextValue;
    obj.options[obj.options[obj.options.selectedIndex].index-1].value = currerntValue;

    self.focus();
    obj.options.selectedIndex--;
  }
}

function down(obj) {
  var currerntText;
  var nextText;
  var currerntValue;
  var nextValue;
  if(obj.options[obj.options.selectedIndex].index != obj.length-1)
  {
    currerntText = obj.options[obj.options.selectedIndex].text;
    nextText = obj.options[obj.options[obj.options.selectedIndex].index+1].text;
    obj.options[obj.options.selectedIndex].text =  nextText;
    obj.options[obj.options[obj.options.selectedIndex].index+1].text = currerntText;

    currerntValue = obj.options[obj.options.selectedIndex].value;
    nextValue = obj.options[obj.options[obj.options.selectedIndex].index+1].value;
    obj.options[obj.options.selectedIndex].value =  nextValue;
    obj.options[obj.options[obj.options.selectedIndex].index+1].value = currerntValue;

    self.focus();
    obj.options.selectedIndex++;
  }
}

function checkTextFieldNotEmpty(elm, emptyAlertMsg) {
    var value = trimString(elm.value);
    if (value.length == 0) {
    	alert(emptyAlertMsg);
        elm.focus();
        return false;
    } else {
    	return true;
    }
}

function showIsoColck() {
	document.write('<font  id="calendarClock"  >  </font>&nbsp;');
	setInterval('isodatetime()',1000);

}
//don't call isodatetime function directly. try showisotime function please.
function isodatetime() {
	var today = new Date();
	var year  = today.getYear();
	var month = today.getMonth() + 1;
	var day  = today.getDate();
	var hour = today.getHours();
	var hourUTC = today.getUTCHours();
	var diff = hour - hourUTC;
	var hourdifference = Math.abs(diff);
	var minute = today.getMinutes();
	var minuteUTC = today.getUTCMinutes();
	var minutedifference;
	var second = today.getSeconds();
	var timezone;
	if (year < 2000) {
		year = year + 1900;
	}
	if (minute != minuteUTC && minuteUTC < 30 && diff < 0) {
		hourdifference--;
	}
	if (minute != minuteUTC && minuteUTC > 30 && diff > 0) {
		hourdifference--;
	}
	if (minute != minuteUTC) {
		minutedifference = ":30";
	} else {
		minutedifference = ":00";
	}
	if (hourdifference < 10) {
		timezone = "0" + hourdifference + minutedifference;
	} else {
		timezone = "" + hourdifference + minutedifference;
	}
	if (diff < 0) {
		timezone = "-" + timezone;
	} else {
		timezone = "+" + timezone;
	}
	if (month <= 9) month = "0" + month;
	if (day <= 9) day = "0" + day;
	if (hour <= 9) hour = "0" + hour;
	if (minute <= 9) minute = "0" + minute;
	if (second <= 9) second = "0" + second;
	time = year + "/" + month + "/" + day + "&nbsp;"
		+ hour + ":" + minute + ":" + second + "&nbsp;&nbsp;(GMT " + timezone + ")";
	document.all.calendarClock.innerHTML = time;
}

