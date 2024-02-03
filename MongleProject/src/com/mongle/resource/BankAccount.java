package com.mongle.resource;

import java.util.ArrayList;

/*
 * 계좌 관리 파트 멤버 클래스 입니다.
 * */
public class BankAccount {

	private int depositAmount; // 잔액
	private String accountNumber; // 계좌 번호
	private String bankDepo; // 금융사
	private String titleDepo; // 예적금명
	private ArrayList<History> history;

	public static ArrayList<BankAccount> list = new ArrayList<>();

	// findAccount 메서드 추가
	public static BankAccount findAccount(String accountNumber) {
		for (BankAccount account : list) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount, ArrayList<History> his) {

		this.accountNumber = AccountNumber;
		this.depositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.history = his;

	}

	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount) {

		this.accountNumber = AccountNumber;
		this.depositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.history = new ArrayList<History>();

	}

	public ArrayList<History> getHis() {
		return history;
	}

	public void setHis(ArrayList<History> his) {
		this.history = his;
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
		return String.format("BankAccount [depositAmount=%s, accountNumber=%s, bankDepo=%s, titleDepo=%s, history=%d]",
				depositAmount, accountNumber, bankDepo, titleDepo, history.size());
	}


}
// class
