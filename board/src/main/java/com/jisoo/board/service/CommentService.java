package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.CommentVo;

@Service
public interface CommentService {
	public boolean insertComment(CommentVo commentVo);
	public List<CommentVo> getComments(Long boardId);
	public boolean updateComment(CommentVo commentVo);
	public boolean delteComment(Long commentId);
	public boolean isOwner(Long commentId, Long userId);
}
