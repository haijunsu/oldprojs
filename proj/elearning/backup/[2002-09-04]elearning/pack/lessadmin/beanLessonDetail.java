package lessadmin;


public class beanLessonDetail
{
	private String classId;
	private String className;
	private String classType;
	private String keywords;
	private String description;
	private String classTime;
	private String passStander;
	private String setTime;
	private String peoples;
	private String passCount;
	private String classStatus;

	public beanLessonDetail() {
		this.classId = "";
		this.className = "";
		this.classType = "0";
		this.keywords = "";
		this.description = "";
		this.classTime = "0";
		this.passStander = "0";
		this.setTime = "0";
		this.peoples = "0";
		this.passCount = "0";
		this.classStatus = "0";
	}

	public beanLessonDetail(String classId, String className, String classType, 
				String keywords, String description, String classTime, 
				String passStander, String setTime, String peoples, String passCount, 
				String classStatus) {
		this.classId = classId;
		this.className = className;
		this.classType = classType;
		this.keywords = keywords;
		this.description = description;
		this.classTime = classTime;
		this.passStander = passStander;
		this.setTime = setTime;
		this.peoples = peoples;
		this.passCount = passCount;
		this.classStatus = classStatus;
	}
	
	public void setClassId(String classId) {
		this.classId = classId; 
	}

	public void setClassName(String className) {
		this.className = className; 
	}

	public void setClassType(String classType) {
		this.classType = classType; 
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords; 
	}

	public void setDescription(String description) {
		this.description = description; 
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime; 
	}

	public void setPassStander(String passStander) {
		this.passStander = passStander; 
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime; 
	}

	public void setPeoples(String peoples) {
		this.peoples = peoples; 
	}

	public void setPassCount(String passCount) {
		this.passCount = passCount; 
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus; 
	}
	public String getClassId() {
		return (this.classId); 
	}

	public String getClassName() {
		return (this.className); 
	}

	public String getClassType() {
		return (this.classType); 
	}

	public String getKeywords() {
		return (this.keywords); 
	}

	public String getDescription() {
		return (this.description); 
	}

	public String getClassTime() {
		return (this.classTime); 
	}

	public String getPassStander() {
		return (this.passStander); 
	}

	public String getSetTime() {
		return (this.setTime); 
	}

	public String getPeoples() {
		return (this.peoples); 
	}

	public String getPassCount() {
		return (this.passCount); 
	}

	public String getClassStatus() {
		return (this.classStatus); 
	}

	public String toString() {
		String ret = null;
		ret = "classId = " + classId + "\n";
		ret += "className = " + className + "\n";
		ret += "classType = " + classType + "\n";
		ret += "keywords = " + keywords + "\n";
		ret += "description = " + description + "\n";
		ret += "classTime = " + classTime + "\n";
		ret += "passStander = " + passStander + "\n";
		ret += "setTime = " + setTime + "\n";
		ret += "peoples = " + peoples + "\n";
		ret += "passCount = " + passCount + "\n";
		ret += "classStatus = " + classStatus + "\n";
		return ret;
	}


}