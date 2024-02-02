package com.mongle.resource;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

public class PointItem {
	
	private String pointdate;
	private String stratedate;
	
	public static ArrayList<PointItem> list = new ArrayList<>();
	
	public void pointItem(String pointdate,String stratedate) {
		this.pointdate = pointdate;
		this.stratedate = stratedate;
	}
	
	public static void getPoint() {
		DataBase.getPrivateUser().get(0).get(list);
	}

	public String getPointdate() {
		return pointdate;
	}

	public String getStratedate() {
		return stratedate;
	}

	public void setPointdate(String pointdate) {
		this.pointdate = pointdate;
	}

	public void setStratedate(String stratedate) {
		this.stratedate = stratedate;
	}

	@Override
	public String toString() {
		return String.format("[출석일=%s, 연속출석일=%s]", pointdate, stratedate);
	}
	

}
