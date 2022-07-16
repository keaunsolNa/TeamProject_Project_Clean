package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class VacationCategoryDTO implements Serializable {
	private static final long serialVersionUID = 5113613357578816605L;
	
	private int categoryNo;
	private String vacationName;
	
	
	
	public VacationCategoryDTO() {
	}



	public VacationCategoryDTO(int categoryNo, String vacationName) {
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



	@Override
	public String toString() {
		return "vacationCategory [categoryNo=" + categoryNo + ", vacationName=" + vacationName + "]";
	}
	
	

}
