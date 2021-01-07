package com.dev.model;

import com.opencsv.bean.CsvBindByName;

public class User {

	@CsvBindByName
	private long id;
	@CsvBindByName(column = "username")
	private String userName;
	@CsvBindByName
	private String password;
	@CsvBindByName
	private String accessToken;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", accessToken=" + accessToken
				+ "]";
	}

}
