package com.mongle.resource;

public class UserData {
	
	private String level = "1";
	private String id;
	private String pw;
	private String name;
	
	
	public String getLevel() {
		return level;
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}