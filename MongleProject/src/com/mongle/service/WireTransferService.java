package com.mongle.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.view.MongleVisual;

public class WireTransferService {

	public static void extracted() {
		Scanner scanner = new Scanner(System.in);

		boolean loop = true;
		while (loop) {
			MongleVisual.menuHeader("송금");
			System.out.println();
			System.out.printf("%22s 1.송금하기\n", " ");
			System.out.printf("%22s 2.정산하기\n", " ");
			System.out.printf("%22s 3.예약송금\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			System.out.printf("%22s 번호를 입력하세요:", " ");
			String sel = scanner.nextLine();

			if (sel.equals("1")) {
				// 송금하기
				MongleVisual.menuMove("송금 화면");
				transaction();

			} else if (sel.equals("2")) {
				MongleVisual.menuMove("정산하기 화면");
				// 정산하기(더치페이)
				dutchpay(Integer.parseInt(sel));

			} else if (sel.equals("3")) {
				MongleVisual.menuMove("예약송금 화면");
				// 예약송금
				reserveTransfer();

			} else if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				// 이전으로
				loop = false;

			} else {
				MongleVisual.wrongInput();
			}
		}

	}// extracted

	private static void dutchpay(int shareChoice) {
		// 정산하기(더치페이) 인원 설정 로직 구현
		Scanner scanner = new Scanner(System.in);
		int amount = 0;
		MongleVisual.menuHeader("정산하기");
		// 정수 입력을 위한 예외 처리
		try {
			System.out.printf("%22s정산 금액 입력: ", " ");
			amount = scanner.nextInt();
		} catch (Exception e) {
			scanner.nextLine(); //
			return;
		}

		if (amount < 0) {
			System.out.printf("%22s잘못된 입력입니다. 다시 입력해주세요.", " ");
			return;
		}

		// 정산하기(더치페이)금액 설정 로직
		System.out.printf("%22s정산 금액이 " + amount + "원으로 설정되었습니다.\n", " ");
		System.out.println();
		// 정산하기(더치페이) 금액 설정 로직 구현
		System.out.printf("%22s정산 인원 설정:", " ");
		int numberOfPeople = scanner.nextInt();
		scanner.nextLine();

		// 정산(더치페이)할 대상 선택하기
		System.out.printf("%22s정산 대상 입력(이름):", " ");
		String targetNamesInput = scanner.nextLine();
		String[] targetNames = targetNamesInput.split("\\s+");

		// 요청인원 출력
		int totalPeople = targetNames.length + 1; // 입력된 대상의 수와 사용자 자신을 포함하여 총 인원 계산
		System.out.printf("%22s정산하기 총 인원: %d명\n", "", totalPeople);
		System.out.println();
		// 사용자에게 카카오톡 또는 메세지로 공유할지 선택하도록 안내
		System.out.printf("%22s정산 결과 공유방법 선택\n", " ");
		System.out.println();
		System.out.printf("%22s1. 카카오톡 공유\n", " ");
		System.out.printf("%22s2. 메세지 공유\n", " ");
		MongleVisual.choiceGuidePrint();
		String shareOption = scanner.nextLine();

		switch (shareOption) {
		case "1":
			shareKakaoTalk(totalPeople);
			System.out.printf("%22s카카오톡으로 공유하기 - \"메시지 전송완료.\"\n", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s 금액이 모두 모였습니다.\n", " ");
			History.make(BankAccount.list.get(0).getAccountNumber(), "더치페이 정산",
					((amount / numberOfPeople) * (numberOfPeople - 1)));
			break;

		case "2":
			shareMessage(totalPeople);
			System.out.printf("%22s메세지로 공유 - \"메시지 전송 완료.\"", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s.\n", " ");
			System.out.printf("%22s금액이 모두 모였습니다.\n", " ");
			History.make(BankAccount.list.get(0).getAccountNumber(), "더치페이 정산",
					((amount / numberOfPeople) * (numberOfPeople - 1)));
			break;

		default:
			System.out.println("잘못된 입력입니다. 기본 설정으로 메시지를 공유합니다.");
			shareMessage(totalPeople);
		}
	}

	private static void shareMessage(int totalPeople) {
		System.out.printf("%22s메세지로 공유하기 - \"%d명에게 지불 요청 메시지를 전송중...\"\n", " ", totalPeople);
		System.out.println();
	}

	private static void shareKakaoTalk(int totalPeople) {
		System.out.printf("%22s카카오톡으로 공유하기 - \"%d명에게 지불 요청 메시지를 전송중...\"\n", " ", totalPeople - 1);
		System.out.println();
	}

	private static void reserveTransfer() {
		// 예약송금 로직 구현
		Scanner scanner = new Scanner(System.in);

		MongleVisual.menuHeader("예약 송금");
		System.out.println();
		System.out.printf("%22s예약 시간 입력\n", " ");

		// 년도는 수정이 불가능하므로 고정
		int year = 2024;

		// 이번달과 다음달까지 선택 가능하도록 설정
		System.out.printf("%22s24.02월 예약은 1번, 24.03월 예약은 2번(1,2 중 택 1): ", " ");
		int month = scanner.nextInt();

		if (month != 1 && month != 2) {
			System.out.printf("%22s잘못된 입력입니다.", " ");
			return;
		}

		int maxDay;
		if (month == 1) {
			maxDay = 31; // 이번달의 말일
		} else {
			maxDay = 30; // 다음달의 말일
		}

		System.out.printf("%22s예약 설정일 입력(1일부터 " + maxDay + "일 사이): ", " ");
		int day = scanner.nextInt();

		if (day < 1 || day > maxDay) {
			System.out.printf("%22s올바른 날짜를 입력하세요.", " ");
			return;
		}

		// 시간은 정수형태로 입력받도록 함
		System.out.printf("%22s시간 입력(예: 01:26): ", " ");
		System.out.println();
		String timeInput = scanner.next();
		String[] timeParts = timeInput.split(":");
		if (timeParts.length != 2) {
			System.out.printf("%22s[오류 발생] 올바른 형식으로 시간을 입력하세요 (hh:mm).", " ");
			return;
		}
		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);

		if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
			System.out.printf("%22s[오류 발생] 올바른 시간을 입력하세요 (00:00부터 23:59까지).", " ");
			return;
		}
		boolean loop = true;
		// 송금 확인 메시지
		System.out.printf(
				"%22s[" + year + "년 " + month + "월 " + day + "일 " + hour + "시 " + minute + "분]에 예약 송금하시겠습니까? (y/n): ",
				" ");

		while (true) {
			String confirm = scanner.next();

			if (confirm.equals("y")) {
				System.out.println();
				String reservationTime = String.format("%d년 %d월 %d일 %d시 %d분", year, month, day, hour, minute);
				System.out.printf("%22s예약 완료.. 예약 시간: %s\n", " ", reservationTime);
				break;
			} else if (confirm.equals("n")) {
				System.out.printf("%22s예약 취소", " ");
				break;
			} else {
				System.out.printf("%22s올바른 선택을 해주세요.", " ");
			}
		}

	}

	public static int transaction() {
		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("송금하기");

		String header = "+---+-------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.println();
		System.out.printf("%22s 출금할 계좌를 선택해 주세요(번호 입력)\n", " ");
		MongleVisual.choiceGuidePrint();
		String sel = scan.nextLine();

		boolean loop = true;
		while (loop) {
			System.out.println();
			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());
					System.out.printf("%22s 송금 계좌의 은행명", " ");
					String bankName = scan.nextLine();

					System.out.printf("%22s 송금 계좌번호 입력( - 포함):", " ");
					String accountNumber = scan.nextLine();

					// 유효성 검사
					if (acc.getAccountNumber().equals(accountNumber)) {
						System.out.printf("%22s 송금 계좌번호 다시 확인.\n", " ");
						continue;
					}

					System.out.printf("%22s 송금 금액 입력:", " ");
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
