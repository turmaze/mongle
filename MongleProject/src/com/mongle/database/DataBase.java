package com.mongle.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes.Name;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Stream;

class Rpattern{
	Pattern p1 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{4}-[0-9]{3}");//1.Kb
	Pattern p3 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{2}-[0-9]{3}");//2.ibk
	Pattern p4 = Pattern.compile("^[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{2}");//3.nh
	Pattern p10 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{6}-[0-9]{1}");//4.DGB
	Pattern p11 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}-[0-9]{2}");//5.BNK
	Pattern p2 = Pattern.compile("^[0-9]{6}-[0-9]{2}-[0-9]{6}");//1.kb
	Pattern p5 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{6}");//6.신한//6.SC
	Pattern p6 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{6}");//신한//케이벵크
	Pattern p7 = Pattern.compile("^[0-9]{4}-[0-9]{3}-[0-9]{6}");//우리
	Pattern p8 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{5}");//KEB
	Pattern p9 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{3}");//외환,씨티
	Pattern p12 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{7}");//카카오

	Pattern[]block4 = {p1,p3,p4,p10,p11};
	Pattern[]block3 = {p2,p5,p6,p7,p8,p9,p12};
	
	String [] bankblock4 = {"KB국민은행","IBK기업은행", "NH농협은행","DGB대구은행","BNK부산은행"};
	String [] bankblock3 = {"KB 국민은행","신한은행-SC제일은행","신한은행-케이뱅크","우리은행","KEB하나은행","씨티은행-외환은행","카카오벵크",};
}




public class DataBase {
	String name;
	String bank;
	String acc;
	String tel;
	String birth;
	String address;
	String id;
	String pw;
	static ArrayList<HashMap> user = new ArrayList<HashMap>();
	
	
	public static ArrayList<HashMap> getUser() {
		return user;
	}

	public static void setUser(HashMap<String,String> newUser) {
		DataBase.user.add(newUser);
	}

	private static void dataSave() {
		// TODO Auto-generated method stub

	}

	private static void dataLoad() {
		// TODO Auto-generated method stub

	}
	
private boolean validBirth(String input) {
		
		String [] birthSplit = input.split("/");
		int year = Integer.parseInt(birthSplit[0]);
		int month = Integer.parseInt(birthSplit[1]);
		String regex = "";
		
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			regex =  "^[1900-2020]{4}/[01-12]{2}/[01-31]{2}$";
		}else if(month==2){
			if(year%4==0) {
				regex =  "^[1900-2020]{4}/[01-12]{2}/[01-29]{2}$";
			}
			regex =  "^[1900-2020]{4}/[01-12]{2}/[01-28]{2}$";
		}else {
			regex =  "^[1900-2020]{4}/[01-12]{2}/[01-30]{2}$";
		}
		
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.println("잘못된 입력입니다.\n");
			System.out.println("도움이 필요하시면 고객센터에 문의 해주세요");
			return m.matches();
		} else {
			this.birth = input;
			return m.matches();
		} 
	
		
	
	
	
	}
	
	public static boolean validName (String input) {

		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.println("잘못된 입력입니다.\n");
			System.out.println("도움이 필요하시면 고객센터에 문의 해주세요");
			
			return m.matches();
		} else {
			HashMap<String, String> newUser = new HashMap<String, String>();
            newUser.put("이름", input);  // 사용자 아이디 저장
            user.add(newUser);  // ArrayList에 사용자 정보 추가
			return m.matches();
		}
	}

	private boolean validBank (String input) {
		
		//Rpattern p = new Rpattern();
		
		
		
		
	
	}

	public static boolean validAcc (String input) {
		
		Rpattern p = new Rpattern();
		
		int i = 0;
		
		ArrayList<String>bankName = new ArrayList<String>();
		
		String [] numSplit = input.split("-");
		if(numSplit.length==4) {
			for(Pattern indi : p.block4) {
				Matcher m = indi.matcher(input);
				boolean matchfound = m.find();
				if (matchfound) {
					bankName.add(p.bankblock4[i]);
					return m.matches();
				} 
				i++;
			}
		}else if(numSplit.length==3){
			for(Pattern indi : p.block3) {
				Matcher m = indi.matcher(input);
				boolean matchfound = m.find();
				if (matchfound) {
					bankName.add(p.bankblock3[i]);
					return m.matches();
				} 
				i++;
				
			}
		}else {
			System.out.println("계좌번호에 -를 알맞게 입력 부탁드립니다");
			return false;
		}
		
		//if(input )
		
		
		List<String>bankList = new ArrayList<String>();
		for(String name : bankName) {
			if(name.contains("-")) {
				bankList = Arrays.asList(name.split("-"));
			}
		}
		
		bankList.forEach(System.out::println);
		for(String name : bankList) {
			System.out.println(name);
		}
		HashMap<String, String> newUser = new HashMap<String, String>();
		newUser.put("계좌번호", input);  // 사용자 아이디 저장
        user.add(newUser);
		
        
		return true;
	}

	

	public static boolean validId(String input) {
		
		input = input.toLowerCase();	

		Pattern p = Pattern.compile("^[a-z0-9]{4,12}$");
		Matcher m = p.matcher(input);
			
		if(m.matches() && !isIdDuplicate(input)) {
			//System.out.println(m.matches()); //testcode
            HashMap<String, String> newUser = new HashMap<String, String>();
            newUser.put("아이디", input);  // 사용자 아이디 저장
            user.add(newUser);  // ArrayList에 사용자 정보 추가
			return m.matches();			
		}else {
			//System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력입니다.\n\n", " ");
			return m.matches();
		}				
	}//id

	private static boolean isIdDuplicate(String id) {
	        for (HashMap userData : user) {
	            if (userData.containsValue(id)) {
	                return true; // 중복된 ID가 있음
	            }
	        }
	        return false; // 중복된 ID가 없음
	    }

	public static boolean validPw(String input) {

		Pattern p = Pattern.compile("^[a-z0-9]{10,16}$");
		Matcher m = p.matcher(input);

		if (m.matches()) {			
			return m.matches();
		} else {
			// System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력입니다.\n\n", " ");
			return m.matches();
		}
		
	}//pw

//	private static boolean validTest(String object) {
//		//유효성 검사
//		/*	
//		 	이름	(name)		- Private String
//		 	은행 (bank) 		- Private String
//		 	계좌번호 (acc) 	- Private String
//		 	전화번호 (tel) 	- Private String
//		 	생년월일 (birth) - Private String
//		 	주소 (address) 	- Private String
//		 	아이디 (id) 		- Private String
//		 	비밀번호 (pw) 	- Private String
//		 */
//		
//		
//		return false;
//	}

	private static void get(ArrayList userData) {
		// 호출문

	}

}
