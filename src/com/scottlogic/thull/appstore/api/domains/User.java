package com.scottlogic.thull.appstore.api.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "User")
public class User extends DomainObject<User>{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column( unique = true)
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String salt;
	
	public void update(User user) {
		this.setPassword(user.getPassword());
		this.setEmail(user.getEmail());
		this.setSalt(user.getSalt());
		this.setUsername(user.getUsername());
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getId() {
		return id;
	}
	
}
