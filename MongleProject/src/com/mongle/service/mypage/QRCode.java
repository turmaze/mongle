package com.mongle.service.mypage;

import com.mongle.view.MongleVisual;

public class QRCode {
	public static void qr() {
		
		MongleVisual.menuHeader("결제용 QR코드");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command("mspaint.exe", "dat\\qr.png");
			builder.start();
		} catch (Exception e) {
			System.out.println("qr.main");
			e.printStackTrace();
		}
	}
}
