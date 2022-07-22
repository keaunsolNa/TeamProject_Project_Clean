package com.project.clean.model.dto.commonDTO;

import java.sql.Date;

public class BestEmployeePayDTO implements java.io.Serializable{


	private static final long serialVersionUID = -887967325358696593L;

	private int bestEmployeePayHistoryNo;
	private java.sql.Date bestEmployeePayDate;
	private int bestEmployeeNo;
	private int bestEmployeeBonus;
	
	public BestEmployeePayDTO() {
	}

	public BestEmployeePayDTO(int bestEmployeePayHistoryNo, Date bestEmployeePayDate, int bestEmployeeNo,
			int bestEmployeeBonus) {
		super();
		this.bestEmployeePayHistoryNo = bestEmployeePayHistoryNo;
		this.bestEmployeePayDate = bestEmployeePayDate;
		this.bestEmployeeNo = bestEmployeeNo;
		this.bestEmployeeBonus = bestEmployeeBonus;
	}

	public int getBestEmployeePayHistoryNo() {
		return bestEmployeePayHistoryNo;
	}

	public void setBestEmployeePayHistoryNo(int bestEmployeePayHistoryNo) {
		this.bestEmployeePayHistoryNo = bestEmployeePayHistoryNo;
	}

	public java.sql.Date getBestEmployeePayDate() {
		return bestEmployeePayDate;
	}

	public void setBestEmployeePayDate(java.sql.Date bestEmployeePayDate) {
		this.bestEmployeePayDate = bestEmployeePayDate;
	}

	public int getBestEmployeeNo() {
		return bestEmployeeNo;
	}

	public void setBestEmployeeNo(int bestEmployeeNo) {
		this.bestEmployeeNo = bestEmployeeNo;
	}

	public int getBestEmployeeBonus() {
		return bestEmployeeBonus;
	}

	public void setBestEmployeeBonus(int bestEmployeeBonus) {
		this.bestEmployeeBonus = bestEmployeeBonus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BestEmployeePay [bestEmployeePayHistoryNo=" + bestEmployeePayHistoryNo + ", bestEmployeePayDate="
				+ bestEmployeePayDate + ", bestEmployeeNo=" + bestEmployeeNo + ", bestEmployeeBonus="
				+ bestEmployeeBonus + "]";
	}
	
	

	
	
	
}
