package com.mongle.resource;

public class UserData {
	
	private String level = "1";
	private String id;
	private String pw;
	private String name;
	private String birth;
	
	public String getId() {
		return id;
	}
	public String getLevel() {
		return level;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public String getBirth() {
		return birth;
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
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	
}