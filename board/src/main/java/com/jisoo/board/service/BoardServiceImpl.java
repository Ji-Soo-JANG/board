package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public boolean registerBoard(BoardVo boardVo) {
		int insert = boardMapper.insertBoard(boardVo);
		if(insert == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<BoardVo> getAllBoards() {
		List<BoardVo> list = boardMapper.selectAllBoards();
		System.out.println(list);
		return list;
	}

	@Override
	public BoardVo getBoard(Long boardId) {
		BoardVo boardVo = boardMapper.selectBoard(boardId);
		return boardVo;
	}

	@Override
	public boolean isOwner(Long boardId, Long userId) {
		int isOwner = boardMapper.isOwner(boardId, userId);
		return isOwner > 0;
	}
	
	
}
