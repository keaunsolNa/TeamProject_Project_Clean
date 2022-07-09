package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN_PICTURE")
public class AdminPicture implements java.io.Serializable{

	private static final long serialVersionUID = -2625424420628119196L;
	
	@Id
	@Column(name="ADMIN_PICTURE_NO")
	private int pictureNo;
	
	@Column(name="ADMIN_PICTURE_ORIGIN_NAME")
	private String pictureOriginName;
	
	@Column(name="ADMIN_PICTURE_SAVE_NAME")
	private String pictureSaveName;
	
	@Column(name="ADMIN_PICTURE_SAVE_ROOT")
	private String pictureSaveRoot;
	
	@Column(name="ADMIN_PICTURE_THUMBNAIL_NAME")
	private String pictureThumbnailName;
	
	@Column(name="ADMIN_NO")
	private int no;

	public AdminPicture() {
	}

	public AdminPicture(int pictureNo, String pictureOriginName, String pictureSaveName, String pictureSaveRoot,
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
		return "AdminPicture [pictureNo=" + pictureNo + ", pictureOriginName=" + pictureOriginName
				+ ", pictureSaveName=" + pictureSaveName + ", pictureSaveRoot=" + pictureSaveRoot
				+ ", pictureThumbnailName=" + pictureThumbnailName + ", no=" + no + "]";
	}
	
	

}
