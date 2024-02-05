package com.mongle.service.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;
import com.mongle.database.DataBase;
import com.mongle.resource.AttendList;
import com.mongle.view.MongleVisual;

public class Point {
	
	public static int pointService() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			int r = -1;
			
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("포 인 트");

			System.out.println();
			System.out.printf("%22s보유 포인트: %,d", " "
					, Integer.parseInt((String) DataBase.getPrivateUser().get(0).get("point")));
			System.out.println();

			System.out.printf("%22s1. 포인트 내역\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				MongleVisual.menuMove("포인트 내역 화면");
				r = pointList();
				if (r==9) {
					return 9;
				}
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}

	}

	private static int pointList() {
		
		MongleVisual.menuHeader("포인트 내역");
		ArrayList<String> attenddate
		= AttendList.list.get(0).getAttenddate();
		
		for (int i=0; i<attenddate.size(); i++) {
			System.out.printf("%22s|%s  | 출석 |   +10point\t|\n", " ", attenddate.get(i));
		}
		
		MongleVisual.stopper();
			
		return 0;
	}
	
}
