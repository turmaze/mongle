package com.mongle.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History {
	private String date;
	private String memo;
	private int amount;
	private int balance;

	@Override
	public String toString() {
		return String.format("History [date=%s, memo=%s, amount=%s, banlance=%s]", date, memo, amount, balance);
	}

	public History(String date, String memo, int amount, int balance) {
		super();
		this.date = date;
		this.memo = memo;
		this.amount = amount;
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public static void make(String accountNumber, String memo, int amount) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String date = sdf1.format(now);

		for (BankAccount acc : BankAccount.list) {
			if (acc.getAccountNumber().equals(accountNumber)) {
				int rest = acc.getDepositAmount() + amount;
				ArrayList<History> history = new ArrayList<History>();
				if (acc.getHis() != null) {
					for (int i = 0; i < acc.getHis().size(); i++) {
						History h = new History(acc.getHis().get(i).getDate(), acc.getHis().get(i).getMemo(),
								acc.getHis().get(i).getAmount(), acc.getHis().get(i).getBalance());
						history.add(h);
					}
				}
				History h = new History(date, memo, amount, rest);
				history.add(h);

				BankAccount.list.set(BankAccount.list.indexOf(acc),
						new BankAccount(acc.getBankDepo(), acc.getTitleDepo(), acc.getAccountNumber(), rest, history));

			}
		}

	}
}
