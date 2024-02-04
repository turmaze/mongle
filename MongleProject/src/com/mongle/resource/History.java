package com.mongle.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import com.mongle.service.invest.Loan;
import com.mongle.view.MongleVisual;

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
		BankAccount acc = BankAccount.findAccount(accountNumber);
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

	public static int check(String accountNumber) {
		ArrayList<History> history = new ArrayList<History>();
		BankAccount acc = BankAccount.findAccount(accountNumber);
		if (acc.getHis() != null) {
			history = acc.getHis();
		}

		Collections.sort(history, new Comparator<History>() {
			public int compare(History o1, History o2) {
				String date1 = o1.getDate();
				String date2 = o2.getDate();

				return date2.compareTo(date1);
			}
		});

		Scanner scan = new Scanner(System.in);
		int index = 0;
		boolean loop = true;

		transHistory(history, index);

		while (loop) {
			System.out.printf("%22s8. 다음 페이지\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			while (loop) {
				MongleVisual.choiceGuidePrint();
				String sel = scan.nextLine();
				try {
					if (sel.equals("8")) {
						if (history.size() < 10) {
							System.out.printf("%22s더이상 불러올 내역이 없습니다.\n", " ");
						} else {
							index += 10;
							transHistory(history, index);
						}
						break;
					} else if (sel.equals("9")) {
						MongleVisual.menuMove("홈 화면");
						return 9;
					} else if (sel.equals("0")) {
						MongleVisual.menuMove("이전 화면");
						return 0;
					} else {
						MongleVisual.wrongInput();
					}
				} catch (NumberFormatException e) {
					MongleVisual.wrongInput();
				}
			} // while
		}
		return 0;
	}

	private static void transHistory(ArrayList<History> history, int index) {
		String header = "+-------------------------+---------------+-------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|           날짜    \t  |      내역      |   거래 금액   |        잔액       |\n", " ");
		System.out.printf("%s\n", header);

		printAsciiTable(history, index); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
	}

	public static void printAsciiTable(ArrayList<History> data, int index) { // 표에 반복해서 출력하는 메서드

		for (int i = index; i < ((data.size() < index + 10) ? data.size() : index + 10); i++) {
			System.out.printf("|   %-10s   |%8s\t  |%,10d   |%,14d원  |\n", data.get(i).getDate(), data.get(i).getMemo(),
					data.get(i).getAmount(), data.get(i).getBalance());

		}
	}
}
