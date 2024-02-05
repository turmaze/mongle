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
import com.mongle.yourapp.StartPage;

public class Main {
	
	public static void main(String[] args) {
		programRun();
			
	}

	private static void programRun() {
		DataBase.dataLoad();
		StartPage.startPage();
		DataBase.dataSave();
	}
	
}

