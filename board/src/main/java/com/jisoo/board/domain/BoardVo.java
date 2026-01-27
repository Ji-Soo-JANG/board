package com.jisoo.board.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

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
    
    @Override
    public String toString() {
    	int end = content.length() > 10 ? 10 : content.length();
    	
        return "BoardVo {\n" +
               "  boardId=" + boardId + "\n" +
               "  title=" + title + "\n" +
               "  content=" + content.substring(0, end) + "\n" +
               "  writerId=" + writerId + "\n" +
               "  writerName=" + writerName + "\n" +
               "  likeCount=" + likeCount + "\n" +
               "  viewCount=" + viewCount + "\n" +
               "  createdAt=" + createdAt + "\n" +
               "  updatedAt=" + updatedAt + "\n" +
               "  deletedAt=" + deletedAt + "\n" +
               "}";
    }

}
