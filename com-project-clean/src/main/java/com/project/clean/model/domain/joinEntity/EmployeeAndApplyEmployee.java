package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;

@Entity
@Table(name="TBL_APPLY_EMPLOYEE")
public class EmployeeAndApplyEmployee implements Serializable{

	private static final long serialVersionUID = -1745146064316843313L;
	
	@Id
	@Column(name="EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name="EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="EMPLOYEE_PWD")
	private String employeePwd;
	
	@Column(name="EMPLOYEE_BIRTH")
	private java.sql.Date employeeBirth;
	
	@Column(name="EMPLOYEE_GENDER")
	private String employeeGender;
	
	@Column(name="EMPLOYEE_PHONE")
	private String employeePhone;
	
	@Column(name="EMPLOYEE_HIRE_DATE")
	private java.sql.Date employeeHireDate;
	
	@Column(name="EMPLOYEE_RETIRE_DATE")
	private java.sql.Date employeeRetireDate;
	
	@Column(name="EMPLOYEE_SUM_COUNT")
	private int employeeSumCount;
	
	@Column(name="EMPLOYEE_SUM_TIME")
	private int employeeSumTime;
	
	@Column(name="EMPLOYEE_LAST_LOGIN_DATE")
	private java.sql.Date employeeLastLoginDate;
	
	@Column(name="EMPLOYEE_BLACKLIST_YN")
	private String employeeBlackListYn;
	
	@Column(name="EMPLOYEE_RETIRE_YN")
	private String employeeRetireYn;
	
	@Column(name="REQUEST_DATE")
	private java.sql.Date RequestDate;
	
	@Column(name="EMPLOYEE_FIRST_CONFIRM_YN")
	private String employeeFirstConfirmYn;
	
	@Column(name="EMPLOYEE_SECOND_CONFIRMER")
	private String employeeSecondConfirmYn;
	
	@Column(name="EMPLOYEE_LAST_CONFIRM_YN")
	private String employeeLastConfirmYn;
	
	@Column(name="EMPLOYEE_LAST_CONFIRM_DATE")
	private java.sql.Date employeeLastConfirmDate;
	
	@Column(name="EMPLOYEE_REGIST_RETURN_YN")
	private String employeeRegistReturnYn;

	@OneToMany
	@JoinColumn(name="EMPLOYEE_NO")
	List<ApplyEmployee> applyEmployeeDTO;

	public EmployeeAndApplyEmployee() {
	}

	public EmployeeAndApplyEmployee(int employeeNo, String employeeName, String employeeId, String employeePwd,
			Date employeeBirth, String employeeGender, String employeePhone, Date employeeHireDate,
			Date employeeRetireDate, int employeeSumCount, int employeeSumTime, Date employeeLastLoginDate,
			String employeeBlackListYn, String employeeRetireYn, Date requestDate, String employeeFirstConfirmYn,
			String employeeSecondConfirmYn, String employeeLastConfirmYn, Date employeeLastConfirmDate,
			String employeeRegistReturnYn, List<ApplyEmployee> applyEmployeeDTO) {
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeePwd = employeePwd;
		this.employeeBirth = employeeBirth;
		this.employeeGender = employeeGender;
		this.employeePhone = employeePhone;
		this.employeeHireDate = employeeHireDate;
		this.employeeRetireDate = employeeRetireDate;
		this.employeeSumCount = employeeSumCount;
		this.employeeSumTime = employeeSumTime;
		this.employeeLastLoginDate = employeeLastLoginDate;
		this.employeeBlackListYn = employeeBlackListYn;
		this.employeeRetireYn = employeeRetireYn;
		RequestDate = requestDate;
		this.employeeFirstConfirmYn = employeeFirstConfirmYn;
		this.employeeSecondConfirmYn = employeeSecondConfirmYn;
		this.employeeLastConfirmYn = employeeLastConfirmYn;
		this.employeeLastConfirmDate = employeeLastConfirmDate;
		this.employeeRegistReturnYn = employeeRegistReturnYn;
		this.applyEmployeeDTO = applyEmployeeDTO;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePwd() {
		return employeePwd;
	}

	public void setEmployeePwd(String employeePwd) {
		this.employeePwd = employeePwd;
	}

	public java.sql.Date getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(java.sql.Date employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public java.sql.Date getEmployeeHireDate() {
		return employeeHireDate;
	}

	public void setEmployeeHireDate(java.sql.Date employeeHireDate) {
		this.employeeHireDate = employeeHireDate;
	}

	public java.sql.Date getEmployeeRetireDate() {
		return employeeRetireDate;
	}

	public void setEmployeeRetireDate(java.sql.Date employeeRetireDate) {
		this.employeeRetireDate = employeeRetireDate;
	}

	public int getEmployeeSumCount() {
		return employeeSumCount;
	}

	public void setEmployeeSumCount(int employeeSumCount) {
		this.employeeSumCount = employeeSumCount;
	}

	public int getEmployeeSumTime() {
		return employeeSumTime;
	}

	public void setEmployeeSumTime(int employeeSumTime) {
		this.employeeSumTime = employeeSumTime;
	}

	public java.sql.Date getEmployeeLastLoginDate() {
		return employeeLastLoginDate;
	}

	public void setEmployeeLastLoginDate(java.sql.Date employeeLastLoginDate) {
		this.employeeLastLoginDate = employeeLastLoginDate;
	}

	public String getEmployeeBlackListYn() {
		return employeeBlackListYn;
	}

	public void setEmployeeBlackListYn(String employeeBlackListYn) {
		this.employeeBlackListYn = employeeBlackListYn;
	}

	public String getEmployeeRetireYn() {
		return employeeRetireYn;
	}

	public void setEmployeeRetireYn(String employeeRetireYn) {
		this.employeeRetireYn = employeeRetireYn;
	}

	public java.sql.Date getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(java.sql.Date requestDate) {
		RequestDate = requestDate;
	}

	public String getEmployeeFirstConfirmYn() {
		return employeeFirstConfirmYn;
	}

	public void setEmployeeFirstConfirmYn(String employeeFirstConfirmYn) {
		this.employeeFirstConfirmYn = employeeFirstConfirmYn;
	}

	public String getEmployeeSecondConfirmYn() {
		return employeeSecondConfirmYn;
	}

	public void setEmployeeSecondConfirmYn(String employeeSecondConfirmYn) {
		this.employeeSecondConfirmYn = employeeSecondConfirmYn;
	}

	public String getEmployeeLastConfirmYn() {
		return employeeLastConfirmYn;
	}

	public void setEmployeeLastConfirmYn(String employeeLastConfirmYn) {
		this.employeeLastConfirmYn = employeeLastConfirmYn;
	}

	public java.sql.Date getEmployeeLastConfirmDate() {
		return employeeLastConfirmDate;
	}

	public void setEmployeeLastConfirmDate(java.sql.Date employeeLastConfirmDate) {
		this.employeeLastConfirmDate = employeeLastConfirmDate;
	}

	public String getEmployeeRegistReturnYn() {
		return employeeRegistReturnYn;
	}

	public void setEmployeeRegistReturnYn(String employeeRegistReturnYn) {
		this.employeeRegistReturnYn = employeeRegistReturnYn;
	}

	public List<ApplyEmployee> getApplyEmployeeDTO() {
		return applyEmployeeDTO;
	}

	public void setApplyEmployeeDTO(List<ApplyEmployee> applyEmployeeDTO) {
		this.applyEmployeeDTO = applyEmployeeDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeAndApplyEmployee [employeeNo=" + employeeNo + ", employeeName=" + employeeName + ", employeeId="
				+ employeeId + ", employeePwd=" + employeePwd + ", employeeBirth=" + employeeBirth + ", employeeGender="
				+ employeeGender + ", employeePhone=" + employeePhone + ", employeeHireDate=" + employeeHireDate
				+ ", employeeRetireDate=" + employeeRetireDate + ", employeeSumCount=" + employeeSumCount
				+ ", employeeSumTime=" + employeeSumTime + ", employeeLastLoginDate=" + employeeLastLoginDate
				+ ", employeeBlackListYn=" + employeeBlackListYn + ", employeeRetireYn=" + employeeRetireYn
				+ ", RequestDate=" + RequestDate + ", employeeFirstConfirmYn=" + employeeFirstConfirmYn
				+ ", employeeSecondConfirmYn=" + employeeSecondConfirmYn + ", employeeLastConfirmYn="
				+ employeeLastConfirmYn + ", employeeLastConfirmDate=" + employeeLastConfirmDate
				+ ", employeeRegistReturnYn=" + employeeRegistReturnYn + ", applyEmployeeDTO=" + applyEmployeeDTO + "]";
	}

	
	
	
	
}
