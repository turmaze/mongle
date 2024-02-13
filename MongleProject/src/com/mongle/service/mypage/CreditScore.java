package com.mongle.service.mypage;

import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;
/**
 * 신용 점수 클래스
 */
public class CreditScore {

	/**
	 * 신용 점수 호출 및 신용 점수 팁 안내
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int creditScoreService() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {

			HashMap<String, Object> userData = new HashMap<String, Object>();
			for (int i = 0; i < DataBase.getPrivateUser().size(); i++) {

				for (Object key : DataBase.getPrivateUser().get(i).keySet()) {
					userData.put((String) key, DataBase.getPrivateUser().get(i).get((String) key));
				}
			}
			
			System.out.println();
			MongleVisual.menuHeader("\t현재 신용 점수 : "+userData.get("credscore"));
			System.out.printf("%30s◡̈신용점수 올리는 팁◡̈\n", " ");
			System.out.println();
			System.out.printf("%22s- 정기적으로 신용카드를 사용하세요.\n", " ");
			System.out.printf("%22s- 연체하지 마세요.\n", " ");
			System.out.printf("%22s- 대출은 자주 이용하지 마세요.\n", " ");
			System.out.printf("%22s- 사금융 이용은 자제하세요.\n", " ");
			System.out.printf("%22s- 주거래 은행의 신용거래 실적을 늘리세요.\n", " ");
			System.out.printf("%22s\n", " ");

			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();

			if (sel.equals("9")) {
				MongleVisual.menuMove("홈 화면");
				return 9;
			} else if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			}
		}
		return 0;

	}

}
