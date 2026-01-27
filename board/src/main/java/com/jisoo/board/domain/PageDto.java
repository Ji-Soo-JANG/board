package com.jisoo.board.domain;

import lombok.Data;

@Data
public class PageDto {
	private int size = 10;
	private int pageSize = 10;

	private String searchType;
	private String keyword;
	
	private int currentPage = 1;
	private int pages;
	private int start;
	private int startPage;
	private int end;
	private int endPage;
	
	public void calcPage(int pages) {
		this.pages = pages;
		
		if (currentPage < 1) currentPage = 1;
		if (pages > 0 && currentPage > pages) currentPage = pages;
		
		start = size * (currentPage - 1) + 1;
		end = currentPage * size;
		startPage = pageSize *( (currentPage - 1) / pageSize ) + 1;
		endPage = Math.min(startPage + pageSize - 1, pages);
		
		return;
	}
}

