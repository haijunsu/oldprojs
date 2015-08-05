//��ͼ����
/*---------------begin---------------*/

/*
*	����ͼ�εĺ���
*/
function addX(obj){
	if (obj==null) return;
	if (obj.xscale<2) obj.xscale+=0.1;
	showChart_Ex(obj);
}
function decX(obj){
	if (obj==null) return;
	if (obj.xscale>0.2) obj.xscale-=0.1;
	showChart_Ex(obj);
}
function addY(obj){
	if (obj==null) return;
	if (obj.yscale<2) obj.yscale+=0.1;
	showChart_Ex(obj);
}
function decY(obj){
	if (obj==null) return;
	if (obj.yscale>0.2)	obj.yscale-=0.1;
	showChart_Ex(obj);
}

/*
*	������ͼObject
*	yValueAr:	��ά���飬��ͼ��Ҫ�Ĳο�ֵ
*	xTextAr:	һά���飬X����ʾ����������
*	titleAr:	һά���飬ͼ����ʾ����������
*	chartType:	0--��ͼ��1--��ͼ  2--��ͼ
*	xscale:		x�����
*	yscale:		y�����
*	chartTitle:	��ͼ�ı���
*	showDiv:	��Ҫ��ҳ����ʾ��div��id
*/
function createChart(yValueAr,xTextAr,titleAr,chartType,xscale,yscale,chartTitle,showDiv){
	this.yValueAr=yValueAr
	this.xTextAr=xTextAr
	this.titleAr=titleAr
	this.chartType=chartType
	this.xscale=xscale
	this.yscale=yscale
	this.chartTitle=chartTitle
	this.showDiv=showDiv

	this.colors = ["#6495ED","#DC143C","#00008B","#008B8B","#B8860B","#A9A9A9","#006400","#BDB76B",
	"#8B008B","#556B2F","#FF8C00","#9932CC","#8B0000","#E9967A","#8FBC8B","#483D8B","#2F4F4F",
	"#00CED1","#9400D3","#FF1493","#00BFFF","#696969","#1E90FF","#B22222","#228B22","#FF00FF"];

	return this;
}

/*
*	��ʾͼ��
*	chartObj:	��createChart()������Object
*/
function showChart_Ex(chartObj){
	if (chartObj==null) return;
	//����group
	var objGroup = document.createElement("v:group");	
	objGroup.CoordSize = "4320 3240";
	objGroup.Style="WIDTH:"+432*chartObj.xscale+"pt; HEIGHT:"+324*chartObj.yscale+"pt";

	//������߿�
	var outBox = document.createElement("v:rect");
	outBox.Style= "WIDTH: 4320px; HEIGHT: 3240px";
	outBox.CoordSize = "21000,21600";
	outBox.FillColor = "white";
		
	//������߿��shadow
	var obShadow = document.createElement("v:shadow");
	obShadow.On = "t";
	obShadow.Color = "silver";
	obShadow.Offset = "4pt,3pt";
	outBox.insertBefore(obShadow,null);

	//�����ڱ߿�
	var inBox = document.createElement("v:rect");
	inBox.Style= "LEFT: 40px; WIDTH: 4240px; POSITION: absolute; TOP: 40px; HEIGHT: 3160px";
	inBox.CoordSize = "21000,21600";
	inBox.FillColor = "white";
	inBox.StrokeWeight = "1.5pt";
	
	var ctTextBox = document.createElement("v:TextBox");
	ctTextBox.Inset = "0,0,0,0";
	ctTextBox.Style = "font-size:12pt;text-align:left";
	ctTextBox.innerHTML = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+chartObj.chartTitle;
	inBox.insertBefore(ctTextBox);
			
	objGroup.insertBefore(outBox,null);	
	objGroup.insertBefore(inBox,null);

	if (chartObj.chartType<2){	//��ͼ�Ϳ�ͼ��Ҫ
		//������ͼ����
		var drawBox = document.createElement("v:rect");
		drawBox.Style= "LEFT: 300px; WIDTH: 3220px; POSITION: absolute; TOP: 400px; HEIGHT: 2540px";
		drawBox.CoordSize = "21000,21600";
		drawBox.FillColor = "#CCFFFF";
		drawBox.StrokeColor = "white";

		//����������
		//x��
		var xAxis = document.createElement("v:line");
		xAxis.From = "300,2940";
		xAxis.To = "300,400";
		xAxis.StrokeWeight = "1pt";
		xAxis.Style="Z-INDEX:1;position:absolute";
		var xArrow = document.createElement("v:stroke");
		xArrow.EndArrow = "classic";
		xAxis.insertBefore(xArrow,null);

		//y��
		var yAxis = document.createElement("v:line");
		yAxis.From = "300,2940";
		yAxis.To = "3620,2940";
		yAxis.StrokeWeight = "1pt";
		yAxis.Style="Z-INDEX:1;position:absolute";
		var yArrow = document.createElement("v:stroke");
		yArrow.EndArrow = "classic";
		yAxis.insertBefore(yArrow,null);
		
		objGroup.insertBefore(drawBox,null);
		objGroup.insertBefore(xAxis,null);
		objGroup.insertBefore(yAxis,null);

		//ԭ�㼰x,y�᳤��
		var xOrigin = 300;
		var yOrigin = 2940;
		var xLen = 3620-300;
		var yLen = 2940-400;
		
		var yMaxValue = 0;
		for (var i=0;i<chartObj.yValueAr.length;i++)
			for(var j=0;j<chartObj.yValueAr[0].length;j++){
				if (chartObj.yValueAr[i][j]>yMaxValue)
					yMaxValue=chartObj.yValueAr[i][j];
			}

		var yMaxValue = yMaxValue*1.12;
		var yValue = parseInt(Math.exp( parseInt(Math.log(yMaxValue)/Math.LN10)*Math.LN10))/10
		var t=parseInt(String(yMaxValue).substring(0,1));
		if (t >5)
			yValue *=10;
		else if (t>2)
			yValue *=5;
		else if (t>1)
			yValue *=4;
		else
			yValue *= 2
				
		var yScale = parseFloat(yLen)/parseFloat(yMaxValue);	
		var xScale = parseFloat(xLen)/chartObj.xTextAr.length;
		var xW = xScale/(chartObj.titleAr.length+1)
//�����̶�		
		//y��
		for (var i=yMaxValue;i>0;i-=yValue){
			//�̶���
			var tmp_line = document.createElement("v:line");
			tmp_line.From = "300," + (i*yScale+400);
			tmp_line.To = "350," + (i*yScale+400);
			tmp_line.StrokeWeight = "1pt";
			tmp_line.Style="Z-INDEX:1;position:absolute";
			objGroup.insertBefore(tmp_line,null);

			//�̶�ֵ
			var tRect = document.createElement("v:rect");
			tRect.Style="top:"+(i*yScale+400-45)+";left:55;width:250;height:90;position:absolute";
			tRect.StrokeColor="white"
			var tTextBox = document.createElement("v:TextBox");
			tTextBox.Inset = "0,0,0,0";
			tTextBox.Style = "font-size:9pt;text-align:right";
			tTextBox.innerText = yMaxValue-i;
			tRect.insertBefore(tTextBox,null);
			objGroup.insertBefore(tRect,null);
		}
		//x��
		for (var i=0;i<xTextAr.length;i++){
			//�̶���
			var tmp_line = document.createElement("v:line");
			tmp_line.From = (300+i*xScale+xScale/2)+",2940";
			tmp_line.To = (300+i*xScale+xScale/2)+",2990";
			tmp_line.StrokeWeight = "1pt";
			tmp_line.Style="Z-INDEX:1;position:absolute";
			objGroup.insertBefore(tmp_line,null);

			//�̶�ֵ
			var tRect = document.createElement("v:rect");
			tRect.Style="top:2990;left:"+(300+i*xScale)+";width:"+xScale+";height:90;position:absolute";
			tRect.StrokeColor="white"
			var tTextBox = document.createElement("v:TextBox");
			tTextBox.Inset = "0,0,0,0";
			tTextBox.Style = "font-size:9pt;text-align:center";
			tTextBox.innerText = chartObj.xTextAr[i];
			tRect.insertBefore(tTextBox,null);
			objGroup.insertBefore(tRect,null);
		}
	} 

	var coloridx = 0;
	//��ͼ
	if (chartObj.chartType==2)
	{
		var ar=new Array()
		var sum=0;
		for (var i=0;i<chartObj.yValueAr[0].length;i++)
			sum += chartObj.yValueAr[0][i];
		var maxScale = 360<<16;
		for (var i=0;i<chartObj.yValueAr[0].length;i++)
			ar[i]=chartObj.yValueAr[0][i]/sum;		
		var halfcoordsize = 1000;
		sum=0;
		var st="";
		var tbTitleStr="<tr bgcolor=\"white\">";
		var tbValueStr="<tr bgcolor=\"white\">";
		//����
		for (var i=0;i<ar.length;i++)
		{
			st= "m "+halfcoordsize+","+halfcoordsize+" ae"+ halfcoordsize+","+ halfcoordsize+","+halfcoordsize+","+halfcoordsize+","+parseInt(sum*maxScale)+","+parseInt( ar[i] * maxScale)+ "xe";
			
			var pieObj = document.createElement("v:shape");
			pieObj.FillColor = chartObj.colors[coloridx];
			pieObj.CoordSize = "2000,2000";
			pieObj.Style = "top:300px;left:800px;width:2000px;height:2000px;position=absolute";
			pieObj.Title = chartObj.xTextAr[i]+":"+parseInt(ar[i]*100+0.5)+"%";
			pieObj.Path=st;
			sum += ar[i];
			objGroup.insertBefore(pieObj,null);

			//ͼ��
			var colBox = document.createElement("v:rect");
			colBox.Style= "LEFT: 3630px; WIDTH: 100px; POSITION: absolute; TOP: "+(500+i*120)+"px; HEIGHT: 100px";
			colBox.FillColor = chartObj.colors[coloridx];

			coloridx+=(coloridx == (chartObj.colors.length-1)) ? -chartObj.colors.length+1 : 1;
			
			var titleBox = document.createElement("v:rect");
			titleBox.Style= "position:absolute;LEFT: 3750px; WIDTH: 630px; POSITION: absolute; TOP: "+(500+i*120)+"px; HEIGHT: 100px";
			titleBox.StrokeColor = "white";
			
			var titleTextBox = document.createElement("v:TextBox");
			titleTextBox.Inset = "0,0,0,0";
			titleTextBox.Style = "font-size:9pt;text-align:left";
			titleTextBox.innerText = chartObj.xTextAr[i];
			titleBox.insertBefore(titleTextBox,null);
			
			objGroup.insertBefore(colBox,null);
			objGroup.insertBefore(titleBox,null);
			tbTitleStr+="<td align=center>"+chartObj.xTextAr[i]+"</td>";
			tbValueStr+="<td align=right>"+chartObj.yValueAr[0][i]+"</td>";
		}
		
		tbTitleStr+="</tr>";
		tbValueStr+="</tr>";
		var listTableBox = document.createElement("v:rect");
		listTableBox.FillColor="white";
		listTableBox.StrokeColor="white";
		listTableBox.Style="LEFT:300px;WIDTH:3800px;POSITION:absolute;TOP:2500px;HEIGHT:500px";
		listTableBox.innerHTML = "<font size=2>"+chartObj.titleAr[0]+"</font><table bgcolor=\"black\" CELLSPACING=1 CELLPADDING=1>"+tbTitleStr+tbValueStr+"</table>";
		objGroup.insertBefore(listTableBox,null);
		
	} else if (chartType<2)	{
		//��ͼ
		for (var i=0;i<chartObj.yValueAr.length;i++){
			var sp="";
			for (var j=0;j<chartObj.yValueAr[0].length;j++){
				if (chartObj.chartType==0)	//��ͼ
				{
					sp += parseInt(300+j*xScale+xScale/2) + "," + parseInt(2940-yScale*chartObj.yValueAr[i][j]) + " ";
					var curPoint = document.createElement("v:rect");
					curPoint.Style = "top:"+parseInt(2940-yScale*chartObj.yValueAr[i][j]-10)+";left:"+parseInt(300+j*xScale+xScale/2-10)+";width:20;height:20;position:absolute";
					curPoint.FillColor=chartObj.colors[coloridx];
					curPoint.StrokeColor = chartObj.colors[coloridx];
					curPoint.Title = chartObj.yValueAr[i][j];
					//alert(curPoint.outerHTML);
					objGroup.insertBefore(curPoint,null);

				} else if (chartType==1){	//��ͼ
					var iTop = 2940-yScale*yValueAr[i][j];
					var iLeft = 300+xW/2+i*xW+j*xScale;
					var dRect = document.createElement("v:rect");
					dRect.Style = "width:"+xW+";height:"+(yScale*chartObj.yValueAr[i][j])+";top:"+iTop+";left:"+ iLeft+";position:absolute";
					dRect.StrokeColor = "white";
					dRect.Title = chartObj.titleAr[i] + ":" + chartObj.yValueAr[i][j];
					dRect.FillColor = chartObj.colors[coloridx];					
					objGroup.insertBefore(dRect,null);
				}
			}
			if (chartType==0)	//��ͼ
			{ 
				var pLine = document.createElement("v:PolyLine");
				pLine.Filled = "f";
				pLine.Title = chartObj.titleAr[i]
				pLine.Points = sp;
				pLine.Style = "position:absolute";
				pLine.StrokeColor = chartObj.colors[coloridx];				
				objGroup.insertBefore(pLine,null);
			} 
			

			//ͼ��
			var colBox = document.createElement("v:rect");
			colBox.Style= "LEFT: 3630px; WIDTH: 100px; POSITION: absolute; TOP: "+(500+i*120)+"px; HEIGHT: 100px";
			colBox.FillColor = chartObj.colors[coloridx];
			coloridx+=(coloridx == (chartObj.colors.length-1)) ? -chartObj.colors.length+1 : 1;
			
			var titleBox = document.createElement("v:rect");
			titleBox.Style= "position:absolute;LEFT: 3750px; WIDTH: 630px; POSITION: absolute; TOP: "+(500+i*120)+"px; HEIGHT: 100px";
			titleBox.StrokeColor = "white";
			
			var titleTextBox = document.createElement("v:TextBox");
			titleTextBox.Inset = "0,0,0,0";
			titleTextBox.Style = "font-size:9pt;text-align:left";
			titleTextBox.innerText = chartObj.titleAr[i];
			titleBox.insertBefore(titleTextBox,null);
			
			objGroup.insertBefore(colBox,null);
			objGroup.insertBefore(titleBox,null);

		}
	}
	chartObj.showDiv.innerHTML = objGroup.outerHTML;
}
/*---------------end---------------*/
