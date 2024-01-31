package com.mongle.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;


public class DataBase {

	static ArrayList<UserData> userList = new ArrayList<UserData>();	
	
	public static ArrayList<UserData> getUserList() {
		return userList;
	}

	public static void setUserList(UserData userData) {
		DataBase.userList.add(userData);
	}

	public static void dataSave() {
		
		try {
			
			UserData userData = new UserData();
			
			userData = userList.get(userList.size()-1);
			
			FileWriter writer = new FileWriter(ResourcePath.MEMBERFOLDER + "\\" + userData.getId() + ".dat");			
			writer.write(userData.getId());
			writer.write("\r\n");
			writer.write(userData.getPw());
			writer.write("\r\n");
			writer.write(userData.getName());
			writer.write("\r\n");
			writer.write("1");
			
			
			writer.close();
			
			
			
		} catch (Exception e) {
			System.out.println("DataBase.dataSave Error");
			e.printStackTrace();
		}

		
	}
	
	// 파일에서 사용자 데이터 읽기
	public static void dataLoad() {

        try {
        	
        	File dir = new File(ResourcePath.MEMBERFOLDER);
        	
        	for (File file : dir.listFiles()) {
                if (file.isFile()&&file.getName().contains(".dat")) {

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    UserData userData = new UserData();

                    userData.setId(reader.readLine());
                    userData.setPw(reader.readLine());
                    userData.setName(reader.readLine());
                    userData.setLevel(reader.readLine());

                    reader.close();

                    userList.add(userData);

                }
            }
        	
        	
        } catch (Exception e) {
        	System.out.println("DataBase.dataLoad Error");
            e.printStackTrace();
        }
    }


	public static boolean validId(String input) {
		
		input = input.toLowerCase();	

		Pattern p = Pattern.compile("^[a-z0-9]{4,12}$");
		Matcher m = p.matcher(input);
		
		if(m.matches() && !isIdDuplicate(input)) {
			//System.out.println(m.matches()); //testcode
            UserData newUser = new UserData();
            newUser.setId(input);  // 사용자 아이디 저장
            setUserList(newUser);;  // ArrayList에 사용자 정보 추가
			return m.matches();			
		}else {
			//System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력이거나 중복입니다.\n\n", " ");
			return false;
		}				
	}//id

	public static boolean isIdDuplicate(String id) {

        for (UserData userData : DataBase.userList) {
            //if (userData.containsValue(id)) {
            if(id.equals(userData.getId())) {
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
	
	public static void pwSave(String pw2) {
		
		getUserList().get(userList.size()-1).setPw(pw2); // ArrayList에 사용자 정보 추가
		
	}
	
	
	public static boolean validName (String input) {

		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
			
			return m.matches();
		} else {
            getUserList().get(userList.size()-1).setName(input);// ArrayList에 사용자 정보 추가
			return m.matches();
		}
	}//name
	

	
	public static boolean validBirth(String input) {
		
		
		
		String marker ;
		if(input.contains("/")) {
			marker = "/";
		}else if(input.contains("-")) {
			marker = "-";
		}else {
			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			return false;
		}
		
		String [] birthSplit = input.split(marker);
		int year = Integer.parseInt(birthSplit[0]);
		int month = Integer.parseInt(birthSplit[1]);
		String regex;
		
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			regex = "^(19|20)\\d{2}([\\/.-])(0[1-9]|1[1,2])([\\/.-])(0[1-9]|[12][0-9]|3[01])$";
		}else if(month==2){
			if(year%4==0) {
				regex =  "^(19|20)\\d{2}([\\/.-])(02)([\\/.-])(0[1-9]|1[0-9]|2[0-9])$";
			}
			regex =  "^(19|20)\\d{2}([\\/.-])(02)([\\/.-])(0[1-9]|1[0-9]|2[0-8])$";
		}else {
			regex =  "^(19|20)\\d{2}([\\/.-])(0[1-9]|1[1,2])([\\/.-])(0[1-9]|[12][0-9]|3[0])$";
		}
		
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean matchfound = m.find();
		System.out.println();
		if (!matchfound) {
			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
			return false;
		}else {
			//HashMap<String, String> newUser = new HashMap<String, String>();
	        getUserList().get(userList.size()-1).setBirth(input);// 사용자 생일 저장
		
			return matchfound;
		}
		
	}//birth
	

	
	
	
//	public static boolean validAcc (String input) {
//		
//		Rpattern p = new Rpattern();
//		
//		int i = 0;
//		
//		
//		
//		//String [] banklist = new String[];
//		
//		String [] numSplit = input.split("-");
//		if(numSplit.length==4) {
//			for(Pattern indi : p.block4) {
//				Matcher m = indi.matcher(input);
//				boolean matchfound = m.find();
//				if (matchfound) {
//					if(input.contains("-")) {
//						bankName = Arrays.asList(p.bankblock4[i].split("-"));
//					}else {
//						bankName.add(p.bankblock4[i]);
//					}
//					//return m.matches();
//				} 
//				i++;
//			}
//		}else if(numSplit.length==3){
//			for(Pattern indi : p.block3) {
//				Matcher m = indi.matcher(input);
//				boolean matchfound = m.find();
//				if (matchfound) {
//					if(input.contains("-")) {
//						bankName = Arrays.asList(p.bankblock3[i].split("-"));
//					}else {
//						bankName.add(p.bankblock3[i]);
//					}
//					//return m.matches();
//				} 
//				i++;
//				
//			}
//		}else {
//			System.out.printf("\n%22s계좌번호에 -를 알맞게 입력 부탁드립니다.\n"," ");
//			
//			return false;
//		}
//		
//		
//		
//		System.out.println(Arrays.toString(bankName.toArray()));
//		
//		HashMap<String, String> newUser = new HashMap<String, String>();
//		newUser.put("계좌번호", input);  // 사용자 아이디 저장
//        user.add(newUser);
//		
//        
//        
//		return true;
//	}

	
//	public static boolean validBank (String input) {
//		String choice;
//		if(input.equals("1")) {
//			 choice = bankName.get(0);
//		}else if(input.equals("2")) {
//			choice = bankName.get(1);
//		}else {
//			System.out.printf("\n%22s보기에 나온 숫자를 입력해주세요 "," ");
//			return false;
//		}
//		
//		
//		
//		HashMap<String, String> newUser = new HashMap<String, String>();
//		newUser.put("은행명",choice);  // 사용자 아이디 저장
//        user.add(newUser);
//		
//		
//		
//		
//		return true;
//	}

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
