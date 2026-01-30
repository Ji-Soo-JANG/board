package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.PageDto;
import com.jisoo.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public boolean registerBoard(BoardVo boardVo) {
		boardVo.setIsNotice('N');
		int insert = boardMapper.insertBoard(boardVo);
		if(insert == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<BoardVo> getAllBoards() {
		List<BoardVo> list = boardMapper.selectAllBoards();
//		System.out.println(list);
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

	@Override
	public boolean updateBoard(BoardVo boardVo) {
		int update = boardMapper.updateBoard(boardVo);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBoard(Long boardId) {
		int delete = boardMapper.deleteBoard(boardId);
		if(delete == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean increaseViewCount(Long boardId) {
		int upViewCount = boardMapper.increaseViewCount(boardId);
		if(upViewCount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean increaseLikeCount(Long boardId) {
		int upLikeCount = boardMapper.increaseLikeCount(boardId);
		if(upLikeCount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean decreaseLikeCount(Long boardId) {
		int downLikeCount = boardMapper.decreaseLikeCount(boardId);
		if(downLikeCount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertBoardLike(Long boardId, Long userId) {
		int boardLike = boardMapper.insertBoardLike(boardId, userId);
		if(boardLike == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBoardLike(Long boardId, Long userId) {
		int boardLike = boardMapper.deleteBoardLike(boardId, userId);
		if(boardLike == 1) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	/*
		insert 먼저 하게 되면  DB constraint 위반(unique(board_id, user_id))이기 때문에 delete 먼저 수행함
	*/	
	public int toggleLike(Long boardId, Long userId) {
		int delete = boardMapper.deleteBoardLike(boardId, userId);
		if(delete == 1) {
			boardMapper.decreaseLikeCount(boardId);
			return -1;
		}
		
		int insert = boardMapper.insertBoardLike(boardId, userId);
		if(insert == 1) {
			boardMapper.increaseLikeCount(boardId);
			return 1;
		}
		
		return 0;
	}

	@Override
	public List<BoardVo> getboards(int start, int end) {
		List<BoardVo> list = boardMapper.selectBoardsPage(start, end);
		return list;
	}

	@Override
	public int getPageCount(PageDto pageDto) {
		int count = boardMapper.getCount(pageDto);
		int page = count / 10;
		if(count % 10 != 0) {
			page += 1;
		}
		
		return page;
	}

	@Override
	public List<BoardVo> getBoardsByKeword(PageDto pageDto) {
		List<BoardVo> list = boardMapper.selectBoardsByKeword(pageDto);
		return list;
	}

	@Override
	public List<BoardVo> getTopBoardsByViews(int count) {
		List<BoardVo> list = boardMapper.selectTopBoardsByViews(count);
		return list;
	}

	@Override
	public int countTodayBoard() {
		int count = boardMapper.countTodayBoard();
		return count;
	}

	@Override
	public List<BoardVo> getRecentNotice() {
		List<BoardVo> list = boardMapper.selectRecentNotices();
		return list;
	}
	
	
}
