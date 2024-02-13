package com.mongle.view;

import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;
import com.mongle.database.DataBase;

/**
 * 공용 view 클래스
 */
public class MongleVisual {
	/**
	 * 일정 간격 격차 넣기
	 */
	public static void pusher() {
		DataBase.dataSave();
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	/**
	 * 계속하시려면 엔터 문구 안내
	 */
	public static void stopper() {
		Scanner scan = new Scanner(System.in);
		DataBase.dataSave();
		System.out.printf("%22s계속하시려면 엔터를 눌러주세요\n", " ");
		scan.nextLine();
	}

	/**
	 * 올바르지 않은 입력 문구 안내
	 */
	public static void wrongInput() {
		System.out.println();
		System.out.printf("%22s입력이 올바르지 않습니다.\n\n", " ");
		stopper();
	}

	/**
	 * 완료 문구 안내
	 */
	public static void successPrint() {
		System.out.println();
		System.out.printf("%22s완료되었습니다.\n\n", " ");
		stopper();
	}

	/**
	 * 선택(번호) 안내
	 */
	public static void choiceGuidePrint() {
		System.out.println();
		System.out.printf("%22s선택(번호): ", " ");
	}

	/**
	 * 메뉴 이동 안내
	 * 
	 * @param menuName 이동할 메뉴 이름
	 */
	public static void menuMove(String menuName) {
		System.out.printf("\n%22s%s으로 이동합니다.\n\n", " ", menuName);
		stopper();
	}

	/**
	 * 메뉴 헤더
	 * 
	 * @param titleName 메뉴 헤더 이름
	 */
	public static void menuHeader(String titleName) {
		if (!titleName.equals("시작화면") && !titleName.equals("")) {
			pusher();
		}
		System.out.printf("%22s╔═════════════════════════════════╗\n", " ");
		System.out.printf("%40s\n", titleName);
		System.out.printf("%22s╚═════════════════════════════════╝\n", " ");

	}

}
