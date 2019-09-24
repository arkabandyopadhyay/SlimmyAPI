package com.sapi;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sapi.common.BaseUserVO;
import com.sapi.common.RepositoryVO;
import com.sapi.controller.WrapperController;
import com.sapi.service.GitHubAPIWorkFlow;
import com.sapi.service.GitLabAPIWorkFlow;

@RunWith(SpringRunner.class)
//@WebMvcTest(WrapperController.class)
@SpringBootTest
public class SlimmyApiApplicationTests {
//
//	@Autowired
//	private MockMvc mvc;
	
	@Autowired
	private GitHubAPIWorkFlow gitHubAPIWorkFlow;
	
	@Autowired
	private GitLabAPIWorkFlow gitlabAPIWorkFlow;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testFetchRepo() throws Exception {
//		CompletableFuture<List<List<RepositoryVO>>> githubResult = new CompletableFuture<List<List<RepositoryVO>>>();
//		Mockito.when(gitHubAPIWorkFlow.execute("arka", "test", null)).thenReturn(githubResult);
//		Mockito.when(gitlabAPIWorkFlow.execute("arka", "test", null)).thenReturn(githubResult);
//		
//		mvc.perform(get("/api/arka/test")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk());
		List<BaseUserVO> list = new ArrayList<>();
		Mockito.when(gitlabAPIWorkFlow.fetchUser("arka")).thenReturn(list);
		
		assertNotNull(gitHubAPIWorkFlow.fetchUser("arka"));
	
	}

}
