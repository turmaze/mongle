package com.mongle.resource;

import java.util.ArrayList;

/**
 * 계좌 클래스
 */
public class BankAccount {

	private int depositAmount; // 잔액
	private String accountNumber; // 계좌 번호
	private String bankDepo; // 금융사
	private String titleDepo; // 예적금명
	private ArrayList<History> history;

	/**
	 * 계좌 리스트
	 */
	public static ArrayList<BankAccount> list = new ArrayList<>();

	/**
	 * 계좌 정보 탐색
	 * 
	 * @param accountNumber 검색할 계좌 번호
	 * @return 검색한 계좌 정보
	 */
	public static BankAccount findAccount(String accountNumber) {
		for (BankAccount account : list) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	/**
	 * 계좌 생성자
	 * 
	 * @param bankDepo      금융사
	 * @param titleDepo     예적금명
	 * @param AccountNumber 계좌 번호
	 * @param DepositAmount 잔액
	 * @param history       거래 내역 리스트
	 */
	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount,
			ArrayList<History> history) {

		this.accountNumber = AccountNumber;
		this.depositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.history = history;

	}

	/**
	 * 계좌 생성자
	 * 
	 * @param bankDepo      금융사
	 * @param titleDepo     예적금명
	 * @param AccountNumber 계좌 번호
	 * @param DepositAmount 잔액
	 */
	public BankAccount(String bankDepo, String titleDepo, String AccountNumber, int DepositAmount) {

		this.accountNumber = AccountNumber;
		this.depositAmount = DepositAmount;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.history = new ArrayList<History>();

	}

	/**
	 * 거래 내역 리스트 Getter
	 * @return 거래 내역 리스트
	 */
	public ArrayList<History> getHis() {
		return history;
	}
	/**
	 * 거래 내역 리스트 Setter
	 * @return 거래 내역 리스트
	 */
	public void setHis(ArrayList<History> his) {
		this.history = his;
	}
	/**
	 * 잔액 Getter
	 * @return 잔액
	 */
	public int getDepositAmount() {
		return depositAmount;
	}
	/**
	 * 잔액 Setter
	 * @return 잔액
	 */
	public void setDepositAmount(int depositAmount) {
		depositAmount = depositAmount;
	}
	/**
	 * 계좌 번호 Getter
	 * @return 계좌 번호
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * 계좌 번호 Setter
	 * @return 계좌 번호
	 */
	public void setAccountNumber(String accountNumber) {
		accountNumber = accountNumber;
	}
	/**
	 * 금융사 Getter
	 * @return 금융사
	 */
	public String getBankDepo() {
		return bankDepo;
	}
	/**
	 * 금융사 Setter
	 * @return 금융사
	 */
	public void setBankDepo(String bankDepo) {
		this.bankDepo = bankDepo;
	}
	/**
	 * 예적금명 Getter
	 * @return 예적금명
	 */
	public String getTitleDepo() {
		return titleDepo;
	}
	/**
	 * 예적금명 Setter
	 * @return 예적금명
	 */
	public void setTitleDepo(String titleDepo) {
		this.titleDepo = titleDepo;
	}

	/**
	 * 계좌 toString()
	 * String.format("BankAccount [depositAmount=%s, accountNumber=%s, bankDepo=%s, titleDepo=%s, history=%d]",
				depositAmount, accountNumber, bankDepo, titleDepo, history.size());
	 */
	@Override
	public String toString() {
		return String.format("BankAccount [depositAmount=%s, accountNumber=%s, bankDepo=%s, titleDepo=%s, history=%d]",
				depositAmount, accountNumber, bankDepo, titleDepo, history.size());
	}

}
// class
