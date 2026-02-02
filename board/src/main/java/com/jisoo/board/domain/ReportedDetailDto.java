package com.jisoo.board.domain;

import java.util.List;

import lombok.Data;

@Data
public class ReportedDetailDto {
	private String targetType;
	private Long targetId;	
	private Long userId;
	private Long boardId;
	private String title;
	private String content;
	private List<ReportVo> list; 
}
