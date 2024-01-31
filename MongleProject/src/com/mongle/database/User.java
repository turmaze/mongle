package com.mongle.database;

public class User {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private int count=0;
	
	
	public void setCount() {
		this.count++;
	}
	public int getCount() {
		return count;
	}
	//private String address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		setCount();
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
		setCount();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		setCount();
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		setCount();
		this.birth = birth;
	}
}
