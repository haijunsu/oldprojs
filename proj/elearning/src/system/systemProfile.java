/**
 * ”≥…‰System.properties
 */

package system;

public class systemProfile {
	private String driver;
	private String datasource;
	private String datasourceurl;
	private String database;
	private String owner;
	private String userid;
	private String password; 
	private String lessonDir; 
	
	public String toString() {
		String ret = null;
		String separator = System.getProperties().getProperty("line.separator");
		ret = "driver = " + driver + separator;
		ret += "datasource = " + datasource + separator;
		ret += "datasourceurl = " + datasourceurl + separator;
		ret += "database = " + database + separator;
		ret += "owner = " + owner + separator;
		ret += "userid = " + userid + separator;
		ret += "password = " + password + separator;
		ret += "lessondir = " + lessonDir + separator;
		return ret;
	}


	public systemProfile(String driver, String datasource, String datasourceurl, 
				String database, String owner, String userid, String password, 
				String lessonDir) {
		this.driver = driver;
		this.datasource = datasource;
		this.datasourceurl = datasourceurl;
		this.database = database;
		this.owner = owner;
		this.userid = userid;
		this.password = password;
		this.lessonDir = lessonDir;
	}

	public systemProfile() {
		this.driver = "";
		this.datasource = "";
		this.datasourceurl = "";
		this.database = "";
		this.owner = "";
		this.userid = "";
		this.password = "";
		this.lessonDir = "";
	}
	
	public String getDriver() {
		return (this.driver); 
	}

	public void setDriver(String driver) {
		this.driver = driver; 
	}

	public String getDatasource() {
		return (this.datasource); 
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource; 
	}

	public String getDatasourceurl() {
		return (this.datasourceurl); 
	}

	public void setDatasourceurl(String datasourceurl) {
		this.datasourceurl = datasourceurl; 
	}

	public String getDatabase() {
		return (this.database); 
	}

	public void setDatabase(String database) {
		this.database = database; 
	}

	public String getOwner() {
		return (this.owner); 
	}

	public void setOwner(String owner) {
		this.owner = owner; 
	}

	public String getUserid() {
		return (this.userid); 
	}

	public void setUserid(String userid) {
		this.userid = userid; 
	}

	public String getPassword() {
		return (this.password); 
	}

	public void setPassword(String password) {
		this.password = password; 
	}

	public String getLessonDir() {
		return (this.lessonDir); 
	}

	public void setLessonDir(String lessonDir) {
		this.lessonDir = lessonDir; 
	}

}