package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.PageDto;

@Service
public interface BoardService {
	public boolean registerBoard(BoardVo boardVo);
	public List<BoardVo> getAllBoards();
	public List<BoardVo> getboards(int start, int end);
	public List<BoardVo> getBoardsByKeword(PageDto pageDto);
	public List<BoardVo> getTopBoardsByViews(int count);
	public List<BoardVo> getRecentNotice();
	public int getPageCount(PageDto pageDto);
	public BoardVo getBoard(Long boardId);
	public boolean isOwner(Long boardId, Long userId);
	public boolean updateBoard(BoardVo boardVo);
	public boolean deleteBoard(Long boardId);
	public boolean increaseViewCount(Long boardId);
	public boolean increaseLikeCount(Long boardId);
	public boolean decreaseLikeCount(Long boardId);
	public boolean insertBoardLike(Long boardId, Long userId);
	public boolean deleteBoardLike(Long boardId, Long userId);
	int toggleLike(Long boardId, Long userId);
	int countTodayBoard();
}
