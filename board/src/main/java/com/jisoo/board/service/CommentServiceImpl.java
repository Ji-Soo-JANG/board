package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.CommentVo;
import com.jisoo.board.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	private CommentMapper commentMapper;
	
	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public boolean insertComment(CommentVo commentVo) {
		System.out.println("commentVo: " + commentVo);
		int insert = commentMapper.insertComment(commentVo);
		System.out.println("insert: " + insert);
		if(insert == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<CommentVo> getComments(Long boardId) {
		List<CommentVo> list = commentMapper.selectComments(boardId);
		return list;
	}

	@Override
	public boolean updateComment(CommentVo commentVo) {
		int update = commentMapper.updateComment(commentVo);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delteComment(Long commentId) {
		int delete = commentMapper.deleteComment(commentId);
		if(delete == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isOwner(Long commentId, Long userId) {
		int isOwner = commentMapper.isOwner(commentId, userId);
		if(isOwner == 1) {
			return true;
		}
		return false;
	}
	
	
}
