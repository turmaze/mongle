package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class LogIn {
	
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
