package test;

import java.util.Scanner;

public class Asset {

	static String assetTitle = "계좌 관리";

	public static void main(String[] args) {
		menuHeader(assetTitle);
		
	}
	

	public static void menuHeader(String titleName) {
		// 메뉴 헤더 화면
		
		System.out.printf("%22s===================================\n", " ");
		System.out.printf("%42s\n", titleName);
		System.out.printf("%22s===================================\n", " ");
		System.out.println();
		System.out.printf("%25s 1.계좌개설", " ");
		System.out.printf("%10s 2.계좌조회\n", " ");
		System.out.printf("%25s 3.계좌해지", " ");
		System.out.printf("%10s 4.이자계산기\n", " ");
		System.out.printf("%25s 9.홈으로", " ");
		System.out.printf("%10s 0.이전으로\n", " ");
		System.out.printf("%22s번호를 입력하세요:"," ");
		choice();
	}// menuHeader

	public static void choice() {

		Scanner sc = new Scanner(System.in);
		String sel = sc.nextLine();

		if (sel.equals("1")) {
			DepoSave.depoSave();
			
		} else if (sel.equals("2")) {
			OpenAccount oa = new OpenAccount();
			oa.OpenDeposit();
		} else if (sel.equals("3")) {

		} else if (sel.equals("4")) {

		} else if (sel.equals("9")) {

		} else if (sel.equals("0")) {
		
		}
		

	}// choice
public static void pause() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("계속하려면 엔터를 입력하세요.");
		sc.nextLine(); 
		System.out.println(); //위 아래 구분 위해 엔터 하나씩 넣어놓음
	}
}// class
