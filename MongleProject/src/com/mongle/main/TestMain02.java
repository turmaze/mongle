package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.sign.SignUp;
import com.mongle.yourapp.LogIn;

public class TestMain02 {

	public static void main(String[] args) {
		DataBase.dataLoad(ResourcePath.MEMBER);
//		DataBase.dataLoad(ResourcePath.ADMIN);
		
		
		SignUp.signUp();

		// LogIn.logIn();
		DataBase.loadPrivateUser(LogIn.primaryKey);
//		InvestService.investMenu();

		// MainMenu.MainMenu();

		// Stock.stockService();
		// AttendanceCheck.attendanceCheckService();
		System.out.println(Investment.list);
		DataBase.changeData(DataBase.getUser(), "account", BankAccount.list); // sample
		DataBase.changeData(DataBase.getUser(), "invest", Investment.list); // sample
		DataBase.dataSave(ResourcePath.MEMBER);
//		DataBase.dataSave(ResourcePath.ADMIN);
	}

}
