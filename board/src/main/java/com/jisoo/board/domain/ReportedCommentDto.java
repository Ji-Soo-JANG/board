package com.jisoo.board.domain;

import lombok.Data;

@Data
public class ReportedCommentDto {
	private Long boardId;
	private Long commentId;
	private Long userId; 
	private String content;
	private int reportCount;
}
