package com.mongle.service.invest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.resource.Investment;
import com.mongle.view.MongleVisual;

/***
 * 투자관리 서비스 클래스
 */
public class InvestmentManagementService {

	/***
	 * 환전 관리 메서드
	 * 
	 * @return
	 */
	public static int ExchangeSave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		MongleVisual.menuHeader("보유 외화 상세보기");
		while (loop) {
			int j =printTableEx(Investment.list, "환전");
			//int j = printTableExs(Investment.list, "환전");
			System.out.println();
			System.out.printf("%22s1. 판매 \n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String num4 = scan.nextLine();

			if (num4.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.printf("%22s판매하고 싶은 외화 종류(번호) 입력 :", " ");
				int removeN = scan.nextInt();
				scan.nextLine();
				if(removeN > j) {
					System.out.printf("%22s잘못된 입력입니다. 다시 입력해 주세요 : ", " ");
					System.out.println();
				}else {
				System.out.printf("%22s정말로 판매 하시겠습니까?\n", " ");
				System.out.printf("%22s선택(y/n) : ", " ");
				while (loop) {
					String num = scan.nextLine();
					if (num.equals("y")) {

						transaction(removeN, "환전");
						remove(Investment.list, removeN, "환전");
						MongleVisual.menuHeader("보유 외화 상세보기");
						printTableEx(Investment.list, "환전");
						System.out.println();
						MongleVisual.stopper();
						return 0;
					} else if (num.equals("n")) {
						MongleVisual.menuMove("이전 화면");
						return 0;
					} else {
						MongleVisual.wrongInput();
						break;
					}
				}
				}} else if (num4.equals("0")) {
				return 0;
			} else {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}

	/***
	 * 금 상품 관리 메서드
	 * 
	 * @return
	 */
	public static int goldSave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		MongleVisual.menuHeader("보유 금 상품 상세보기");

		while (loop) {

			int j = printTableGold(Investment.list, "금");
			System.out.println();
			System.out.printf("%22s1. 판매 \n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String num3 = scan.nextLine();

			if (num3.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.printf("%22s판매할 상품 번호 입력 :", " ");
				int removeN = scan.nextInt();
				scan.nextLine();
				if(removeN > j) {
					System.out.printf("%22s잘못된 입력입니다. 다시 입력해 주세요 : ", " ");
					System.out.println();
				}else {
				System.out.printf("%22s정말로 판매 하시겠습니까?\n", " ");
				System.out.printf("%22s선택(y/n): ", " ");

				while (loop) {
					String num = scan.nextLine();
					if (num.equals("y")) {

						transaction(removeN, "금");
						remove(Investment.list, removeN, "금");
						MongleVisual.menuHeader("보유 금 상품 상세보기");
						printTableGold(Investment.list, "금");
						System.out.println();
						MongleVisual.stopper();
						return 0;
					} else if (num.equals("n")) {
						MongleVisual.menuMove("이전 화면");
						return 0;
					} else {
						MongleVisual.wrongInput();
						break;
					}
				}
				}} else if (num3.equals("0")) {
				return 0;

			} else {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}

	/***
	 * 주식 상품 관리 메서드
	 * 
	 * @return
	 */
	public static int stockSave() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		boolean loop2 = true;
		MongleVisual.menuHeader("보유 주식 상세보기");
		while (loop) {

			printTableStock(Investment.list, "주식");

			System.out.println();
			System.out.printf("%22s1. 매수\n", " ");
			System.out.printf("%22s2. 일괄매도\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			while (loop) {
				String num2 = scan.nextLine();

				if (num2.equals("1")) { //// 매수

					System.out.printf("%22s매수할 상품번호 선택: ", " ");
					int plus = scan.nextInt();
					scan.nextLine();
					System.out.printf("%22s수량 입력 : ", " ");
					int num = scan.nextInt();
					scan.nextLine();

					int total = stockcare(Investment.list, plus, "주식", num); // 합쳐진 수..
					transactionStock(plus, "주식", num); // 계좌 골라서 넣기..

					// 매수하고 상세보기 불러오기
					MongleVisual.menuHeader("보유 주식 상세보기");
					printTablePlusStock(Investment.list, "주식", total, plus);
					System.out.println();
					MongleVisual.stopper();
					return 0;

				} else if (num2.equals("2")) { // 일괄매도

					System.out.printf("%22s일괄매도할 상품번호 선택 :", " ");
					int removeN = scan.nextInt();
					scan.nextLine();

					System.out.printf("%22s정말로 매도하시겠습니까?\n", " ");
					System.out.printf("%22s선택(y/n) : ", " ");
					while (loop2) {
						String answer = scan.nextLine();
						if (answer.equals("y")) {
							transaction(removeN, "주식");

							remove(Investment.list, removeN, "주식");
							MongleVisual.menuHeader("보유 주식 상세보기");
							printTableStock(Investment.list, "주식");
							System.out.println();
							MongleVisual.stopper();
							return 0;
						} else if (answer.equals("n")) {
							MongleVisual.menuMove("이전 화면");
							return 0;
						} else {
							MongleVisual.wrongInput();
							break;
						}
					}
				} else if (num2.equals("0")) {
					return 0;
				} else {
					MongleVisual.wrongInput();
					break;
				}
			}
		}
		return 0;
	}

	/***
	 * 금 상품 출력 메서드
	 * 
	 * @param data   투자 상품 불러오는 리스트
	 * @param invest 투자 상품 명
	 */

	public static int printTableGold(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		String header = "+----+------------+---------------+------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|         \t|   시장가\t|    수량    |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {

				System.out.printf("%22s|%3d | %5s\t|   %,.0f원\t|  %,5d개   |\n", " ", j + 1, data.get(i).getRealTitle(),
						data.get(i).getPrice(), data.get(i).getAmount());
				j++;
			}
		}
		System.out.printf("%22s%s\n", " ", header);
		return j;
	}

	/***
	 * 환전 상품 관리 메서드
	 * 
	 * @param data   투자 상품 불러오는 리스트
	 * @param invest 투자 상품 명
	 */
	public static int printTableEx(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		String header = "+----+------------+------------------------+--------------+------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|         \t|         외화명    \t |    구매가\t|    수량    |\n", " ");
		System.out.printf("%22s%s\n", " ", header);

		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {

				System.out.printf("%22s|%3d | %5s\t|%12s\t |    %,.0f원\t|  %,5d개   |\n", " ", j + 1,
						data.get(i).getRealTitle(), data.get(i).getTitleDepo(), data.get(i).getPrice(),
						data.get(i).getAmount());

				j++;

			}
		}
		System.out.printf("%22s%s\n", " ", header);
		return j;
	}
	

	/***
	 * 주식 상품 관리 메서드
	 * 
	 * @param data   투자 상품 불러오는 리스트
	 * @param invest 투자 상품 명
	 */
	public static void printTableStock(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		String header = "+----+------------+---------------+---------------+------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|         \t|    상품명 \t|   구매가\t|    수량    |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				System.out.printf("%22s|%3d | %5s\t|%7s\t|   %,.0f원\t|  %,5d개   |\n", " ", j + 1,
						data.get(i).getRealTitle(), data.get(i).getBankDepo(), data.get(i).getPrice(),
						data.get(i).getAmount());
				j++;

			}

		}
		System.out.printf("%22s%s\n", " ", header);
	}

	/***
	 * 매수 후 출력 메서드
	 * 
	 * @param data   투자 상품 불러오는 리스트
	 * @param invest 투자 상품 명
	 * @param total  매수 후 변한 수량
	 * @param plus   매수할 상품번호
	 */
	public static void printTablePlusStock(ArrayList<Investment> data, String invest, int total, int plus) {

		int j = 0;
		int printNum = 1;
		String header = "+----+------------+---------------+---------------+------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|         \t|    상품명 \t|   구매가\t|    수량    |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				if (printNum == plus) {
					data.get(i).setAmount(total);
				}
				System.out.printf("%22s|%3d | %5s\t|%7s\t|   %,.0f원\t|  %,5d개   |\n", " ", printNum,
						data.get(i).getTitleDepo(), data.get(i).getBankDepo(), data.get(i).getPrice(),
						data.get(i).getAmount());
				printNum++;
			}
			j++;
		}
		System.out.printf("%22s%s\n", " ", header);
	}

	/***
	 * 투자 상품 판매 메서드
	 * 
	 * @param data    투자 상품 불러오는 리스트
	 * @param removeN 판매하려는 상품 번호
	 * @param invest  판매하려는 투자 상품
	 */
	public static void remove(ArrayList<Investment> data, int removeN, String invest) {
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					data.remove(i);
					return; // 삭제 후 바로 메서드 종료
				}
			}
		}
	}

	/***
	 * 주식 매수후 수량 계산 메서드
	 * 
	 * @param data    투자 상품 불러오는 리스트
	 * @param removeN 매수하려는 상품 번호
	 * @param invest  판매하려는 투자 상품
	 * @param num     매수 수량
	 * @return 기존 상품 수 + 매수 수량
	 */
	public static int stockcare(ArrayList<Investment> data, int removeN, String invest, int num) {
		int j = 0;
		int care = 0;
		int money = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					care = data.get(i).getAmount() + num;
					// money = (int)data.get(i).getPrice() * care ;

				}
			}
		}
		return care;
	}

	/***
	 * 판매 후 가격 계산하는 메서드
	 * 
	 * @param data    투자 상품 불러오는 리스트
	 * @param removeN 판매하려는 상품 번호
	 * @param invest  판매하려는 투자 상품
	 * @return 판매하려는 상품의 가격 * 수량 > 계좌에 들어가는 값
	 */
	public static int totalmoney(ArrayList<Investment> data, int removeN, String invest) {
		int j = 0;
		int total = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					total = (int) data.get(i).getPrice() * data.get(i).getAmount();
				}
			}
		}
		return total;
	}

	/***
	 * 매수 후 가격 계산하는 메서드
	 * 
	 * @param data    투자 상품 불러오는 리스트
	 * @param removeN 매수하려는 상품 번호
	 * @param invest  판매하려는 투자상품
	 * @param num     매수할 수량
	 * @return 매수 시장가 * 매수 수량 > 계좌에서 빠질 값
	 */
	public static int stockmoney(ArrayList<Investment> data, int removeN, String invest, int num) {
		int j = 0;
		int money = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					// care = data.get(i).getAmount()+num;
					money = (int) data.get(i).getPrice() * num;
				}
			}
		}
		return money;
	}

	/***
	 * 수익이 입금될 계좌 선택 하는 메서드
	 * 
	 * @param removeN 판매하려는 상품 번호
	 * @param invest  판매하려는 투자 상품
	 * @return
	 */
	public static int transaction(int removeN, String invest) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		MongleVisual.menuHeader("계좌 선택");
		String header = "+----+------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|      금융사   \t|         상품명      \t|         계좌번호\t|       잔액      |\n");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.printf("%22s판매 수익이 입금될 계좌 선택(번호) \n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");

		while (loop) {
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			int totalPrice = totalmoney(Investment.list, removeN, invest);
			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 1;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());
					if (totalPrice > 0) {
						History.make(acc.getAccountNumber(), invest + " 수익", totalPrice);
						System.out.println();

						System.out.printf("%22s완료되었습니다.\n", " ");
						// MongleVisual.stopper();
						loop = false;
					}
				} else {
					MongleVisual.wrongInput();
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}

	/***
	 * 매도를 위한 계좌 선택 메서드
	 * 
	 * @param removeN 판매하려는 상품 번호
	 * @param invest  판매하려는 투자 상품
	 * @param num     매수할 수량
	 * @return
	 */
	public static int transactionStock(int removeN, String invest, int num) {
		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("계좌 선택");
		String header = "+----+------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|      금융사   \t|         상품명      \t|         계좌번호\t|       잔액      |\n");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.printf("%22s결제할 계좌 선택(번호)\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		boolean loop = true;

		while (loop) {
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			int totalPrice = stockmoney(Investment.list, removeN, "주식", num);

			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());
					if (acc.getDepositAmount() > totalPrice) {
						History.make(acc.getAccountNumber(), invest + " 매수", -totalPrice);
						System.out.println();

						System.out.printf("%22s매수가 완료되었습니다.\n", " ");

						// MongleVisual.stopper();
						loop = false;
					}
				} else {
					MongleVisual.wrongInput();
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}

	public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("| %-3d|%-8s\t|%-18s\t|%15s\t|%,15d원|\n", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());
		}
	}

}
