package com.sapi.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUserVO extends BaseUserVO {
	
	private String login, email, company, location, created_at;
	
	public GitHubUserVO(String login, String email, String company, String location, String created_at) {
		super();
		this.login = login;
		this.email = email;
		this.company = company;
		this.location = location;
		this.created_at = created_at;
		this.repositoryname = "GitHub";
		this.username = login;
	}
	
	public GitHubUserVO() {
		this.repositoryname = "GitHub";
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
		this.username = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
}
