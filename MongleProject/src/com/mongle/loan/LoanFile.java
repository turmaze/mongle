package com.mongle.loan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.mongle.asset.GiveAccount;
import com.mongle.resource.BankAccount;

public class LoanFile {
	private static final String LOAN = "dat\\loan.txt";
	public static ArrayList<Loan> filelist = new ArrayList<>();
	
	
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(LoanFile.LOAN));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split(",");
				LoanFile.filelist.add(new Loan(temp[0], Integer.parseInt(temp[1]),
											Float.parseFloat(temp[2]), Integer.parseInt(temp[3]),Integer.parseInt(temp[4])));
			}

			reader.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(LoanFile.LOAN));

			for (Loan acc : LoanFile.filelist) {
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
