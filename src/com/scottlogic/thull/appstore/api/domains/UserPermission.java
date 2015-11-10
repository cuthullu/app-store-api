package com.scottlogic.thull.appstore.api.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserPermission extends DomainObject<UserPermission>{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String permission;
	
	@Column
	private String rolename;
	
	@Override
	public void update(UserPermission t) {
		this.permission = t.permission;
		this.rolename = t.rolename;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
