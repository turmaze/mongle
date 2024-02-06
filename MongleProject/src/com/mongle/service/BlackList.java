package com.mongle.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.channels.SelectableChannel;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.MainMenu;

public class BlackList {
	public static int blackList() {

		MongleVisual.menuMove("블랙리스트 관리");

		System.out.printf("\n%22s1. 블랙리스트 확인\r\n", " ");
		System.out.printf("\n%22s2. 블랙리스트 등록/수정\r\n", " ");
		System.out.printf("\n%22s9. 홈으로\r\n", " ");
		System.out.printf("\n%22s0. 이전으로\r\n", " ");
		System.out.printf("\n%22s선택 입력: ", " ");
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();

		
		
		
		
		if (input.equals("1")) {
			callBlackList();
		} else if (input.equals("2")) {
			addBlackList();
		} else if (input.equals("9")) {
			return 9;
		} else if (input.equals("0")) {
			return 0;
		} else {
			MongleVisual.wrongInput();
		}
		return 0;

	}

	private static void addBlackList() {
		MongleVisual.pusher();
		System.out.printf("\n%22s대상 아이디 입력: ", " ");
		Scanner scan = new Scanner(System.in);
		String idcheck = scan.nextLine();
		try {
			JSONParser parser = new JSONParser();
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			boolean check = false;
			for (Object obj : list) {
				if (((JSONObject) obj).get("level").equals("1")) {
					idcheck = (String) ((JSONObject) obj).get("id");
					String level = (String)((JSONObject)obj).get("level");
					break;
				}

			}
			if (!idcheck.equals(null)) {
				changeBlacklevel(idcheck);
			} else {
				MongleVisual.wrongInput();
				addBlackList();
			}

		} catch (Exception e) {
			System.out.println("BlackList.addBlackList");
			e.printStackTrace();
		}

	}

	private static void callBlackList() {
		MongleVisual.pusher();
		int count = 0;
		try {
			JSONParser parser = new JSONParser();
			Scanner scan = new Scanner(System.in);
			JSONArray list = (JSONArray) parser.parse(new FileReader(ResourcePath.MEMBER));
			ArrayList<String> dark = new ArrayList<>();
			String findID;
			for (Object obj : list) {
				if (((JSONObject) obj).get("level").equals("3")) {
					count++;
					findID = (String) ((JSONObject) obj).get("id");
					dark.add(findID);
					System.out.printf("\n%22s%d.%s\r", " ", count, findID);
				}
			}
			String select = "";
			
			if(dark.size()==0) {
				System.out.printf("\r\n%22s블랙리스트에 등록된 회원이 없습니다\r\n", " ");
				blackList();
			}else {
				do {
					System.out.printf("\r\n%22s작업할 아이디를 선택해주세요\r", " ");
					System.out.printf("\n%22s번호 선택: \r\n", " ");
					String pick = scan.nextLine();
					select = dark.get(Integer.parseInt(pick) - 1);
					
				} while (select.equals(null));
				
			}

			System.out.printf("\r\n%22s1. 블랙리스트 등급 조정하기\r\n", " ");
			System.out.printf("\r\n%22s2. 유저 탈퇴시키기\r", " ");
			String s = scan.nextLine();

			if (s.equals("1")) {
				changeBlacklevel(select);
			} else if (s.equals("2")) {
				kickUser();
			}

		} catch (Exception e) {
			System.out.println("BlackList.callBlackList");
			e.printStackTrace();
		}

	}

	private static void kickUser() {
		System.out.printf("\n%22s대상 아이디 입력: ", " ");
		Scanner scan = new Scanner(System.in);
		String idcheck = scan.nextLine();
		try {
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			for (HashMap obj : list) {
				if ((obj).get("id").equals(idcheck)) {
					list.remove(obj);
					break;
				}
			}
		
			DataBase.dataSave();

		} catch (Exception e) {
			System.out.println("BlackList.addBlackList");
			e.printStackTrace();
		}

	}

	private static void changeBlacklevel(String select) {
		MongleVisual.pusher();
		Scanner scan = new Scanner(System.in);
		UserData user = new UserData();
		System.out.printf("\r\n%22s1. 블랙리스트 등급 상향하기\r\n", " ");
		System.out.printf("\n%22s2. 블랙리스트 등급 하향하기\r\n", " ");
		System.out.printf("\n%22s선택 입력: ", " ");
		String choice = scan.nextLine();
		String level;
		try {
			JSONParser parser = new JSONParser();
			ArrayList<HashMap> list = DataBase.getUser();
			String findID;
			String findlevel;
			for (HashMap obj : list) {
				if ((obj).get("id").equals(select)) {
					findID = (String) (obj).get("id");
					findlevel = (String) (obj).get("level");
					if (choice.equals("1")) {
						level = "3";
						if (level.equals(findlevel)) {
							System.out.printf("\r\n%22s이미 블랙리스트입니다\r\n", " ");
						}
						obj.replace("level", level);
						System.out.printf("\r\n%22s블랙 리스트 등록 완료\r\n", " ");

					} else if (choice.equals("2")) {
						level = "1";
						if (level.equals(findlevel)) {
							System.out.printf("\r\n%22s이미 일반 회원입니다\r\n", " ");
						}
						obj.replace("level", level);
						System.out.printf("\r\n%22s블랙 리스트 수정완료\r\n", " ");
					}
				} 
			}

		} catch (Exception e) {
			System.out.println("BlackList.changeBlacklevel");
			e.printStackTrace();
		}

	}
}
