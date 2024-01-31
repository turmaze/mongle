package com.mongle.resource;

import java.util.ArrayList;

/*
 * 계좌 관리 파트 멤버 클래스 입니다.
 * */
public class BankAccount {

	private int depositAmount; // 잔액
	private String accountNumber; // 계좌 번호
	private String bankDepo;
	private String titleDepo;

	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount) {

		this.accountNumber = AccountNumber;
		this.depositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;

	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		depositAmount = depositAmount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		accountNumber = accountNumber;
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
		return " [계좌 번호=" + accountNumber + " " + "잔액=" + depositAmount + " " + "은행명=" + bankDepo + " " + "상품명="
				+ titleDepo;

	}// toString

}
// class
