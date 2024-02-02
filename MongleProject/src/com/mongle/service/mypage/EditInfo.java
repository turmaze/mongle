package com.mongle.service.mypage;

import java.util.HashMap;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

public class EditInfo {
	
	public static void edit() {
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
		
		
	}
}
