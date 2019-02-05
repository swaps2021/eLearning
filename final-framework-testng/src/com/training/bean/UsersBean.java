package com.training.bean;

public class UsersBean {
	private String Fname;
	private String Lname;
	private String email;
	private String phone;
	private String Uname;
	private String pwd;

	public UsersBean() {
	}

	public UsersBean(String Fname, String Lname, String email,String phone,String Uname,String pwd) {
		super();
		this.Fname = Fname;
		this.Lname = Lname;
		this.email=email;
		this.phone=phone;
		this.Uname=Uname;
		this.pwd=pwd;
	}

	public String getFirstName() {
		return Fname;
	}

	public void setFirstName(String Fname) {
		this.Fname = Fname;
	}

	public String getLastName() {
		return Lname;
	}

	public void setLastName(String Lname) {
		this.Lname = Lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getUserName() {
		return Uname;
	}

	public void setUserName(String Uname) {
		this.Uname = Uname;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "UsersBean [Fname=" + Fname + ", Lname=" + Lname + ", Email=" +email+",Phone="+phone+",Username="+Uname+", Password="+pwd+"]";
	
	}

}
