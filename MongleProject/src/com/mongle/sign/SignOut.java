package com.mongle.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

public class SignOut {

	public static int signOutService() {
		Scanner scan = new Scanner(System.in);

		while (true) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("회원 탈퇴");

			System.out.println();

			System.out.printf("%22s1. 탈퇴\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				System.out.printf("%22s정말로 탈퇴 하시겠습니까?(y/n)", " ");
				String in = scan.nextLine();
				if (in.equals("y")) {
					System.out.printf("%22s탈퇴가 완료되었습니다.\n", " ");
					System.out.printf("%22s그동안 이용해 주셔서 감사합니다.", " ");
					setSignOut();
					System.exit(0);
					break;
				} else if (in.equals("n")) {
					System.out.printf("%22s탈퇴가 취소되었습니다.", " ");
					System.out.println();
				}

			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}
		return 0;
	}

	public static void setSignOut() {
		
		try {
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			for (HashMap obj : list) {
				if ((obj).get("id").equals(DataBase.getPrivateUser().get(0).get("id"))) {
					list.remove(obj);
					break;
				}
			}
		
			DataBase.dataSave();

		} catch (Exception e) {
			System.out.println("BlackList.addBlackList");
			e.printStackTrace();
		}
		
	}

}
