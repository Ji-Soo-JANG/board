package com.jisoo.board.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReportedBoardDto {
	private Long boardId;
	private String title;
	private String content;
	private Long userId;
	private int reportCount;
	private LocalDateTime lastReportedAt;
}
