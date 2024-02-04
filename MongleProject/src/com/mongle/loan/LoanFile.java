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
	public static ArrayList<Loan> loanlist = new ArrayList<>();
	
	
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(LoanFile.LOAN));

			String line = null;
			while ((line = reader.readLine()) != null) {
				Object[] temp = line.split(",");
				LoanFile.loanlist.add(new Loan((String)temp[0], (int)temp[1],
											(float)temp[2], (int)temp[3],(int)temp[4]));
			}

			reader.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(LoanFile.LOAN));

			for (Loan acc : LoanFile.loanlist) {
				String line = String.format("%s,%d,%f,%d,%d\r\n",acc.getLoanName(),
						acc.getPrincipal(),acc.getRate(), acc.getLoanPeriod(),
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
