package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.database.DataBase;
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
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class LogIn {
	
	public static String primaryKey;
	
	public static void logIn() {
		try {
			userLogin();
		} catch (Exception e) {
			System.out.println("LogIn.logIn");
			e.printStackTrace();
		}
	}
	private static String userLogin() {
		String checklevel = "" ; 
		try {
			
			JSONParser parser = new JSONParser();
			UserData user = new UserData();
			Scanner scan = new Scanner(System.in);
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			MongleVisual.menuHeader("로그인");
			String checkID = "";
			String checkPW = "";
			do {
				System.out.printf("\n%22s아이디: ", " ");
				user.setId(scan.nextLine()); 

//				System.out.printf("\n%22s비밀번호: ", " ");
//				user.setPw(scan.nextLine());
				
				
				for (Object obj : list) {
					if (((JSONObject)obj).get("id").equals(user.getId()) ) {
						checklevel = (String) ((JSONObject)obj).get("level");
						checkID = (String) ((JSONObject)obj).get("id");
						checkPW = (String) ((JSONObject)obj).get("pw");
					}
				}
				if(checkID.equals(user.getId())){//&&checkPW.equals(Encrypt.encrypt(user.getPw()))) {
					//로그인 상태 
					System.out.printf("\n%22s로그인 성공\r\n", " ");
					primaryKey = user.getId();
					System.out.println(primaryKey);
					MainMenu.mainMenu(checklevel);
					
				}else {
					System.out.printf("\n%22s로그인 실패\r\n", " ");
				}

			} while (!checkID.equals(user.getId()));//||!checkPW.equals(user.getPw()));
			
			return checklevel;
			
		} catch (Exception e) {
			System.out.println("LogIn.userLogin");
			e.printStackTrace();
			
		}
		
		return checklevel;
	}

//
}
