package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.project.clean.model.domain.joinEntity.AdminMemberRoleAndAuthority;

public class EmployeeAndAdminMemberAuthorityDTO implements Serializable{

	private int employeeNo;
	private String employeeName;
	private String employeeId;
	private String employeePwd;
	private java.sql.Date employeeBirth;
	private String employeeGender;
	private String employeePhone;
	private java.sql.Date employeeHireDate;
	private java.sql.Date employeeRetireDate;
	private int employeeSumCount;
	private int employeeSumTime;
	private java.sql.Date employeeLastLoginDate;
	private String employeeBlackListYn;
	private String employeeRetireYn;
	private java.sql.Date RequestDate;
	private String employeeFirstConfirmYn;
	private String employeeSecondConfirmYn;
	private String employeeLastConfirmYn;
	private java.sql.Date employeeLastConfirmDate;
	private String employeeRegistReturnYn;
	private String employeeEmail;
	private String employeeAddress;
	private String employeePictureSaveName;
	private String employeePictureSaveRoot;
	private String employeePictureThumbnail;
	private List<AdminMemberRoleAndAuthority> employeeMemberRoleeeAndAuthority;
	public EmployeeAndAdminMemberAuthorityDTO() {
		super();
	}
	public EmployeeAndAdminMemberAuthorityDTO(int employeeNo, String employeeName, String employeeId,
			String employeePwd, Date employeeBirth, String employeeGender, String employeePhone, Date employeeHireDate,
			Date employeeRetireDate, int employeeSumCount, int employeeSumTime, Date employeeLastLoginDate,
			String employeeBlackListYn, String employeeRetireYn, Date requestDate, String employeeFirstConfirmYn,
			String employeeSecondConfirmYn, String employeeLastConfirmYn, Date employeeLastConfirmDate,
			String employeeRegistReturnYn, String employeeEmail, String employeeAddress, String employeePictureSaveName,
			String employeePictureSaveRoot, String employeePictureThumbnail,
			List<AdminMemberRoleAndAuthority> employeeMemberRoleeeAndAuthority) {
		super();
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
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeePictureSaveName = employeePictureSaveName;
		this.employeePictureSaveRoot = employeePictureSaveRoot;
		this.employeePictureThumbnail = employeePictureThumbnail;
		this.employeeMemberRoleeeAndAuthority = employeeMemberRoleeeAndAuthority;
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
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeePictureSaveName() {
		return employeePictureSaveName;
	}
	public void setEmployeePictureSaveName(String employeePictureSaveName) {
		this.employeePictureSaveName = employeePictureSaveName;
	}
	public String getEmployeePictureSaveRoot() {
		return employeePictureSaveRoot;
	}
	public void setEmployeePictureSaveRoot(String employeePictureSaveRoot) {
		this.employeePictureSaveRoot = employeePictureSaveRoot;
	}
	public String getEmployeePictureThumbnail() {
		return employeePictureThumbnail;
	}
	public void setEmployeePictureThumbnail(String employeePictureThumbnail) {
		this.employeePictureThumbnail = employeePictureThumbnail;
	}
	public List<AdminMemberRoleAndAuthority> getEmployeeMemberRoleeeAndAuthority() {
		return employeeMemberRoleeeAndAuthority;
	}
	public void setEmployeeMemberRoleeeAndAuthority(List<AdminMemberRoleAndAuthority> employeeMemberRoleeeAndAuthority) {
		this.employeeMemberRoleeeAndAuthority = employeeMemberRoleeeAndAuthority;
	}
	@Override
	public String toString() {
		return "EmployeeAndAdminMemberAuthorityDTO [employeeNo=" + employeeNo + ", employeeName=" + employeeName
				+ ", employeeId=" + employeeId + ", employeePwd=" + employeePwd + ", employeeBirth=" + employeeBirth
				+ ", employeeGender=" + employeeGender + ", employeePhone=" + employeePhone + ", employeeHireDate="
				+ employeeHireDate + ", employeeRetireDate=" + employeeRetireDate + ", employeeSumCount="
				+ employeeSumCount + ", employeeSumTime=" + employeeSumTime + ", employeeLastLoginDate="
				+ employeeLastLoginDate + ", employeeBlackListYn=" + employeeBlackListYn + ", employeeRetireYn="
				+ employeeRetireYn + ", RequestDate=" + RequestDate + ", employeeFirstConfirmYn="
				+ employeeFirstConfirmYn + ", employeeSecondConfirmYn=" + employeeSecondConfirmYn
				+ ", employeeLastConfirmYn=" + employeeLastConfirmYn + ", employeeLastConfirmDate="
				+ employeeLastConfirmDate + ", employeeRegistReturnYn=" + employeeRegistReturnYn + ", employeeEmail="
				+ employeeEmail + ", employeeAddress=" + employeeAddress + ", employeePictureSaveName="
				+ employeePictureSaveName + ", employeePictureSaveRoot=" + employeePictureSaveRoot
				+ ", employeePictureThumbnail=" + employeePictureThumbnail + ", employeeMemberRoleeeAndAuthority="
				+ employeeMemberRoleeeAndAuthority + "]";
	}
	
	
	
	
	
}
