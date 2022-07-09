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
	private String name;
	
	@Column(name="AUTHORITY_NAME")
	private String desc;

	public Authority() {
	}

	public Authority(int authorityCode, String name, String desc) {
		this.authorityCode = authorityCode;
		this.name = name;
		this.desc = desc;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Authority [authorityCode=" + authorityCode + ", name=" + name + ", desc=" + desc + "]";
	}

}
