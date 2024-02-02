package com.mongle.yourapp;

import java.io.FileReader;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class FindAcc {
	public static void findAcc() {
		Scanner scan = new Scanner(System.in); 
		
		MongleVisual.pusher();
		MongleVisual.menuHeader("아이디 / 비밀번호 찾기");

		
		System.out.printf("\n%22s1. 아이디 찾기", " ");
		System.out.printf("\n%22s2. 비밀번호 찾기", " ");
		System.out.printf("\n%22s0. 이전으로", " ");
		
		String choice = scan.nextLine();
		
		if(choice.equals("1")) {
			findMyId();
		}else if (choice.equals("2")) {
			findMyPw();
		}else if (choice.equals("0")) {
			StartPage.startPage();
		}
	}
	private static void findMyPw() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		try {
			System.out.printf("\n%22s아이디(ID) 입력: ", " ");
			user.setId(scan.nextLine());
			System.out.printf("\n%22s전화번호 입력: ", " ");
			user.setPhone(scan.nextLine());
			System.out.printf("\n%22s이름 입력: ", " ");
			user.setName(scan.nextLine());
			do {
				JSONParser parser = new JSONParser();
				JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
				String checkID = "";
				String checkPhone = "";
				String checkName = "";
				String findPw = "";
				for (Object obj : list) {
					if (((JSONObject)obj).get("id").equals(user.getId()) ) {
						findPw = (String)((JSONObject)obj).get("pw"); 
						checkPhone = (String) ((JSONObject)obj).get("phone");
						checkID = (String) ((JSONObject)obj).get("id");
						checkName = (String) ((JSONObject)obj).get("name");
					}
				}
				if(user.getName().equals(checkName)&&user.getId().equals(checkID)&&user.getPhone().equals(checkPhone)) {
					System.out.printf("\n%22s비밀번호: %s", " ",findPw);
					break;
				}
			}while(true);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}
	private static void findMyId() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		try {
			System.out.printf("\n%22s전화번호 입력: ", " ");
			user.setPhone(scan.nextLine());
			System.out.printf("\n%22s이름 입력: ", " ");
			user.setName(scan.nextLine());
			do {
				JSONParser parser = new JSONParser();
				JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
				String checkPhone = "";
				String checkName = "";
				String findPw = "";
				for (Object obj : list) {
					if (((JSONObject)obj).get("id").equals(user.getId()) ) {
						findPw = (String)((JSONObject)obj).get("pw"); 
						checkPhone = (String) ((JSONObject)obj).get("phone");
						checkName = (String) ((JSONObject)obj).get("name");
					}
				}
				if(user.getName().equals(checkName)&&user.getPhone().equals(checkPhone)) {
					System.out.printf("\n%22s비밀번호: %s", " ",findPw);
					break;
				}
			}while(true);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}
	
}
