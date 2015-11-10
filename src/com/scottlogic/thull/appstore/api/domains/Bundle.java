package com.scottlogic.thull.appstore.api.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Bundle")
public class Bundle extends DomainObject<Bundle>{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@NotNull
	@Size(min = 3, max = 32)
	private String title;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void update(Bundle newBundle) {
		this.setTitle(newBundle.getTitle());
	}

	
}
