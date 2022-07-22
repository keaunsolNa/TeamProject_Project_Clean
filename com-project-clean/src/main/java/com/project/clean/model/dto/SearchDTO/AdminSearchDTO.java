package com.project.clean.model.dto.SearchDTO;

public class AdminSearchDTO {
	private String category;
	private String categoryValue;
	public AdminSearchDTO() {
		super();
	}
	public AdminSearchDTO(String category, String categoryValue) {
		super();
		this.category = category;
		this.categoryValue = categoryValue;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	@Override
	public String toString() {
		return "AdminSearchDTO [category=" + category + ", categoryValue=" + categoryValue + "]";
	}

	
}
