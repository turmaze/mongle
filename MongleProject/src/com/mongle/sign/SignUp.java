package com.mongle.sign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.view.MongleVisual;
import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.service.InfoConsent;
import com.mongle.yourapp.Encrypt;


public class SignUp {
	public static void signUp() {
		Scanner scan = new Scanner(System.in);
		UserData userData = new UserData();
		HashMap<String, Object> newUser = new HashMap<String, Object>();  

		
		MongleVisual.menuHeader("회원가입");
	
		//----------------id----------------
		do {
			System.out.printf("\n%22s(4~12자리 영문과 숫자 조합)\n"," ");
			System.out.printf("%22s1.아이디: "," ");		
			userData.setId(scan.nextLine());
		}
		while(!Validate.validId(userData.getId()));
		newUser.put("id", userData.getId());
		DataBase.setUser(newUser);  // HashMap을 ArrayList에 추가
		

		
		//----------------pw----------------
		String pw1, pw2;
		do {
			pw1 = pwCheck();
	
			System.out.printf("\n%22s3.비밀번호 재확인: "," ");		
			pw2 = scan.nextLine();
			
			//DataBase.user pw 추가
			if (pw1.equals(pw2)) {
				Encrypt encrypt = new Encrypt();
				String finPw = Encrypt.encrypt(pw2); //pw 암호화
				
				newUser.put("salt", Encrypt.AbcSalt); //get salt
				newUser.put("pw", finPw);  // 유효한 비밀번호를 HashMap에 저장
				//DataBase.setUser(newUser);  // HashMap을 ArrayList에 추가
				break;
			} else {
				System.out.printf("\n%22s비밀번호가 다릅니다.\n\n", " ");
			}
			
		} while(!pw1.equals(pw2));
		
		//pw1을 DataBase.java의 HashMap("비밀번호",pw1)으로 저장하고 HashMap을 DataBase.java의 Arraylist에 저장하는 코드			// System.out.println(m.matches()); //testcode

		
		//----------------infoConsent
		
			MongleVisual.menuHeader("동의서");
			String ua = Integer.toString(InfoConsent.useConsent("이용약관"));
			String ia =Integer.toString(InfoConsent.useConsent("개인정보 수집이용 동의서"));
			userData.setUserAgree(ua);
			userData.setInfoAgree(ia);
			newUser.put("useragree", userData.getUserAgree());
			newUser.put("infoagree", userData.getInfoAgree());
		
		//----------------name----------------
		String name; 
		name = nameCheck();
		userData.setName(name);
		newUser.put("name", userData.getName());
		
		//----------------birth----------
		String birth;
		birth = birthCheck();
		userData.setBirth(birth);
		newUser.put("birth", userData.getBirth());
		
		//사용자 레벨 설정
		newUser.put("level",userData.getLevel());
		
		//--------phoneNumber------
		String phone;
		phone = phoneCheck();
		userData.setPhone(phone);
		newUser.put("phone", userData.getPhone());
		
		
		
		
		//---------------CredScore---------
		
		int s = (int)(Math.random()*400+599);
		String score = Integer.toString(s);
		userData.setCredScore(score);
		newUser.put("credscore", userData.getCredScore());
		
		//---------------SafeSendSetting---------
		newUser.put("safesendsetting", userData.getSafeSendSetting());
		
		//---------------Point---------
		
		userData.setPoint("0");
		newUser.put("point", userData.getPoint());
		
		
		System.out.println("newuser = "+ newUser);
		
		//----------------final----------------
		DataBase.dataSave();
		
		//test
		System.out.printf("\n\n%22snow folder Test\n", " ");
		System.out.println(DataBase.getUser());
		
		MongleVisual.stopper();
		
	}

	public static String pwCheck() {
		Scanner scan = new Scanner(System.in);
		String pw1;
		do {
			System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n"," ");
			System.out.printf("%22s2.비밀번호: "," ");		
			pw1 = scan.nextLine();
		} while(!Validate.validPw(pw1));
		return pw1;
	}

	public static String phoneCheck() {
		Scanner scan = new Scanner(System.in);
		String phone;
		do {
			System.out.printf("\n%22s(예시: 01033448899)"," ");
			System.out.printf("\n%22s3. 전화번호: "," ");	
			phone = scan.nextLine();
			if(phone.contains("-")||phone.contains(" ")||phone.contains(".")) {
				phone = phone.replace("-", "");
				phone = phone.replace(" ", "");
				phone = phone.replace(".", "");
			}
			//System.out.println(phone); //testcode
			}
			while(!Validate.validPhone(phone));
		return phone;
	}

	public static String nameCheck() {
		Scanner scan = new Scanner(System.in);
		String name;
		do {
			System.out.printf("\n%22s(2~5자리 한글)\n"," ");
			System.out.printf("\n%22s3.이름: "," ");	
			name = scan.nextLine();
			}
			while(!Validate.validName(name));
		return name;
	}

	public static String birthCheck() {
		Scanner scan = new Scanner(System.in);
		String birth;
		do {
			System.out.printf("\n%22s(예시 19990314)\n"," ");
			System.out.printf("\n%22s3. 생년월일: "," ");	
			birth = scan.nextLine();
		}while(!Validate.validBirth(birth));
		return birth;
	}

}

