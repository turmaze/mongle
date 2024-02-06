package com.mongle.service.mypage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
import com.mongle.service.asset.GiveAccount;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

public class SafeSend {

	private String fromwho; // 보내는 분
	private String fromacc;// "출금 계좌");
	private String towho; // 받는분");
	private String toacc;// 송금 계좌");
	private String money;// 금액");

	public static ArrayList<SafeSend> trans = new ArrayList<>();

	public SafeSend(String fromwho, String fromacc, String towho, String toacc, String money) {
		super();
		this.fromwho = fromwho;
		this.fromacc = fromacc;
		this.towho = towho;
		this.toacc = toacc;
		this.money = money;
	}

	@Override
	public String toString() {
		return "SafeSend [fromwho=" + fromwho + ", fromacc=" + fromacc + ", towho=" + towho + ", toacc=" + toacc
				+ ", money=" + money + "]";
	}

	public String getFromwho() {
		return fromwho;
	}

	public void setFromwho(String fromwho) {
		this.fromwho = fromwho;
	}

	public String getFromacc() {
		return fromacc;
	}

	public void setFromacc(String fromacc) {
		this.fromacc = fromacc;
	}

	public String getTowho() {
		return towho;
	}

	public void setTowho(String towho) {
		this.towho = towho;
	}

	public String getToacc() {
		return toacc;
	}

	public void setToacc(String toacc) {
		this.toacc = toacc;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public static ArrayList<SafeSend> getTrans() {
		return trans;
	}

	public static void setTrans(ArrayList<SafeSend> trans) {
		SafeSend.trans = trans;
	}

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
		load();
		SafeSend s = new SafeSend((String) DataBase.getPrivateUser().get(0).get("name"), accNum, "홍길동", toacc,
				Integer.toString(money));
		trans.add(s);
		save();

	}

	public static void rejection() {
		load();
		System.out.printf("%22s%s님께서 송금 받기를 거부하셨습니다.\n", " ", trans.get(0).getTowho());
		System.out.printf("%22s송금하신 금액 %,d원이 환불 처리되었습니다.\n", " ", Integer.parseInt(trans.get(0).getMoney()));
		History.make(BankAccount.list.get(0).getAccountNumber(), "송금 취소 환불", Integer.parseInt(trans.get(0).getMoney()));
		trans.remove(0);
		save();
		MongleVisual.stopper();
	}

	public static void getSafeMoney() {

		if (!validSafe(LogIn.primaryKey)) {
			return;
		}
		load();
		Scanner scan = new Scanner(System.in);

		System.out.printf("%22s거래 기록이 없는 사용자(%s)가 송금을 보냈습니다.\n", " ", trans.get(0).getFromwho());
		System.out.printf("%22s금액: %,d원\n", " ", Integer.parseInt(trans.get(0).getMoney()));
		System.out.printf("%22s송금을 받으시겠습니까?(y/n)", " ");
		String sel = scan.nextLine();
		sel = sel.toLowerCase();

		if (sel.equals("y")) {
			History.make(BankAccount.list.get(0).getAccountNumber(), trans.get(0).getFromwho(),
					Integer.parseInt(trans.get(0).getMoney()));
			MongleVisual.successPrint();
			trans.remove(0);
			save();

		} else if (sel.equals("n")) {
			MongleVisual.stopper();
			// 일단 아무일도 없는척
			// fromwho의 fromacc로 money 반환 및 senddate 날짜 입력
		} else {
			MongleVisual.wrongInput();
		}

		return;
	}

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(ResourcePath.SAFE + "\\" + LogIn.primaryKey + ".dat"));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				trans.add(new SafeSend(temp[0], temp[1], temp[2], temp[3], temp[4]));
			}

			reader.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(ResourcePath.SAFE + "\\" + LogIn.primaryKey + ".dat"));
			for (SafeSend s : trans) {

				String line = String.format("%s,%s,%s,%s,%s\r\n", s.getFromwho(), s.getFromacc(), s.getTowho(),
						s.getToacc(), s.getMoney());
				writer.write(line);
			}

			writer.close();
			trans.clear();

		} catch (Exception e) {
			System.out.println("save");
			e.getStackTrace();
		}
	}

}
