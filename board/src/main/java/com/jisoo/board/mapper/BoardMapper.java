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
}
