package com.jisoo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jisoo.board.domain.CommentVo;

@Mapper
public interface CommentMapper {
	public int insertComment(CommentVo commentVo);
	public List<CommentVo> selectComments(Long boardId);
	public int updateComment(CommentVo commentVo);
	public int deleteComment(Long CommentId);
	public int isOwner(Long commentId, Long userId);
}
