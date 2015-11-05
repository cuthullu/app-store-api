package com.scottlogic.thull.appstore.api.resources;

import com.scottlogic.thull.appstore.api.domains.User;

public class UserPasswordHolder {
	
	private User user;
	
	private String password;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
