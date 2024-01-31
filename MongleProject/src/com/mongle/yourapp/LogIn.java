package com.mongle.yourapp;

<<<<<<< HEAD
import java.util.Scanner;

import com.mongle.database.DataBase;
=======
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;
import java.lang.reflect.Member;
import java.security.Identity;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
>>>>>>> origin/05
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class LogIn {
<<<<<<< HEAD
	
	public static UserData mainUser;

	public static void LogIn() {

		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("로그인");
		UserData coreUser = new UserData();
		do {

			String id;
			String pw;

			System.out.printf("\n%22s아이디: "," ");
			
			id = scan.nextLine();

			System.out.printf("\n%22s비밀번호: "," ");
			pw = scan.nextLine();

//			coreUser = mainUser(id, pw, coreUser);

			if (coreUser == null) {
				System.out.printf("\n%22s로그인 실패"," ");
			}

		} while (coreUser == null);
		
		mainUser = coreUser;
=======

	//public static UserData mainUser;

	public static void LogIn() throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		Scanner scan = new Scanner(System.in);
		
		JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
		
		MongleVisual.menuHeader("로그인");
		UserData coreUser = new UserData();
		
		String checkID = "";
		String checkPW = "";
		
		String id = null;
		String pw = null;
		
		do {

			

			System.out.printf("\n%22s아이디: ", " ");

			id = scan.nextLine();

			System.out.printf("\n%22s비밀번호: ", " ");
			pw = scan.nextLine();
			
			
			
			for (Object obj : list) {
				if (((JSONObject)obj).get("ID").equals(id) ) {
					checkID = (String) ((JSONObject)obj).get("ID");
					System.out.println(checkID);
					checkPW = (String) ((JSONObject)obj).get("비밀번호");
					System.out.println(checkPW);
				}
						
			}
			
			if(checkID.equals(id)&&checkPW.equals(Encrypt.encrypt(pw))) {
				System.out.printf("\n%22s로그인 성공\r\n", " ");
			}else {
				System.out.printf("\n%22s로그인 실패\r\n", " ");
			}

		} while (!checkID.equals(id)||!checkPW.equals(pw));

		//mainUser = coreUser;
>>>>>>> origin/05

	}

//	private static UserData mainUser(String id, String pw, UserData mainUser) {
//		for (UserData userData : DataBase.getUserList()) {
//
//			if (id.equals(userData.getId()) && pw.equals(userData.getPw())) {
//				mainUser = userData;
//				System.out.printf("\n%22s로그인 성공\r\n"," ");
//				return mainUser;
//
//			}
//			
//		}
//		return null;
//	}
}
