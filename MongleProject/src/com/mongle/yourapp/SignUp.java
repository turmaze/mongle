package com.mongle.yourapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.view.MongleVisual;
import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.yourapp.Encrypt;


public class SignUp {
	public static void signUp() {
		Scanner scan = new Scanner(System.in);
		UserData userData = new UserData();
		HashMap<String, Object> newUser = new HashMap<>();  

		MongleVisual.menuHeader("회원가입");
	
		//----------------id----------------s
		do {
			System.out.printf("\n%22s(4~12자리 영문과 숫자 조합)\n"," ");
			System.out.printf("%22s1.아이디: "," ");		
			userData.setId(scan.nextLine());
		}
		while(!DataBase.validId(userData.getId()));
		newUser.put("ID", userData.getId());
		DataBase.setUser(newUser);  // HashMap을 ArrayList에 추가
		

		
		//----------------pw----------------
		String pw1, pw2;
		do {
			do {
				System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n"," ");
				System.out.printf("%22s2.비밀번호: "," ");		
				userData.setPw(scan.nextLine());
				pw1 = userData.getPw();
			} while(!DataBase.validPw(pw1));
	
			System.out.printf("\n%22s3.비밀번호 재확인: "," ");		
			pw2 = scan.nextLine();
			
			//DataBase.user pw 추가
			if (pw1.equals(pw2)) {
				
				String finPw = Encrypt.encrypt(pw2); //pw 암호화
				
				newUser.put("비밀번호", finPw);  // 유효한 비밀번호를 HashMap에 저장
				//DataBase.setUser(newUser);  // HashMap을 ArrayList에 추가
				break;
			} else {
				System.out.printf("\n%22s비밀번호가 다릅니다.\n\n", " ");
			}
			
		} while(!pw1.equals(pw2));
		
		//pw1을 DataBase.java의 HashMap("비밀번호",pw1)으로 저장하고 HashMap을 DataBase.java의 Arraylist에 저장하는 코드			// System.out.println(m.matches()); //testcode

		
		//----------------name----------------
		String name; 
		
		do {
			System.out.printf("\n%22s(2~5자리 한글)\n"," ");
			System.out.printf("\n%22s3.이름: "," ");	
			name = scan.nextLine();
			}
			while(!DataBase.validName(name));
		
		//----------------birth----------
		String birth;
		do {
			System.out.printf("\n%22s(예시 1999/03/14)\n"," ");
			System.out.printf("\n%22s3. 생년월일: "," ");	
			birth = scan.nextLine();
			}
			while(!DataBase.validBirth(birth));
		
		
		
		//---------------account---------
//String acc; 
//		
//		do {
//			System.out.printf("\n%22s(12~14 숫자(-입력 필수))\n"," ");
//			System.out.printf("\n%22s3.계좌번호: "," ");	
//			acc = scan.nextLine();
//			}
//			while(!DataBase.validAcc(acc));
//		
//		//----------------bank ---------
//		
//		do {
//			System.out.printf("\n%22s1\n"," ");
//			System.out.printf("\n%22s3.계좌번호: "," ");	
//			acc = scan.nextLine();
//			}
//			while(!DataBase.validAcc(acc));
		
		
		System.out.println("newuser = "+newUser);
		
		//----------------final----------------
		DataBase.dataSave();
		scan.close();
		
		//test
		System.out.printf("\n\n%22snow folder Test\n", " ");
		System.out.println(DataBase.getUser());
		
	}

}

