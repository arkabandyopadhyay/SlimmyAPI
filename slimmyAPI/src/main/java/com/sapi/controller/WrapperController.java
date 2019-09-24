package com.sapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import com.sapi.common.GitLabUserVO;
import com.sapi.common.RepositoryVO;
import com.sapi.service.APIWorkFlow;
import com.sapi.service.GitHubAPIWorkFlow;
import com.sapi.service.GitLabAPIWorkFlow;

@RequestMapping("/api")
@RestController
public class WrapperController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GitHubAPIWorkFlow gitHubAPIWorkFlow;
	
	@Autowired
	private GitLabAPIWorkFlow gitLabAPIWorkFlow;
	
	private static Logger log = LoggerFactory.getLogger(WrapperController.class);

	@GetMapping("/{user}/{repoName}")
	public ResponseEntity<List> fetchRepo(@PathVariable(value = "user") String userName,@PathVariable(value="repoName",required = false) String repoName,@RequestParam(name = "type",required = false) final String type) {
		CompletableFuture<List<List<RepositoryVO>>> githubResult = gitHubAPIWorkFlow.execute(userName, repoName, type);
		CompletableFuture<List<List<RepositoryVO>>> gitlabResult = gitLabAPIWorkFlow.execute(userName, repoName, type);
		log.info("started fetchRepo");
		CompletableFuture.allOf(githubResult, gitlabResult).join();
		
		List<List<RepositoryVO>> finalList = new ArrayList<>();
		try {
			finalList.addAll(githubResult.get());
			finalList.addAll(gitlabResult.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		log.info("completed processing");
		return new ResponseEntity<List>(finalList, HttpStatus.OK);		
	}

}
