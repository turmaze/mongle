package com.mongle.asset;

import java.util.ArrayList;

/*
 * 계좌 관리 파트 멤버 클래스 입니다.
 * */
public class BankAccount {
//data <파일 쓰기 식별자 id              
//불러오기 : 계좌 가지고있는 데이터

	private  int DepositAmount; // 잔액
	private  String AccountNumber; // 계좌 번호
	private String bankDepo;
	private String titleDepo;

	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount) {

		this.AccountNumber = AccountNumber;
		this.DepositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;

	}

	

	public int getDepositAmount() {
		return DepositAmount;
	}



	public void setDepositAmount(int depositAmount) {
		DepositAmount = depositAmount;
	}



	public String getAccountNumber() {
		return AccountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}



	public String getBankDepo() {
		return bankDepo;
	}



	public void setBankDepo(String bankDepo) {
		this.bankDepo = bankDepo;
	}



	public String getTitleDepo() {
		return titleDepo;
	}



	public void setTitleDepo(String titleDepo) {
		this.titleDepo = titleDepo;
	}



	@Override
	public String toString() {
		return " [계좌 번호=" + AccountNumber + " " + "잔액=" + DepositAmount + " " + "은행명=" + bankDepo + " " + "상품명="
				+ titleDepo;

	}// toString


}
// class
