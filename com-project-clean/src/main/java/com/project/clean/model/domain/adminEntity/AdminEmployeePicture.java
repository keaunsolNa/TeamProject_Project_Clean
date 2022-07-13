package com.project.clean.model.domain.adminEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMPLOYEE_PICTURE")
public class AdminEmployeePicture {

	@Id
	@Column(name = "EMPLOYEE_PICTURE_NO")
	private int employeePictureNo;

	@Column(name = "EMPLOYEE_PICTURE_ORGIN_NAME")
	private String employeePictureOrginName;
	
	@Column(name = "EMPLOYEE_PICTURE_SAVE_NAME")
	private String employeePictureSaveName;
	
	@Column(name = "EMPLOYEE_PICTURE_SAVE_ROOT")
	private String employeePictureSaveRoot;
	
	@Column(name = "EMPLOYEE_PICTURE_THUMBNAIL_NAME")
	private String employeePictureThumbnailName;
	
	@Column(name = "EMPLOYEE_NO")
	private int employeeNo;

	public AdminEmployeePicture() {
		super();
	}

	public AdminEmployeePicture(int employeePictureNo, String employeePictureOrginName, String employeePictureSaveName,
			String employeePictureSaveRoot, String employeePictureThumbnailName, int employeeNo) {
		super();
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

	@Override
	public String toString() {
		return "EmployeePicture [employeePictureNo=" + employeePictureNo + ", employeePictureOrginName="
				+ employeePictureOrginName + ", employeePictureSaveName=" + employeePictureSaveName
				+ ", employeePictureSaveRoot=" + employeePictureSaveRoot + ", employeePictureThumbnailName="
				+ employeePictureThumbnailName + ", employeeNo=" + employeeNo + "]";
	}

	
}
