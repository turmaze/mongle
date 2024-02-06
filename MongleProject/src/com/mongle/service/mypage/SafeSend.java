package com.mongle.service.mypage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

public class SafeSend {

//	private String fromwho; //보내는 분
//	private String fromacc;//"출금 계좌");
//	private String towho; //받는분");
//	private String toacc;//송금 계좌");
//	private String money;//금액");
//	private String senddate;//날짜");

	public static ArrayList<HashMap> trans = new ArrayList<HashMap>();

	public static int safeSendService() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("안심송금서비스");

			System.out.printf("%22s1. 사용 설정\n", " ");
			System.out.printf("%22s2. 미사용 설정\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();

			if (sel.equals("1")) {

				MongleVisual.pusher();

				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "1");
				}

				System.out.printf("%22s안심송금 서비스 사용으로 설정되었습니다.\n", " ");

				MongleVisual.stopper();
			} else if (sel.equals("2")) {

				MongleVisual.pusher();

				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "0");
				}

				System.out.printf("%22s안심송금 서비스 미사용으로 설정되었습니다.\n", " ");

				MongleVisual.stopper();
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}

		}
		return 0;

	}

	public static boolean validSafe(String id) { // safesend설정 여부
		for (HashMap map : DataBase.getUser()) {
			if (map.get("id").equals(id)) {
				return map.get("safesendsetting").equals("1"); // 설정 했으면 true 리턴
			}
		}
		return false;
	}

	public static void sendSafeMoney(String accNum, String toacc, int money) {
		if (!validSafe(LogIn.primaryKey)) {
			return;
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String date = sdf1.format(now);
		HashMap<String, Object> newTrans = new HashMap<String, Object>();

		newTrans.put("fromWho", DataBase.getPrivateUser().get(0).get("name"));
		newTrans.put("fromAcc", accNum);
		newTrans.put("toWho", "홍길동");
		newTrans.put("toAcc", toacc);
		newTrans.put("money", money);
		newTrans.put("sendDate", date);

		trans.add(newTrans);
		save();

	}

	public static void rejection() {
		System.out.printf("%22s%s님께서 송금 받기를 거부하셨습니다.\n", " ", trans.get(0).get("toWho"));
		System.out.printf("%22s송금하신 금액 %,d원이 환불 처리되었습니다.\n", " ", (int)trans.get(0).get("money"));
		History.make(BankAccount.list.get(0).getAccountNumber(), "송금 취소 환불", (int) trans.get(0).get("money"));

		MongleVisual.stopper();
	}

	public static void getSafeMoney() {

		if (!validSafe(LogIn.primaryKey)) {
			return;
		}

		Scanner scan = new Scanner(System.in);

		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			String path = ResourcePath.SAFE + "//" + LogIn.primaryKey + ".dat";
			FileReader reader = new FileReader(path);
			File file = new File(path);
			trans.clear();

			if (!file.exists()) {
				return;
			}
			JSONArray arr = (JSONArray) parser.parse(reader);
			for (Object obj : arr) {
				JSONObject item = (JSONObject) obj;
				HashMap<String, Object> transData = new HashMap<>();
				for (Object key : item.keySet()) {
					transData.put((String) key, item.get((String) key));
				}
				trans.add(transData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.printf("%22s%s님께서 송금을 보냈습니다.\n", " ", trans.get(0).get("fromWho"));
		System.out.printf("%22s금액: %,d원\n", " ", trans.get(0).get("money"));
		System.out.printf("%22s송금을 받으시겠습니까?(y/n)", " ");
		String sel = scan.nextLine();
		sel = sel.toLowerCase();

		if (sel.equals("y")) {
			History.make(BankAccount.list.get(0).getAccountNumber(), (String) trans.get(0).get("fromWho"),
					(int) trans.get(0).get("money"));
			MongleVisual.successPrint();
		} else if (sel.equals("n")) {
			MongleVisual.stopper();
			// 일단 아무일도 없는척
			// fromwho의 fromacc로 money 반환 및 senddate 날짜 입력
		} else {
			MongleVisual.wrongInput();
		}

		return;
	}

	public static void save() {
		try {

			// set pretty printing
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(ResourcePath.SAFE + "//" + LogIn.primaryKey + ".dat");
			FileWriter writer = new FileWriter(file, false); // 덮쓰

			writer.write(gson.toJson(trans));
			writer.flush(); // 버퍼 비우기

			writer.close();

		} catch (Exception e) {
			System.out.println("safesend");
			e.printStackTrace();
		}

	}

}
