package com.project.clean.model.domain.joinEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.clean.model.domain.commonEntity.Employee;

@Entity
@Table(name="TBL_BEST_EMPLOYEE_PAY")
@SequenceGenerator(
		name = "BEST_EMPLOYEE_PAY_SEQ_TBL_GENERATOR",
		sequenceName = "SEQ_TBL_BEST_EMPLOYEE_PAY",
		initialValue = 1,
		allocationSize = 1
)
public class BestEmployeePayAndEmployee implements java.io.Serializable{


	private static final long serialVersionUID = -887967325358696593L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BEST_EMPLOYEE_PAY_SEQ_TBL_GENERATOR"
	)
	@Column(name="BEST_EMPLOYEE_PAY_HISTORY_NO")
	private int bestEmployeePayHistoryNo;

	@Column(name="BEST_EMPLOYEE_PAY_DATE")
	private java.sql.Date bestEmployeePayDate;
	
	@Column(name="BEST_EMPLOYEE_NO")
	private int bestEmployeeNo;
	
	@Column(name="BEST_EMPLOYEE_BONUS")
	private int bestEmployeeBonus;
	
	@ManyToOne
	@JoinColumn(name = "BEST_EMPLOYEE_NO", insertable = false, updatable = false)
	private Employee employee;
	
	public BestEmployeePayAndEmployee() {
	}

	public BestEmployeePayAndEmployee(int bestEmployeePayHistoryNo, Date bestEmployeePayDate, int bestEmployeeNo,
			int bestEmployeeBonus, Employee employee) {
		super();
		this.bestEmployeePayHistoryNo = bestEmployeePayHistoryNo;
		this.bestEmployeePayDate = bestEmployeePayDate;
		this.bestEmployeeNo = bestEmployeeNo;
		this.bestEmployeeBonus = bestEmployeeBonus;
		this.employee = employee;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BestEmployeePayAndEmployee [bestEmployeePayHistoryNo=" + bestEmployeePayHistoryNo
				+ ", bestEmployeePayDate=" + bestEmployeePayDate + ", bestEmployeeNo=" + bestEmployeeNo
				+ ", bestEmployeeBonus=" + bestEmployeeBonus + ", employee=" + employee + "]";
	}

	

	
	
	
}
