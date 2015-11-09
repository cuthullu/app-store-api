package com.scottlogic.thull.appstore.api.utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBProperties extends MysqlDataSource{
	
	private static final long serialVersionUID = -638673391501092366L;

	public DBProperties()  {
		super.setUrl("jdbc:mysql://" + System.getenv("DATABASE_URL"));
		super.setPassword(System.getenv("DATABASE_PASSWORD"));
		super.setUser(System.getenv("DATABASE_USER"));
	}

}
