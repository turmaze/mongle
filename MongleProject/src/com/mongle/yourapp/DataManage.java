package com.mongle.yourapp;

import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;

public class DataManage {
	public static void dataManage() {
		System.out.printf("\n%22s데이터 출력\n"," ");
		System.out.printf("\n%22s출력하시고 싶은 데이터를 선택해주세요\n"," ");
		System.out.printf("\n%22s1. 이용자수 (일,월,년)\n"," ");
		System.out.printf("\n%22s1. 이용자수 (일,월,년)\n"," ");
		System.out.printf("\n%22s2. 총 연결된 계좌수\n"," ");
		System.out.printf("\n%22s3. 거래가 Mongle에서 일어난 횟수 (일,월,년)\n"," ");
		System.out.printf("\n%22s4. 은행별 등록된 계좌 계수\n"," ");
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		if(choice.equals("1")) {
			userNum();
		}else if(choice.equals("2")){
			//accNum();
		}else if(choice.equals("3")) {
		//	transNum();
		}else if(choice.equals("4")) {
			//bankNum();
		}else {
		//	advanceCalc();
		}
		
	}

	private static void userNum() {
		int count = 0 ;
		
		System.out.printf("\n%22s1. 이용자수 (일)\n"," ");
		System.out.printf("\n%22s2. 이용자수 (월)\n"," ");
		System.out.printf("\n%22s3. 이용자수 (년)\n"," ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		ArrayList<String> checkdate = new ArrayList<String>();		
		
		Calendar c1 = Calendar.getInstance();
		String currentDate = String.format("%tF\n", c1);
		currentDate = currentDate.replace("-", "");
		System.out.println(currentDate);
		
		
		
		dataManage();
	}
}
