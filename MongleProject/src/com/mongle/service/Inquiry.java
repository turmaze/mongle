package com.mongle.service;

class Inquiry {
	private String titleInq;
	private String txtInq;

	public Inquiry(String titleInq, String txtInq) {
		super();
		this.titleInq = titleInq;
		this.txtInq = txtInq;
	}

	public String getTitleInq() {
		return titleInq;
	}

	public void setTitleInq(String titleInq) {
		this.titleInq = titleInq;
	}

	public String getTxtInq() {
		return txtInq;
	}

	public void setTxtInq(String txtInq) {
		this.txtInq = txtInq;
	}

	@Override
	public String toString() {
		return String.format("Inquiry [titleInq=%s, txtInq=%s]", titleInq, txtInq);
	}

}