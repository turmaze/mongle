package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.yourapp.LogIn;

public class TestMain01 {

	public static void main(String[] args) {
		DataBase.dataLoad();
//		SignUp.signUp();

		LogIn.logIn();
//		InvestService.investMenu();

		// MainMenu.MainMenu();

		// Stock.stockService();
		// AttendanceCheck.attendanceCheckService();
		DataBase.changeData(DataBase.getUser(), "account", BankAccount.list); // sample
		DataBase.changeData(DataBase.getUser(), "invest", Investment.list); // sample
		DataBase.dataSave();
	}

}
