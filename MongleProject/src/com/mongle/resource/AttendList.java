package com.mongle.resource;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

/**
 * 출석 멤버 클래스
 */
public class AttendList {

	private ArrayList<String> attenddate; // 출석일 리스트
	private String stratedate; // 연속 출석일
	private String emoji; // 출석 마크 이모지

	/**
	 * 출석 정보 리스트
	 */
	public static ArrayList<AttendList> list = new ArrayList<>();

	/**
	 * 출석 정보 리스트 생성자
	 */
	public AttendList(ArrayList<String> attenddate, String stratedate, String emoji) {
		this.attenddate = attenddate;
		this.stratedate = stratedate;
		this.emoji = emoji;
	}

	/**
	 * 포인트 획득
	 */
	public static void getPoint() {
		String point = (String) DataBase.getPrivateUser().get(0).get("point");
		point = (Integer.parseInt(point) + 10) + "";

		DataBase.getPrivateUser().get(0).replace("point", point);
	}

	/**
	 * 출석일 리스트 Getter
	 * 
	 * @return 출석일 리스트
	 */
	public ArrayList<String> getAttenddate() {
		return attenddate;
	}

	/**
	 * 연속 출석일 Getter
	 * 
	 * @return 연속 출석일
	 */
	public String getStratedate() {
		return stratedate;
	}

	/**
	 * 출석일 리스트 Setter
	 * 
	 * @return attenddate 출석일 리스트
	 */
	public void setAttenddate(ArrayList<String> Attenddate) {
		this.attenddate = Attenddate;
	}

	/**
	 * 연속 출석일 Setter
	 * 
	 * @return stratedate 연속 출석일
	 */
	public void setStratedate(String stratedate) {
		this.stratedate = stratedate;
	}

	/**
	 * 출석 마크 Getter
	 * 
	 * @return 출석 마크
	 */
	public String getEmoji() {
		return emoji;
	}

	/**
	 * 출석 마크 Setter
	 * 
	 * @return emoji 출석 마크
	 */
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	/**
	 * 출석 toString() 
	 * String.format("[출석일=%s, 연속출석일=%s]", attenddate, stratedate);
	 */
	@Override
	public String toString() {
		return String.format("[출석일=%s, 연속출석일=%s]", attenddate, stratedate);
	}

}
