package com.mongle.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes.Name;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mongle.resource.ResourcePath;



public class DataBase {
	

	static ArrayList<HashMap> user = new ArrayList<HashMap>();
	
	
	public static ArrayList<HashMap> getUser() {
		return user;
	}

	public static void setUser(HashMap<String,String> newUser) {
		DataBase.user.add(newUser);
	}

	public static void dataSave() {
		
		try {
			
			//set pretty printing
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(ResourcePath.MEMBER);
			//System.out.println(file.getAbsolutePath());// 경로 찾는 테스트 코드 
			FileWriter writer = new FileWriter(file,false); //덮쓰
						
			//String json = gson.toJson(user);
			writer.write(gson.toJson(user));
			writer.flush(); //버퍼 비우기
			
			writer.close();
			System.out.printf("\n%22ssave 완 ", " "); //testcode
			
		} catch (Exception e) {
			System.out.println("DataBase.dataSave Error");
			e.printStackTrace();
		}

		
	}
	
	// 파일에서 사용자 데이터 읽기
	public static void dataLoad() {

		JSONParser parser = new JSONParser();
        try {
            //FileReader 객체 생성
            FileReader reader = new FileReader(ResourcePath.MEMBER);
            // JSON 데이터를 파싱하여 JSONArray로 변환
            JSONArray userList = (JSONArray) parser.parse(reader);
            
            
            user.clear(); // 기존 리스트를 비움
            Iterator<Object> iterator = userList.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                HashMap<String, String> userData = new HashMap<>();
                // 가정: JSON 객체의 모든 키는 문자열이고, 값도 문자열임
                for (Object key : jsonObject.keySet()) {
                    userData.put((String) key, (String) jsonObject.get(key));
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
		
		if(m.matches() && !isIdDuplicate(input)) {
			return m.matches();			
		}else {
			//System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력이거나 중복입니다.\n\n", " ");
			return false;
		}				
	}//id

	private static boolean isIdDuplicate(String id) {

        for (HashMap userData : user) {
            //if (userData.containsValue(id)) {
            if(id.equals(userData.get("ID"))) {
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
	
	public static boolean validName (String input) {

		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
			
			return m.matches();
		} else {
            //getUserList().get(userList.size()-1).setName(input);// ArrayList에 사용자 정보 추가
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
	        //getUserList().get(userList.size()-1).setBirth(input);// 사용자 생일 저장
		
			return matchfound;
		}
		
	}//birth

	
	

	private static void get(ArrayList userData) {
		// 호출문

	}

}
