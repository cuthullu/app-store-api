package com.scottlogic.thull.appstore.api.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;

import com.owlike.genson.annotation.JsonIgnore;


@Entity
@Table( name = "User",
	uniqueConstraints={@UniqueConstraint(columnNames = {"username"}, name = "username"), 
	@UniqueConstraint(columnNames = {"email"}, name = "email")}
)
@JsonIgnoreProperties( { "salt", "password"})
public class User extends DomainObject<User>{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Column
	private String username;
	
	@NotNull
	@Email
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	@JsonIgnore
	private String salt;
	
	public void update(User user) {
		this.setEmail(user.getEmail());
		this.setUsername(user.getUsername());
		if(user.getPassword() != null && user.getSalt() != null) {
			this.setPassword(user.getPassword());
			this.setSalt(user.getSalt());
		}
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
	@JsonIgnore(serialize=false,deserialize=true)
	public String getPassword() {
		return password;
	}
	
	@JsonIgnore(serialize=false,deserialize=true)
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getSalt() {
		return salt;
	}

	@JsonIgnore
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getId() {
		return id;
	}
	
}
