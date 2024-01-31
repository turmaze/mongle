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

	// 클래스를 Account 이름으로 하나 만들어서 > 상품 정보, 잔액 , 상품 이름 > 클래스 배열 < 저장되게 0 ㅡ 3
	// 송금하기 에서 계좌 번호,입금할 금액 받으면 > 계좌번호(잔액)에 연동되게 >
	// 상품 가입하면 그 은행에 맞는 계좌번호 난수로 생성.
	// 계좌번호 마다 잔액 int로 변수 하나 있어야 함.

}
// class
