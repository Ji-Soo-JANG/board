package com.jisoo.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.CommentVo;
import com.jisoo.board.domain.ReportVo;
import com.jisoo.board.domain.ReportedBoardDto;
import com.jisoo.board.domain.ReportedCommentDto;
import com.jisoo.board.domain.ReportedDetailDto;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.mapper.AdminMapper;
import com.jisoo.board.mapper.BoardMapper;
import com.jisoo.board.mapper.CommentMapper;
import com.jisoo.board.mapper.ReportMapper;
import com.jisoo.board.mapper.UserMapper;


@Service
public class AdminServiceImpl implements AdminService {
	private AdminMapper adminMapper;
	private BoardMapper boardMapper;
	private UserMapper userMapper;
	private ReportMapper reportMapper;
	private CommentMapper commentMapper;
	
	public AdminServiceImpl(AdminMapper adminMapper, BoardMapper boardMapper, UserMapper userMapper, ReportMapper reportMapper, CommentMapper commentMapper) {
		this.adminMapper = adminMapper;
		this.boardMapper = boardMapper;
		this.userMapper = userMapper;
		this.reportMapper = reportMapper;
		this.commentMapper = commentMapper;
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
		List<ReportedBoardDto> list = adminMapper.selectReportsByType();
		return list;
	}

	@Override
	public ReportedDetailDto getDetail(String targetType, Long targetId) {
		ReportedDetailDto dto = new ReportedDetailDto();
		
		dto.setTargetType(targetType);
		
		if("board".equals(targetType)) {
			BoardVo boardVo = boardMapper.selectBoard(targetId);
			
			dto.setTargetId(boardVo.getBoardId());
			dto.setTitle(boardVo.getTitle());
			dto.setContent(boardVo.getContent());
			dto.setUserId(boardVo.getWriterId());
			dto.setBoardId(dto.getTargetId());
		}
		else if("comment".equals(targetType)){
			CommentVo commentVo = commentMapper.selectComment(targetId);
			dto.setContent(commentVo.getContent());
			dto.setBoardId(commentVo.getBoardId());
			dto.setTargetId(commentVo.getCommentId());
			dto.setUserId(commentVo.getWriterId());
		}
		
		List<ReportVo> list = reportMapper.selectReportsById(targetType.toUpperCase(), targetId);
		dto.setList(list);
		return dto;
	}

	@Override
	@Transactional
	public void processReport(String targetType, Long targetId, Long userId, String action, Boolean suspend, Integer days, Long adminId) {
	    boolean doSuspend = Boolean.TRUE.equals(suspend);
	    int suspendDays = (days == null ? 7 : days);
	    LocalDateTime until;
	    
	    if (suspendDays == 9999) {
	        until = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
	    } else {
	        until = LocalDateTime.now().plusDays(suspendDays);
	    }
	    
	    if("resolve".equals(action)) {
	    	if("board".equals(targetType)) {
	    		adminMapper.deleteBoard(targetId);
	    		System.out.println("완료됨");
	    	}
	    	else if("comment".equals(targetType)) {
	    		commentMapper.deleteComment(targetId);
	    		System.out.println("comment");
	    	}
	    	
	    	if(doSuspend) {
	    		userMapper.suspendUser(userId, until);
	    	}
	    }
	    
	    reportMapper.updateByTarget(action, targetType.toUpperCase(), targetId, adminId);
	    
	    
		return;
	}

	@Override
	public List<ReportedCommentDto> selectReportedComments() {
		List<ReportedCommentDto> list = adminMapper.selectReportedComments();
		return list;
	}
}
