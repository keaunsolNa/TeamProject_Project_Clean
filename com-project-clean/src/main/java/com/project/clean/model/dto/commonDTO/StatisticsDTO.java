package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class StatisticsDTO implements Serializable{

	private static final long serialVersionUID = -5162647341352602860L;
	
	private int jobCode;
	private String job;
	private int avgSalary;
	
	public StatisticsDTO() {}
	public StatisticsDTO(int jobCode, String job, int avgSalary) {

		this.jobCode = jobCode;
		this.job = job;
		this.avgSalary = avgSalary;
	}
	public int getJobCode() {
		return jobCode;
	}
	public void setJobCode(int jobCode) {
		this.jobCode = jobCode;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(int avgSalary) {
		this.avgSalary = avgSalary;
	}
	@Override
	public String toString() {
		return "StatisticsDTO [jobCode=" + jobCode + ", job=" + job + ", avgSalary=" + avgSalary + ", getJobCode()="
				+ getJobCode() + ", getJob()=" + getJob() + ", getAvgSalary()=" + getAvgSalary() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}