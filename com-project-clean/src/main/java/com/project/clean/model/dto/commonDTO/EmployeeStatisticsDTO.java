package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class EmployeeStatisticsDTO implements Serializable{
	
	private static final long serialVersionUID = -7355368336438277877L;
	
	private int ranking;
	private String name;
	private int totalWorkingHours;
	
	public EmployeeStatisticsDTO() {}

	public EmployeeStatisticsDTO(int ranking, String name, int totalWorkingHours) {
		this.ranking = ranking;
		this.name = name;
		this.totalWorkingHours = totalWorkingHours;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalWorkingHours() {
		return totalWorkingHours;
	}

	public void setTotalWorkingHours(int totalWorkingHours) {
		this.totalWorkingHours = totalWorkingHours;
	}

	@Override
	public String toString() {
		return "EmployeeStatisticsDTO [ranking=" + ranking + ", name=" + name + ", totalWorkingHours="
				+ totalWorkingHours + "]";
	}

}
