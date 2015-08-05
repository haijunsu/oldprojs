/**
 * ”≥…‰System.t_userstyle±Ì
 */

package system;
import com.htyz.*; 

public class userStyle {
	private String user_id;
	private String font;
	private String defaultfont;
	private String defaulttitlefont;
	private String headerfont;
	private String bgcolor;
	private String fontcolor;
	private String linkcolor;
	private String linkstyle;
	private String onmousecolor;
	private String onmouselink;
	private String onmousestyle;
	private String titlecolor;
	private String titlefontcolor;
	private String catfontcolor;
	private String tablecolor;
	private String firstrowcolor;
	private String tworowcolor;
	private String bbscolor;
	private String bbslinkcolor;
	private String tablebordercolor;
	private String stressfont;
	private String newfont;
	private String tabelwidth;
	private String mainleftwidth;
	private String leftline;
	private String rightline;
	private String lines_in_page;

	public userStyle() {
		this.user_id = "";
		this.font = font;
		this.defaultfont = "";
		this.defaulttitlefont = "";
		this.headerfont = "";
		this.bgcolor = "";
		this.fontcolor = "";
		this.linkcolor = "";
		this.linkstyle = "";
		this.onmousecolor = "";
		this.onmouselink = "";
		this.onmousestyle = "";
		this.titlecolor = "";
		this.titlefontcolor = "";
		this.catfontcolor = "";
		this.tablecolor = "";
		this.firstrowcolor = "";
		this.tworowcolor = "";
		this.bbscolor = "";
		this.bbslinkcolor = "";
		this.tablebordercolor = "";
		this.stressfont = "";
		this.newfont = "";
		this.tabelwidth = "";
		this.mainleftwidth = "";
		this.leftline = "";
		this.rightline = "";
		this.lines_in_page = "10";
	}

	public userStyle(String user_id, String font, String defaultfont, 
				String defaulttitlefont, String headerfont, String bgcolor, 
				String fontcolor, String linkcolor, String linkstyle, 
				String onmousecolor, String onmouselink, String onmousestyle, 
				String titlecolor, String titlefontcolor, String catfontcolor, 
				String tablecolor, String firstrowcolor, String tworowcolor, 
				String bbscolor, String bbslinkcolor, String tablebordercolor, 
				String stressfont, String newfont, String tabelwidth, 
				String mainleftwidth, String leftline, String rightline, 
				String lines_in_page) {
		this.user_id = user_id;
		this.font = font;
		this.defaultfont = defaultfont;
		this.defaulttitlefont = defaulttitlefont;
		this.headerfont = headerfont;
		this.bgcolor = bgcolor;
		this.fontcolor = fontcolor;
		this.linkcolor = linkcolor;
		this.linkstyle = linkstyle;
		this.onmousecolor = onmousecolor;
		this.onmouselink = onmouselink;
		this.onmousestyle = onmousestyle;
		this.titlecolor = titlecolor;
		this.titlefontcolor = titlefontcolor;
		this.catfontcolor = catfontcolor;
		this.tablecolor = tablecolor;
		this.firstrowcolor = firstrowcolor;
		this.tworowcolor = tworowcolor;
		this.bbscolor = bbscolor;
		this.bbslinkcolor = bbslinkcolor;
		this.tablebordercolor = tablebordercolor;
		this.stressfont = stressfont;
		this.newfont = newfont;
		this.tabelwidth = tabelwidth;
		this.mainleftwidth = mainleftwidth;
		this.leftline = leftline;
		this.rightline = rightline;
		this.lines_in_page = lines_in_page;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id; 
	}

	public void setFont(String font) {
		this.font = font; 
	}

	public void setDefaultfont(String defaultfont) {
		this.defaultfont = defaultfont; 
	}

	public void setDefaulttitlefont(String defaulttitlefont) {
		this.defaulttitlefont = defaulttitlefont; 
	}

	public void setHeaderfont(String headerfont) {
		this.headerfont = headerfont; 
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor; 
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor; 
	}

	public void setLinkcolor(String linkcolor) {
		this.linkcolor = linkcolor; 
	}

	public void setLinkstyle(String linkstyle) {
		this.linkstyle = linkstyle; 
	}

	public void setOnmousecolor(String onmousecolor) {
		this.onmousecolor = onmousecolor; 
	}

	public void setOnmouselink(String onmouselink) {
		this.onmouselink = onmouselink; 
	}

	public void setOnmousestyle(String onmousestyle) {
		this.onmousestyle = onmousestyle; 
	}

	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor; 
	}

	public void setTitlefontcolor(String titlefontcolor) {
		this.titlefontcolor = titlefontcolor; 
	}

	public void setCatfontcolor(String catfontcolor) {
		this.catfontcolor = catfontcolor; 
	}

	public void setTablecolor(String tablecolor) {
		this.tablecolor = tablecolor; 
	}

	public void setFirstrowcolor(String firstrowcolor) {
		this.firstrowcolor = firstrowcolor; 
	}

	public void setTworowcolor(String tworowcolor) {
		this.tworowcolor = tworowcolor; 
	}

	public void setBbscolor(String bbscolor) {
		this.bbscolor = bbscolor; 
	}

	public void setBbslinkcolor(String bbslinkcolor) {
		this.bbslinkcolor = bbslinkcolor; 
	}

	public void setTablebordercolor(String tablebordercolor) {
		this.tablebordercolor = tablebordercolor; 
	}

	public void setStressfont(String stressfont) {
		this.stressfont = stressfont; 
	}

	public void setNewfont(String newfont) {
		this.newfont = newfont; 
	}

	public void setTabelwidth(String tabelwidth) {
		this.tabelwidth = tabelwidth; 
	}

	public void setMainleftwidth(String mainleftwidth) {
		this.mainleftwidth = mainleftwidth; 
	}

	public void setLeftline(String leftline) {
		this.leftline = leftline; 
	}

	public void setRightline(String rightline) {
		this.rightline = rightline; 
	}

	public void setLines_in_page(String lines_in_page) {
		this.lines_in_page = lines_in_page; 
	}
	public String getUser_id() {
		return (this.user_id); 
	}

	public String getFont() {
		return (this.font); 
	}

	public String getDefaultfont() {
		return (this.defaultfont); 
	}

	public String getDefaulttitlefont() {
		return (this.defaulttitlefont); 
	}

	public String getHeaderfont() {
		return (this.headerfont); 
	}

	public String getBgcolor() {
		return (this.bgcolor); 
	}

	public String getFontcolor() {
		return (this.fontcolor); 
	}

	public String getLinkcolor() {
		return (this.linkcolor); 
	}

	public String getLinkstyle() {
		return (this.linkstyle); 
	}

	public String getOnmousecolor() {
		return (this.onmousecolor); 
	}

	public String getOnmouselink() {
		return (this.onmouselink); 
	}

	public String getOnmousestyle() {
		return (this.onmousestyle); 
	}

	public String getTitlecolor() {
		return (this.titlecolor); 
	}

	public String getTitlefontcolor() {
		return (this.titlefontcolor); 
	}

	public String getCatfontcolor() {
		return (this.catfontcolor); 
	}

	public String getTablecolor() {
		return (this.tablecolor); 
	}

	public String getFirstrowcolor() {
		return (this.firstrowcolor); 
	}

	public String getTworowcolor() {
		return (this.tworowcolor); 
	}

	public String getBbscolor() {
		return (this.bbscolor); 
	}

	public String getBbslinkcolor() {
		return (this.bbslinkcolor); 
	}

	public String getTablebordercolor() {
		return (this.tablebordercolor); 
	}

	public String getStressfont() {
		return (this.stressfont); 
	}

	public String getNewfont() {
		return (this.newfont); 
	}

	public String getTabelwidth() {
		return (this.tabelwidth); 
	}

	public String getMainleftwidth() {
		return (this.mainleftwidth); 
	}

	public String getLeftline() {
		return (this.leftline); 
	}

	public String getRightline() {
		return (this.rightline); 
	}

	public String getLines_in_page() {
		return (this.lines_in_page); 
	}

	public String toString() {
		String ret = null;
		ret = "user_id = " + user_id + "\n";
		ret += "font = " + font + "\n";
		ret += "defaultfont = " + defaultfont + "\n";
		ret += "defaulttitlefont = " + defaulttitlefont + "\n";
		ret += "headerfont = " + headerfont + "\n";
		ret += "bgcolor = " + bgcolor + "\n";
		ret += "fontcolor = " + fontcolor + "\n";
		ret += "linkcolor = " + linkcolor + "\n";
		ret += "linkstyle = " + linkstyle + "\n";
		ret += "onmousecolor = " + onmousecolor + "\n";
		ret += "onmouselink = " + onmouselink + "\n";
		ret += "onmousestyle = " + onmousestyle + "\n";
		ret += "titlecolor = " + titlecolor + "\n";
		ret += "titlefontcolor = " + titlefontcolor + "\n";
		ret += "catfontcolor = " + catfontcolor + "\n";
		ret += "tablecolor = " + tablecolor + "\n";
		ret += "firstrowcolor = " + firstrowcolor + "\n";
		ret += "tworowcolor = " + tworowcolor + "\n";
		ret += "bbscolor = " + bbscolor + "\n";
		ret += "bbslinkcolor = " + bbslinkcolor + "\n";
		ret += "tablebordercolor = " + tablebordercolor + "\n";
		ret += "stressfont = " + stressfont + "\n";
		ret += "newfont = " + newfont + "\n";
		ret += "tabelwidth = " + tabelwidth + "\n";
		ret += "mainleftwidth = " + mainleftwidth + "\n";
		ret += "leftline = " + leftline + "\n";
		ret += "rightline = " + rightline + "\n";
		ret += "lines_in_page = " + lines_in_page + "\n";
		return ret;
	}
	
	public void setUserStyle(String userid) {
		beanGetdata bgd = new beanGetdata();
		try {
			bgd.executeSelect("SELECT * FROM t_userstyle WHERE user_id = '" + userid + "'");
			this.user_id = bgd.getFieldValue("user_id", 0);
			this.font = bgd.getFieldValue("font", 0);
			this.defaultfont = bgd.getFieldValue("defaultfont", 0);
			this.defaulttitlefont = bgd.getFieldValue("defaulttitlefont", 0);
			this.headerfont = bgd.getFieldValue("headerfont", 0);
			this.bgcolor = bgd.getFieldValue("bgcolor", 0);
			this.fontcolor = bgd.getFieldValue("fontcolor", 0);
			this.linkcolor = bgd.getFieldValue("linkcolor", 0);
			this.linkstyle = bgd.getFieldValue("linkstyle", 0);
			this.onmousecolor = bgd.getFieldValue("onmousecolor", 0);
			this.onmouselink = bgd.getFieldValue("onmouselink", 0);
			this.onmousestyle = bgd.getFieldValue("onmousestyle", 0);
			this.titlecolor = bgd.getFieldValue("titlecolor", 0);
			this.titlefontcolor = bgd.getFieldValue("titlefontcolor", 0);
			this.catfontcolor = bgd.getFieldValue("catfontcolor", 0);
			this.tablecolor = bgd.getFieldValue("tablecolor", 0);
			this.firstrowcolor = bgd.getFieldValue("firstrowcolor", 0);
			this.tworowcolor = bgd.getFieldValue("tworowcolor", 0);
			this.bbscolor = bgd.getFieldValue("bbscolor", 0);
			this.bbslinkcolor = bgd.getFieldValue("bbslinkcolor", 0);
			this.tablebordercolor = bgd.getFieldValue("tablebordercolor", 0);
			this.stressfont = bgd.getFieldValue("stressfont", 0);
			this.newfont = bgd.getFieldValue("newfont", 0);
			this.tabelwidth = bgd.getFieldValue("tabelwidth", 0);
			this.mainleftwidth = bgd.getFieldValue("mainleftwidth", 0);
			this.leftline = bgd.getFieldValue("leftline", 0);
			this.rightline = bgd.getFieldValue("rightline", 0);
			this.lines_in_page = bgd.getFieldValue("lines_in_page", 0);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			bgd.destory();
		}
	}
}