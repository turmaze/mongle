package com.mongle.service.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.sign.SignUp;
import com.mongle.sign.Validate;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.Encrypt;
import com.mongle.yourapp.LogIn;

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

			System.out.printf("%22s1. 비밀번호 변경\n", " ");
			System.out.printf("%22s2. 전화번호 변경\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				changePw();
				loop = false;
			} else if (sel.equals("2")) {
				changePhone();
				loop = false;
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}
		return 0;

	}

	public static void changePhone() {
		Scanner scan = new Scanner(System.in);
		String setPhone;
		do {
			System.out.printf("%22s변경할 전화번호 입력: ", " ");
			setPhone = scan.nextLine();

			if (setPhone.contains("-") || setPhone.contains(" ") || setPhone.contains(".")) {
				setPhone = setPhone.replace("-", "");
				setPhone = setPhone.replace(" ", "");
				setPhone = setPhone.replace(".", "");
			}
		} while (!Validate.validPhone(setPhone));

		DataBase.changeData(DataBase.getPrivateUser(), "phone", setPhone);
		System.out.printf("%22s전화번호 변경완료\n", " ");
	}

	public static String check() {
		Scanner scan = new Scanner(System.in);
		String pw1;
		do {
			System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n", " ");
			System.out.printf("%22s변경할 비밀번호 입력: ", " ");
			pw1 = scan.nextLine();
		} while (!Validate.validPw(pw1));
		return pw1;
	}

	public static void changePw() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		try {
			do {
				String setPW = check();
				System.out.printf("%22s변경할 비밀번호 다시입력: ", " ");
				String checksetPW = scan.nextLine();
				if (setPW.equals(checksetPW)) {
					Encrypt encrypt = new Encrypt();
					String finPw = Encrypt.encrypt(setPW);
					DataBase.changeData(DataBase.getPrivateUser(), "salt", Encrypt.AbcSalt);
					DataBase.changeData(DataBase.getPrivateUser(), "pw", finPw);
					System.out.printf("\n%22s비밀번호 변경완료", " ");
					loop = false;
					break;
				} else {
					System.out.printf("\n%22s다시입력하세요\n", " ");
				}
			} while (loop);
		} catch (Exception e) {
			System.out.println("editInfo");
			e.printStackTrace();
		}
	}
}
