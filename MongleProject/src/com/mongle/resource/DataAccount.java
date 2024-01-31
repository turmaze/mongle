package com.mongle.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataAccount {
	private static final String path = "src/com/mongle/resource/member.json";
	private static String txt = "";
	public static ArrayList<BankAccount> list = new ArrayList<>();

	public static void load(String Id, BankAccount acc) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = null;

			while ((line = reader.readLine()) != null) {
				txt += line + "\r\n";
			}

			reader.close();

			System.out.println(txt);

			JSONParser parser = new JSONParser();

			JSONArray arr = (JSONArray) parser.parse(txt);

			JSONArray blist = new JSONArray();

			for (Object obj : arr) {

				JSONObject item = (JSONObject) obj;

				if (item.get("ID").equals(Id)) {
					if (item.containsKey("bankDepo")) {
					list.add(new BankAccount((String)item.get("bankDepo"),(String)item.get("titleDepo"),(String)item.get("accountNumber"),(int)item.get("depositAmount")));
					}
					putBank(blist, acc);
					item.put("계좌", blist);
				}
			}
			save();
//			System.out.println(arr);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private static JSONArray putBank(JSONArray blist, BankAccount acc) {

		JSONObject b = new JSONObject();
		b.put("depositAmount", acc.getDepositAmount());
		b.put("accountNumber", acc.getAccountNumber());
		b.put("bankDepo", acc.getBankDepo());
		b.put("titleDepo", acc.getTitleDepo());
		blist.add(b);

		return blist;

	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			writer.write(txt);

			writer.close();

		} catch (Exception e) {
			System.out.println("esave");
			e.getStackTrace();
		}

	}

}
