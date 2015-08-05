//////////////////////////////////
//
//   BeanTopicQuestion 问题表映射
//
//             苏海军
//
//////////////////////////////////

package exam;

import java.util.Vector;

public class BeanTopicQuestion {
	private String topicId;
	private String classId;
	private String topicType;
	private String topicDescription;
	private String topicMark;
	private String topicStatus;
	private String userId;
	private String topicDate;
	private Vector answer;

	public BeanTopicQuestion() {
		this.topicId = "";
		this.classId = "";
		this.topicType = "";
		this.topicDescription = "";
		this.topicMark = "";
		this.topicStatus = "";
		this.userId = "";
		this.topicDate = "";
		this.answer = new Vector();
	}

	public BeanTopicQuestion(String topicId, String classId, String topicType, 
				String topicDescription, String topicMark, String topicStatus, 
				String userId, String topicDate) {
		this.topicId = topicId;
		this.classId = classId;
		this.topicType = topicType;
		this.topicDescription = topicDescription;
		this.topicMark = topicMark;
		this.topicStatus = topicStatus;
		this.userId = userId;
		this.topicDate = topicDate;
		this.answer = new Vector();
	}

	public void addAnswer(BeanTopicAnswer topicAnswer) {
		answer.addElement(topicAnswer);
	}
	
	public int getAnswerCount() {
		return answer.size();
	}
	
	public BeanTopicAnswer getAnswer(int i) {
		return (BeanTopicAnswer) answer.elementAt(i);
	}

	public String getTopicId() {
		return (this.topicId); 
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId; 
	}

	public String getClassId() {
		return (this.classId); 
	}

	public void setClassId(String classId) {
		this.classId = classId; 
	}

	public String getTopicType() {
		return (this.topicType); 
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType; 
	}

	public String getTopicDescription() {
		return (this.topicDescription); 
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription; 
	}

	public String getTopicMark() {
		return (this.topicMark); 
	}

	public void setTopicMark(String topicMark) {
		this.topicMark = topicMark; 
	}

	public String getTopicStatus() {
		return (this.topicStatus); 
	}

	public void setTopicStatus(String topicStatus) {
		this.topicStatus = topicStatus; 
	}

	public String getUserId() {
		return (this.userId); 
	}

	public void setUserId(String userId) {
		this.userId = userId; 
	}

	public String getTopicDate() {
		return (this.topicDate); 
	}

	public void setTopicDate(String topicDate) {
		this.topicDate = topicDate; 
	}

	public String toString() {
		String ret = null;
		ret = "topicId = " + topicId + "\n";
		ret += "classId = " + classId + "\n";
		ret += "topicType = " + topicType + "\n";
		ret += "topicDescription = " + topicDescription + "\n";
		ret += "topicMark = " + topicMark + "\n";
		ret += "topicStatus = " + topicStatus + "\n";
		ret += "userId = " + userId + "\n";
		ret += "topicDate = " + topicDate + "\n";
		ret += "answer = " + answer + "\n";
		return ret;
	}
	
}