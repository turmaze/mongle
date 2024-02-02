package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.StartPage;

public class TestMain01 {

	public static void main(String[] args) {
		DataBase.dataLoad(ResourcePath.MEMBER);
//		SignUp.signUp();
		StartPage.startPage();
//		LogIn.logIn();
//		InvestService.investMenu();

		// MainMenu.MainMenu();

		// Stock.stockService();
		// AttendanceCheck.attendanceCheckService();
		DataBase.changeData(DataBase.getUser(), "account", BankAccount.list); // sample
		DataBase.changeData(DataBase.getUser(), "invest", Investment.list); // sample
		DataBase.dataSave(ResourcePath.MEMBER);
	}

}
