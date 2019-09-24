package com.sapi.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseUserVO {

	protected String repositoryname;
	protected String username;
	protected Long id;
	protected String login;
	
	public Long getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRepositoryname() {
		return repositoryname;
	}

	public String getUsername() {
		return username;
	}
	
	public BaseUserVO() {
		
	}
	@Override
	public String toString() {
		return "BaseUserVO [repositoryName=" + repositoryname + ", userName=" + username + "]";
	}
	
}
