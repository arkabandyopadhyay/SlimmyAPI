package com.sapi.common;

import java.util.List;

public class BaseUserVO {

	protected String repositoryName;
	protected String userName;
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRepositoryName() {
		return repositoryName;
	}

	public String getUserName() {
		return userName;
	}
	
	public BaseUserVO() {
		
	}
	@Override
	public String toString() {
		return "BaseUserVO [repositoryName=" + repositoryName + ", userName=" + userName + "]";
	}
	
}
