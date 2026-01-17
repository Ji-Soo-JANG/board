package com.jisoo.board.domain;

import lombok.Data;

@Data
public class UserVo {
	private Long userId;
	private String loginId;
	private String password;
	private String nickname;
	private String role;
	private java.time.LocalDateTime createdAt;
	private java.time.LocalDateTime updatedAt;
	private java.time.LocalDateTime deletedAt;
}
