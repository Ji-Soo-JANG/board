package com.jisoo.board.domain;

import java.util.List;

import lombok.Data;

@Data
public class ReportedBoardDetailDto {
	private Long boardId;
	private String title;
	private String content;
	private Long userId;

	private List<ReportVo> list; 
}
