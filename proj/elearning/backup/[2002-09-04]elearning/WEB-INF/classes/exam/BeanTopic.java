//////////////////////////////////
//
//   BeanTopic 题目更新和删除
//
//             苏海军
//
//////////////////////////////////

package exam;

import com.htyz.beanGetdata;
import com.htyz.beanElearnTools;

public class BeanTopic {
	private BeanTopicQuestion topicQuestion;
	private BeanTopicAnswer topicAnswer;
	private beanGetdata bgd = new beanGetdata();
	private beanElearnTools ets = new beanElearnTools();

	public BeanTopic(BeanTopicQuestion topicQuestion) {
		this.topicQuestion = topicQuestion;
	}

	public BeanTopic(BeanTopicAnswer topicAnswer) {
		this.topicAnswer = topicAnswer;
	}
	
	public BeanTopicQuestion getTopicQuestion() {
		return (this.topicQuestion); 
	}

	public void setTopicQuestion(BeanTopicQuestion topicQuestion) {
		this.topicQuestion = topicQuestion; 
	}

	public BeanTopicAnswer getTopicAnswer() {
		return (this.topicAnswer); 
	}

	public void setTopicAnswer(BeanTopicAnswer topicAnswer) {
		this.topicAnswer = topicAnswer; 
	}

	//更新题库
	public void update() throws Exception {
		delete();
		insert();
	}
	
	//插入题目
	public void insert() throws Exception {
		String sql;
		
		sql = "INSERT INTO t_test " 
			+ "(topic_id, class_id, topic_type, topic_description, topic_mark, topic_status, user_id, topic_date) "
			+ "VALUES ("
			+ "'" + topicQuestion.getTopicId() + "', "
			+ "'" + topicQuestion.getClassId() + "', "
			+ "'" + topicQuestion.getTopicType() + "', "
			+ "'" + ets.check_quote(topicQuestion.getTopicDescription()) + "', "
			+ "" + topicQuestion.getTopicMark() + ", "
			+ "'" + topicQuestion.getTopicStatus() + "', "
			+ "'" + topicQuestion.getUserId() + "', "
			+ "'" + bgd.getDbDate() + "') ";
		bgd.execute(sql);
			
			 
		for (int i=0; i<topicQuestion.getAnswerCount(); i++) {
			sql = "INSERT INTO t_testlib  " 
				+ "(topic_id, topic_answer, answer_description, is_answer) "
				+ "VALUES ("
				+ "'" + topicQuestion.getAnswer(i).getTopicId() + "', "
				+ "'" + topicQuestion.getAnswer(i).getTopicAnswer() + "', "
				+ "'" + ets.check_quote(topicQuestion.getAnswer(i).getAnswerDescription()) + "', "
				+ "'" + topicQuestion.getAnswer(i).getIsAnswer() + "') ";
			bgd.execute(sql);
		}
	}
	
	//删除题目
	public void delete() throws Exception {
		String sql = "DELETE FROM t_testlib WHERE topic_id = '" + topicQuestion.getTopicId() + "'";
		bgd.execute(sql);
		sql = "DELETE FROM t_test WHERE topic_id = '" + topicQuestion.getTopicId() + "' AND class_id = '" + topicQuestion.getClassId()+ "'";
		bgd.execute(sql);
	}
	
	//添加一条答案
	public void insertAnswer() throws Exception {
		String sql;
		sql = "INSERT INTO t_testlib  " 
			+ "(topic_id, topic_answer, answer_description, is_answer) "
			+ "VALUES ("
			+ "'" + topicAnswer.getTopicId() + "', "
			+ "'" + topicAnswer.getTopicAnswer() + "', "
			+ "'" + ets.check_quote(topicAnswer.getAnswerDescription()) + "', "
			+ "'" + topicAnswer.getIsAnswer() + "') ";
		bgd.execute(sql);
	}

	//删除一条答案
	public void deleteAnswer() throws Exception {
		String sql;
		sql = "DELETE FROM t_testlib WHERE topic_id = '" + topicAnswer.getTopicId() + "' AND topic_answer = '" + topicAnswer.getTopicAnswer() + "'";
		bgd.execute(sql);
	}
}