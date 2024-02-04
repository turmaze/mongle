package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.sign.SignUp;
import com.mongle.view.MongleVisual;

public class StartPage {
	public static void startPage() {
		Scanner scan = new Scanner(System.in);

		boolean loop = true;

		while (loop) {
			
			MongleVisual.pusher();

			MongleVisual.menuHeader("시작화면");
			System.out.printf("\n%22s1. 로그인", " ");
			System.out.printf("\n%22s2. 회원가입", " ");
			System.out.printf("\n%22s3. ID/PW 찾기", " ");
			System.out.printf("\n%22s0. 프로그램 종료\n", " ");
			MongleVisual.choiceGuidePrint();

			String choice = "";

			choice = scan.nextLine();

			if (choice.equals("1")) {
				LogIn.logIn();
			} else if (choice.equals("2")) {
				SignUp.signUp();
			} else if (choice.equals("3")) {
				FindAcc.findAcc();
			} else if (choice.equals("0")) {
				return;
			} else {
				MongleVisual.wrongInput();
			}
		}
	}
}
