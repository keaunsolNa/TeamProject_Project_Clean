package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_VACATION_CATEGORY")
public class VacationCategory implements Serializable{

	private static final long serialVersionUID = -4616502151436870323L;
	
	@Id
	@Column(name="CATEGORY_NO")
	private int categoryNo;
	
	@Column(name="VACATION_NAME")
	private String vacationName;

	public VacationCategory() {
	}

	public VacationCategory(int categoryNo, String vacationName) {
		this.categoryNo = categoryNo;
		this.vacationName = vacationName;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getVacationName() {
		return vacationName;
	}

	public void setVacationName(String vacationName) {
		this.vacationName = vacationName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "VacationCategory [categoryNo=" + categoryNo + ", vacationName=" + vacationName + "]";
	}
	
	
}
