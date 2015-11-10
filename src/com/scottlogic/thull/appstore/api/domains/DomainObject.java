package com.scottlogic.thull.appstore.api.domains;

public abstract class DomainObject<T> {
	
	private Integer idFromRemote;
	
	public void setIdFromRemote(Integer idFromRemote) {
		this.idFromRemote = idFromRemote;
		
	}
	
	public Integer getIdFromRemote() {
		return this.idFromRemote;
	}
	
	public abstract void update(T t);
	
	public abstract Integer getId();
	
}
