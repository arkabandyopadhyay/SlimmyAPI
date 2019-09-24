package com.sapi.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;

@Service
public class GitLabAPIWorkFlow extends APIWorkFlow{
	
	@Autowired
	RestTemplate restTemplate;
	
	private HttpEntity<String> getEntity() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return entity;
	}

	public GitLabAPIWorkFlow() {
		
	}
	
	@Override
	public List<BaseUserVO> fetchUser(String userName){
		ResponseEntity<List<BaseUserVO>> resp = restTemplate.exchange(
				"https://gitlab.com/api/v4/users?search=" + userName,
				  HttpMethod.GET,
				  getEntity(),
				  new ParameterizedTypeReference<List<BaseUserVO>>(){});
		List<BaseUserVO> resultMap = resp.getBody();
		return resultMap;
	}
	
	@Override
	public List<List<RepositoryVO>> executeAll(List<BaseUserVO> resultMap,String projectName,String ownerType){
		
		final String repositoryURL =  ownerType==null ? "https://gitlab.com/api/v4/users/%s/projects?owned=false":"https://gitlab.com/api/v4/users/%s/projects?owned=true";
		List<List<RepositoryVO>> resultList = resultMap.parallelStream().map(userVO->{

			ResponseEntity<List<RepositoryVO>> response = restTemplate.exchange(
					String.format(repositoryURL, userVO.getId()),
					  HttpMethod.GET,
					  getEntity(),
					  new ParameterizedTypeReference<List<RepositoryVO>>(){});
			List<RepositoryVO> list = response.getBody();
			
			return (projectName==null)?
					list:list.stream().filter(repo->repo.filter(projectName)).
					collect(Collectors.toList());
		}).filter(list->list!=null&&list.isEmpty()==false).collect(Collectors.toList());
		
		return resultList;
	}
	
}
