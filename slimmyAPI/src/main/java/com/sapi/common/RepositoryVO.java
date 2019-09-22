package com.sapi.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryVO{
	
	
	private Owner owner;
	private String name;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public RepositoryVO() {
		super();
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean filter(String filterText) {
		filterText = filterText.toLowerCase();
		if(this.description == null && this.name == null) return false;
		return (this.description != null &&( this.description.toLowerCase().contains(filterText) || 
				this.description.toLowerCase().indexOf(filterText) != -1) )||
				(this.name != null && (this.name.toLowerCase().contains(filterText) || 
				this.name.toLowerCase().indexOf(filterText) != -1));
	}
	
	public RepositoryVO(String name, String description,Owner owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	
	class Owner{
		private String name;
		private String login;
		private Long id;
		
		public Owner() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name==null?login:name;
		}
		

		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login==null?name:login;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
				
		
	}
}
