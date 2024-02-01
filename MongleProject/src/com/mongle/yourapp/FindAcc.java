package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class FindAcc {
	public static void findAcc() {
		Scanner scan = new Scanner(System.in); 
		
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
		
		try {
			
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
		
		
	}

	private static void findMyPw() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		System.out.printf("\n%22s아이디(ID) 입력: ", " ");
		user.setId(scan.nextLine());
		System.out.printf("\n%22s전화번호 입력: ", " ");
		user.setPhone(scan.nextLine());
		System.out.printf("\n%22s이름 입력: ", " ");
		user.setName(scan.nextLine());
		
		
		
	}

	private static void findMyId() {
		
		
	}
	
}
