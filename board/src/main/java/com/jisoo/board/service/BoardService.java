package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.BoardVo;

@Service
public interface BoardService {
	public boolean registerBoard(BoardVo boardVo);
	public List<BoardVo> getAllBoards();
	public BoardVo getBoard(Long boardId);
	public boolean isOwner(Long boardId, Long userId);
}
