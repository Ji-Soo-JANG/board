package com.jisoo.board.domain;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentVo {
	Long commentId;
	Long boardId;
	Long writerId;
	String content;
	LocalDateTime createdAt;
	LocalDateTime deletedAt;
	LocalDateTime updatedAt;
}
