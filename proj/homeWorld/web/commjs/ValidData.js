/**
 * <P>����У������</P>
 * 
 * @version 1.0
 * @author LiuJian
 */
 
/**
 * <P>�����ظ�У�麯��</P>
 * <P>������������repeat�����壺(����,��ʾ����)</P>
 * 
 * @param form
 * @return Boolean �ɹ� - true��ʧ�� - false
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
	//������ʾ����������
    if (arrayErr.length > 0) {
       alert(arrayErr.join('\n'));
       focusField.focus();
    }
    return bValid;
}

/**
 * <P>���ݳ���У�麯��</P>
 * <P>������������longness�����壺(����,��ʾ����,Ҫ��,����)</P>
 * <P>Ҫ��--���ڣ�1�����ڣ�2��С�ڣ�3</P>
 * <P>����Ϊ0����ȡ��һ����Ч�ֶε����ݳ���Ϊ��׼����</P>
 * 
 * @param form
 * @return Boolean �ɹ� - true��ʧ�� - false
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
				//ȡ��һ����Ч����
				if (intLength == 0) {
					intLength = strValue.length;
				}
				//��¼����У��
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
	//������ʾ����������
    if (arrayErr.length > 0) {
       alert(arrayErr.join('\n'));
       focusField.focus();
    }
    return bValid;
}

/**
 * <P>����У�麯��</P>
 * <P>��¼������������required�����壺(����,��ʾ����)</P>
 * <P>��Ч������������masks�����壺(����,��ʾ����,����)</P>
 * <P>���������������floats�����壺(����,��ʾ����)</P>
 * <P>���ڲ�����������dates�����壺(����,��ʾ����)</P>
 * <P>�Ƚϲ�����������compares�����壺(����,��ʾ����,����)</P>
 * 
 * @param form
 * @param int �����־--��¼��1����Ч��2����������3�����ڣ�4��
 * @param				���ڣ�5�����ڣ�6��С�ڣ�7
 * @return Boolean �ɹ� - true��ʧ�� - false
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
 * <P>����У�鼰��ʽ������</P>
 * <P>������ÿ����λ�Ӷ��ţ�С�������λ</P>
 * 
 * @param String ��У���ַ���
 * @return String �ɹ� - ��ʽ���������ַ������� - ""��ʧ�� - error
 * 
 */
function validfloat(strValue) {
	var strReturn = "";
	var intStart;
    var bErr = false;
	var regexpMask = RegExp("^[\\d\.\,\-]*$");
	var arrResult;

	strValue = trim(strValue);
	//������ַ�
	if (strValue == ""){ return "" }
	//����Ƿ��ַ�
	if (regexpMask.exec(strValue) == null){ bErr = true }
	//���˶��ŷָ���
	regexpMask = RegExp("[\\d\.\-]+","g");
	while (bErr == false && (arrResult = regexpMask.exec(strValue)) != null) {
		strReturn = strReturn + arrResult;
	}
	//У��Ƿ����ź�С����
	strValue = parseFloat(strReturn);
	if (bErr == false && (strReturn != strValue || strValue > 100000000000000 || strValue < -100000000000000)){ bErr = true }
	//���ݷǷ�����
	if (bErr == true){ return "error" }
	//4��5��
	intStart = 0.005;
	if (strReturn.substring(0,1) == "-" ){ intStart = -0.005 }
	strReturn = (parseFloat(strReturn) + intStart) + ""
	//С�����0
	intStart = strReturn.indexOf(".");
	strValue = ".00";
	if (intStart != -1) { 
		strValue = strReturn.substring(intStart,strReturn.length) + "00";
		strValue = strValue.substring(0,3);
		strReturn = strReturn.substring(0,intStart)
	}
	//�Ӷ��ŷָ���
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
 * <P>����У�鼰��ʽ������</P>
 * <P>���ڸ�ʽyyyy-mm-nn</P>
 * 
 * @param String ��У���ַ���
 * @return String �ɹ� - ��ʽ�������ַ������� - ""��ʧ�� - error
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

//���պ���
/**
 * <P>���պ���</P>
 * <P>ɾ�������ַ������˵Ŀո�</P>
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
