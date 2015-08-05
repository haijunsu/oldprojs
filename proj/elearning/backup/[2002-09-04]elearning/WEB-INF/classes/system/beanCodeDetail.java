package system;


public class beanCodeDetail
{
	private String codeId;
	private String codeValue;
	private String codeNamec;
	private String codeNamee;


	public beanCodeDetail() {
		this.codeId = "";
		this.codeValue = "";
		this.codeNamec = "";
		this.codeNamee = "";
	}


	public beanCodeDetail(String codeId, String codeValue, String codeNamec, 
				String codeNamee) {
		this.codeId = codeId;
		this.codeValue = codeValue;
		this.codeNamec = codeNamec;
		this.codeNamee = codeNamee;
	}
	
	public void setCodeId(String codeId) {
		this.codeId = codeId; 
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue; 
	}

	public void setCodeNamec(String codeNamec) {
		this.codeNamec = codeNamec; 
	}

	public void setCodeNamee(String codeNamee) {
		this.codeNamee = codeNamee; 
	}
	public String getCodeId() {
		return (this.codeId); 
	}

	public String getCodeValue() {
		return (this.codeValue); 
	}

	public String getCodeNamec() {
		return (this.codeNamec); 
	}

	public String getCodeNamee() {
		return (this.codeNamee); 
	}

	public String toString() {
		String ret = null;
		ret = "codeId = " + codeId + "\n";
		ret += "codeValue = " + codeValue + "\n";
		ret += "codeNamec = " + codeNamec + "\n";
		ret += "codeNamee = " + codeNamee + "\n";
		return ret;
	}

}