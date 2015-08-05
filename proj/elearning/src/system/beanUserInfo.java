//////////////////////////////////////////////
//
//  用户信息模块
//
//     柳林     2002.12.19
//
//////////////////////////////////////////////
package system;
import com.htyz.*;

public class beanUserInfo
{
	String Userid;
	String UserName;
	String Password;
	String Repass;
	String Email;
	String Company;//公司
	String Department; //部门
	String Phone;
	String Groupid;//组id
	String UserRight;//权限
	String UserStatus;
	String RegTime;
	String Contract;
	String Sex;
	String Birthday;
	String Shengxiao;
	String Stars;
	String Webaddr;
	String Oicq;
	String Msn;
	String Country;
	String Pravency;
	String City;
	String Resume;
	String strSignature;//签名
	String Userimg;
	String Selfimg;
	String Selfwidth;
	String Selfheight;
	
	public beanUserInfo()
	{
		//初始化用户信息
		UserInit();
	}

	//给用户信息指定初始值
	public void UserInit()
	{
	 Userid = "";
	 UserName = "";
	 Password = "";
	 Email = "";
	 Company = "";//公司
	 Department = ""; //部门
	 Phone = "";
	 Groupid = "0001";//组id
	 UserRight = "3";//权限
	 UserStatus = "2";
	 RegTime = "";
	 Contract = "";
	 Sex = "";
	 Birthday = "";
	 Shengxiao = "";
	 Stars = "";
	 Webaddr = "";
	 Oicq = "";
	 Msn = "";
	 Country = "";
	 Pravency = "";
	 City = "";
	 Resume = "";
	 strSignature = ""; //签名
	 Userimg = "";
	 Selfimg = "";
	 Selfwidth = "";
	 Selfheight = "";
	}
	
	//初始化用户信息
	public void setUserInfo(String userid)
	{
		//用户信息缓冲
		beanGetdata userbgd = new beanGetdata();
		userbgd.executeSelect("SELECT * FROM t_user WHERE user_id = '" + userid + "'");
		if(userbgd.getRowCount()<1) {
			UserInit();
		}else {
			setUserid(userbgd.getFieldValue("user_id", 0));
			setUserName(userbgd.getFieldValue("user_name", 0));
			setPassword(userbgd.getFieldValue("user_pass", 0));
			setRepass(userbgd.getFieldValue("user_pass", 0));
			setEmail(userbgd.getFieldValue("email", 0));
			setCompany(userbgd.getFieldValue("company", 0));
			setDepartment(userbgd.getFieldValue("department", 0));
			setPhone(userbgd.getFieldValue("phone", 0));
			setContract(userbgd.getFieldValue("contract", 0));
			setSex(userbgd.getFieldValue("sex", 0));
			setBirthday(userbgd.getFieldValue("birthday", 0));
			setShengxiao(userbgd.getFieldValue("shengxiao", 0));
			setStars(userbgd.getFieldValue("stars", 0));
			setWebaddr(userbgd.getFieldValue("webaddr", 0));
			setOicq(userbgd.getFieldValue("oicq", 0));
			setMsn(userbgd.getFieldValue("msn", 0));
			setCountry(userbgd.getFieldValue("country", 0));
			setPravency(userbgd.getFieldValue("pravency", 0));
			setCity(userbgd.getFieldValue("city", 0));
			setResume(userbgd.getFieldValue("resume", 0));
			setSignature(userbgd.getFieldValue("signature", 0));
			setUserimg(userbgd.getFieldValue("user_img", 0));
			setSelfimg(userbgd.getFieldValue("self_img", 0));
			setSelfwidth(userbgd.getFieldValue("self_width", 0));
			setSelfheight(userbgd.getFieldValue("self_height", 0));
		
			setRegisterTime(userbgd.getFieldValue("register_time", 0));
			setGroupid(userbgd.getFieldValue("group_id", 0));
			setUserRight(userbgd.getFieldValue("user_right",0));
			setUserStatus(userbgd.getFieldValue("user_status", 0));
		}
	}

	//设置用户属性
	//Userid
	public void setUserid(String useridvalue)
	{
		Userid = useridvalue;
	}
	public String getUserid()
	{
		return Userid;
	}
	//UserName
	public void setUserName(String usernamevalue)
	{
		UserName = usernamevalue;
	}
	public String getUserName()
	{
		return UserName;
	}
	//Password
	public void setPassword(String passwordvalue)
	{
		Password = passwordvalue;
	}
	public String getPassword()
	{
		return Password;
	}
	//Repass
	public void setRepass(String repassvalue)
	{
		Repass = repassvalue;
	}
	public String getRepass()
	{
		return Repass;
	}
	//Email
	public void setEmail(String emailvalue)
	{
		Email = emailvalue;
	}
	public String getEmail()
	{
		return Email;
	}
	public void setCompany(String companyvalue)
	{
		Company = companyvalue;
	}
	public String getCompany()
	{
		return Company;
	}
	public void setDepartment(String departmentvalue)
	{
		Department = departmentvalue;
	}
	public String getDepartment()
	{
		return Department;
	}
	//Phone
	public void setPhone(String phonevalue)
	{
		Phone = phonevalue;
	}
	public String getPhone()
	{
		return Phone;
	}
	//Groupid
	public void setGroupid(String groupidvalue)
	{
		Groupid = groupidvalue;
	}
	public String getGroupid()
	{
		return Groupid;
	}
	//UserRight
	public void setUserRight(String userrightvalue)
	{
		UserRight = userrightvalue;
	}
	public String getUserRight()
	{
		return UserRight;
	}
	//UserStatus
	public void setUserStatus(String userstatusvalue)
	{
		UserStatus = userstatusvalue;
	}
	public String getUserStatus()
	{
		return UserStatus;
	}
	//RegTime
	public void setRegisterTime(String regtimevalue)
	{
		RegTime = regtimevalue;
	}
	public String getRegisterTime()
	{
		return RegTime;
	}
	//Contract
	public void setContract(String contractvalue)
	{
		Contract = contractvalue;
	}
	public String getContract()
	{
		return Contract;
	}
	//Sex
	public void setSex(String sexvalue)
	{
		Sex = sexvalue;
	}
	public String getSex()
	{
		return Sex;
	}
	//Birthday
	public void setBirthday(String birthdayvalue)
	{
		Birthday = birthdayvalue;
	}
	public String getBirthday()
	{
		return Birthday;
	}
	//Year of Birthday
	public String getYear()
	{
		if(Birthday.length()<10) {
			return "";
		}else{
			return Birthday.substring(0,4);
		}
	}
	//Month of Birthday
	public String getMonth()
	{
		if(Birthday.length()<10) {
			return "";
		}else{
			return Birthday.substring(5,7);
		}
	}
	//Month of Birthday
	public String getDay()
	{
		if(Birthday.length()<10) {
			return "";
		}else{
			return Birthday.substring(8,10);
		}
	}
	//Shengxiao
	public void setShengxiao(String sxvalue)
	{
		Shengxiao = sxvalue;
	}
	public String getShengxiao()
	{
		return Shengxiao;
	}
	//Stars
	public void setStars(String starsvalue)
	{
		Stars = starsvalue;
	}
	public String getStars()
	{
		return Stars;
	}
	//Webaddr
	public void setWebaddr(String webaddrvalue)
	{
		Webaddr = webaddrvalue;
	}
	public String getWebaddr()
	{
		return Webaddr;
	}
	//Oicq
	public void setOicq(String oicqvalue)
	{
		Oicq = oicqvalue;
	}
	public String getOicq()
	{
		return Oicq;
	}
	//Msn
	public void setMsn(String msnvalue)
	{
		Msn = msnvalue;
	}
	public String getMsn()
	{
		return Msn;
	}
	//Country
	public void setCountry(String countryvalue)
	{
		Country = countryvalue;
	}
	public String getCountry()
	{
		return Country;
	}
	//Pravency
	public void setPravency(String pravencyvalue)
	{
		Pravency = pravencyvalue;
	}
	public String getPravency()
	{
		return Pravency;
	}
	//City
	public void setCity(String cityvalue)
	{
		City = cityvalue;
	}
	public String getCity()
	{
		return City;
	}
	//Resume
	public void setResume(String resumevalue)
	{
		Resume = resumevalue;
	}
	public String getResume()
	{
		return Resume;
	}
	//Signature
	public void setSignature(String signaturevalue)
	{
		strSignature = signaturevalue;
	}
	public String getSignature()
	{
		return strSignature;
	}
	
	//Userimg
	public void setUserimg(String userimgvalue)
	{
		Userimg = userimgvalue;
	}
	public String getUserimg()
	{
		return Userimg;
	}
	//Selfimg
	public void setSelfimg(String selfimgvalue)
	{
		Selfimg = selfimgvalue;
	}
	public String getSelfimg()
	{
		return Selfimg;
	}
	//Sex
	public void setSelfwidth(String selfwidthvalue)
	{
		Selfwidth = selfwidthvalue;
	}
	public String getSelfwidth()
	{
		return Selfwidth;
	}
	//Sex
	public void setSelfheight(String selfheightvalue)
	{
		Selfheight = selfheightvalue;
	}
	public String getSelfheight()
	{
		return Selfheight;
	}
}
