package com.mongle.resource;

public class UserData {
	
	private String level = "1";
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String phone;
	private String credScore;
	private String safeSendSetting = "0";
	private String point;
	private String userAgree;
	private String infoAgree;
	




	public String getUserAgree() {
		return userAgree;
	}
	public String getInfoAgree() {
		return infoAgree;
	}
	public String getCredScore() {
		return credScore;
	}
	public String getSafeSendSetting() {
		return safeSendSetting;
	}
	public String getPoint() {
		return point;
	}
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
	public String getPhone() {
		return phone;
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
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	public void setCredScore(String credScore) {
		this.credScore = credScore;
	}
	public void setSafeSendSetting(String safeSendSetting) {
		this.safeSendSetting = safeSendSetting;
	}
	public void setUserAgree(String userAgree) {
		this.userAgree = userAgree;
	}
	public void setInfoAgree(String infoAgree) {
		this.infoAgree = infoAgree;
	}
	
	
}