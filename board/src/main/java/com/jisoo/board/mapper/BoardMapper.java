package com.jisoo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jisoo.board.domain.BoardVo;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardVo boardVo);
	public List<BoardVo> selectAllBoards();
	public BoardVo selectBoard(Long boardId);
	public int isOwner(Long boardId, Long userId);
	public int updateBoard(BoardVo boardVo);
	public int deleteBoard(Long boardId);
	public int increaseViewCount(Long boardId);
	public int increaseLikeCount(Long boardId);
	public int decreaseLikeCount(Long boardId);
	public int insertBoardLike(Long boardId, Long userId);
	public int deleteBoardLike(Long boardId, Long userId);
}
