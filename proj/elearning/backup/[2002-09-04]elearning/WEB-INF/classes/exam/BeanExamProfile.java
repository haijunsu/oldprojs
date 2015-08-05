//////////////////////////////////
//
//   BeanExamProfile 考试课目设置题目
//
//             苏海军
//
//////////////////////////////////

package exam;

import com.htyz.beanGetdata;
import com.htyz.beanElearnTools;
import javax.servlet.http.HttpServletRequest;

public class BeanExamProfile {
	private String[] examNum;
	private String[] mark;

	public BeanExamProfile() {
		//init
	}
	
	//带参数初始化
	public BeanExamProfile(String classId) throws Exception {
		initExamProfile(classId);	
	}
	
	//
	private void initExamProfile(String classId) throws Exception {
		beanGetdata bgd = new beanGetdata();
		beanGetdata bgd0 = new beanGetdata();
		
		String sql = "SELECT * FROM t_examprofile WHERE class_id = '" + classId + "' ORDER BY mark_types";
		bgd.executeSelect(sql);
		sql = "SELECT DISTINCT topic_mark FROM t_test WHERE class_id = '" + classId + "' ORDER BY topic_mark";
		bgd0.executeSelect(sql);
		
		examNum = new String[bgd0.getRowCount() + 1];
		mark = new String[bgd0.getRowCount() + 1];
		
		for ( int i=0; i<bgd0.getRowCount(); i++) {
			mark[i] = bgd0.getFieldValue("topic_mark", i);
			examNum[i] = "0";
			for ( int j=0; j<bgd.getRowCount(); j++) {
				if ( mark[i].equals(bgd.getFieldValue("mark_types", j).trim())) {
					examNum[i] = bgd.getFieldValue("topic_num", j);
					break;
				}
			}
		}
		
		mark[bgd0.getRowCount()] = "time";
		examNum[bgd0.getRowCount()] = "0";
		for ( int j=0; j<bgd.getRowCount(); j++) {
			if ( mark[bgd0.getRowCount()].equals(bgd.getFieldValue("mark_types", j).trim())) {
				examNum[bgd0.getRowCount()] = bgd.getFieldValue("topic_num", j);
				break;
			}
		}

		return;
	}
	
	//获取某类弄题目考试数量
	public String getExamNum(int i) throws Exception {
		return examNum[i];
	}

	//获取某类弄题目考试数量
	public String getMark(int i) throws Exception {
		return mark[i];
	}
	
	//获取选择项目数
	public int getTypesCount() throws Exception {
		return mark.length;
	}

	//更新考题设置
	public boolean update(HttpServletRequest req) throws Exception {
		//删除
		//添加
		String classId = getParameter( req, "classid", "");
		if (classId.equals(""))
			throw new Exception("没有找到课程代码！");
//		int markCount = Integer.parseInt( getParameter( req, "markCount", "0"));
		int markCount = getTypesCount();
		for ( int i=0; i<markCount - 1; i++) {
			mark[i] = getParameter( req, "mark" + Integer.toString(i), "0");
			examNum[i] = getParameter( req, "examNum" + Integer.toString(i), "0");
		}
		mark[markCount - 1] = getParameter( req, "mark" + Integer.toString(markCount - 1), "0");;
		examNum[markCount - 1] = getParameter( req, "examNum" + Integer.toString(markCount - 1), "0");
		String sql = "DELETE FROM t_examprofile WHERE class_id = '" + classId + "'";
		beanGetdata bgd = new beanGetdata();
		bgd.execute(sql);
		
		for ( int i=0; i<markCount; i++) {
			sql = "INSERT INTO t_examprofile (class_id, mark_types, topic_num) VALUES ("
				+ "'" + classId + "', '" + mark[i] + "', " + examNum[i] + ")";
			bgd.execute(sql);
		}

		return true;
	}
	
	private String getParameter( HttpServletRequest req, String parameter, String defaultValue) {
		return req.getParameter(parameter)==null?defaultValue:req.getParameter(parameter);
	}

}