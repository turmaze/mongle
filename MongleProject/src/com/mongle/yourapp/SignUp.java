package com.mongle.yourapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.view.MongleVisual;
import com.mongle.database.DataBase;

public class SignUp {
	public static void signUp() {
		Scanner scan = new Scanner(System.in);
		
		MongleVisual.menuHeader("회원가입");
	
		//----------------id----------------
		String id;
		do {
		System.out.printf("\n%22s(4~12자리 영문과 숫자 조합)\n"," ");
		System.out.printf("%22s1.아이디: "," ");		
		id = scan.nextLine();
		}
		while(!DataBase.validId(id));

		
		//----------------pw----------------
		String pw1,pw2;
		do {
			do {
				System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n"," ");
				System.out.printf("%22s2.비밀번호: "," ");		
				pw1 = scan.nextLine();
			} while(!DataBase.validPw(pw1));
	
			System.out.printf("\n%22s3.비밀번호 재확인: "," ");		
			pw2 = scan.nextLine();
			
			//DataBase.user pw 추가
			if (pw1.equals(pw2)) { 
				DataBase.pwSave(pw2);
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
		
		
		
		//test
		
		
		
		
		scan.close();
	}

}



//System.out.printf("%22s(4~12자리 영문과 숫자 조합)\n"," ");
//System.out.printf("%22s1.아이디: "," ");		
//String id = scan.nextLine();
//
//while(!DataBase.validId(id)) {
//	System.out.printf("%22s(4~12자리 영문과 숫자 조합)\n"," ");
//	System.out.printf("%22s1.아이디: "," ");		
//	id = scan.nextLine();
//}
