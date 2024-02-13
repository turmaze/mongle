package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.sign.SignUp;
import com.mongle.view.MongleVisual;
/**
 * 시작 화면 클래스
 */
public class StartPage {
	/**
	 * 시작 메뉴
	 */
	public static void startPage() {
		Scanner scan = new Scanner(System.in);

		boolean loop = true;
		String choice = "";

		while (loop) {
			
			String tit = """
                            _____                 _      
                           |     | ___  ___  ___ | | ___ 
                           | | | || . ||   || . || || -_|
                           |_|_|_||___||_|_||_  ||_||___|
                                            |___|        
			""";
			System.out.println(tit);
			MongleVisual.menuHeader("시작화면");
			System.out.printf("\n%22s1. 로그인", " ");
			System.out.printf("\n%22s2. 회원가입", " ");
			System.out.printf("\n%22s3. ID/PW 찾기", " ");
			System.out.printf("\n%22s0. 프로그램 종료\n", " ");
			MongleVisual.choiceGuidePrint();

			choice = scan.nextLine();

			if (choice.equals("1")) {
				LogIn.logIn();
			} else if (choice.equals("2")) {
				SignUp.signUp();
			} else if (choice.equals("3")) {
				FindAcc.findAcc();
			} else if (choice.equals("0")) {
				System.out.printf("%22s프로그램을 종료합니다.", " ");
				return;
			} else {
				MongleVisual.wrongInput();
			}
			
			MongleVisual.pusher();
		}
	}
}
