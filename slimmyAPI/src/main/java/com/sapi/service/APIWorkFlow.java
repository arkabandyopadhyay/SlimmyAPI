package com.sapi.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;

public abstract class APIWorkFlow implements WorkFlow{

	 private static Logger log = LoggerFactory.getLogger(APIWorkFlow.class);
	
	public APIWorkFlow() {
	
	}
	
	 @Async("asyncExecutor")
	public CompletableFuture<List<List<RepositoryVO>>> execute(String userName, String projectName, String ownerType){
		if(projectName == null || projectName.isEmpty()) 
			throw new IllegalArgumentException("projectName is invalid");
		log.info("started fetching user details");
		List<BaseUserVO> resultMap = fetchAllUser(userName);
		log.info("started fetching project details");
		return CompletableFuture.completedFuture(this.executeAll(resultMap, projectName, ownerType));
	}

	protected List<BaseUserVO> fetchAllUser(String userName){
		if(userName == null || userName.isEmpty()) 
			throw new IllegalArgumentException("UserName is not valid");
		return fetchUser(userName);
	}
	
	
}
