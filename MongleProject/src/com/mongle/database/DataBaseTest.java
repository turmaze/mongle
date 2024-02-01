package com.mongle.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.resource.BankAccount;

public class DataBaseTest {
	private static final String path = "src/com/mongle/resource/member.json";
	public static ArrayList<BankAccount> list = new ArrayList<>();

	public static void load(String Id) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));

			String txt = "";
			String line = null;

			while ((line = reader.readLine()) != null) {
				txt += line + "\r\n";
			}

			reader.close();

			JSONParser parser = new JSONParser();
			JSONArray arr = (JSONArray) parser.parse(txt);
			JSONArray blist = new JSONArray();

			for (Object obj : DataBase.getUser()) {

				JSONObject item = (JSONObject) obj;

				if (item.get("ID").equals(Id)) {
					if (item.containsKey("계좌")) {
						JSONArray temp = (JSONArray) item.get("계좌");
						for (Object ob : temp) {
							
						}
					}
				}
			}
//							blist.add(ob);
//							list.add((BankAccount) ob);
			save(arr);
			System.out.println(arr);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void load(String Id, BankAccount acc) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));

			String txt = "";
			String line = null;

			while ((line = reader.readLine()) != null) {
				txt += line + "\r\n";
			}

			reader.close();

			JSONParser parser = new JSONParser();
			JSONArray arr = (JSONArray) parser.parse(txt);
			JSONArray blist = new JSONArray();

			for (Object obj : arr) {

				JSONObject item = (JSONObject) obj;

				if (item.get("ID").equals(Id)) {
					if (item.containsKey("계좌")) {
						JSONArray temp = (JSONArray) item.get("계좌");
						for (Object ob : temp) {
							blist.add(ob);
							list.add((BankAccount) ob);
						}
					}
					blist = putBank(blist, acc);
					item.put("계좌", blist);
				}
			}
			save(arr);
			System.out.println(arr);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static JSONArray putBank(JSONArray blist, BankAccount acc) {

		JSONObject b = new JSONObject();
		b.put("depositAmount", acc.getDepositAmount());
		b.put("accountNumber", acc.getAccountNumber());
		b.put("bankDepo", acc.getBankDepo());
		b.put("titleDepo", acc.getTitleDepo());
		blist.add(b);
		list.add(acc);

		return blist;

	}

	public static void save(JSONArray arr) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));

			writer.write(arr.toJSONString());

			writer.flush(); // 버퍼 비우기
			writer.close();
			System.out.println("완");

		} catch (Exception e) {
			System.out.println("esave");
			e.getStackTrace();
		}

	}

}
