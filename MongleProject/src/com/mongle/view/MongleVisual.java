package com.mongle.view;

public class MongleVisual {
	
	public static void pusher() {
		for (int i=0; i<30; i++) {
			System.out.println();
		}
	}

	private static void menuList(String menu, int menuType) {
		// menuType 0 -> 가운데 + 세로 한줄
		// menuType 1 -> 가운데 + 세로 두줄
		// menuType 2 -> 위에 + 세로 한줄
		
		// menu -> 변수명 ex) 투자관리, 대출관리
		
		
		
	}

	public static void menuHeader(String titleName) {
		// 메뉴 헤더 화면
		System.out.printf("%22s===================================\n"," ");
		System.out.printf("%40s\n",titleName);
		System.out.printf("%22s===================================\n"," ");
		
	}

	private static void menuPage() {
		// 로그인 후 메뉴 화면
	}

	private static void findAccPage() {
		//계정 찾기 화면
		
	}

	private static void signUpPage() {
		//회원가입 화면
	}

	private static void loginPage() {
		//로그인 화면
	}

	private static void startPage() {
		//첫 실행화면
		
	}
	
	
	
	
}
