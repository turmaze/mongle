package com.mongle.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.view.MongleVisual;

/**
 * 송금관리 클래스
 */
public class WireTransferService {

	public static int extracted() {
		Scanner scanner = new Scanner(System.in);
		int r = -1;
		boolean loop = true;
		while (loop) {
			MongleVisual.menuHeader("송금");
			System.out.println();
			System.out.printf("%22s1.송금하기\n", " ");
			System.out.printf("%22s2.정산하기\n", " ");
			System.out.printf("%22s3.예약송금\n", " ");
			System.out.printf("%22s0.이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String sel = scanner.nextLine();

			if (sel.equals("1")) {
				// 송금하기
				MongleVisual.menuMove("송금 화면");
				r = transaction();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("2")) {
				MongleVisual.menuMove("정산하기 화면");
				// 정산하기(더치페이)
				r = dutchpay();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("3")) {
				MongleVisual.menuMove("예약송금 화면");
				// 예약송금
				r = reserveTransfer();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				// 이전으로
				return 0;

			} else {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}// extracted

	/**
	 * 정산하기
	 * 
	 * @param shareChoice
	 * @return 메뉴 이동을 위한 변수
	 */

	private static int dutchpay() {
		// 정산하기(더치페이) 인원 설정 로직 구현
		Scanner scanner = new Scanner(System.in);
		int amount = 0;
		MongleVisual.menuHeader("정산하기");

		// 정수 입력을 위한 예외 처리
		boolean loop = true;
		while (loop) {
			try {
				System.out.printf("%22s정산 금액 입력: ", " ");
				String input = scanner.nextLine();
				if (Integer.parseInt(input) < 0) {
					System.out.printf("%22s입력오류.재입력하세요.", " ");
				} else {
					amount = Integer.parseInt(input);
					loop = false;
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		}
		// 정산하기(더치페이)금액 설정 로직
		System.out.printf("%22s정산 금액이 %d원으로 설정되었습니다.\n", " ", amount);
		System.out.println();

		// 정산하기(더치페이) 인원 설정 로직 구현
		System.out.printf("%22s정산 요청할 인원 설정:", " ");
		int numberOfPeople = 0;
		boolean validInput = false;

		while (!validInput) {
			try {
				numberOfPeople = scanner.nextInt();
				scanner.nextLine();
				if (numberOfPeople <= 0) {
					System.out.printf("%22s입력오류. 재입력하세요", " ");
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.out.printf("%22s정수로 입력하세요", " ");
				scanner.nextLine();
			}
		}
		System.out.printf("%22s", " ");
		scanner.nextLine();

		// [정산금액/정산요청인원+1] 원을 정산요청할까요?
		System.out.printf("%22s%,d원씩 %d명에게 정산요청할까요? (y/n)", " ", (amount / (numberOfPeople + 1)), numberOfPeople);
		String confirm = scanner.nextLine();

		if (confirm.equalsIgnoreCase("n")) {
			System.out.println("정산요청 취소");
			return 0;
		} else if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("입력오류. 기본 설정으로 진행합니다.");
		}

		double amountPerPerson = amount / (numberOfPeople + 1);
		int roundedAmountPerPerson = (int) amountPerPerson;

		System.out.printf("%22s정산금액: %,d원\n", "", roundedAmountPerPerson);
		System.out.println();

		System.out.printf("%22s정산 요청 대상자의 전화번호(- 포함X):\n", " ");
		Set<String> phoneNumbers = new HashSet<>(); // 전화번호 중복방지

		for (int i = 0; i < numberOfPeople; i++) { // 정산 요청할 인원 설정만큼 입력 반복
			while (true) {
				System.out.printf("%22s010", " ");
				String phoneNumber = scanner.nextLine().trim();
				if (phoneNumber.length() != 8 || !phoneNumber.matches("[0-9]+")) {
					System.out.printf("%22s입력오류(010으로 시작하는 8자의 숫자).\n", " ");
				} else if (!phoneNumbers.add(phoneNumber)) { // 전화번호 중복방지
					System.out.printf("%22s중복입력.다른 번호를 입력해주세요.\n", " ");
				} else {
					break;
				}
			}
		}

		System.out.println();
		// 정산 결과 공유방법 선택 안내
		System.out.printf("%22s정산 결과 공유방법 선택\n", " ");
		System.out.println();
		System.out.printf("%22s1. 카카오톡 공유\n", " ");
		System.out.printf("%22s2. 메세지 공유\n", " ");
		MongleVisual.choiceGuidePrint();
		String shareOption = scanner.nextLine();

		switch (shareOption) {
		case "1":
			shareKakaoTalk(numberOfPeople);
			System.out.printf("%22s카카오톡으로 공유하기 - \"메시지 전송완료.\"\n", " ");
			break;

		case "2":
			shareMessage(numberOfPeople);
			System.out.printf("%22s메세지로 공유 - \"메시지 전송 완료.\"\n", " ");
			break;

		default:
			System.out.println("잘못된 입력입니다. mongle로 공유합니다.");
			shareMessage(numberOfPeople);
		}
		System.out.printf("%22s.\n", " ");
		System.out.printf("%22s.\n", " ");
		System.out.printf("%22s.\n", " ");
		System.out.printf("%22s금액이 모두 모였습니다.\n", " ");
		History.make(BankAccount.list.get(0).getAccountNumber(), "더치페이 정산",
				((amount / numberOfPeople) * (numberOfPeople - 1)));
		MongleVisual.menuMove("이전 화면");
		return 0;
	}

	/**
	 * 정산 메세지 전송-메세지
	 *
	 * @param totalPeople 정산 총 인원(자신 포함)
	 */
	private static void shareMessage(int totalPeople) {
		System.out.printf("%22s메세지로 공유하기 - \"%d명에게 지불 요청 메시지를 전송중...\"\n", " ", totalPeople);
		System.out.println();
	}

	/**
	 * 정산메세지 전송-카카오톡
	 *
	 * @param totalPeople 정산 총 인원(자신 포함)
	 */
	private static void shareKakaoTalk(int totalPeople) {
		System.out.printf("%22s카카오톡으로 공유하기 - \"%d명에게 지불 요청 메시지를 전송중...\"\n", " ", totalPeople);
		System.out.println();
	}

	/**
	 * 예약 송금
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */

	private static int reserveTransfer() {
		// 예약송금 로직 구현
		Scanner scanner = new Scanner(System.in);

		MongleVisual.menuHeader("예약 송금");
		System.out.println();
		System.out.printf("%22s2024년 내 예약 가능 ", " ");
		System.out.println();
		System.out.printf("%22s예약희망 월(月) 입력: ", " ");

		int year = LocalDate.now().getYear();
		int month = 0;
		int day = 0;
		int hour = 0; 
		int minute = 0; 
		boolean loop = true;

		while (loop) {
			try {
				month = scanner.nextInt();

				if (month < 1 || month > 12) {
					System.out.printf("%22s유효한 월(1月 ~ 12月) 입력:", " ");
					continue;
				}
				System.out.println();
				System.out.printf("%22s예약희망 일(日) 입력: ", " ");
				day = scanner.nextInt();

				LocalDate currentDate = LocalDate.now();
				LocalDate userDate = LocalDate.of(year, month, day);
				System.out.println();

				if (userDate.isBefore(currentDate)) {
					System.out.printf("%22s예약희망 월(月) 입력 오류", " ");
					System.out.printf("%22s예약 희망 월(月) 재입력하세요:", " ");
					continue;
				}
				System.out.println();
				if (day < 1 || day > userDate.lengthOfMonth()) {
					System.out.printf("%22s예약희망 일(日) 입력 오류", " ");
					System.out.printf("%22s유효한 일(日)재입력하세요:\n ", " ");
					continue;
				}

				loop = true;
				while (loop) {
					System.out.printf("%22s시간 입력(예: 01:26): ", " ");
					String timeInput = scanner.next();
					String[] timeParts = timeInput.split(":");
					if (timeParts.length != 2) {
						System.out.printf("%22s시간입력오류. 시간을 재입력하세요(hh:mm).\n", " ");
						continue;
					}
					hour = Integer.parseInt(timeParts[0]);
					minute = Integer.parseInt(timeParts[1]);

					if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
						System.out.printf("%22s시간을 재입력하세요(00:00부터 23:59까지)\n", " ");
						continue;
					} else {
						loop = false;
					}
				}

				System.out.printf("%22s예약 희망 일자: %d월 %d일 %d시 %d분에 예약하시겠습니까? (y/n) ", " ", month, day, hour, minute);
				String retry = scanner.next();
				if (retry.equals("y")) {
					System.out.printf("%22s예약 시간 설정 완료. 출금계좌 선택창으로 이동합니다.\n", " ");
					scanner.nextLine();
					scanner.nextLine();
				} else if (retry.equals("n")) {
					System.out.printf("%22s예약 취소", " ");
					loop = false;
					return 0;
				} else {
					System.out.printf("%22s올바른 선택을 해주세요.\n", " ");
					loop = false;
				}
			} catch (Exception e) {
				System.out.printf("%22s예약희망 일(日) 입력 오류.일(日)재입력하세요:", " ");
				scanner.nextLine();
			}
		}

		transaction();
		return 0;
	}

	public static int transaction() {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuHeader("송금하기");

		System.out.println();
		String header = "+---+-------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|       금융사   \t|         상품명      \t|         계좌번호\t|       잔액       |\n");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.println();
		System.out.printf("%22s출금할 계좌를 선택해 주세요(번호 입력)\n", " ");
		MongleVisual.choiceGuidePrint();
		String sel = scan.nextLine();
		BankAccount selectedAccount = null;
		do {
			try {
				int selectedIndex = Integer.parseInt(sel) - 1;
				if (selectedIndex >= 0 && selectedIndex < filteredList.size()) {
					selectedAccount = filteredList.get(selectedIndex);
					// 선택한 계좌가 유효한지 확인
					if (selectedAccount == null) {
						System.out.printf("%22s선택한 계좌가 존재하지 않습니다.\n", " ");
					} else {
						break;
					}
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요:", " ");
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 번호를 입력해주세요:", " ");
			}

			// 다시 선택 받음
			sel = scan.nextLine();
		} while (true);

		boolean loop = true;
		while (loop) {
			System.out.println();
			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());

					// 송금 계좌 은행 번호 선택
					System.out.printf("%22s+----+-----------+\n", " ");
					System.out.printf("%22s|번호|  은행명  |\n", " ");
					System.out.printf("%22s+----+-----------+\n", " ");
					System.out.printf("%22s| 1  |   농협   |\n", " ");
					System.out.printf("%22s| 2  |   기업   |\n", " ");
					System.out.printf("%22s| 3  |   국민   |\n", " ");
					System.out.printf("%22s| 4  |   신한   |\n", " ");
					System.out.printf("%22s| 5  |   우리   |\n", " ");
					System.out.printf("%22s| 6  |   하나   |\n", " ");
					System.out.printf("%22s| 7  |   씨티   |\n", " ");
					System.out.printf("%22s| 8  |   산업   |\n", " ");
					System.out.printf("%22s| 9  |   수협   |\n", " ");
					System.out.printf("%22s| 10 |   카카오 |\n", " ");
					System.out.printf("%22s+---+-----------+\n", " ");
					System.out.printf("%22s송금할 은행을 선택하세요(번호 입력): ", " ");
					int bankChoice = Integer.parseInt(scan.nextLine());

					// 유효성 검사
					if (bankChoice < 1 || bankChoice > 10) {
						System.out.printf("%22s올바른 은행 번호를 선택해주세요.\n", " ");
						continue;
					}

					String[] bankNames = { "농협", "기업", "국민", "신한", "우리", "하나", "씨티", "산업", "수협", "카카오" };
					String bankName = bankNames[bankChoice - 1];

					System.out.printf("%22s송금 계좌번호 입력( - 포함X): ", " ");
					String accountNumber = scan.nextLine();

					// 송금 계좌번호 11~15자리 입력
					if (accountNumber.length() < 11 || accountNumber.length() > 15) {
						System.out.printf("%22s송금 계좌번호를 확인 후 다시 입력해주세요.\n", " ");
						continue;
					} else {

						// 유효성 검사-숫자만 입력가능하도록
						boolean isNumber = accountNumber.matches("[0-9]+");
						if (!isNumber) {
							System.out.printf("%22s송금 계좌번호를 확인 후 다시 입력해주세요.\n", " ");
							continue;
						}
					}

					System.out.printf("%22s송금 금액 입력 : ", " ");
					int money = scan.nextInt();
					scan.nextLine();

					if (acc.getDepositAmount() >= money) {
						int rest = acc.getDepositAmount() - money;
						History.make(acc.getAccountNumber(), "송금", -money);
						System.out.println();
						System.out.printf("%22s송금 완료.\n", " ");
						System.out.println();
						System.out.printf("%22s송금 후 잔액 %,d원.\n", " ", rest);
						System.out.println();
						System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
						scan.nextLine();
						loop = false;
						return 0;

					} else if (acc.getDepositAmount() < money) {
						System.out.printf("%22s계좌 잔액 부족\n", " ");
						System.out.printf("%22s다시 선택하세요.\n", " ");
					}
				} else if (sel.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
				} else {
					MongleVisual.wrongInput();
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}

	/**
	 * 송금하기
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int transaction1() {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuHeader("송금하기");

		String header = "+---+-------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|       금융사   \t|         상품명      \t|         계좌번호\t|       잔액       |\n");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.println();
		System.out.printf("%22s출금할 계좌를 선택해 주세요(번호 입력)\n", " ");
		MongleVisual.choiceGuidePrint();
		String sel = scan.nextLine();
		BankAccount selectedAccount = null;
		do {
			try {
				int selectedIndex = Integer.parseInt(sel) - 1;
				if (selectedIndex >= 0 && selectedIndex < filteredList.size()) {
					selectedAccount = filteredList.get(selectedIndex);
					// 선택한 계좌가 유효한지 확인
					if (selectedAccount == null) {
						System.out.printf("%22s선택한 계좌가 존재하지 않습니다.\n", " ");
					} else {
						break;
					}
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요:", " ");
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 번호를 입력해주세요:", " ");
			}

			// 다시 선택 받음
			sel = scan.nextLine();
		} while (true);

		boolean loop = true;
		while (loop) {
			System.out.println();
			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());

					// 송금 계좌 은행 번호 선택
					System.out.printf("%22s+----+---------------+\n", " ");
					System.out.printf("%22s|번호|   은행명|\n", " ");
					System.out.printf("%22s+----+---------------+\n", " ");
					System.out.printf("%22s| 1  |     농협     |\n", " ");
					System.out.printf("%22s| 2  |     기업     |\n", " ");
					System.out.printf("%22s| 3  |     국민     |\n", " ");
					System.out.printf("%22s| 4  |     신한     |\n", " ");
					System.out.printf("%22s| 5  |     우리     |\n", " ");
					System.out.printf("%22s| 6  |     하나     |\n", " ");
					System.out.printf("%22s| 7  |     씨티     |\n", " ");
					System.out.printf("%22s| 8  |     산업     |\n", " ");
					System.out.printf("%22s| 9  |     수협     |\n", " ");
					System.out.printf("%22s| 10 |     카카오|\n", " ");
					System.out.printf("%22s+----+---------------+\n", " ");
					System.out.printf("%22s송금할 은행을 선택하세요(번호 입력): ", " ");
					int bankChoice = Integer.parseInt(scan.nextLine());

					// 유효성 검사
					if (bankChoice < 1 || bankChoice > 10) {
						System.out.printf("%22s올바른 은행 번호를 선택해주세요.\n", " ");
						continue;
					}

					String[] bankNames = { "농협", "기업", "국민", "신한", "우리", "하나", "씨티", "산업", "수협", "카카오" };
					String bankName = bankNames[bankChoice - 1];

					System.out.printf("%22s송금 계좌번호 입력( - 포함X): ", " ");
					String accountNumber = scan.nextLine();

					// 송금 계좌번호 11~15자리 입력
					if (accountNumber.length() < 11 || accountNumber.length() > 15) {
						System.out.printf("%22s송금 계좌번호를 확인 후 다시 입력해주세요.\n", " ");
						continue;
					} else {

						// 유효성 검사-숫자만 입력가능하도록
						boolean isNumber = accountNumber.matches("[0-9]+");
						if (!isNumber) {
							System.out.printf("%22s송금 계좌번호를 확인 후 다시 입력해주세요.\n", " ");
							continue;
						}
					}

					System.out.printf("%22s송금 금액 입력 : ", " ");
					int money = scan.nextInt();
					scan.nextLine();

					if (acc.getDepositAmount() >= money) {
						int rest = acc.getDepositAmount() - money;
						History.make(acc.getAccountNumber(), "송금", -money);
						System.out.println();
						System.out.printf("%22s송금 완료.\n", " ");
						System.out.printf("%22s송금 후 잔액 %,d원.\n", " ", rest);

						System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
						scan.nextLine();
						loop = false;
						return 0;

					} else if (acc.getDepositAmount() < money) {
						System.out.printf("%22s계좌 잔액 부족\n", " ");
						System.out.printf("%22s다시 선택하세요.\n", " ");
					}
				} else if (sel.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
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
			System.out.printf("|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}
}
