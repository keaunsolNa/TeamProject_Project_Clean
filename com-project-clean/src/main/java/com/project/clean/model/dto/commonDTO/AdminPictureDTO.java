package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminPictureDTO implements Serializable{

	private static final long serialVersionUID = 2922056038607077676L;
	private int pictureNo;
	private String pictureOriginName;
	private String pictureSaveName;
	private String pictureSaveRoot;
	private String pictureThumbnailName;
	private int no;
	public AdminPictureDTO() {
	}
	public AdminPictureDTO(int pictureNo, String pictureOriginName, String pictureSaveName, String pictureSaveRoot,
			String pictureThumbnailName, int no) {
		this.pictureNo = pictureNo;
		this.pictureOriginName = pictureOriginName;
		this.pictureSaveName = pictureSaveName;
		this.pictureSaveRoot = pictureSaveRoot;
		this.pictureThumbnailName = pictureThumbnailName;
		this.no = no;
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
	public String getPictureThumbnailName() {
		return pictureThumbnailName;
	}
	public void setPictureThumbnailName(String pictureThumbnailName) {
		this.pictureThumbnailName = pictureThumbnailName;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminPictureDTO [pictureNo=" + pictureNo + ", pictureOriginName=" + pictureOriginName
				+ ", pictureSaveName=" + pictureSaveName + ", pictureSaveRoot=" + pictureSaveRoot
				+ ", pictureThumbnailName=" + pictureThumbnailName + ", no=" + no + "]";
	}
	
	
	
}
