package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class EmployeePictureDTO implements Serializable {

	private static final long serialVersionUID = 2128142491423991774L;
	
	private int employeePictureNo ;
	private String employeePictureOrginName;
	private String employeePictureSaveName;
	private String employeePictureSaveRoot;
	private String employeePictureThumbnailName;
	private int employeeNo;
	public EmployeePictureDTO() {
	}
	public EmployeePictureDTO(int employeePictureNo, String employeePictureOrginName, String employeePictureSaveName,
			String employeePictureSaveRoot, String employeePictureThumbnailName, int employeeNo) {
		this.employeePictureNo = employeePictureNo;
		this.employeePictureOrginName = employeePictureOrginName;
		this.employeePictureSaveName = employeePictureSaveName;
		this.employeePictureSaveRoot = employeePictureSaveRoot;
		this.employeePictureThumbnailName = employeePictureThumbnailName;
		this.employeeNo = employeeNo;
	}
	public int getEmployeePictureNo() {
		return employeePictureNo;
	}
	public void setEmployeePictureNo(int employeePictureNo) {
		this.employeePictureNo = employeePictureNo;
	}
	public String getEmployeePictureOrginName() {
		return employeePictureOrginName;
	}
	public void setEmployeePictureOrginName(String employeePictureOrginName) {
		this.employeePictureOrginName = employeePictureOrginName;
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
	public String getEmployeePictureThumbnailName() {
		return employeePictureThumbnailName;
	}
	public void setEmployeePictureThumbnailName(String employeePictureThumbnailName) {
		this.employeePictureThumbnailName = employeePictureThumbnailName;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmployeePayDTO [employeePictureNo=" + employeePictureNo + ", employeePictureOrginName="
				+ employeePictureOrginName + ", employeePictureSaveName=" + employeePictureSaveName
				+ ", employeePictureSaveRoot=" + employeePictureSaveRoot + ", employeePictureThumbnailName="
				+ employeePictureThumbnailName + ", employeeNo=" + employeeNo + "]";
	}
	
	
}

