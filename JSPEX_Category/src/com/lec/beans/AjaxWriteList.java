package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxWriteList extends AjaxWriteResult {
	@JsonProperty("data")
	private List<CategoryDTO> list;  // 데이터 목록
	
	/*
	 * @JsonProperty("depth") private int depth;
	 * 
	 * @JsonProperty("parent") private int parent;
	 * 
	 * @JsonProperty("order") private int order;
	 */   

	public List<CategoryDTO> getList() {
		return list;
	}

	public void setList(List<CategoryDTO> list) {
		this.list = list;
	}

	/*
	 * public int getDepth() { return depth; }
	 * 
	 * public void setDepth(int depth) { this.depth = depth; }
	 * 
	 * public int getParent() { return parent; }
	 * 
	 * public void setParent(int parent) { this.parent = parent; }
	 * 
	 * public int getOrder() { return order; }
	 * 
	 * public void setOrder(int order) { this.order = order; }
	 */
	
	
	
	
}













