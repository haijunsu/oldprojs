//////////////////////////////////
//
//   BeanExamProfile ���Կ�Ŀ������Ŀ
//
//             �պ���
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
	
	//��������ʼ��
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
	
	//��ȡĳ��Ū��Ŀ��������
	public String getExamNum(int i) throws Exception {
		return examNum[i];
	}

	//��ȡĳ��Ū��Ŀ��������
	public String getMark(int i) throws Exception {
		return mark[i];
	}
	
	//��ȡѡ����Ŀ��
	public int getTypesCount() throws Exception {
		return mark.length;
	}

	//���¿�������
	public boolean update(HttpServletRequest req) throws Exception {
		//ɾ��
		//���
		String classId = getParameter( req, "classid", "");
		if (classId.equals(""))
			throw new Exception("û���ҵ��γ̴��룡");
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