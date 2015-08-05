//////////////////////////////////
//
//   BeanTopicAnswer 问题答案表映射
//
//             苏海军
//
//////////////////////////////////

package exam;

public class BeanTopicAnswer {
	private String topicId;
	private String topicAnswer;
	private String answerDescription;
	private String isAnswer;

	public BeanTopicAnswer() {
		this.topicId = "";
		this.topicAnswer = "";
		this.answerDescription = "";
		this.isAnswer = "";
	}

	public BeanTopicAnswer(String topicId, String topicAnswer, 
				String answerDescription, String isAnswer) {
		this.topicId = topicId;
		this.topicAnswer = topicAnswer;
		this.answerDescription = answerDescription;
		this.isAnswer = isAnswer;
	}
	
	public String getTopicId() {
		return (this.topicId); 
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId; 
	}

	public String getTopicAnswer() {
		return (this.topicAnswer); 
	}

	public void setTopicAnswer(String topicAnswer) {
		this.topicAnswer = topicAnswer; 
	}

	public String getAnswerDescription() {
		return (this.answerDescription); 
	}

	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription; 
	}

	public String getIsAnswer() {
		return (this.isAnswer); 
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer; 
	}

	public String toString() {
		String ret = null;
		ret = "topicId = " + topicId + "\n";
		ret += "topicAnswer = " + topicAnswer + "\n";
		ret += "answerDescription = " + answerDescription + "\n";
		ret += "isAnswer = " + isAnswer + "\n";
		return ret;
	}
	
}