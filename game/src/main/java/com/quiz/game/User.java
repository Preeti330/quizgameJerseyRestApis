package com.quiz.game;

public class User {
	
	public String username,mobile_no,access_token,password;
	public User() {
		
	}

	public User(String mobile_no, String password,String username) {
		super();
		this.mobile_no = mobile_no;
		this.password = password;
		this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
