package com.accenture.codingtest.springbootcodingtest;

import java.util.List;

public class ProjectResponse {
	
	 	private List<ProjectDto> content;
	    private int pageNo;
	    private int pageSize;
	    private long totalElements;
	    private int totalPages;
	    private boolean last;
		public List<ProjectDto> getContent() {
			return content;
		}
		public void setContent(List<ProjectDto> content) {
			this.content = content;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public long getTotalElements() {
			return totalElements;
		}
		public void setTotalElements(long totalElements) {
			this.totalElements = totalElements;
		}
		public int getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
		public boolean isLast() {
			return last;
		}
		public void setLast(boolean last) {
			this.last = last;
		}
		public ProjectResponse(List<ProjectDto> content, int pageNo, int pageSize, long totalElements, int totalPages,
				boolean last) {
			super();
			this.content = content;
			this.pageNo = pageNo;
			this.pageSize = pageSize;
			this.totalElements = totalElements;
			this.totalPages = totalPages;
			this.last = last;
		}
		public ProjectResponse() {
			super();
		}
	    
	    

}
