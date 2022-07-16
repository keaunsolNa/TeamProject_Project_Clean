package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="AdminPicture")
@Table(name="TBL_ADMIN_PICTURE")
@SequenceGenerator(
		name = "ADMIN_PICTURE_SEQ_TBL_GENERATOR",
		sequenceName = "SEQ_TBL_ADMIN_PICTURE",
		initialValue = 1,
		allocationSize = 1
)
public class AdminPicture implements java.io.Serializable{

	private static final long serialVersionUID = -2625424420628119196L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ADMIN_PICTURE_SEQ_TBL_GENERATOR"
	)
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
	private int adminNo;

	public AdminPicture() {
	}

	public AdminPicture(int pictureNo, String pictureOriginName, String pictureSaveName, String pictureSaveRoot,
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
		return "AdminPicture [pictureNo=" + pictureNo + ", pictureOriginName=" + pictureOriginName
				+ ", pictureSaveName=" + pictureSaveName + ", pictureSaveRoot=" + pictureSaveRoot + ", adminNo="
				+ adminNo + "]";
	}

	
	

}
