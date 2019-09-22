package com.sapi.common;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseSearchVO {

	private boolean incomplete_results;
	private int total_count;
	private List<BaseUserVO> items;
	
	public boolean isIncomplete_results() {
		return incomplete_results;
	}
	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public List<BaseUserVO> getItems() {
		return items;
	}
	public void setItems(List<BaseUserVO> items) {
		this.items = items;
	}
	
}
