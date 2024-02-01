package com.mongle.service.invest;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class Fund {

	public static void fundService() {

		boolean loop = true;

		Scanner scan = new Scanner(System.in);
		System.out.println();

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("펀드");

			System.out.println();

			String header = "+---------------------------------+";
			System.out.printf("%22s%s\n", " ", header);
			System.out.printf("%22s|\t펀드상품 검색은 브라우저를 통해\t|\n", " ");
			System.out.printf("%22s|   \t외부 사이트를 이용합니다.\t|\n", " ");
			System.out.printf("%22s%s\n", " ", header);

			System.out.println();

			System.out.printf("%40s\n", "1. 펀드상품 검색");
			System.out.printf("%40s\n", "9. 홈으로");
			System.out.printf("%40s\n", "0. 이전으로");
			System.out.printf("%40s", "선택(번호): ");

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
				loop = false;
			} else if (sel.equals("0")) {
				loop = false;
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

	}// Fund

}
