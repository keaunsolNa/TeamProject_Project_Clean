package com.project.clean.model.domain.commonEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="persistent_logins")
@Entity
public class PersistentLogin {

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="series")
	private String series;
	
	@Column(name="token")
	private String token;
	
	@Column(name="last_used")
	private java.sql.Date lastUsed;

	public PersistentLogin() {
	}

	public PersistentLogin(String userName, String series, String token, Date lastUsed) {
		this.userName = userName;
		this.series = series;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public java.sql.Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(java.sql.Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Override
	public String toString() {
		return "PersistentLogin [userName=" + userName + ", series=" + series + ", token=" + token + ", lastUsed="
				+ lastUsed + "]";
	}
	
	
	
}
