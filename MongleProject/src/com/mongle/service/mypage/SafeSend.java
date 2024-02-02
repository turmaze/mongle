package com.mongle.service.mypage;

import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class SafeSend {

	public static int safeSendService() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("안심송금");

			System.out.println();
			System.out.println();

			System.out.printf("%22s1. 사용 설정\n", " ");
			System.out.printf("%22s2. 미사용 설정\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.printf("%22s선택(숫자): ", " ");
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				System.out.printf("%22s안심송금 서비스 사용으로 설정되었습니다.\n", " ");
				
				for (HashMap map : DataBase.getPrivateUser()) {
					System.out.println(map);
				}
				
				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "1");
				}
				
				for (HashMap map : DataBase.getPrivateUser()) {
					System.out.println(map);
				}
				
				MongleVisual.stopper();
			} else if (sel.equals("2")) {
				System.out.printf("%22s안심송금 서비스 미사용으로 설정되었습니다.\n", " ");
				
				for (HashMap map : DataBase.getPrivateUser()) {
					System.out.println(map);
				}
				
				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "0");
				}
				
				for (HashMap map : DataBase.getPrivateUser()) {
					System.out.println(map);
				}
				
				MongleVisual.stopper();
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}

		}
		return 0;

	}

}
