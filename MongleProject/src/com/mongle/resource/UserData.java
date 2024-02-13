package com.mongle.resource;

/**
 * 유저 클래스
 */
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

	/**
	 * 이용약관 동의 Getter
	 * 
	 * @return 이용약관 동의 여부
	 */
	public String getUserAgree() {
		return userAgree;
	}

	/**
	 * 정보수집동의서 Getter
	 * 
	 * @return 정보수집동의 여부
	 */
	public String getInfoAgree() {
		return infoAgree;
	}

	/**
	 * 신용점수 Getter
	 * 
	 * @return 신용점수
	 */
	public String getCredScore() {
		return credScore;
	}

	/**
	 * 안심송금서비스 설정값 Getter
	 * 
	 * @return 안심송금서비스 설정값
	 */
	public String getSafeSendSetting() {
		return safeSendSetting;
	}

	/**
	 * 포인트 Getter
	 * 
	 * @return 포인트
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * Id Getter
	 * 
	 * @return Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 회원 레벨 Getter
	 * 
	 * @return 1==일반 회원, 2==관리자, 3==블랙리스트 회원
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 비밀번호 Getter
	 * 
	 * @return 비밀번호
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * 이름 Getter
	 * 
	 * @return 이름
	 */
	public String getName() {
		return name;
	}

	/**
	 * 생년월일 Getter
	 * 
	 * @return 생년월일
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * 번호 Getter
	 * 
	 * @return 번호
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Id Setter
	 * 
	 * @param id Id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 비밀번호 Setter
	 * 
	 * @param pw 비밀번호
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}

	/**
	 * 이름 Setter
	 * 
	 * @param name 이름
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 생년월일 Setter
	 * 
	 * @param birth 생년월일
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * 번호 Setter
	 * 
	 * @param phone 번호
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 포인트 Setter
	 * 
	 * @param point 포인트
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * 회원 레벨 Setter
	 * 
	 * @param level 레벨
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 신용점수 Setter
	 * 
	 * @param credScore 신용점수
	 */
	public void setCredScore(String credScore) {
		this.credScore = credScore;
	}

	/**
	 * 안심송금서비스 설정 Setter
	 * 
	 * @param safeSendSetting 안심송금서비스 설정값
	 */
	public void setSafeSendSetting(String safeSendSetting) {
		this.safeSendSetting = safeSendSetting;
	}

	/**
	 * 이용약관 동의 Setter
	 * 
	 * @param userAgree 이용약관 동의
	 */
	public void setUserAgree(String userAgree) {
		this.userAgree = userAgree;
	}

	/**
	 * 정보수집동의서 Setter
	 * 
	 * @param infoAgree 정보수집동의서
	 */
	public void setInfoAgree(String infoAgree) {
		this.infoAgree = infoAgree;
	}

}