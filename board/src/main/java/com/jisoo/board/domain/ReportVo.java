package com.jisoo.board.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportVo {
	private Long reportId;
	private String targetType;
	private Long targetId;
	private Long reporterId;
	private String reason;
	private String detail;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime resolvedAt;
	private Long resolvedBy;

}
