package com.jisoo.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.ReportVo;
import com.jisoo.board.domain.ReportedBoardDetailDto;
import com.jisoo.board.domain.ReportedBoardDto;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.mapper.AdminMapper;
import com.jisoo.board.mapper.BoardMapper;
import com.jisoo.board.mapper.ReportMapper;
import com.jisoo.board.mapper.UserMapper;


@Service
public class AdminServiceImpl implements AdminService {
	private AdminMapper adminMapper;
	private BoardMapper boardMapper;
	private UserMapper userMapper;
	private ReportMapper reportMapper;
	
	public AdminServiceImpl(AdminMapper adminMapper, BoardMapper boardMapper, UserMapper userMapper, ReportMapper reportMapper) {
		this.adminMapper = adminMapper;
		this.boardMapper = boardMapper;
		this.userMapper = userMapper;
		this.reportMapper = reportMapper;
	}

	@Override
	public boolean deleteBoard(Long boardId) {
		int delete = adminMapper.deleteBoard(boardId);
		if(delete == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean recoveryBoard(Long boardId) {
		int recovery = adminMapper.recoveryBoard(boardId);
		if(recovery== 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean writeNotice(BoardVo boardVo) {
		boardVo.setIsNotice('Y');
		int write = boardMapper.insertBoard(boardVo);
		if(write == 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<UserVo> searchUsers(String keyword, String role, int page) {
		int limit = 10;
		int offset = (page - 1) * limit;

		List<UserVo> users = userMapper.findUsers(keyword, role, offset, limit);
		
		return users;
	}

	@Override
	public boolean changeRole(Long userId, String role) {
		int update = userMapper.updateRole(userId, role);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean suspendUser(Long userId, int days) {
	    LocalDateTime until = LocalDateTime.now().plusDays(days);
		int update = userMapper.suspendUser(userId, until);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<ReportedBoardDto> selectReportedBoards() {
		List<ReportedBoardDto> list = reportMapper.selectReportsByType("BOARD");
		return list;
	}

	@Override
	public ReportedBoardDetailDto getDetail(Long boardId) {
		ReportedBoardDetailDto dto = new ReportedBoardDetailDto();
		BoardVo boardVo = boardMapper.selectBoard(boardId);
		List<ReportVo> list = reportMapper.selectReportsById("BOARD", boardId);
		
		dto.setBoardId(boardVo.getBoardId());
		dto.setTitle(boardVo.getTitle());
		dto.setContent(boardVo.getContent());
		dto.setUserId(boardVo.getWriterId());
		dto.setList(list);
		
		return dto;
	}

	@Override
	@Transactional
	public void processReport(String action, Long boardId, Long userId, Boolean suspend, Integer days, Long adminId) {
	    boolean doSuspend = Boolean.TRUE.equals(suspend);
	    int suspendDays = (days == null ? 7 : days);
	    LocalDateTime until;
	    
	    if (suspendDays == 9999) {
	        until = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
	    } else {
	        until = LocalDateTime.now().plusDays(suspendDays);
	    }
	    
	    if("resolve".equals(action)) {
	    	adminMapper.deleteBoard(boardId);
	    	
	    	if(doSuspend) {
	    		userMapper.suspendUser(userId, until);
	    	}
	    }
	    
	    reportMapper.updateByTarget(action, "BOARD", boardId, adminId);
	    
		return;
	}
}
