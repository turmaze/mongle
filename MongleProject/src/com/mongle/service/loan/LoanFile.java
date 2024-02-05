package com.mongle.service.loan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.mongle.resource.BankAccount;
import com.mongle.service.asset.GiveAccount;
/**
 * 대출 상품 데이터 클래스
 */
public class LoanFile {
	/**
	 * 대출 상품 데이터 파일 경로 저장
	 * 대출 상품 데이터 저장된 리스트
	 */
	private static final String LOAN = "dat\\loan.txt";
	public static ArrayList<ManageLoan> filelist = new ArrayList<>();
	
	/**
	 * 데이터 파일 로딩
	 */
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(LoanFile.LOAN));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				LoanFile.filelist.add(new ManageLoan(temp[0], Integer.parseInt(temp[1]),
											Float.parseFloat(temp[2]), Integer.parseInt(temp[3]),Integer.parseInt(temp[4])));
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
			BufferedWriter writer = new BufferedWriter(new FileWriter(LoanFile.LOAN));

			for (ManageLoan acc : LoanFile.filelist) {
				String line = String.format("%s,%d,%f,%d,%d\r\n",acc.getloanName(),
						acc.getprincipal(),acc.getRate(), acc.getloanPeriod(),
						acc.getrPeriod());
				writer.write(line);
			}

			writer.close();

		} catch (Exception e) {
			System.out.println("esave");
			e.getStackTrace();
		}

	}
}
