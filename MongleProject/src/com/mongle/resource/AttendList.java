package com.mongle.resource;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

/*
 * 포인트 관리 파트 멤버 클래스 입니다.
 * */
public class AttendList {
	
	private ArrayList<String> attenddate; //출석일
	private String stratedate; //연속 출석일
	private String emoji; //출석 표시 이모지
	
	public static ArrayList<AttendList> list = new ArrayList<>();
	 	
	public void pointItem(ArrayList<String> attenddate,String stratedate) {
		this.attenddate = attenddate;
		this.stratedate = stratedate;
	}
	
	public AttendList(ArrayList<String> attenddate, String stratedate, String emoji) {
		this.attenddate = attenddate;
		this.stratedate = stratedate;
		this.emoji = emoji;
	}
	
	public static void getPoint() {
		String point = (String) DataBase.getPrivateUser().get(0).get("point");
		point = (Integer.parseInt(point)+10)+"";
		
		DataBase.getPrivateUser().get(0).replace("point", point);
	}

	public ArrayList<String> getAttenddate() {
		return attenddate;
	}
	
	public String getStratedate() {
		return stratedate;
	}

	public void setPointdate(ArrayList<String> pointdate) {
		this.attenddate = pointdate;
	}

	public void setStratedate(String stratedate) {
		this.stratedate = stratedate;
	}
	
	public String getEmoji() {
		return emoji;
	}
	
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	@Override
	public String toString() {
		return String.format("[출석일=%s, 연속출석일=%s]", attenddate, stratedate);
	}

}
