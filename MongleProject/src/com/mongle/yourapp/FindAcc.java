package com.mongle.yourapp;

import java.io.FileReader;
import java.security.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.xml.crypto.dsig.SignedInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.sign.SignUp;
import com.mongle.sign.Validate;
import com.mongle.view.MongleVisual;

public class FindAcc {
	public static void findAcc() {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuMove("계정 찾기");
		MongleVisual.menuHeader("아이디 / 비밀번호 재설정");

		System.out.printf("\n%22s1. 아이디 찾기", " ");
		System.out.printf("\n%22s2. 비밀번호 재설정", " ");
		System.out.printf("\n%22s0. 이전으로", " ");

		System.out.println();
		MongleVisual.choiceGuidePrint();
		String choice = scan.nextLine();

		if (choice.equals("1")) {
			findMyId();
		} else if (choice.equals("2")) {
			findMyPw();
		} else if (choice.equals("0")) {
			StartPage.startPage();
		}else {
			MongleVisual.wrongInput();
		}
	}

	private static void findMyPw() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		int count = 1;
		boolean loop = true;
		try {
			do {
				System.out.printf("\n%22s아이디(ID) 입력: ", " ");
				user.setId(scan.nextLine());
				count++;
				choicePrint(count);
			}while(!findMatch(user.getId()));
			
			String phone;
			do {
				System.out.printf("\n%22s(예시: 01033448899)"," ");
				System.out.printf("\n%22s전화번호: "," ");	
				phone = scan.nextLine();
				if(phone.contains("-")||phone.contains(" ")||phone.contains(".")) {
					phone = phone.replace("-", "");
					phone = phone.replace(" ", "");
					phone = phone.replace(".", "");
				}
			}while(!Validate.validPhone(phone));
			user.setPhone(phone);
			String name;
			do {
				System.out.printf("\n%22s(2~5자리 한글)\n"," ");
				System.out.printf("\n%22s이름: "," ");	
				name = scan.nextLine();
				}
				while(!Validate.validName(name));
			user.setName(name);
			
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			do {
				String checkID = "";
				String checkPhone = "";
				String checkName = "";
				for (HashMap obj : list) {

					if (obj.get("id").equals(user.getId())) {
						checkPhone = (String) (obj).get("phone");
						checkID = (String) (obj).get("id");
						checkName = (String) (obj).get("name");

						if (user.getName().equals(checkName) && user.getId().equals(checkID)
								&& user.getPhone().equals(checkPhone)) {
							String setPW ;
							do {
								System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n"," ");
								System.out.printf("\n%22s변경할 비밀번호 입력: ", " ");
								setPW = scan.nextLine();
							} while(!Validate.validPw(setPW));
							System.out.printf("\n%22s변경할 비밀번호 다시입력: ", " ");
							String checksetPW = scan.nextLine();
							if (setPW.equals(checksetPW)) {
								Encrypt encrypt = new Encrypt();
								String finPw = Encrypt.encrypt(setPW);
								(obj).replace("salt", Encrypt.AbcSalt);
								(obj).replace("pw", finPw);
								System.out.printf("\n%22s비밀번호 변경완료\r\n", " ");
								MongleVisual.stopper();
								MongleVisual.pusher();
								loop = false;
								return;
							}else {
								MongleVisual.wrongInput();
							}
							break;
						}
						break;
					}
				}
			} while (loop==true);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}

	public static void choicePrint(int count) {
		if(count==3) {
			System.out.printf("\n%22s계속 하시겠습니까? Y/N : ", " ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			if(input.equals("y")||input.equals("Y")){
				findMyPw();
			}else if(input.equals("n")||input.equals("N")) {
				findAcc();
			}else {
				System.out.printf("\n%22s잘못 입력하셨습니다", " ");
				choicePrint(3);
			}
		}
	}
	
	public static boolean findMatch(String id) {
		
		for (HashMap userData : DataBase.getUser()) {
			// if (userData.containsValue(id)) {
			if (id.equals(userData.get("id"))) {
				return true; // 중복된 ID가 있음
			}
		}
		MongleVisual.wrongInput();
		return false; // 중복된 ID가 없음
	}

	private static void findMyId() {
		UserData user = new UserData();
		Scanner scan = new Scanner(System.in);
		try {
			String phone;
			do {
				System.out.printf("\n%22s(예시: 01033448899)"," ");
				System.out.printf("\n%22s전화번호: "," ");	
				phone = scan.nextLine();
				if(phone.contains("-")||phone.contains(" ")||phone.contains(".")) {
					phone = phone.replace("-", "");
					phone = phone.replace(" ", "");
					phone = phone.replace(".", "");
				}
			}while(!Validate.validPhone(phone));
			user.setPhone(phone);
			String name;
			do {
				System.out.printf("\n%22s(2~5자리 한글)\n"," ");
				System.out.printf("\n%22s이름: "," ");	
				name = scan.nextLine();
				}
				while(!Validate.validName(name));
			user.setName(name);
			boolean check = true;
			JSONParser parser = new JSONParser();
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			do {
				String checkPhone = "";
				String checkName = "";
				String findID = "";
				for (Object obj : list) {
					if (((JSONObject) obj).get("name").equals(user.getName())) {
						findID = (String) ((JSONObject) obj).get("id");
						checkPhone = (String) ((JSONObject) obj).get("phone");
						checkName = (String) ((JSONObject) obj).get("name");
						if (user.getName().equals(checkName) && user.getPhone().equals(checkPhone)) {
							System.out.printf("\n%22s아이디는 %s입니다\r\n", " ", findID);
							check = false;
							break;
						}else {
							//System.out.printf("\n%22s유저 정보를 찾을수 없습니다. 다시 입력해주세요", " ");
							MongleVisual.wrongInput();
							findMyId();
						}
					}
				}
			} while (check==true);
		} catch (Exception e) {
			System.out.println("FindAcc.findAcc");
			e.printStackTrace();
		}
	}

}
