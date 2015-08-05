package system;


public class beanMenuDetail
{
	private String menuId;
	private String menuNamec;
	private String menuNamee;
	private String menuUrl;
	private String menuRight;


	public beanMenuDetail() {
		this.menuId = "";
		this.menuNamec = "";
		this.menuNamee = "";
		this.menuUrl = "";
		this.menuRight = "0";
	}

	public beanMenuDetail(String menuId, String menuNamec, String menuNamee, 
				String menuUrl, String menuRight) {
		this.menuId = menuId;
		this.menuNamec = menuNamec;
		this.menuNamee = menuNamee;
		this.menuUrl = menuUrl;
		this.menuRight = menuRight;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId; 
	}

	public void setMenuNamec(String menuNamec) {
		this.menuNamec = menuNamec; 
	}

	public void setMenuNamee(String menuNamee) {
		this.menuNamee = menuNamee; 
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl; 
	}

	public void setMenuRight(String menuRight) {
		if (menuRight.equals("")) menuRight = "0";
		this.menuRight = menuRight; 
	}
	public String getMenuId() {
		return (this.menuId); 
	}

	public String getMenuNamec() {
		return (this.menuNamec); 
	}

	public String getMenuNamee() {
		return (this.menuNamee); 
	}

	public String getMenuUrl() {
		return (this.menuUrl); 
	}

	public String getMenuRight() {
		return (this.menuRight); 
	}

	public String toString() {
		String ret = null;
		ret = "menuId = " + menuId + "\n";
		ret += "menuNamec = " + menuNamec + "\n";
		ret += "menuNamee = " + menuNamee + "\n";
		ret += "menuUrl = " + menuUrl + "\n";
		ret += "menuRight = " + menuRight + "\n";
		return ret;
	}
}