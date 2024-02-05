package com.mongle.service.invest;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class Fund {

	public static int fundService() {

		boolean loop = true;
		int r = -1;

		Scanner scan = new Scanner(System.in);
		System.out.println();

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("펀드");

			System.out.println();

			String header = "+---------------------------------+";
			System.out.printf("%22s%s\n", " ", header);
			System.out.printf("%22s|펀드상품 검색은 브라우저를 통해\t|\n", " ");
			System.out.printf("%22s|외부 사이트를 이용합니다.\t|\n", " ");
			System.out.printf("%22s%s\n", " ", header);

			System.out.println();

			System.out.printf("%22s1. 펀드상품 검색\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();

			if (sel.equals("1")) {
				try {
					ProcessBuilder builder = new ProcessBuilder();
					builder.command("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe",
							"https://www.fundguide.net/Fund/SimpleSearch");
					builder.start();
				} catch (Exception e) {
					System.out.println("Fund.main");
					e.printStackTrace();
				}
			} else if (sel.equals("9")) {
				MongleVisual.menuMove("홈 화면");
				return 9;
			} else if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			} else {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			}

		}
		return 0;

	}// Fund

}
