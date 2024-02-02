package com.mongle.main;

import javax.xml.crypto.Data;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.service.MypageService;
import com.mongle.service.invest.Stock;
import com.mongle.sign.SignOut;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.MainMenu;
import com.mongle.yourapp.StartPage;

public class TestMain06 {
	
	public static void main(String[] args) {
		DataBase.dataLoad();
		StartPage.startPage();
//		LogIn.logIn();
		
		DataBase.changeData(DataBase.getPrivateuser(), "계좌", BankAccount.list);
		DataBase.changeData(DataBase.getUser(), "계좌", BankAccount.list);
		DataBase.dataSave();
		//SignOut.signOutService();
		//MypageService.mypageService();
		//MainMenu.mainMenu("1");
		//Stock.stockService();
		
	}
	
}

