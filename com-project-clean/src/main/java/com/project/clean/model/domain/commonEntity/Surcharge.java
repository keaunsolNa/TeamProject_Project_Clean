package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_SURCHARGE")
public class Surcharge implements Serializable{

	private static final long serialVersionUID = 6202658244685475646L;
	
	@Id
	@Column(name="SURCHARGE_INSURANCE")
	private int surchargeInsurance; 
	
	@Column(name="SURCHARGE_COMMISSION")
	private int surchargeCommission;
	
	@Column(name="SURCHARGE_BONUS")
	private int surchargeBonus;

	public Surcharge() {
	}

	public Surcharge(int surchargeInsurance, int surchargeCommission, int surchargeBonus) {
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
		return "Surcharge [surchargeInsurance=" + surchargeInsurance + ", surchargeCommission=" + surchargeCommission
				+ ", surchargeBonus=" + surchargeBonus + "]";
	}
	
	
}
