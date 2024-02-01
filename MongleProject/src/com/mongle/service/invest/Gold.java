package com.mongle.service.invest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mongle.asset.DepoSave;
import com.mongle.resource.BankAccount;
import com.mongle.service.InvestService;
import com.mongle.view.MongleVisual;

public class Gold {

	private static double price;
	private static int num;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static int goldService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 상품 정보 리스트
		boolean loop = true;
		int r = -1;

		while (loop) {
			
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("금");
			
			table = searchGold(table); // 금상품 검색 메서드

			System.out.println();
			System.out.printf("%22s1. 구매\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();

			while (loop) {
				System.out.printf("%22s선택(번호) :", " ");
				String sel = scan.nextLine();
				if (sel.equals("1")) {
					Gold gold = new Gold();
					gold.setPrice((table.get(0).getRate() + table.get(0).getMaxRate()) / 2);
					System.out.println();
					gold.setNum(orderGold(gold.price, scan));
					loop = false;
				} else if (sel.equals("9")) {
					System.out.printf("%22s홈 화면으로 이동합니다.", " ");
					return 9;
				} else if (sel.equals("0")) {
					System.out.printf("%22s이전 화면으로 이동합니다.", " ");
					return 0;
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요.", " ");
				}
			} // while
		} // while
		return 0;
	}

	public static int orderGold(double price, Scanner scan) {
		boolean loop = true;
		System.out.printf("%22s===================================\n", " ");
		System.out.printf("%22s   \t  호가 %,.0f원\n", " ", price);
		System.out.printf("%22s===================================\n", " ");
		while (loop) {
			System.out.printf("%22s주문 수량 입력(숫자):", " ");
			try {
				String num = scan.nextLine();
				if (Integer.parseInt(num) > 0) {

					transaction(price, Integer.parseInt(num));

					
					return Integer.parseInt(num);
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 형식으로 입력해주세요.", " ");
			}
		} // while
		return 0;
	}

	private static void transaction(double price, int num) {
		Scanner scan = new Scanner(System.in);

		String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s주문 내역을 결제할 계좌를 선택해주세요.\n", " ");
		boolean loop = true;

		while (loop) {
			System.out.printf("%22s선택(번호) : ", " ");
			String sel = scan.nextLine();
			double totalPrice = price * num;

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {

					for (BankAccount acc : BankAccount.list) {
						if (acc.getAccountNumber()
								.equals(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber())) {
							if (acc.getDepositAmount() > totalPrice) {

								double rest = acc.getDepositAmount() - totalPrice;
								BankAccount.list.set(BankAccount.list.indexOf(acc), new BankAccount(acc.getBankDepo(),
										acc.getTitleDepo(), acc.getAccountNumber(), (int) rest));
								
								System.out.println();
								System.out.printf("%22s주문가격 %,.0f원(시장가) / 주문 수량 : %s\n", " ", price, num);
								System.out.printf("%22s거래가 완료되었습니다.\n", " ");
								System.out.printf("%22s거래 후 잔액은 %,.0f원입니다.\n", " ", rest);
								System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
								scan.nextLine();
								loop = false;
							} else if (acc.getDepositAmount() < totalPrice) {
								System.out.printf("%22s계좌의 잔액이 부족합니다.\n", " ");
								System.out.printf("%22s다시 선택해주세요.\n", " ");
							}
						}
					}
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}
		}

	}

	public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}

	private static List<InfoProduct> searchGold(List<InfoProduct> table) {
		String header = "+------------+--------------------+----------+-----------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|   고시날짜   |       상품명\t\t| 최고가(1g) | 최저가(1g) |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		try {
			String url = "https://apis.data.go.kr/1160100/service/GetGeneralProductInfoService/getGoldPriceInfo?serviceKey=lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String period = getTagValue("basDt", eElement);
				String title = getTagValue("itmsNm", eElement);
				double maxrate = Double.parseDouble(getTagValue("hipr", eElement));
				double rate = Double.parseDouble(getTagValue("lopr", eElement));

				InfoProduct d = new InfoProduct("", title, period, rate, maxrate);
				table.add(d);
			}
			printAsciiTable(table);
		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		System.out.printf("%22s%s\n", " ", header);

		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < 10; i += 2) {
			System.out.printf("%22s|%-12s|%-12s\t|%9s원|%9s원|\n", " ", data.get(i).getPeriod(), data.get(i).getTitle(),
					data.get(i).getMaxRate(), data.get(i).getRate());
		}
	}

	public static String getTagValue(String tag, Element eElement) {
		String result = "";
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		result = nlList.item(0).getTextContent();
		return result;
	}

}
