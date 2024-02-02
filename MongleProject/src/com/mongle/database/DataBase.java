package com.mongle.database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.yourapp.LogIn;

public class DataBase {

	static ArrayList<HashMap> user = new ArrayList<HashMap>();
	static ArrayList<HashMap> privateUser = new ArrayList<HashMap>();

	public static ArrayList<HashMap> getUser() {
		return user;
	}

	public static void setUser(HashMap<String, Object> newUser) {
		DataBase.user.add(newUser);
	}

	public static ArrayList<HashMap> getPrivateUser() {
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

				if (item.get("id").equals(LogIn.primaryKey)) { // LogIn.primaryKey 로 꼭 바꾸기!!!!!!!!!!!!!!
					HashMap<String, Object> userData = new HashMap<String, Object>();
					for (Object key : item.keySet()) {
						userData.put((String) key, item.get((String) key));

						if (key.equals("account")) {
							JSONArray temp = (JSONArray) item.get("account");
							for (Object ob : temp) {
								JSONObject it = (JSONObject) ob;
								int num = (int) ((long) it.get("depositAmount"));
								BankAccount b = new BankAccount((String) it.get("bankDepo"),
										(String) it.get("titleDepo"), (String) it.get("accountNumber"), num);

								BankAccount.list.add(b);
							}
						}

						if (key.equals("invest")) {
							JSONArray temp = (JSONArray) item.get("invest");
							for (Object ob : temp) {
								JSONObject it = (JSONObject) ob;

								int amount = (int)((long) it.get("amount"));
								int price = (int)((long) it.get("price"));
								Investment i = new Investment((String) it.get("bankDepo"), (String) it.get("titleDepo"),
										price, amount);

								Investment.list.add(i);
							}
						}

					}
					privateUser.add(userData);
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

	public static void changeData(ArrayList<HashMap> arrayList, String key, Object value) {
		// 기존 privateUser ArrayList에서 "id"가 "asd159"인 데이터의 "name" 수정
		for (HashMap<String, Object> user : arrayList) {
		    if (user.get("id").equals(LogIn.primaryKey)) {//LogIn.primaryKey 로 바꾸기!!!!!!!!!!!!!!!!!!
		        user.put(key, value);
		        
		        break; // 수정한 후에는 루프 종료
		    }
		}
	} //loadPrivateUser
	

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

}
