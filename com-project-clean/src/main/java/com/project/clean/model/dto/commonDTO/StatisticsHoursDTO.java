package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class StatisticsHoursDTO implements Serializable{
	
	private static final long serialVersionUID = -4923230664702983140L;
	
	private int avgHours;
	private int totalHours;
	private int employeeSumTime;
	
	public StatisticsHoursDTO() {}

	public StatisticsHoursDTO(int avgHours, int totalHours, int employeeSumTime) {
		this.avgHours = avgHours;
		this.totalHours = totalHours;
		this.employeeSumTime = employeeSumTime;
	}

	public int getAvgHours() {
		return avgHours;
	}

	public void setAvgHours(int avgHours) {
		this.avgHours = avgHours;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public int getEmployeeSumTime() {
		return employeeSumTime;
	}

	public void setEmployeeSumTime(int employeeSumTime) {
		this.employeeSumTime = employeeSumTime;
	}

	@Override
	public String toString() {
		return "StatisticsHoursDTO [avgHours=" + avgHours + ", totalHours=" + totalHours + ", employeeSumTime="
				+ employeeSumTime + "]";
	}
	
	

}
