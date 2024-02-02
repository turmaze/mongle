package com.mongle.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.sign.SignUp;
import com.mongle.yourapp.LogIn;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		DataBase.dataLoad();
//		DataBase.dataLoad(ResourcePath.ADMIN);
		
		
		SignUp.signUp();

		// LogIn.logIn();
		DataBase.loadPrivateUser(LogIn.primaryKey);
//		InvestService.investMenu();

		// MainMenu.MainMenu();

		// Stock.stockService();
		// AttendanceCheck.attendanceCheckService();
		System.out.println(Investment.list);
		DataBase.changeData(DataBase.getUser(), "계좌", BankAccount.list); // sample
		DataBase.changeData(DataBase.getUser(), "투자", Investment.list); // sample
		DataBase.dataSave();
//		DataBase.dataSave(ResourcePath.ADMIN);
		

	}// main
}
