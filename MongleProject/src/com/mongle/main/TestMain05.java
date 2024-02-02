package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.ResourcePath;
import com.mongle.yourapp.StartPage;

public class TestMain05 {
	public static void main(String[] args) {
		DataBase.dataLoad();
		StartPage.startPage();
		DataBase.changeData();
//		LogIn.logIn();
		
		
		
		
		//DataBase.changeData(DataBase.getPrivateUser(), "계좌", BankAccount.list);
		//DataBase.changeData(DataBase.getUser(), "계좌", BankAccount.list);
		DataBase.dataSave();
		//SignOut.signOutService();
		//MypageService.mypageService();
		//MainMenu.mainMenu("1");
		//Stock.stockService();
		
	}
}
