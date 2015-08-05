//////////////////////////////////////////////
//
//  用户信息模块
//
//     苏海军     2002.2.24
//
//////////////////////////////////////////////
package system;

public class beanUserInfo
{
	String Userid;
	String UserName;
	String Password;
	String Birthday;
	String Sex;
	String Email;
	String Phone;
	String Company;//公司
	String strArea;//地区
	String strSignature;//签名
	String UserRight;//权限
	String Groupid;//组id
	String UserStatus;
	String RegTime = "asdfkjas;";
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
		Birthday = "";
		Sex = "";
		Email = "";
		Phone = "";
		Company = "";
		strArea = "";
		strSignature = "";
		UserRight = "3";
		Groupid = "0001";
		UserStatus = "0";
		RegTime = "";
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
	//Birthday
	public void setBirthday(String birthdayvalue)
	{
		Birthday = birthdayvalue;
	}
	public String getBirthday()
	{
		return Birthday;
	}
	//Company
	public void setCompany(String companyvalue)
	{
		Company = companyvalue;
	}
	public String getCompany()
	{
		return Company;
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
	//Phone
	public void setPhone(String phonevalue)
	{
		Phone = phonevalue;
	}
	public String getPhone()
	{
		return Phone;
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
	//Groupid
	public void setGroupid(String groupidvalue)
	{
		Groupid = groupidvalue;
	}
	public String getGroupid()
	{
		return Groupid;
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
	//Area
	public void setArea(String areavalue)
	{
		strArea = areavalue;
	}
	public String getArea()
	{
		return strArea;
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
	
}
