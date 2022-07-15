package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminPictureDTO implements Serializable{

	private static final long serialVersionUID = 2922056038607077676L;
	private int pictureNo;
	private String pictureOriginName;
	private String pictureSaveName;
	private String pictureSaveRoot;
	private int adminNo;
	
	public AdminPictureDTO() {
	}

	public AdminPictureDTO(int pictureNo, String pictureOriginName, String pictureSaveName, String pictureSaveRoot,
			int adminNo) {
		this.pictureNo = pictureNo;
		this.pictureOriginName = pictureOriginName;
		this.pictureSaveName = pictureSaveName;
		this.pictureSaveRoot = pictureSaveRoot;
		this.adminNo = adminNo;
	}

	public int getPictureNo() {
		return pictureNo;
	}

	public void setPictureNo(int pictureNo) {
		this.pictureNo = pictureNo;
	}

	public String getPictureOriginName() {
		return pictureOriginName;
	}

	public void setPictureOriginName(String pictureOriginName) {
		this.pictureOriginName = pictureOriginName;
	}

	public String getPictureSaveName() {
		return pictureSaveName;
	}

	public void setPictureSaveName(String pictureSaveName) {
		this.pictureSaveName = pictureSaveName;
	}

	public String getPictureSaveRoot() {
		return pictureSaveRoot;
	}

	public void setPictureSaveRoot(String pictureSaveRoot) {
		this.pictureSaveRoot = pictureSaveRoot;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminPictureDTO [pictureNo=" + pictureNo + ", pictureOriginName=" + pictureOriginName
				+ ", pictureSaveName=" + pictureSaveName + ", pictureSaveRoot=" + pictureSaveRoot + ", adminNo="
				+ adminNo + "]";
	}

	
}
