package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_AUTHORITY")
public class Authority implements java.io.Serializable {

	private static final long serialVersionUID = 9096040664221519104L;
	
	@Id
	@Column(name="AUTHORITY_CODE")
	private int authorityCode;
	
	@Column(name="AUTHORITY_DESC")
	private String desc;
	
	@Column(name="AUTHORITY_NAME")
	private String name;

	public Authority() {
	}

	public Authority(int authorityCode, String desc, String name) {
		this.authorityCode = authorityCode;
		this.desc = desc;
		this.name = name;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Authority [authorityCode=" + authorityCode + ", desc=" + desc + ", name=" + name + "]";
	}

	
	

}
