package com.sapi.service;

import java.util.List;

import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;

public interface WorkFlow {
	
	public List<List<RepositoryVO>> executeAll(List<BaseUserVO> resultMap,String projectName,String ownerType);
		
	public List<BaseUserVO> fetchUser(String userName);
}
