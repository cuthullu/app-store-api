package com.scottlogic.thull.appstore.api.dao;

import java.util.List;

import com.scottlogic.thull.appstore.api.domains.User;

public interface UserDao {
	
	public List<User> getAllUsers();
	
	public User getUser(Integer id);
	
	public User getUser(String username);
	
	public Integer createUser(User user);
	
	public User updateUser(User User);
	
	public boolean deleteUser(User user);
	
}
