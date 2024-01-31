package com.mongle.asset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.mongle.resource.BankAccount;

public class GiveAccount {
	private static final String BANK = "dat\\계좌 생성 데이터.txt";
	public static ArrayList<BankAccount> glist = new ArrayList<>();

	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(GiveAccount.BANK));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				GiveAccount.glist.add(new BankAccount(temp[0], "", temp[1], 0));
			}

			reader.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(GiveAccount.BANK));

			for (BankAccount acc : GiveAccount.glist) {
				String line = String.format("%s,%s\r\n", acc.getBankDepo(), acc.getAccountNumber());
				writer.write(line);
			}

			writer.close();

		} catch (Exception e) {
			System.out.println("esave");
			e.getStackTrace();
		}

	}

}
