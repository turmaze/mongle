package com.mongle.service.mypage;

import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

public class EditInfo {
	
	public static int edit() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
		
		MongleVisual.menuHeader("개인정보 조회 및 수정");
		HashMap<String, Object> userData = new HashMap<String, Object>();
		for (int i = 0; i < DataBase.getPrivateUser().size(); i++) {

			for (Object key : DataBase.getPrivateUser().get(i).keySet()) {
				userData.put((String) key, DataBase.getPrivateUser().get(i).get((String) key));
			}
		}
		
		System.out.printf("%22s아이디 : %s\n", " ", userData.get("id"));
		System.out.printf("%22s이름 : %s\n", " ", userData.get("name"));
		System.out.printf("%22s생년월일 : %s\n", " ", userData.get("birth"));
		System.out.printf("%22s전화번호 : %s\n", " ", userData.get("phone"));
		System.out.println();
		
		System.out.printf("%22s9. 홈으로\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");

		String sel = scan.nextLine();
		if (sel.equals("1")) {

		} else if (sel.equals("9")) {
			return 9;
		} else if (sel.equals("0")) {
			return 0;
		}
	}
		return 0;
		
	}
}
