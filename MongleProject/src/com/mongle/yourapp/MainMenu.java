package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.main.Main;
import com.mongle.view.MongleVisual;

public class MainMenu {
	public static void MainMenu() {

		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("메인메뉴");

		if (LogIn.mainUser.getLevel().equals("1")) {
			userMenu();
		} else if (LogIn.mainUser.getLevel().equals("2")) {
			adminMenu();
		}
	}

	private static void userMenu() {
		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("메인메뉴");

		System.out.printf("\n%22s1. 자산관리", " ");
		System.out.printf("\n%22s2. 송금", " ");
		System.out.printf("\n%22s3. 투자", " ");
		System.out.printf("\n%22s4. 고객센터", " ");
		System.out.printf("\n%22s5. MyPage", " ");
		System.out.printf("\n%22s0. 로그아웃", " ");
		System.out.printf("\n\r\n%22s선택번호: ", " ");
		String choice = scan.nextLine();

		System.out.println(choice);

	}

	private static void adminMenu() {
		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("관리자 페이지");

		
		System.out.printf("\n%22s1. 회원 관리", " ");
		System.out.printf("\n%22s2. 데이터 관리", " ");
		System.out.printf("\n%22s3. 문의 처리", " ");
		System.out.printf("\n%22s0. 로그아웃", " ");
		

		System.out.printf("\n%22s선택번호: ", " ");
		String choice = scan.nextLine();

		System.out.println(choice);

	}
}
