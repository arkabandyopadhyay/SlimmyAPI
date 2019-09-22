package com.sapi.service;

import java.util.Arrays;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapi.common.BaseSearchVO;
import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;

@RequestMapping("/users")
@RestController
public class WrapperController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{user}")
	public List<BaseUserVO> fetchUser(@PathVariable(value = "user") String user) {
		//page=2
		BaseSearchVO resultMap = restTemplate.getForObject("https://api.github.com/search/users?q=" + user,
				BaseSearchVO.class);
		
		return resultMap.getItems();
	}
	
	@GetMapping("/lab/{user}")
	public List fetchLabUser(@PathVariable(value = "user") String user) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		ResponseEntity<List<BaseUserVO>> response = restTemplate.exchange(
				"https://gitlab.com/api/v4/users?search=" + user,
				  HttpMethod.GET,
				  entity,
				  new ParameterizedTypeReference<List<BaseUserVO>>(){});
		
		return response.getBody();

	}
	
	@GetMapping("/lab/repos/{user}/{repoName}")
	public List fetchLabRepositoryDetails(@PathVariable(value = "user") String userName,@PathVariable(value="repoName",required = false) String repoName,@RequestParam(name = "type",required = false) final String type) {
		BaseSearchVO resultMap = restTemplate.getForObject("https://api.github.com/search/users?q=" + userName,
				BaseSearchVO.class);
		
		List<List<RepositoryVO>> resultList = resultMap.getItems().parallelStream().map(userVO->{

			Map<String,String> map = new HashMap<String,String>();
			map.put("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Private-Token", "Dh5V_nrZyGo-YUvhz1ia");
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<>("body", headers);
			ResponseEntity<List<RepositoryVO>> response = restTemplate.exchange(
					"https://gitlab.com/api/v4/users/"+userVO.getId()+"/projects?owned=false",
					  HttpMethod.GET,
					  entity,
					  new ParameterizedTypeReference<List<RepositoryVO>>(){});
			List<RepositoryVO> list = response.getBody();
			
			return (repoName==null)?
					list:list.stream().filter(repo->repo.filter(repoName)).
					collect(Collectors.toList());
		}).collect(Collectors.toList());
		
		return resultList;
	}
	

	@GetMapping("/repos/{user}/{repoName}")
	public List fetchRepositoryDetails(@PathVariable(value = "user") String userName,@PathVariable(value="repoName",required = false) String repoName,@RequestParam(name = "type",required = false) final String type) {
		BaseSearchVO resultMap = restTemplate.getForObject("https://api.github.com/search/users?q=" + userName,
				BaseSearchVO.class);
		
		List<List<RepositoryVO>> resultList = resultMap.getItems().parallelStream().map(userVO->{

			
			ResponseEntity<List<RepositoryVO>> response = restTemplate.exchange(
					"https://api.github.com/users/"+userVO.getUserName()+"/repos?type="+type == null ? "all" : "owner",
					  HttpMethod.GET,
					  null,
					  new ParameterizedTypeReference<List<RepositoryVO>>(){});
			List<RepositoryVO> list = response.getBody();
			
			return (repoName==null)?
					list:list.stream().filter(repo->repo.filter(repoName)).
					collect(Collectors.toList());
		}).collect(Collectors.toList());
		
		return resultList;
	}
}
