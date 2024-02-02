package com.mongle.service.mypage;

import java.util.HashMap;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

public class EditInfo {
	public void edit() {
		MongleVisual.menuHeader("개인정보 조회 및 수정");
		HashMap<String, Object> userData = new HashMap<String, Object>();
		for (int i = 0; i < DataBase.getPrivateuser().size(); i++) {
			
			for (Object Key : DataBase.getPrivateuser().get(i).keySet()) {
				
			}
		}

//		System.out.printf("%22s아이디 : %s\n", " ", DataBase.getPrivateuser().);

	}
}
