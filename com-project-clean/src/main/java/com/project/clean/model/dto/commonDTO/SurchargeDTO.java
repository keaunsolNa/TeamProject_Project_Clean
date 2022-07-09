package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class SurchargeDTO implements Serializable{

	private static final long serialVersionUID = -6477041770471783119L;

	private int surchargeInsurance; 
	private int surchargeCommission;
	private int surchargeBonus;
	
	public SurchargeDTO() {
	}

	public SurchargeDTO(int surchargeInsurance, int surchargeCommission, int surchargeBonus) {
		this.surchargeInsurance = surchargeInsurance;
		this.surchargeCommission = surchargeCommission;
		this.surchargeBonus = surchargeBonus;
	}

	public int getSurchargeInsurance() {
		return surchargeInsurance;
	}

	public void setSurchargeInsurance(int surchargeInsurance) {
		this.surchargeInsurance = surchargeInsurance;
	}

	public int getSurchargeCommission() {
		return surchargeCommission;
	}

	public void setSurchargeCommission(int surchargeCommission) {
		this.surchargeCommission = surchargeCommission;
	}

	public int getSurchargeBonus() {
		return surchargeBonus;
	}

	public void setSurchargeBonus(int surchargeBonus) {
		this.surchargeBonus = surchargeBonus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SurchargeDTO [surchargeInsurance=" + surchargeInsurance + ", surchargeCommission=" + surchargeCommission
				+ ", surchargeBonus=" + surchargeBonus + "]";
	}

	
	
}
