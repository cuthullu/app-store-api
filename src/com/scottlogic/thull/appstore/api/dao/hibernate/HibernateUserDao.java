package com.scottlogic.thull.appstore.api.dao.hibernate;

import java.util.List;

import com.scottlogic.thull.appstore.api.dao.UserDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.commands.*;
import com.scottlogic.thull.appstore.api.domains.User;

public class HibernateUserDao extends HibernateDao implements UserDao {

	@Override
	public List<User> getAllUsers() {
		return callDatabase(null, new GetAllMethod<User>(User.class));
	}

	@Override
	public User getUser(Integer id) {
		return callDatabase(id, new GetMethod<User>(User.class));
	}

	@Override
	public User getUser(String username) {
		return callDatabase(username, new GetByUniqueFieldMethod<User>(User.class, "username"));
	}

	@Override
	public Integer createUser(User user) {
		return callDatabase(user, new CreateMethod<User>());
	}

	@Override
	public User updateUser(User user) {
		return callDatabase(user, new UpdateMethod<User>(User.class));
	}

	@Override
	public boolean deleteUser(Integer id) {
		return callDatabase(id, new DeleteMethod<User>(User.class));
	}

}
