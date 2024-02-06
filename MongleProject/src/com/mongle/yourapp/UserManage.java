package com.mongle.yourapp;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.service.BlackList;
import com.mongle.sign.SignUp;
import com.mongle.view.MongleVisual;

public class UserManage {
	public static void userManage() {
		MongleVisual.menuMove("회원관리 관리");
		MongleVisual.menuHeader("회원관리 관리");
		System.out.printf("\n%22s1. 이름 수정\r\n", " ");
		System.out.printf("\n%22s2. 회원 강제 탈퇴\r\n", " ");
		System.out.printf("\n%22s0. 이전으로\r\n", " ");
		System.out.println();
		Scanner scan = new Scanner(System.in);
		MongleVisual.choiceGuidePrint();
		String choice = scan.nextLine();
		if(choice.equals("1")) {
			changeName();
		}else if(choice.equals("2")) {
			BlackList.kickUser();
		}else {
			return;
		}
	}

	public static void changeName() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		boolean check = true;
		
		try {
//			JSONParser parser = new JSONParser();
//			//JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
//			JSONArray list = (JSONArray) DataBase.getUser();
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			do {
				System.out.printf("\n%22s해당 아이디 입력: ", " ");
				String inputID = scan.nextLine();
				System.out.printf("\n%22s해당 계정의 전화번호 입력: ", " ");
				String inputPhone = scan.nextLine();
				String findID = "";
				String checkPhone = "";
				for (HashMap obj : list) {
					if ((obj).get("id").equals(inputID)) {
						findID = (String) (obj).get("id");
						checkPhone = (String) (obj).get("phone");
						if(findID.equals(inputID)&&checkPhone.equals(inputPhone)) {
							System.out.printf("\n%22s변경할 이름 입력: ", " ");
							String newNAME = scan.nextLine();
							System.out.printf("\n%22s변경할 이름 다시입력: ", " ");
							String newNAMECheck = scan.nextLine();
							if(newNAME.equals(newNAMECheck)) {
								( obj).replace("name", newNAME);
								System.out.println((obj).get("name"));
								DataBase.dataSave();
								System.out.printf("\n%22s변경 완료\r\n", " ");
								check = false;
							}
						}else {
							System.out.printf("\n%22s해당 유저를 찾을수 없습니다  ", " ");
						}
					}
				}
				
				
			} while (check == true);
		}catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
}
