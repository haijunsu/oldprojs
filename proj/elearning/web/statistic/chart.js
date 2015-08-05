//作图程序
/*---------------begin---------------*/

/*
*	缩放图形的函数
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
*	创建作图Object
*	yValueAr:	二维数组，画图主要的参考值
*	xTextAr:	一维数组，X轴显示的文字数组
*	titleAr:	一维数组，图例显示的文字数组
*	chartType:	0--线图　1--框图  2--饼图
*	xscale:		x轴比例
*	yscale:		y轴比例
*	chartTitle:	作图的标题
*	showDiv:	需要在页面显示的div的id
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
*	显示图形
*	chartObj:	由createChart()创建的Object
*/
function showChart_Ex(chartObj){
	if (chartObj==null) return;
	//创建group
	var objGroup = document.createElement("v:group");	
	objGroup.CoordSize = "4320 3240";
	objGroup.Style="WIDTH:"+432*chartObj.xscale+"pt; HEIGHT:"+324*chartObj.yscale+"pt";

	//创建外边框
	var outBox = document.createElement("v:rect");
	outBox.Style= "WIDTH: 4320px; HEIGHT: 3240px";
	outBox.CoordSize = "21000,21600";
	outBox.FillColor = "white";
		
	//创建外边框的shadow
	var obShadow = document.createElement("v:shadow");
	obShadow.On = "t";
	obShadow.Color = "silver";
	obShadow.Offset = "4pt,3pt";
	outBox.insertBefore(obShadow,null);

	//创建内边框
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

	if (chartObj.chartType<2){	//线图和框图需要
		//创建画图区域
		var drawBox = document.createElement("v:rect");
		drawBox.Style= "LEFT: 300px; WIDTH: 3220px; POSITION: absolute; TOP: 400px; HEIGHT: 2540px";
		drawBox.CoordSize = "21000,21600";
		drawBox.FillColor = "#CCFFFF";
		drawBox.StrokeColor = "white";

		//创建坐标轴
		//x轴
		var xAxis = document.createElement("v:line");
		xAxis.From = "300,2940";
		xAxis.To = "300,400";
		xAxis.StrokeWeight = "1pt";
		xAxis.Style="Z-INDEX:1;position:absolute";
		var xArrow = document.createElement("v:stroke");
		xArrow.EndArrow = "classic";
		xAxis.insertBefore(xArrow,null);

		//y轴
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

		//原点及x,y轴长度
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
//创建刻度		
		//y轴
		for (var i=yMaxValue;i>0;i-=yValue){
			//刻度线
			var tmp_line = document.createElement("v:line");
			tmp_line.From = "300," + (i*yScale+400);
			tmp_line.To = "350," + (i*yScale+400);
			tmp_line.StrokeWeight = "1pt";
			tmp_line.Style="Z-INDEX:1;position:absolute";
			objGroup.insertBefore(tmp_line,null);

			//刻度值
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
		//x轴
		for (var i=0;i<xTextAr.length;i++){
			//刻度线
			var tmp_line = document.createElement("v:line");
			tmp_line.From = (300+i*xScale+xScale/2)+",2940";
			tmp_line.To = (300+i*xScale+xScale/2)+",2990";
			tmp_line.StrokeWeight = "1pt";
			tmp_line.Style="Z-INDEX:1;position:absolute";
			objGroup.insertBefore(tmp_line,null);

			//刻度值
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
	//饼图
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
		//画饼
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

			//图例
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
		//作图
		for (var i=0;i<chartObj.yValueAr.length;i++){
			var sp="";
			for (var j=0;j<chartObj.yValueAr[0].length;j++){
				if (chartObj.chartType==0)	//线图
				{
					sp += parseInt(300+j*xScale+xScale/2) + "," + parseInt(2940-yScale*chartObj.yValueAr[i][j]) + " ";
					var curPoint = document.createElement("v:rect");
					curPoint.Style = "top:"+parseInt(2940-yScale*chartObj.yValueAr[i][j]-10)+";left:"+parseInt(300+j*xScale+xScale/2-10)+";width:20;height:20;position:absolute";
					curPoint.FillColor=chartObj.colors[coloridx];
					curPoint.StrokeColor = chartObj.colors[coloridx];
					curPoint.Title = chartObj.yValueAr[i][j];
					//alert(curPoint.outerHTML);
					objGroup.insertBefore(curPoint,null);

				} else if (chartType==1){	//框图
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
			if (chartType==0)	//线图
			{ 
				var pLine = document.createElement("v:PolyLine");
				pLine.Filled = "f";
				pLine.Title = chartObj.titleAr[i]
				pLine.Points = sp;
				pLine.Style = "position:absolute";
				pLine.StrokeColor = chartObj.colors[coloridx];				
				objGroup.insertBefore(pLine,null);
			} 
			

			//图例
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
