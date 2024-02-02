package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.StartPage;

public class TestMain01 {

	public static void main(String[] args) {
		DataBase.dataLoad();
//		SignUp.signUp();
		StartPage.startPage();
//		LogIn.logIn();

		DataBase.changeData();

        DataBase.dataSave();
	}

}
