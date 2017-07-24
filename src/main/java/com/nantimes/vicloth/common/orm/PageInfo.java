package com.nantimes.vicloth.common.orm;

import java.io.Serializable;



public class PageInfo implements Serializable {

	private static final long serialVersionUID = 587754556498974978L;

	
	private int showCount = 3;
	
	private int totalPage;
	
	private int totalResult;
	
	private int currentPage;

	private int currentResult;
	private String sortField;
	private String order;


	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentResult() {
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	public PageInfo(){}

	public PageInfo(Integer pageindex, Integer pagesize){
		int currentPage = pageindex;
		int pageSize = pagesize;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;	
		this.showCount=pageSize;
		this.currentResult=currentResult;
	}

}

