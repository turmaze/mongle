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

/**
 * 메인 클래스
 */
public class Main {
	/**
	 * 메인 클래스
	 * 
	 * @param args 인자값
	 */
	public static void main(String[] args) {
		programRun();

	}

	/**
	 * 프로그램 실행
	 */
	private static void programRun() {
		DataBase.dataLoad();
		StartPage.startPage();
		DataBase.dataSave();
	}

}
