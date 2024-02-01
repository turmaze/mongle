package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class SignOut {
	
	public static void signOutService() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			MongleVisual.menuHeader("회원 탈퇴");

			System.out.println();

			System.out.printf("%22s1. 탈퇴\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.printf("%22s선택(번호): ", " ");       
			
			System.out.println();

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				System.out.printf("%22s정말로 탈퇴 하시겠습니까?(y/n)", " ");
				String in = scan.nextLine();
				if (in.equals("y")) {
					System.out.printf("%22s탈퇴가 완료되었습니다.\n", " ");
					System.out.printf("%22s그동안 이용해 주셔서 감사합니다.", " ");
					break;
				} else if (in.equals("n")) {
					System.out.printf("%22s탈퇴가 취소되었습니다.", " ");
					System.out.println();
				}
				
			} else if (sel.equals("9")) {
				break;
			} else if (sel.equals("0")) {
				break;
			}
		}
	}
	
	public static void setSignOut() {
		
	}
	
}
