/**
 * <P>数据校验程序包</P>
 * 
 * @version 1.0
 * @author LiuJian
 */
 
/**
 * <P>数据重复校验函数</P>
 * <P>参数函数名：repeat；定义：(列名,提示内容)</P>
 * 
 * @param form
 * @return Boolean 成功 - true；失败 - false
 * 
 */
function validRepeat(form) {
    var intErr = 0;
    var intTemp;
    var strValue, strTemp;
    var bErr;
    var bValid = true;
	var regexpMask = RegExp("\\w+","g");
   	var fieldRowstate = form.rowstate;
    var focusField = null;
	var arrResult;
   	var arrayField = new Array();
    var arrayErr = new Array();
	oParameter = new repeat();
    for (x in oParameter) {
		intTemp = 0;
		while ((arrResult = regexpMask.exec(oParameter[x][0])) != null) {
			arrayField[intTemp++] = form[arrResult];
		}
    	if (arrayField[0].length == null) {return bValid;}
    	for (var intFor = 0; intFor < arrayField[0].length - 1;intFor++) {
    		if (fieldRowstate[intFor].value == "0" || 
    				fieldRowstate[intFor].value == "1" || fieldRowstate[intFor].value =="4"){
        		strValue = "";
            	for (var intTemp = 0; intTemp < arrayField.length; intTemp++) {
        			strValue = strValue + arrayField[intTemp][intFor].value;
				}
            	for (var intRow = intFor + 1; intRow < arrayField[0].length;intRow++) {
                	strTemp = "";
                	for (var intTemp = 0; intTemp < arrayField.length; intTemp++) {
            			strTemp = strTemp + arrayField[intTemp][intRow].value;
					}
	       	        if (strValue == strTemp && (fieldRowstate[intRow].value == "0" || 
	       	        		fieldRowstate[intRow].value == "1" || fieldRowstate[intRow].value =="4")) {
	                    if (intErr == 0) {
   		                	for (var intTemp = 0; intTemp < arrayField.length; intTemp++) {
			        			if (arrayField[intTemp][intRow].type != "hidden") {
        	    	            	focusField = document.getElementById("DynData").childNodes(intRow).all(arrayField[intTemp][intRow].name);
									break;
			        			}
							}
	        	        }
	            	    arrayErr[intErr++] = oParameter[x][1];
    	            	bValid = false;
    	       	    }
    			}
    		}
    	}
    }
	//错误提示，焦点设置
    if (arrayErr.length > 0) {
       alert(arrayErr.join('\n'));
       focusField.focus();
    }
    return bValid;
}

/**
 * <P>数据长度校验函数</P>
 * <P>参数函数名：longness；定义：(列名,提示内容,要求,长度)</P>
 * <P>要求--等于：1；大于：2；小于：3</P>
 * <P>长度为0，则取第一个有效字段的数据长度为标准长度</P>
 * 
 * @param form
 * @return Boolean 成功 - true；失败 - false
 * 
 */
function validLongness(form) {
	var intLength;
    var intErr = 0;
	var intRow=0;
	var intTask;
    var strValue;
    var bErr;
   	var bLength;
    var bValid = true;
   	var field;
	var fieldTemp;
   	var fieldRowstate = form.rowstate;
    var focusField = null;
    var arrayErr = new Array();
	oParameter = new longness();
    for (x in oParameter) {
    	fieldTemp = form[oParameter[x][0]];
    	intTask = oParameter[x][2];
		intLength = oParameter[x][3];
    	field = fieldTemp;
		intRow=0;
	   	bLength = false;
   		bLength=(fieldTemp.length!=null);
		do{
			if (bLength) {
				field = fieldTemp[intRow];
				fieldRowstate = form.rowstate[intRow];
			}
			if (document.getElementById("DynData") == null || document.getElementById("DynData").all(field.name) == null || 
					fieldRowstate.value == "0" || fieldRowstate.value == "1" || fieldRowstate.value =="4"){
				strValue = field.value;
				//取第一个有效长度
				if (intLength == 0) {
					intLength = strValue.length;
				}
				//记录长度校验
    	        bErr = false;
                if (intTask == 1 && strValue.length != intLength){bErr = true;}
                if (intTask == 2 && (strValue.length < intLength)){bErr = true;}
                if (intTask == 3 && (strValue.length > intLength)){bErr = true;}
       	        if (bErr) {
                    if (intErr == 0) {
                	    focusField = field;
            	        if (bLength) {
        	            	focusField = document.getElementById("DynData").childNodes(intRow).all(field.name);
    	                }
	                }
	                arrayErr[intErr++] = oParameter[x][1];
    	            bValid = false;
	       	    }
			}
			intRow++;
		} while (bLength && intRow!=fieldTemp.length)
	}
	//错误提示，焦点设置
    if (arrayErr.length > 0) {
       alert(arrayErr.join('\n'));
       focusField.focus();
    }
    return bValid;
}

/**
 * <P>数据校验函数</P>
 * <P>必录参数函数名：required；定义：(列名,提示内容)</P>
 * <P>有效参数函数名：masks；定义：(列名,提示内容,规则)</P>
 * <P>浮点参数函数名：floats；定义：(列名,提示内容)</P>
 * <P>日期参数函数名：dates；定义：(列名,提示内容)</P>
 * <P>比较参数函数名：compares；定义：(列名,提示内容,参数)</P>
 * 
 * @param form
 * @param int 任务标志--必录：1；有效：2；浮点数：3；日期：4；
 * @param				等于：5；大于：6；小于：7
 * @return Boolean 成功 - true；失败 - false
 * 
 */
function validField(form,intTask) {
    var intErr = 0;
	var intRow;
	var strMask;
    var strValue;
    var bErr;
   	var bLength;
	var bValid = true;
	var field;
	var fieldTemp;
	var fieldRowstate= form.rowstate;
    var focusField = null;
    var arrayErr = new Array();
    if (intTask == 1){oParameter = new required();}
    if (intTask == 2){oParameter = new masks();}
    if (intTask == 3){oParameter = new floats();}
    if (intTask == 4){oParameter = new dates();}
    if (intTask == 5 || intTask == 6 || intTask == 7 ){oParameter = new compares();}
    for (x in oParameter) {
    	if (intTask == 2){strMask = oParameter[x][2];}
	    if (intTask == 5 || intTask == 6 || intTask == 7 ){strMask = oParameter[x][2];}
    	fieldTemp = form[oParameter[x][0]];
    	
    	if(fieldTemp==null){return true;}
    	
    	if(fieldTemp==null){
    		return true;
    	}
    	field = fieldTemp;
		intRow=0;
   		bLength=(fieldTemp.length!=null);
		do{
			if (bLength) {
				field = fieldTemp[intRow];
				fieldRowstate = form.rowstate[intRow];
			}
			if (document.getElementById("DynData") == null || 
					document.getElementById("DynData").all(field.name) == null || 
						fieldRowstate.value=="1" || fieldRowstate.value=="4") {
            	if (field.type == 'text' || field.type == 'textarea' || field.type == 'radio' || 
    		            	field.type == 'select-one' || field.type == 'password') {
					if (field.type == "select-one") {
						var si = field.selectedIndex;
						strValue = field.options[si].value;
					} else {
						strValue = trim(field.value);
					}
        	        bErr = false;
	                if (intTask == 1 && strValue == ""){bErr = true;}
	                if (intTask == 2 && strValue.match(strMask) == null){bErr = true;}
	                if (intTask == 3 && (strValue = validfloat(strValue)) == "error"){bErr = true;}
	                if (intTask == 4 && (strValue = validdate(strValue)) == "error"){bErr = true;}
	                if (intTask == 5 && strValue != strMask){bErr = true;}
	                if (intTask == 6 && strValue <= strMask){bErr = true;}
	                if (intTask == 7 && strValue >= strMask){bErr = true;}
        	        if (bErr) {
                        if (intErr == 0) {
                    	    focusField = field;
                	        if (bLength) {
            	            	focusField = document.getElementById("DynData").childNodes(intRow).all(field.name);
        	                }
    	                }
		                arrayErr[intErr++] = oParameter[x][1];
        	            bValid = false;
            	    } else {
						field.value = strValue;
            	    }
            	}
        	}
			intRow++;
		} while (bLength && intRow!=fieldTemp.length)
    }
    if (arrayErr.length > 0) {
       alert(arrayErr.join('\n'));
       focusField.focus();
    }
    return bValid;
}

/**
 * <P>浮点校验及格式化函数</P>
 * <P>浮点数每隔三位加逗号，小数点后两位</P>
 * 
 * @param String 被校验字符串
 * @return String 成功 - 格式化浮点数字符串；空 - ""；失败 - error
 * 
 */
function validfloat(strValue) {
	var strReturn = "";
	var intStart;
    var bErr = false;
	var regexpMask = RegExp("^[\\d\.\,\-]*$");
	var arrResult;

	strValue = trim(strValue);
	//检验空字符
	if (strValue == ""){ return "" }
	//检验非法字符
	if (regexpMask.exec(strValue) == null){ bErr = true }
	//过滤逗号分隔符
	regexpMask = RegExp("[\\d\.\-]+","g");
	while (bErr == false && (arrResult = regexpMask.exec(strValue)) != null) {
		strReturn = strReturn + arrResult;
	}
	//校验非法负号和小数点
	strValue = parseFloat(strReturn);
	if (bErr == false && (strReturn != strValue || strValue > 100000000000000 || strValue < -100000000000000)){ bErr = true }
	//数据非法返回
	if (bErr == true){ return "error" }
	//4舍5入
	intStart = 0.005;
	if (strReturn.substring(0,1) == "-" ){ intStart = -0.005 }
	strReturn = (parseFloat(strReturn) + intStart) + ""
	//小数点后补0
	intStart = strReturn.indexOf(".");
	strValue = ".00";
	if (intStart != -1) { 
		strValue = strReturn.substring(intStart,strReturn.length) + "00";
		strValue = strValue.substring(0,3);
		strReturn = strReturn.substring(0,intStart)
	}
	//加逗号分隔符
	while (strReturn.length >= 3) {
		if (strValue.substring(0,1) != "."){ strValue = "," + strValue }
		strValue = strReturn.substring(strReturn.length - 3,strReturn.length) +  strValue;
		strReturn = strReturn.substring(0,strReturn.length - 3)
	}
	if (strReturn != "-" && strReturn != "" && strReturn != "0" && strValue.substring(0,1) != ".") {
		strReturn = strReturn + ",";
	}
	strValue = strReturn + strValue;
	return strValue;
}

/**
 * <P>日期校验及格式化函数</P>
 * <P>日期格式yyyy-mm-nn</P>
 * 
 * @param String 被校验字符串
 * @return String 成功 - 格式化日期字符串；空 - ""；失败 - error
 * 
 */
function validdate(strDate) {
	var intFor = 0;
	var strYear, strMonth, strDay;
    var bErr = false;
	var regexpMask = RegExp("\\d+","g");
	var arrResult;
    var arrayDate = new Array();
	strDate = trim(strDate);
	while ((arrResult = regexpMask.exec(strDate)) != null) {
		arrayDate[intFor++] = arrResult;
	}
    if (arrayDate.length != 3) { bErr = true }
	strYear = arrayDate[0];
	strMonth = arrayDate[1];
	strDay = arrayDate[2];
    if (strYear < 100) { 
		strYear = arrayDate[2];
		strMonth = arrayDate[0];
		strDay = arrayDate[1];
    }
	if (strMonth < 1 || strMonth > 12) { bErr = true }
	if (strDay < 1 || strDay > 31) { bErr = true }
	if ((strMonth == 4 || strMonth == 6 || strMonth == 9 || strMonth == 11) && (strDay == 31)) { bErr = true }
	if (strMonth == 2) {
		if (strDay > 29) { bErr = true }
		if ((strYear % 100 == 0 && strYear % 400 != 0 || strYear % 4 != 0) && strDay == 29) { bErr = true }
	}
	if (strYear < 1900 || strYear > 3000) { bErr = true }
	strMonth = "00" + strMonth;
	strMonth = strMonth.substring(strMonth.length - 2,strMonth.length);
	strDay = "00" + strDay;
	strDay = strDay.substring(strDay.length - 2,strDay.length)
	strDate = strYear +"-"+ strMonth +"-"+ strDay;
	if (bErr) {return "error"}
    return strDate;
}

//缩空函数
/**
 * <P>缩空函数</P>
 * <P>删除输入字符串两端的空格</P>
 * 
 * @param String
 * @return String
 * 
 */
function trim(strValue) {
	var strReturn = "";
	var intStart = -1;
	var regexpMask = RegExp("\\S+","g");
	var arrResult;
	while ((arrResult = regexpMask.exec(strValue)) != null) {
		if (intStart == -1) {intStart = arrResult.index}
		strReturn = strValue.substring(intStart,arrResult.lastIndex);
	}
	return strReturn;
}
