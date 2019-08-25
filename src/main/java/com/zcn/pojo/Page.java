package com.zcn.pojo;

import java.util.List;

public class Page<T> {
	//当前页码，默认为第1页 
	    private int startRow;
		private int currentPage=0;
		private int totalRows;
		//总页数
		private int totalPageCount;
		//总记录数
		private int totalCount;
		//每页显示的条目数
		private int pageSize;
		//上一页
		private int upPageNo;
		//下一页
		private int nextPageNo;
		//每一页的结果集
		private List<T> list;
		private String queryCondition;
		public int getTotalRows() {
			return totalRows;
		}
		public void setTotalRows(int totalRows) {
			this.totalRows = totalRows;
		}
		public int getStartRow() {
			return startRow;
		}
		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}
		
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public int getTotalPageCount() {
			return totalPageCount;
		}
		public void setTotalPageCount(int totalPageCount) {
			this.totalPageCount = totalPageCount;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getUpPageNo() {
			return upPageNo;
		}
		public void setUpPageNo(int upPageNo) {
			this.upPageNo = upPageNo;
		}
		public int getNextPageNo() {
			return nextPageNo;
		}
		public void setNextPageNo(int nextPageNo) {
			this.nextPageNo = nextPageNo;
		}
		public List<T> getList() {
			return list;
		}
		public void setList(List<T> list) {
			this.list = list;
		}
		public String getqueryCondition() {
			return queryCondition;
		}
		public void setqueryCondition(String queryCondition) {
			this.queryCondition = queryCondition;
		}
		
		
    
}
