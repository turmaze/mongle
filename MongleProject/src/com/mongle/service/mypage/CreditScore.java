package com.mongle.service.mypage;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class CreditScore {
	
	public static void creditScoreService() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		while (loop) {
		
		MongleVisual.menuHeader("신용 점수");
		
		System.out.println();
		System.out.printf("%22s현재 신용 점수: \n", " ");
		System.out.println();
		
		System.out.printf("%22s9. 홈으로\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		System.out.printf("%22s선택(숫자): ", " ");
		String sel = scan.nextLine();
		
		if (sel.equals("9")) {
			break;
		} else if (sel.equals("0")) {
			break;
		}
		
		}
		
		System.out.printf("%22s계속하시려면 엔터를 눌러주세요.", " ");
		scan.nextLine();
		
		System.out.println();
		
	}
	
}
