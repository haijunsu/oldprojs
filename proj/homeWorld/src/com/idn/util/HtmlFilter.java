/*
 * @(#)HtmlFilter.java  2003-4-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

/**
 * <P>处理一些HTML页中的TAG工具 </P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class HtmlFilter {

	/**
	 * 私有构造函数，该类不可以被实例化
	 */
	private HtmlFilter() {
	}

	//将字符串转换为HTML
	public static String stringToHtml(String str) {
		str = CommonTools.stringReplace(str, "<", "&lt;");
		str = CommonTools.stringReplace(str, ">", "&gt;");
		str = CommonTools.stringReplace(str, " ", "&nbsp;");
		str = CommonTools.stringReplace(str, "\"", "&quot;");
		str = CommonTools.stringReplace(str, '\n', "<BR>");
		return str;
	}

	public static String formatFlash(String fString) {
		String strFlashBASE =
			"<OBJECT codeBase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0 width=500 height=400 classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000><PARAM NAME=movie VALUE=\"$2\"><PARAM NAME=quality VALUE=high><embed src=\"$2\" quality=high pluginspage=\"http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash\" type=\"application/x-shockwave-flash\" width=500 height=400>$2</embed></OBJECT>";
		int Startwith, Stopwith;
		String tmpFlash, tmpInsert;
		fString = CommonTools.stringReplace(fString, "[FLASH]", "[flash]");
		fString = CommonTools.stringReplace(fString, "[/FLASH]", "[/flash]");
		Startwith = fString.indexOf("[flash]");
		while (Startwith != -1) {
			Stopwith = fString.indexOf("[/flash]");
			tmpFlash = fString.substring(Startwith + 7, Stopwith);
			tmpInsert = CommonTools.stringReplace(strFlashBASE, "$2", tmpFlash);
			fString =
				fString.substring(0, Startwith)
					+ tmpInsert
					+ fString.substring(Stopwith + 8);
			Startwith = fString.indexOf("[flash]");

		}
		return fString;
	}
	public static String formatImage(String fString) {
		String strFlashBASE =
			"<a herf='$1' target=_blank title=开新窗口浏览><img src='$1' border=0 onload='javascript:if(this.width>screen.width-333)this.width=screen.width-333'></a>";
		int Startwith, Stopwith;
		String tmpFlash, tmpInsert;
		fString = CommonTools.stringReplace(fString, "[IMG]", "[img]");
		fString = CommonTools.stringReplace(fString, "[/IMG]", "[/img]");

		Startwith = fString.indexOf("[img]");
		while (Startwith != -1) {
			Stopwith = fString.indexOf("[/img]");
			tmpFlash = fString.substring(Startwith + 5, Stopwith);
			tmpInsert = CommonTools.stringReplace(strFlashBASE, "$1", tmpFlash);
			fString =
				fString.substring(0, Startwith)
					+ tmpInsert
					+ fString.substring(Stopwith + 6);
			Startwith = fString.indexOf("[img]");

		}
		return fString;
	}

	//将网页中的UBB字串换成标准HTML格式

	public static String ubbToHtml(String fString) {
		fString = fString.replace('\n', '\r');
		fString = CommonTools.stringReplace(fString, "\r\r", "</P><P>");
		fString = CommonTools.stringReplace(fString, "\r", "<BR>");
		fString = CommonTools.stringReplace(fString, "[b]", "<b>");
		fString = CommonTools.stringReplace(fString, "[/b]", "</b>");
		fString = CommonTools.stringReplace(fString, "[s]", "<s>");
		fString = CommonTools.stringReplace(fString, "[/s]", "</s>");
		fString = CommonTools.stringReplace(fString, "[u]", "<u>");
		fString = CommonTools.stringReplace(fString, "[/u]", "</u>");
		fString = CommonTools.stringReplace(fString, "[i]", "<i>");
		fString = CommonTools.stringReplace(fString, "[/i]", "</i>");
		fString =
			CommonTools.stringReplace(fString, "[font=宋体]", "<font face='宋体'>");
		fString =
			CommonTools.stringReplace(fString, "[/font=宋体]", "</font id='宋体'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=楷体]",
				"<font face='楷体_GB2312'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=楷体]",
				"</font id='楷体_GB2312'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=新宋体]",
				"<font face='新宋体'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=新宋体]",
				"</font id='新宋体'>");
		fString =
			CommonTools.stringReplace(fString, "[font=黑体]", "<font face='黑体'>");
		fString =
			CommonTools.stringReplace(fString, "[/font=黑体]", "</font id='黑体'>");
		fString =
			CommonTools.stringReplace(fString, "[font=隶书]", "<font face='隶书'>");
		fString =
			CommonTools.stringReplace(fString, "[/font=隶书]", "</font id='隶书'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Andale Mono]",
				"<font face='Andale Mono'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Andale Mono]",
				"</font id='Andale Mono'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Arial]",
				"<font face='Arial'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Arial]",
				"</font id='Arial'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Arial Black]",
				"<font face='Arial Black'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Arial Black]",
				"</font id='Arial Black'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Book Antiqua]",
				"<font face='Book Antiqua'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Book Antiqua]",
				"</font id='Book Antiqua'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Century Gothic]",
				"<font face='Century Gothic'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Century Gothic]",
				"</font id='Century Gothic'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Comic Sans MS]",
				"<font face='Comic Sans MS'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Comic Sans MS]",
				"</font id='Comic Sans MS'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Courier New]",
				"<font face='Courier New'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Courier New]",
				"</font id='Courier New'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Georgia]",
				"<font face='Georgia'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Georgia]",
				"</font id='Georgia'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Impact]",
				"<font face='Impact'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Impact]",
				"</font id='Impact'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Tahoma]",
				"<font face='Tahoma'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Tahoma]",
				"</font id='Tahoma'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Times New Roman]",
				"<font face='Times New Roman'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Times New Roman]",
				"</font id='Times New Roman'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Trebuchet MS]",
				"<font face='Trebuchet MS'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Trebuchet MS]",
				"</font id='Trebuchet MS'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Script MT Bold]",
				"<font face='Script MT Bold'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Script MT Bold]",
				"</font id='Script MT Bold'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Stencil]",
				"<font face='Stencil'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Stencil]",
				"</font id='Stencil'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[font=Lucida Console]",
				"<font face='Lucida Console'>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/font=Lucida Console]",
				"</font id='Lucida Console'>");

		fString =
			CommonTools.stringReplace(fString, "[red]", "<font color=red>");
		fString =
			CommonTools.stringReplace(fString, "[/red]", "</font id=red>");
		fString =
			CommonTools.stringReplace(fString, "[green]", "<font color=green>");
		fString =
			CommonTools.stringReplace(fString, "[/green]", "</font id=green>");
		fString =
			CommonTools.stringReplace(fString, "[blue]", "<font color=blue>");
		fString =
			CommonTools.stringReplace(fString, "[/blue]", "</font id=blue>");
		fString =
			CommonTools.stringReplace(fString, "[white]", "<font color=white>");
		fString =
			CommonTools.stringReplace(fString, "[/white]", "</font id=white>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[purple]",
				"<font color=purple>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/purple]",
				"</font id=purple>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[yellow]",
				"<font color=yellow>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/yellow]",
				"</font id=yellow>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[violet]",
				"<font color=violet>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/violet]",
				"</font id=violet>");
		fString =
			CommonTools.stringReplace(fString, "[brown]", "<font color=brown>");
		fString =
			CommonTools.stringReplace(fString, "[/brown]", "</font id=brown>");
		fString =
			CommonTools.stringReplace(fString, "[black]", "<font color=black>");
		fString =
			CommonTools.stringReplace(fString, "[/black]", "</font id=black>");
		fString =
			CommonTools.stringReplace(fString, "[pink]", "<font color=pink>");
		fString =
			CommonTools.stringReplace(fString, "[/pink]", "</font id=pink>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[orange]",
				"<font color=orange>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/orange]",
				"</font id=orange>");
		fString =
			CommonTools.stringReplace(fString, "[gold]", "<font color=gold>");
		fString =
			CommonTools.stringReplace(fString, "[/gold]", "</font id=gold>");
		fString =
			CommonTools.stringReplace(fString, "[gray]", "<font color=gray>");
		fString =
			CommonTools.stringReplace(fString, "[/gray]", "</font id=gray>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[silver]",
				"<font color=silver>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/silver]",
				"</font id=silver>");
		fString =
			CommonTools.stringReplace(fString, "[beige]", "<font color=beige>");
		fString =
			CommonTools.stringReplace(fString, "[/beige]", "</font id=beige>");
		fString =
			CommonTools.stringReplace(fString, "[teal]", "<font color=teal>");
		fString =
			CommonTools.stringReplace(fString, "[/teal]", "</font id=teal>");
		fString =
			CommonTools.stringReplace(fString, "[navy]", "<font color=navy>");
		fString =
			CommonTools.stringReplace(fString, "[/navy]", "</font id=navy>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[maroon]",
				"<font color=maroon>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/maroon]",
				"</font id=maroon>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[limegreen]",
				"<font color=limegreen>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/limegreen]",
				"</font id=limegreen>");
		fString = CommonTools.stringReplace(fString, "[h1]", "<h1>");
		fString = CommonTools.stringReplace(fString, "[/h1]", "</h1>");
		fString = CommonTools.stringReplace(fString, "[h2]", "<h2>");
		fString = CommonTools.stringReplace(fString, "[/h2]", "</h2>");
		fString = CommonTools.stringReplace(fString, "[h3]", "<h3>");
		fString = CommonTools.stringReplace(fString, "[/h3]", "</h3>");
		fString = CommonTools.stringReplace(fString, "[h4]", "<h4>");
		fString = CommonTools.stringReplace(fString, "[/h4]", "</h4>");
		fString = CommonTools.stringReplace(fString, "[h5]", "<h5>");
		fString = CommonTools.stringReplace(fString, "[/h5]", "</h5>");
		fString = CommonTools.stringReplace(fString, "[h6]", "<h6>");
		fString = CommonTools.stringReplace(fString, "[/h6]", "</h6>");
		fString =
			CommonTools.stringReplace(fString, "[size=1]", "<font size='1'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=1]", "</font id=1>");
		fString =
			CommonTools.stringReplace(fString, "[size=2]", "<font size='2'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=2]", "</font id=2>");
		fString =
			CommonTools.stringReplace(fString, "[size=3]", "<font size='3'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=3]", "</font id=3>");
		fString =
			CommonTools.stringReplace(fString, "[size=4]", "<font size='4'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=4]", "</font id=4>");
		fString =
			CommonTools.stringReplace(fString, "[size=5]", "<font size='5'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=5]", "</font id=5>");
		fString =
			CommonTools.stringReplace(fString, "[size=6]", "<font size='6'>");
		fString =
			CommonTools.stringReplace(fString, "[/size=6]", "</font id=6>");
		fString = CommonTools.stringReplace(fString, "[br]", "<br>");
		fString = CommonTools.stringReplace(fString, "[/url]", "[/URL]");
		fString = CommonTools.stringReplace(fString, "[url", "[URL");
		fString =
			CommonTools.stringReplace(fString, "[left]", "<div align=left>");
		fString =
			CommonTools.stringReplace(fString, "[/left]", "</div id=left>");
		fString = CommonTools.stringReplace(fString, "[center]", "<center>");
		fString = CommonTools.stringReplace(fString, "[/center]", "</center>");
		fString =
			CommonTools.stringReplace(fString, "[right]", "<div align=right>");
		fString =
			CommonTools.stringReplace(fString, "[/right]", "</div id=right>");
		fString = CommonTools.stringReplace(fString, "[list]", "<ul>");
		fString = CommonTools.stringReplace(fString, "[/list]", "</ul>");
		fString = CommonTools.stringReplace(fString, "[list=1]", "<ol type=1>");
		fString = CommonTools.stringReplace(fString, "[/list=1]", "</ol id=1>");
		fString = CommonTools.stringReplace(fString, "[list=a]", "<ol type=a>");
		fString = CommonTools.stringReplace(fString, "[/list=a]", "</ol id=a>");
		fString = CommonTools.stringReplace(fString, "[list=A]", "<ol type=a>");
		fString = CommonTools.stringReplace(fString, "[/list=A]", "</ol id=a>");
		fString = CommonTools.stringReplace(fString, "[*]", "<li>");
		fString = CommonTools.stringReplace(fString, "[/*]", "</li>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[quote]",
				"<BLOCKQUOTE id=quote><font id=quote><hr height=1 noshade id=quote>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/quote]",
				"<hr height=1 noshade id=quote></font id=quote></BLOCKQUOTE id=quote>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[fly]",
				"<marquee width=90% behavior=alternate scrollamount=3>");
		fString = CommonTools.stringReplace(fString, "[/fly]", "</marquee>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[move]",
				"<MARQUEE scrollamount=3>");
		fString = CommonTools.stringReplace(fString, "[/move]", "</MARQUEE>");

		fString =
			CommonTools.stringReplace(
				fString,
				"[quote]",
				"<BR><table cellpadding=0 cellspacing=0 border=0 WIDTH=94% bgcolor=#000000 align=center><tr><td><table width=100% cellpadding=5 cellspacing=1 border=0><TR><TD BGCOLOR=#F8F8F8><font id=quote>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[/quote]",
				"</td></tr></table></td></tr></table>");
		fString =
			CommonTools.stringReplace(
				fString,
				"[quote]",
				"<table cellpadding=0 cellspacing=0 border=0 WIDTH=94% bgcolor=#000000 align=center><tr><td><table width=100% cellpadding=5 cellspacing=1 border=0><TR><TD BGCOLOR=#F8F8F8><font id=quote>");
		fString = formatFlash(fString);
		fString = formatImage(fString);
		return fString;
	}

}
