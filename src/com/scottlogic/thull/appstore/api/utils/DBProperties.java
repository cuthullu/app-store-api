package com.scottlogic.thull.appstore.api.utils;

import java.util.Properties;

public class DBProperties extends Properties{
	
	private static final long serialVersionUID = -638673391501092366L;

	public DBProperties()  {
		super.put("db.url",  "jdbc:mysql://" + System.getenv("DATABASE_URL"));
		super.put("db.user", System.getenv("DATABASE_USER"));
		super.put("db.password", System.getenv("DATABASE_PASSWORD"));
		
	}

}
