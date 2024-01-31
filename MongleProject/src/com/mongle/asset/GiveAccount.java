package com.mongle.asset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GiveAccount {
	private static final String BANK = "dat\\계좌 생성 데이터.txt";
	public static ArrayList<BankAccount> list = new ArrayList<>();

	// 프로그램 시작시 score.txt > 메모리로
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(GiveAccount.BANK));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(", ");
				list.add(new BankAccount(temp[0], "", temp[1], 0));
			}

			reader.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void save() {
		// 수정된 데이터를 파일로 score.txt
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(GiveAccount.BANK));

			// ArrayList<Score> 1개 당 > 텍스트 파일 1줄 저장
			for (BankAccount acc : GiveAccount.list) {
				String line = String.format("%s,%s,%s,%d\r\n", acc.getBankDepo(), acc.getTitleDepo(),
						acc.getAccountNumber(), acc.getDepositAmount());
				writer.write(line);
			}

			writer.close();

		} catch (Exception e) {
			System.out.println("esave");
			e.getStackTrace();
		}

	}

}
