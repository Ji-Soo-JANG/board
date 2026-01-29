package com.jisoo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.PageDto;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardVo boardVo);
	public List<BoardVo> selectAllBoards();
	public List<BoardVo> selectBoardsPage(int start, int end); 
	public List<BoardVo> selectTopBoardsByViews(int count);
	public int getCount(PageDto pageDto);
	public BoardVo selectBoard(Long boardId);
	public int isOwner(Long boardId, Long userId);
	public int updateBoard(BoardVo boardVo);
	public int deleteBoard(Long boardId);
	public int increaseViewCount(Long boardId);
	public int increaseLikeCount(Long boardId);
	public int decreaseLikeCount(Long boardId);
	public int insertBoardLike(Long boardId, Long userId);
	public int deleteBoardLike(Long boardId, Long userId);
	public List<BoardVo> selectBoardsByKeword(PageDto pageDto);
}
