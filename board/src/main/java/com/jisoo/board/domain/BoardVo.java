package com.jisoo.board.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVo {
    private Long boardId;
    private String title;
    private String content;
    private Long writerId;
    private String writerName;
    private int viewCount;
    private int likeCount;
    private int commentCount;
    private char isNotice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
