package com.mongle.asset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.mongle.resource.BankAccount;

/**
 * 계좌 생성을 위한 데이터 클래스
 */
public class GiveAccount {
	private static final String BANK = "dat\\계좌 생성 데이터.txt";
	/**
	 * 계좌 생성용 데이터가 저장된 리스트
	 */
	public static ArrayList<BankAccount> glist = new ArrayList<>();

	/**
	 * 데이터 파일 로딩
	 */
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

	/**
	 * 데이터 파일 저장
	 */
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
