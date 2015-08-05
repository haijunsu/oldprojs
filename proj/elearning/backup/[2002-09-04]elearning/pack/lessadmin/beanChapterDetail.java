package lessadmin;


public class beanChapterDetail
{
	private String classId;
	private String lessonId;
	private String lessonName;
	private String lessonUrl;


	public beanChapterDetail() {
		this.classId = "";
		this.lessonId = "";
		this.lessonName = "";
		this.lessonUrl = "";
	}

	public beanChapterDetail(String classId, String lessonId, String lessonName, 
				String lessonUrl) {
		this.classId = classId;
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.lessonUrl = lessonUrl;
	}
	
	public String getClassId() {
		return (this.classId); 
	}

	public void setClassId(String classId) {
		this.classId = classId; 
	}

	public String getLessonId() {
		return (this.lessonId); 
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId; 
	}

	public String getLessonName() {
		return (this.lessonName); 
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName; 
	}

	public String getLessonUrl() {
		return (this.lessonUrl); 
	}

	public void setLessonUrl(String lessonUrl) {
		this.lessonUrl = lessonUrl; 
	}

	public String toString() {
		String ret = null;
		ret = "classId = " + classId + "\n";
		ret += "lessonId = " + lessonId + "\n";
		ret += "lessonName = " + lessonName + "\n";
		ret += "lessonUrl = " + lessonUrl + "\n";
		return ret;
	}

}