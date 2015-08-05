/**
 * <P>FORM������</P>
 * 
 * @version 1.0
 * @author LiuJian
 */

/**
 * <P>�ύ����</P>
 * <P>���ó����֧��־�ύ��</P>
 * 
 * @param form
 * @param String�������֧��־
 * 
 */
function submitform(thisform,aflag){
	thisform.flag.value=aflag;
	validateSubmit(thisform);
}

/**
 * <P>���ı��溯��</P>
 * <P>���ݸ�����ʾ����</P>
 * 
 * @param form
 * @param String�������֧��־
 * @return int �ύ����1�����ύ����0
 * 
 */

function saveChange(thisform,aflag){
	var intRow=0;
	var intState=0;
   	var field = thisform.rowstate;
   	var freeform=thisform.freeformstate;
   	if((field==null)&&(freeform==null)){return 0;}
   	
   	if(freeform!=null) {
   		if (!(freeform.value == "0" || freeform.value == "3"  || freeform.value == "5")){
			intState=1;
		}
	}
   	if(field!=null) {   	
	   	if (thisform.rowstate.length==null) {
	   		if (!(field.value == "0" || field.value == "3"  || field.value == "5")){
				intState=1;
			}
		}
		else{
			if (thisform.currrow.value!="-1") {
				//������޸���
				do{
					field = thisform.rowstate[intRow];
					if (!(field.value == "0" || field.value == "3"  || field.value == "5")){
						intState=1;
					}
					intRow++;
				} while (intRow!=thisform.rowstate.length)
			}
		}
	}
	//��ʾ����
	if (intState!=0){
		if (confirm("��������Ѹı����ύ��")){
			thisform.flag.value=aflag;
			validateSubmit(thisform);
			return 1;
		}else{
			return -1;
		}
	}
	return 0;
}
		


/**
 * <P>�����¼����</P>
 * <P>��¼��ǰ���㵥Ԫ������λ��</P>
 * 
 * @param input
 * @param String����ǰ��
 * @param String����ǰ��
 * 
 */

function tdfocus(thiscell){
	var thisform=thiscell.form;
	if (thiscell.type != "button") { thiscell.select()};
	thisform.currrow.value=thiscell.parentNode.parentNode.rowIndex - 1;
	thisform.currcolumn.value=thiscell.name;
}
/**
 * <P>�޸ļ�¼����freeform</P>
 * <P>��¼��ǰ�޸ĵ�Ԫ��״̬��־</P>
 * <P>0��1��3��4</P>
 * 
 * @param form
 * 
 */
function freeformchange(thisform){
   	var field = thisform.freeformstate;
   	if(field==null){return;}
   	if (field.value=="0"){field.value="1";}
   	if (field.value=="3"){field.value="4";}
}

/**
 * <P>�޸ļ�¼����gread</P>
 * <P>��¼��ǰ�޸ĵ�Ԫ����״̬��־</P>
 * <P>0��1��3��4</P>
 * 
 * @param form
 * 
 */
function tdchange(thisform){
   	var field = thisform.rowstate;
	if (thisform.rowstate==null) {return;}
	if (thisform.currrow==null) {return;}
	if (thisform.currrow.value==""){thisform.currrow.value="0";}
	//if (thisform.rowstate[thisform.currrow.value]==null) {return;}
	if (thisform.rowstate.length!=null){
		field = thisform.rowstate[thisform.currrow.value];
	}
   	if (field.value=="0"){
   		field.value="1";
   	}
   	if (field.value=="3"){
   		field.value="4";
   	}
}

/**
 * <P>��ɾ������</P>
 * <P>����ǰ�еĸ��м�ɾ����־��������״̬��Ϊֻ��״̬���޸���״̬��־</P>
 * <P>0;1��2��3;4��5</P>
 * 
 * @param form
 * 
 */
function delline(thisform) {
	var tr_item, input_item;
	var intCurrindex = thisform.currrow.value;
	if (intCurrindex!="-1") {
		if (confirm("����ɾ����")){
			tr_item = document.getElementById("DynData").childNodes(intCurrindex);
	    	//��ɾ����־����ֻ��״̬
	    	for (var intFor = 0; intFor < tr_item.childNodes.length;intFor++) {
		        input_item = tr_item.childNodes(intFor).childNodes(0);
				if (input_item.type == "button" ) { 
					input_item.disabled = "true";
				} else {
					input_item.readOnly = "true";
					input_item.style.textDecoration = "line-through";
					input_item.style.backgroundColor = "red";
				}
		   	}
		   	//�޸���״̬��־
		   	var field = thisform.rowstate;
			if (thisform.rowstate.length!=null) {
				field = thisform.rowstate[intCurrindex];
			}
		   	if (field.value=="0" || field.value=="1" || field.value=="2"){
		   		field.value="2";
		   	} else {
		   		field.value="5";	   	
		   	}
	   	}
	}
}

/**
 * <P>���к���</P>
 * <P>�ڱ����β�����һ�м���ص��б�־����ID��</P>
 * <P>�б�־Ϊ3</P>
 * <P>������������fields�����壺(tdclass,����,type,size,value,readonly,class,event)</P>
 * 
 * @param form
 * 
 */
function insline(thisform){
//		var frag = document.createDocumentFragment();
//		frag.appendChild(tr);
	var tr, td, txt, strTemp, arrResult;
	var strTemp, strTdclass, strName, strType, strSize, strValue, strReadonly, strClass, strEvent;
	var regexpMask = RegExp("\<intMaxrow\>");
	var bFirst = true;
	var tbody = document.getElementById("DynData");
	var intMaxrow = 0;
	if (thisform.rowstate!=null) {
		intMaxrow = 1;
		if (thisform.rowstate.length!=null) {
			intMaxrow = thisform.rowstate.length;
		}
	}
	tr = document.createElement("tr");
    tr.setAttribute("align","center");
    oFields = new fields();
    for (x in oFields) {
		strTdclass = oFields[x][0];
		strName = oFields[x][1];
		if (tbody.childNodes(0).all(strName) != null) {
			strType = oFields[x][2];
			strSize = oFields[x][3];
			strValue = oFields[x][4];
			strReadonly = oFields[x][5];
			strClass = oFields[x][6];
			strEvent = oFields[x][7];
			while (strEvent != "" && (arrResult = regexpMask.exec(strEvent)) != null) {
				strEvent = strEvent.substring(0,arrResult.index) + intMaxrow + strEvent.substring(arrResult.lastIndex,strEvent.length);
			}
			if (strType != "hidden") {
				if ( !bFirst ) { tr.appendChild(td) }
	    		td = document.createElement("td");
		    	td.className=strTdclass;
			}
			strTemp = "<input type='"+strType+"' name='"+strName+"' size='"+strSize+"' value='"+strValue+"' class='"+strClass+"' maxlength='"+strSize+"'";
			if (strReadonly == "true") { strTemp = strTemp + "readonly " }
		    strTemp = strTemp + strEvent+">"
		    txt = document.createElement(strTemp);
		    td.appendChild(txt);
    		if (bFirst) {
			    txt = document.createElement("<input type='hidden' name='rowstate' value='3'>");
    			td.appendChild(txt);
	    		txt = document.createElement("<input type='hidden' name='rowid' value=''>");
			    td.appendChild(txt);
			    tr.appendChild(td);
			    bFirst = false;
			}
		}
	}
    tr.appendChild(td);
	if (!tbody.appendChild(tr)) {
		alert("����������֧�ֶ�̬���");
	} else {
		tbody.childNodes(intMaxrow).childNodes(0).childNodes(0).focus();
	}
}
 
 /**
 * <P>ѡ����뺯��</P>
 * <P>��������ѡ�����˵��</P>
 * 
 * @param form: this.form
 * @param String����ǰ��
 * @param String����ǰ��ǰ׺
 * @param String�������view��
 * @param String��view��title
 * @return String: �и���1���޸���0
 */
function selectone(thisform,currrow,currcol,code,cshow){
	var select,last;
	var fieldid=thisform[currcol+"id"];
	var fieldshow=thisform[currcol+"show"];
	last="0";
	if (currrow=="-1"){currrow=thisform.count.value;}
	select=window.showModalDialog("SelectOne.do?arg0="+code+"&arg1="+cshow);
	if(select!=""){
		select.match("&");	
		if (fieldid.length!=null) {
			fieldid= fieldid[currrow];
			fieldshow= fieldshow[currrow];
		}
		if(fieldid.value!=RegExp.rightContext){
			fieldshow.value=RegExp.leftContext;
			fieldid.value=RegExp.rightContext;
			if(document.getElementById("DynData") == null ||document.getElementById("DynData").all(fieldid.name) == null){
				freeformchange(thisform);
			}else{	
				tdchange(thisform);
			}
			last="1";
		}
	}
	return last;
}
			

/**
 * <P>����¼Ϊ��ѡ��ʱ�޸ļ�¼״̬����</P>
 * <P>��¼��ǰ�޸ĵ�Ԫ����״̬��־</P>
 * <P>0��1��3��4</P>
 * 
 * @param this
 * 
 */
function tdboxchange(thisfield){
	var curr_row=thisfield.value;
	if(thisfield.form.currrow!=null){
		thisfield.form.currrow.value=curr_row;
	}
   	var field = thisfield.form.rowstate;
	if (field.length!=null) {
		field = field[curr_row];
	}
   	if (field.value=="0"){
   		field.value="1";
   	}
   	if (field.value=="3"){
   		field.value="4";
   	}
}

/**
 * <P>�޸���״̬</P>
 * <P>���пհ��н���״̬��Ϊ����</P>
 * 
 * @param String ���key������
 * 
 */
function rerowstate(rowid){
	var rowstateField = document.getElementsByName("rowstate");
	var rowidField = document.getElementsByName(rowid);
	if(rowidField.lenght!=null){
		for (var intFor = 0; intFor < 2;intFor++){ 
			if (rowidField[intFor].value == ""){
				rowstateField[intFor].value ="3";
			}
		
		}
	}
}


/**
 * <P>ѡ���������
 *
 * ��ʾ����ǰ׺    ��ȷ���������飬һ��Ϊ��ʾ����ǰ׺+"id"����һ��Ϊ��ʾ����ǰ׺+"show"
 * id�������0Ԫ�أ��������title�������������1��ʼѡ��
 *  ��������ѡ��������˵��
 
 *  ������ͨ��show�е��������ж���ʾ�еĸ���������show�У�����ʾ���С��ޣ�ֻ��ʾid һ�У�
 *	��ʾ��             	  ����
	  1			ֻ��ʾid�У���ѡ���ֵ��ǰ��ǰ׺��ȷ����text�п���	
	  2			��ѡ���ֵ���뵱ǰ�к͵�ǰ��ǰ׺��ȷ����text��
 * </P>
 * 
 * @param String��thisform     form����                              
 * @param String��currrow      ��ǰ�У���ŷ���ֵ���У�����ֻ�ǵ���Ԫ�أ�������û��Ч����              
 * @param String��currcolumn   ��ǰ��ǰ׺��������Ŵ����text�����ǵ���������Ϊ"��ǰ׺"+"id"��
				   �������������˵����text�����ǵ���������Ϊ "��ǰ׺"+"show"��         
 * @param String��flag         ��ʾ����ǰ׺��������ʾ������ǰ׺��                                
 * @return String: �и���1���޸���0
 */
function selectvalue(thisform,currrow,currcol,flag){
	var select,last;
	last="0";	
	thisform.flag.value = flag;
	select =  window.showModalDialog("system/SelectValue.jsp",thisform);	


//alert(select);
	if(select!=""){
		var fieldid=thisform[currcol+"id"];
		var fieldshow=thisform[currcol+"show"];
		
	     if(thisform[currcol+"show"]==null){
	       	  if (fieldid.length!=null) {
				fieldid= fieldid[currrow];
		      }
	       	  if(select!=fieldid.value){
	       		fieldid.value = select;
	       		tdchange(thisform);
	       		last="1";
	         }
	       	 return last;
	       }	
	
	
		select.match("&");	
		if (fieldid.length!=null) {
			fieldid= fieldid[currrow];
			fieldshow= fieldshow[currrow];
		}
		if(fieldid.value!=RegExp.rightContext){
			fieldid.value=RegExp.leftContext;
			fieldshow.value=RegExp.rightContext;
			if(document.getElementById("DynData") == null ||document.getElementById("DynData").all(fieldid.name) == null){
				freeformchange(thisform);
			}else{	
				tdchange(thisform);
			}
			last="1";
		}
	}	
	return last;
}

	/**
	 * <P>�������ű�ѡ�����
	 *
	 *		�������ű����ʾ�������г����е��ֶ�
	 *      ����where�������ʾ����
	 *      ����ѡ�м�¼�������ֶΰ��������ֶ�
	 *      �����غ����returns�����������ֶ���(��Ӧ�ֶ������������������Ӧ�ֶεķ���ֵ)
	 * </P>
	 * 
	 * @param String��this      this����                              
	 * @param String��uid       queryid	
	 * @param String��where     where���	   ��������ѯ
	 * @param String��returns   ����ֵ��Ҫ������ֶ���
	 * @return String: �и���1���޸���0
	 */
	  function selectfield(thiscell,uid,where,returns,currrow){
		var thisform=thiscell.form;
		var select=window.showModalDialog("SelectField.do?arg0="+where+"&arg1="+uid,"","dialogHeight:600px;dialogWidth:800px " );
		
		//var select=window.open("SelectField.do?arg0="+where+"&arg1="+uid,"","height=600px,width=800px,menubar=no,status=no,toolbar=no" );
		
		if(select==""){return "0" ;}
		var field;
		var temp;
		var regexpMask = RegExp("[^`]+","g");
		var reg = RegExp("[^`]+","g");
		var row=currrow;
		if(currrow==""){	
			row=thiscell.parentNode.parentNode.rowIndex -1;
		}
		while ((temp = regexpMask.exec(returns)) != null) {			
			field="";
			if(select!="null"){field=reg.exec(select);}					
			var tempfield=thisform[temp];	
			if((tempfield!=null)&&(tempfield[row]!=null)){tempfield=tempfield[row];}
			if(tempfield!=null){
				tempfield.value=field;
			}				


		}
		
				freeformchange(thisform);

				tdchange(thisform);
		return "1";
	}
