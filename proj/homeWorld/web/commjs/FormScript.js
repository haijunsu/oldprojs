/**
 * <P>FORM函数包</P>
 * 
 * @version 1.0
 * @author LiuJian
 */

/**
 * <P>提交函数</P>
 * <P>设置程序分支标志提交表单</P>
 * 
 * @param form
 * @param String：程序分支标志
 * 
 */
function submitform(thisform,aflag){
	thisform.flag.value=aflag;
	validateSubmit(thisform);
}

/**
 * <P>更改保存函数</P>
 * <P>数据更改提示保存</P>
 * 
 * @param form
 * @param String：程序分支标志
 * @return int 提交返回1；不提交返回0
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
				//检查有无更改
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
	//提示保存
	if (intState!=0){
		if (confirm("表格内容已改变想提交吗？")){
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
 * <P>焦点记录函数</P>
 * <P>记录当前焦点单元的行列位置</P>
 * 
 * @param input
 * @param String：当前行
 * @param String：当前列
 * 
 */

function tdfocus(thiscell){
	var thisform=thiscell.form;
	if (thiscell.type != "button") { thiscell.select()};
	thisform.currrow.value=thiscell.parentNode.parentNode.rowIndex - 1;
	thisform.currcolumn.value=thiscell.name;
}
/**
 * <P>修改记录函数freeform</P>
 * <P>记录当前修改单元的状态标志</P>
 * <P>0该1；3该4</P>
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
 * <P>修改记录函数gread</P>
 * <P>记录当前修改单元的行状态标志</P>
 * <P>0该1；3该4</P>
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
 * <P>行删除函数</P>
 * <P>给当前行的各列加删除标志，将输入状态改为只读状态并修改行状态标志</P>
 * <P>0;1该2；3;4该5</P>
 * 
 * @param form
 * 
 */
function delline(thisform) {
	var tr_item, input_item;
	var intCurrindex = thisform.currrow.value;
	if (intCurrindex!="-1") {
		if (confirm("真想删除吗？")){
			tr_item = document.getElementById("DynData").childNodes(intCurrindex);
	    	//加删除标志，改只读状态
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
		   	//修改行状态标志
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
 * <P>增行函数</P>
 * <P>在表体的尾部添加一行及相关的行标志和行ID列</P>
 * <P>行标志为3</P>
 * <P>参数函数名：fields；定义：(tdclass,列名,type,size,value,readonly,class,event)</P>
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
		alert("这个浏览器不支持动态表格");
	} else {
		tbody.childNodes(intMaxrow).childNodes(0).childNodes(0).focus();
	}
}
 
 /**
 * <P>选择代码函数</P>
 * <P>弹出画面选择代码说明</P>
 * 
 * @param form: this.form
 * @param String：当前行
 * @param String：当前列前缀
 * @param String：代码或view名
 * @param String：view的title
 * @return String: 有更改1；无更改0
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
 * <P>当记录为复选框时修改记录状态函数</P>
 * <P>记录当前修改单元的行状态标志</P>
 * <P>0改1；3改4</P>
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
 * <P>修复行状态</P>
 * <P>若有空白行将其状态改为新增</P>
 * 
 * @param String 存放key的列名
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
 * <P>选择数组项函数
 *
 * 显示的列前缀    所确定两个数组，一个为显示的列前缀+"id"，另一个为显示的列前缀+"show"
 * id的数组的0元素，用来存放title，两个数组均从1开始选择
 *  弹出画面选择数组项说明
 
 *  本函数通过show列的有无来判断显示列的个数。即有show列，就显示两列。无，只显示id 一列，
 *	显示列             	  规则
	  1			只显示id列，将选择的值当前列前缀所确定的text中框中	
	  2			将选择的值放入当前行和当前列前缀所确定的text中
 * </P>
 * 
 * @param String：thisform     form对象                              
 * @param String：currrow      当前行（存放返回值的行，假如只是单个元素，本参数没有效果）              
 * @param String：currcolumn   当前列前缀（用来存放代码的text框我们的起名规则为"列前缀"+"id"，
				   而用来存放中文说明的text框我们的起名规则为 "列前缀"+"show"）         
 * @param String：flag         显示的列前缀（用来显示的数组前缀）                                
 * @return String: 有更改1；无更改0
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
	 * <P>根据三张表选择项函数
	 *
	 *		根据三张表的显示属性来列出所有的字段
	 *      根据where语句来显示数据
	 *      返回选中记录的所有字段包括隐藏字段
	 *      待返回后根据returns添入所给的字段中(对应字段名若不存在则放弃对应字段的返回值)
	 * </P>
	 * 
	 * @param String：this      this对象                              
	 * @param String：uid       queryid	
	 * @param String：where     where语句	   用来做查询
	 * @param String：returns   返回值所要添入的字段名
	 * @return String: 有更改1；无更改0
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
