package aaqary.MobileApp.MainPackage;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable {
	String userName;
	String password;
	String fullName;
	String phoneNo;
	String mobileNo;
	String eMail;
	String creditNo;

	public User(String fullName, String phoneNo, String mobileNo, String eMail,
			String userName, String password, String creditNo) {
		this.userName = userName;
		this.password = password;
		this.creditNo = creditNo;
		this.fullName = fullName;
		this.phoneNo = phoneNo;
		this.mobileNo = mobileNo;
		this.eMail = eMail;
	}

	public String print() {
		String res = "";
		res += getUserName();
		res += ", ";
		res += getPassword();
		res += ", ";
		res += getFullName();
		res += ", ";
		res += getPhoneNo();
		res += ", ";
		res += getMobileNo();
		res += ", ";
		res += geteMail();
		res += ", ";
		res += getCreditNo();
		return res;
	}

	public ArrayList<String> getParams() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(getUserName());
		res.add(getPassword());
		res.add(getFullName());
		res.add(getPhoneNo());
		res.add(getMobileNo());
		res.add(geteMail());
		res.add(getCreditNo());
		return res;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public String getCreditNo() {
		if (creditNo.equals(""))
			return "N/A";
		return creditNo;
	}

	public void setCreditNO(String cNo) {
		this.creditNo = cNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNo() {
		if (phoneNo.equals(""))
			return "N/A";
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		if (mobileNo.equals(""))
			return "N/A";
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String geteMail() {
		if (eMail.equals(""))
			return "N/A";
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setAllUserData(ArrayList<String> data) {
		for (int i = 0; i < data.size(); i++) {
			if (i == 0)
				this.userName = data.get(i);
			else if (i == 1)
				this.password = data.get(i);
			else if (i == 2)
				this.fullName = data.get(i);
			else if (i == 3)
				this.phoneNo = data.get(i);
			else if (i == 4)
				this.mobileNo = data.get(i);
			else if (i == 5)
				this.eMail = data.get(i);
			else if (i == 6)
				this.creditNo = data.get(i);
		}
		for (int i = data.size(); i < 7; i++) {
			if (i == 0)
				this.userName = data.get(i);
			else if (i == 1)
				this.password = data.get(i);
			else if (i == 2)
				this.fullName = data.get(i);
			else if (i == 3)
				this.phoneNo = data.get(i);
			else if (i == 4)
				this.mobileNo = data.get(i);
			else if (i == 5)
				this.eMail = data.get(i);
			else if (i == 6)
				this.creditNo = data.get(i);
		}
	}

}
