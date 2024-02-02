package com.mongle.yourapp;

import java.io.FileReader;
import java.security.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.xml.crypto.dsig.SignedInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.sign.SignUp;
import com.mongle.sign.Validate;
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

		if (choice.equals("1")) {
			findMyId();
		} else if (choice.equals("2")) {
			findMyPw();
		} else if (choice.equals("0")) {
			StartPage.startPage();
		}
	}

	private static void findMyPw() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		try {
			System.out.printf("\n%22s아이디(ID) 입력: ", " ");
			user.setId(scan.nextLine());
			
			String phone = SignUp.phoneCheck();
			user.setPhone(phone);
			
			String name = SignUp.nameCheck();
			user.setName(name);
			
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			do {
				String checkID = "";
				String checkPhone = "";
				String checkName = "";
				for (HashMap obj : list) {

					if (obj.get("id").equals(user.getId())) {
						checkPhone = (String) (obj).get("phone");
						checkID = (String) (obj).get("id");
						checkName = (String) (obj).get("name");

						if (user.getName().equals(checkName) && user.getId().equals(checkID)
								&& user.getPhone().equals(checkPhone)) {
							System.out.printf("\n%22s변경할 비밀번호 입력: ", " ");
							String setPW = scan.nextLine();
							System.out.printf("\n%22s변경할 비밀번호 다시입력: ", " ");
							String checksetPW = scan.nextLine();
							if (setPW.equals(checksetPW)) {
								Encrypt encrypt = new Encrypt();
								String finPw = Encrypt.encrypt(setPW);
								(obj).replace("salt", Encrypt.AbcSalt);
								(obj).replace("pw", finPw);
								System.out.printf("\n%22s비밀번호 변경완료", " ");
								loop = false;
								break;
							}else {
								System.out.printf("\n%22s다시입력하세요\n", " ");
							}
						}
						break;
					}
				}
			} while (loop);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}

	private static void findMyId() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		try {
			String phone = SignUp.phoneCheck();
			user.setPhone(phone);
			String name = SignUp.nameCheck();
			user.setName(name);
			
			JSONParser parser = new JSONParser();
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			do {
				String checkPhone = "";
				String checkName = "";
				String findID = "";
				for (Object obj : list) {
					if (((JSONObject) obj).get("name").equals(user.getName())) {
						findID = (String) ((JSONObject) obj).get("id");
						checkPhone = (String) ((JSONObject) obj).get("phone");
						checkName = (String) ((JSONObject) obj).get("name");
					}
				}
				if (user.getName().equals(checkName) && user.getPhone().equals(checkPhone)) {
					System.out.printf("\n%22s아이디: %s", " ", findID);
					break;
				}
			} while (true);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}

}
