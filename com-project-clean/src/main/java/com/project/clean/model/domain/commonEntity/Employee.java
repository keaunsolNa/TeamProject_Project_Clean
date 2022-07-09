package com.project.clean.model.domain.commonEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "EMPLOYEE")
@Table(name = "TBL_EMPLOYEE")
public class Employee implements java.io.Serializable{

	private static final long serialVersionUID = -4995141440070784493L;
	
	@Id
	@Column(name = "EMPLOYEE_NO")
	private int no;
	
	@Column(name = "EMPLOYEE_NAME")
	private String name;
	
	@Column(name = "EMPLOYEE_ID")
	private String id;
	
	@Column(name = "EMPLOYEE_PWD")
	private String pwd;
	
	@Column(name = "EMPLOYEE_BIRTH" )
	private java.sql.Date birth;
	
	@Column(name = "EMPLOYEE_GENDER")
	private String gender;
	
	@Column(name = "EMPLOYEE_PHONE")
	private String phone;
	
	@Column(name = "EMPLOYEE_HIRE_DATE")
	private java.sql.Date hireDate;
	
	@Column(name ="EMPLOYEE_RETIRE_DATE", nullable=true)
	private java.sql.Date retireDate;
	
	@Column(name ="EMPLOYEE_SUM_COUNT")
	private int sumCount;
	
	@Column(name ="EMPLOYEE_SUM_TIME", nullable=true)
	private int sumTime;
	
	@Column(name="EMPLOYEE_LAST_LOGIN_DATE", nullable=true)
	private java.sql.Date lastLoginDate;
	
	@Column(name="EMPLOYEE_BLACKLIST_YN")
	private String blackListYn;
	
	@Column(name="EMPLOYEE_RETIRE_YN")
	private String retireYn;
	
	@Column(name="REQUEST_DATE", nullable=true)
	private java.sql.Date requestDate;
	
	@Column(name="EMPLOYEE_FIRST_CONFIRM_YN")
	private String firstConfirmYn;
	
	@Column(name="EMPLOYEE_SECOND_CONFIRMER")
	private String secondConfirmer;
	
	@Column(name="EMPLOYEE_LAST_CONFIRM_YN")
	private String lastConfirmYn;

	@Column(name="EMPLOYEE_LAST_CONFIRM_DATE", nullable=true)
	private java.sql.Date lastConfirmDate;
	
	@Column(name="EMPLOYEE_REGIST_RETURN_YN")
	private String registReturnYn;

	public Employee() {
	}

	public Employee(int no, String name, String id, String pwd, Date birth, String gender, String phone, Date hireDate,
			Date retireDate, int sumCount, int sumTime, Date lastLoginDate, String blackListYn, String retireYn,
			Date requestDate, String firstConfirmYn, String secondConfirmer, String lastConfirmYn, Date lastConfirmDate,
			String registReturnYn) {
		this.no = no;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.hireDate = hireDate;
		this.retireDate = retireDate;
		this.sumCount = sumCount;
		this.sumTime = sumTime;
		this.lastLoginDate = lastLoginDate;
		this.blackListYn = blackListYn;
		this.retireYn = retireYn;
		this.requestDate = requestDate;
		this.firstConfirmYn = firstConfirmYn;
		this.secondConfirmer = secondConfirmer;
		this.lastConfirmYn = lastConfirmYn;
		this.lastConfirmDate = lastConfirmDate;
		this.registReturnYn = registReturnYn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public java.sql.Date getBirth() {
		return birth;
	}

	public void setBirth(java.sql.Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public java.sql.Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(java.sql.Date retireDate) {
		this.retireDate = retireDate;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public int getSumTime() {
		return sumTime;
	}

	public void setSumTime(int sumTime) {
		this.sumTime = sumTime;
	}

	public java.sql.Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(java.sql.Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getBlackListYn() {
		return blackListYn;
	}

	public void setBlackListYn(String blackListYn) {
		this.blackListYn = blackListYn;
	}

	public String getRetireYn() {
		return retireYn;
	}

	public void setRetireYn(String retireYn) {
		this.retireYn = retireYn;
	}

	public java.sql.Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(java.sql.Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getFirstConfirmYn() {
		return firstConfirmYn;
	}

	public void setFirstConfirmYn(String firstConfirmYn) {
		this.firstConfirmYn = firstConfirmYn;
	}

	public String getSecondConfirmer() {
		return secondConfirmer;
	}

	public void setSecondConfirmer(String secondConfirmer) {
		this.secondConfirmer = secondConfirmer;
	}

	public String getLastConfirmYn() {
		return lastConfirmYn;
	}

	public void setLastConfirmYn(String lastConfirmYn) {
		this.lastConfirmYn = lastConfirmYn;
	}

	public java.sql.Date getLastConfirmDate() {
		return lastConfirmDate;
	}

	public void setLastConfirmDate(java.sql.Date lastConfirmDate) {
		this.lastConfirmDate = lastConfirmDate;
	}

	public String getRegistReturnYn() {
		return registReturnYn;
	}

	public void setRegistReturnYn(String registReturnYn) {
		this.registReturnYn = registReturnYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Employee [no=" + no + ", name=" + name + ", id=" + id + ", pwd=" + pwd + ", birth=" + birth
				+ ", gender=" + gender + ", phone=" + phone + ", hireDate=" + hireDate + ", retireDate=" + retireDate
				+ ", sumCount=" + sumCount + ", sumTime=" + sumTime + ", lastLoginDate=" + lastLoginDate
				+ ", blackListYn=" + blackListYn + ", retireYn=" + retireYn + ", requestDate=" + requestDate
				+ ", firstConfirmYn=" + firstConfirmYn + ", secondConfirmer=" + secondConfirmer + ", lastConfirmYn="
				+ lastConfirmYn + ", lastConfirmDate=" + lastConfirmDate + ", registReturnYn=" + registReturnYn + "]";
	}
	
	
	
}
