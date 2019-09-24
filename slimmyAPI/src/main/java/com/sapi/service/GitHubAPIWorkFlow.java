package com.sapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapi.common.BaseSearchVO;
import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;

@Service
public class GitHubAPIWorkFlow extends APIWorkFlow {

	@Autowired
	RestTemplate restTemplate;

	public GitHubAPIWorkFlow() {

	}

	@Override
	public List<BaseUserVO> fetchUser(String userName) {
		BaseSearchVO resultMap = restTemplate.getForObject("https://api.github.com/search/users?q=" + userName,
				BaseSearchVO.class);
		return resultMap.getItems();
	}

	@Override
	public List<List<RepositoryVO>> executeAll(List<BaseUserVO> resultMap, String projectName, String ownerType) {
		final String repositoryURL = ownerType == null ? "https://api.github.com/users/%s/repos?type=all"
				: "https://api.github.com/users/%s/repos?type=owner";

		List<List<RepositoryVO>> resultList = resultMap.parallelStream().map(userVO -> {

			ResponseEntity<List<RepositoryVO>> response = restTemplate.exchange(
					String.format(repositoryURL, userVO.getLogin()), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<RepositoryVO>>() {
					});
			List<RepositoryVO> list = response.getBody();

			return (projectName == null) ? list
					: list.stream().filter(repo -> repo.filter(projectName)).collect(Collectors.toList());
		}).filter(list -> list != null && list.isEmpty() == false).collect(Collectors.toList());
		return resultList;
	}

}
