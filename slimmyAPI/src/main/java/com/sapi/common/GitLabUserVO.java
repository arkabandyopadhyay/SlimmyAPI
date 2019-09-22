package com.sapi.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitLabUserVO extends BaseUserVO{

	private String avatar_url;
	private String web_url;
	private String name;
	
	public GitLabUserVO(String avatar_url, String web_url, String name) {
		super();
		this.avatar_url = avatar_url;
		this.web_url = web_url;
		this.name = name;
		this.repositoryName = "GitLab";
	}
	
	
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
