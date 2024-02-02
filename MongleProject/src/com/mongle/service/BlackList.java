package com.mongle.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class BlackList {
	public static void blackList() {
		
		MongleVisual.menuHeader("블랙리스트 관리");
		
		System.out.printf("\n%22s1. 블랙리스트 확인\r\n", " ");
		System.out.printf("\n%22s2. 블랙리스트 등록\r\n", " ");
		System.out.printf("\n%22s9. 홈으로\r\n", " ");
		System.out.printf("\n%22s0. 이전으로\r\n", " ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		if(input.equals("1")) {
			callBlackList();
		}else if(input.equals("2")){
			addBlackList();
		}else if (input.equals("9")) {
			
		}else if(input.equals("0")) {
			System.out.printf("\n%22s다시입력 부탁드립니다\r\n", " ");
		}else {
			
		}
	
		
		
	}

	private static void addBlackList() {
		// TODO Auto-generated method stub
		
	}

	private static void callBlackList() {
		try {
			JSONParser parser = new JSONParser();
			Scanner scan = new Scanner(System.in);
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			ArrayList<String>dark = new ArrayList<>();
			String findID;
			for (Object obj : list) {
				if (((JSONObject)obj).get("level").equals("3") ) {
					findID = (String) ((JSONObject) obj).get("id");
					dark.add(findID);
					System.out.printf("\n%22s%s\r\n", " ",findID);
				}
			}
			System.out.printf("\n%22s작업할 아이디를 선택해주세요\r\n", " ");
			
			
			
		} catch (Exception e) {
			System.out.println("BlackList.callBlackList");
			e.printStackTrace();
		}
		
	}
}
