package com.mongle.database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;

public class DataBase {

	static ArrayList<HashMap> user = new ArrayList<HashMap>();
	static ArrayList<HashMap> privateUser = new ArrayList<HashMap>();

	public static ArrayList<HashMap> getUser() {
		return user;
	}

	public static void setUser(HashMap<String, Object> newUser) {
		DataBase.user.add(newUser);
	}

	public static ArrayList<HashMap> getPrivateuser() {
		return privateUser;
	}

	public static void setPrivateUser(HashMap<String, Object> newUser) {
		DataBase.privateUser.add(newUser);
	}

	public static void loadPrivateUser(String primaryKey) {
		JSONParser parser = new JSONParser();
		try {

			// JSON 파일을 읽어와 JsonArray로 파싱
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			JSONArray jsonArray = (JSONArray) parser.parse(reader);

			privateUser.clear();
//            for (int i = 0; i < jsonArray.size(); i++) {
			for (Object obj : jsonArray) {
//                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
				JSONObject item = (JSONObject) obj;

				// id 값을 가진 데이터 확인
//                String tempId = jsonObject.get("id").getAsString();

				if (item.get("id").equals("true")) { // LogIn.primaryKey 로 꼭 바꾸기!!!!!!!!!!!!!!
					HashMap<String, Object> userData = new HashMap<String, Object>();
					for (Object key : item.keySet()) {
						userData.put((String) key, item.get((String) key));

						if (key.equals("계좌")) {
							JSONArray temp = (JSONArray) item.get("계좌");
							for (Object ob : temp) {
								JSONObject it = (JSONObject) ob;
								int num = (int) ((long) it.get("depositAmount"));
								BankAccount b = new BankAccount((String) it.get("bankDepo"),
										(String) it.get("titleDepo"), (String) it.get("accountNumber"), num);

								BankAccount.list.add(b);
							}
						}

						if (key.equals("투자")) {
							JSONArray temp = (JSONArray) item.get("투자");
							for (Object ob : temp) {
								JSONObject it = (JSONObject) ob;

								int amount = Integer.parseInt((String) it.get("amount"));
								double price = Double.parseDouble((String) it.get("price"));
								Investment i = new Investment((String) it.get("bankDepo"), (String) it.get("titleDepo"),
										price, amount);

								Investment.list.add(i);
							}
						}

					}
					privateUser.add(userData);
//                	String pw = jsonObject.get("pw").getAsString();
//                    String name = jsonObject.get("name").getAsString();
//                    String birth = jsonObject.get("birth").getAsString();
//
//                    // HashMap에 값 추가
//                    HashMap<String, String> tempUser = new HashMap<>();
//                    tempUser.put("id", tempId);
//                    tempUser.put("pw", pw);
//                    tempUser.put("name", name);
//                    tempUser.put("birth", birth);

				}
			}

			if (privateUser.isEmpty()) {
				System.out.println(primaryKey + " 값을 가진 데이터가 존재하지 않습니다.");
			}

			// 가져온 값 출력
			System.out.println("privateUser List test : " + privateUser);
			System.out.println("-끝-");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeData(ArrayList<HashMap> arrayList) {
		// 기존 privateUser ArrayList에서 "id"가 "asd159"인 데이터의 "name" 수정
		for (HashMap<String, Object> user : arrayList) {
		    if (user.get("id").equals("asd159")) {
		        user.put("name", "change success");
		        user.put("birth", "change success");
		        
		        break; // 수정한 후에는 루프 종료
		    }
		}
	}
	

//JsonObject로 만든거		
//	        try {
//	            Gson gson = new Gson();
//	            // JSON 파일을 읽어와 JsonObject로 파싱
//	            JsonObject jsonObject = JsonParser.parseReader(new FileReader(ResourcePath.MEMBER)).getAsJsonObject();
//
//	            // id 값을 가진 데이터 확인
//	            JsonObject targetData = jsonObject.getAsJsonObject(LogIn.primaryKey);
//
//	            if (targetData != null) {
//	            	String tempId = targetData.get("id").getAsString();
//	                String pw = targetData.get("pw").getAsString();
//	                String name = targetData.get("name").getAsString();              
//	                String birth = targetData.get("birth").getAsString();
//	                //String phone = targetData.get("phone").getAsString();
//
//	                HashMap<String, String> tempUser = new HashMap<>();
//	                tempUser.put("id", tempId);
//	                tempUser.put("pw", pw);
//	                tempUser.put("name", name);
//	                tempUser.put("birth", birth);
//	               // tempUser.put("phone", phone);
//	                
//	                privateUser.add(tempUser);
//	                // 가져온 값 출력
//	                System.out.println("privateUser List test : "+tempUser);
//	            
//            	} else {
//                System.out.println("qwerty123 값을 가진 데이터가 존재하지 않습니다.");
//            	}
//	        } catch (IOException e) {
//	        	e.printStackTrace();
//	        }

//		for(HashMap privateData : user) {
//			if(id.equals(privateData.get("ID"))) { //ID값이 맞으면
//				privateUser.get
//				
//				
//			}
//			
//		}

	public static void dataSave() {

		try {

			// set pretty printing
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(ResourcePath.MEMBER);
			// System.out.println(file.getAbsolutePath());// 경로 찾는 테스트 코드
			FileWriter writer = new FileWriter(file, false); // 덮쓰

			// String json = gson.toJson(user);
			writer.write(gson.toJson(user));
			writer.flush(); // 버퍼 비우기

			writer.close();
			System.out.printf("\n%22ssave 완 ", " "); // testcode

		} catch (Exception e) {
			System.out.println("DataBase.dataSave Error");
			e.printStackTrace();
		}

	}

	// 파일에서 사용자 데이터 읽기
	public static void dataLoad() {

		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray userList = (JSONArray) parser.parse(reader);

			user.clear(); // 기존 리스트를 비움
			Iterator<Object> iterator = userList.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				HashMap<String, Object> userData = new HashMap<>();
				// 가정: JSON 객체의 모든 키는 문자열이고, 값도 문자열임
				for (Object key : jsonObject.keySet()) {
					userData.put((String) key, jsonObject.get(key));
				}
				user.add(userData); // 읽은 데이터를 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean validId(String input) {

		input = input.toLowerCase();

		Pattern p = Pattern.compile("^[a-z0-9]{4,12}$");
		Matcher m = p.matcher(input);

		if (m.matches() && !isIdDuplicate(input)) {
			return m.matches();
		} else {
			// System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력이거나 중복입니다.\n\n", " ");
			return false;
		}
	}// id

	private static boolean isIdDuplicate(String id) {

		for (HashMap userData : user) {
			// if (userData.containsValue(id)) {
			if (id.equals(userData.get("id"))) {
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

	}// pw

	public static boolean validName(String input) {

		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n", " ");

			return m.matches();
		} else {
			// getUserList().get(userList.size()-1).setName(input);// ArrayList에 사용자 정보 추가
			return m.matches();
		}
	}// name

	public static boolean validBirth(String input) {

//		if(input.length()>8||input.length()<8) {
//			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
//			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
//		}

		// String [] birthSplit = input.split(marker);
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(4, 6));
		String regex;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			regex = "^((19|20)\\d{2})(0[1-9]|1[1,2])(0[1-9]|[12][0-9]|3[01])$";
		} else if (month == 2) {
			if (year % 4 == 0) {
				regex = "^((19|20)\\d{2})(02)(0[1-9]|1[0-9]|2[0-9])$";
			}
			regex = "^((19|20)\\d{2})(02)(0[1-9]|1[0-9]|2[0-8])$";
		} else {
			regex = "^((19|20)\\d{2})(0[1-9]|1[1,2])(0[1-9]|[12][0-9]|3[0])$";
		}

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean matchfound = m.find();
		System.out.println();
		if (!matchfound) {
			// System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			// System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
			return false;
		} else {
			// HashMap<String, String> newUser = new HashMap<String, String>();
			// getUserList().get(userList.size()-1).setBirth(input);// 사용자 생일 저장

			return matchfound;
		}

	}// birth

	public static boolean validPhone(String phone) {
		int size = phone.length();
		String starter = phone.substring(0, 3);

		System.out.println(starter);
		if (size < 11 || size > 11 || !starter.equals("010")) {
			return false;
		} else {
			return true;
		}

	}

}
