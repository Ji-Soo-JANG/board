package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.ReportedBoardDto;
import com.jisoo.board.domain.ReportedCommentDto;
import com.jisoo.board.domain.ReportedDetailDto;
import com.jisoo.board.domain.UserVo;

@Service
public interface AdminService {
	public boolean deleteBoard(Long boardId);
	public boolean recoveryBoard(Long boardId);
	public boolean writeNotice(BoardVo boardVo);
	public List<UserVo> searchUsers(String keyword, String role, int page);
	public boolean changeRole(Long userId, String role);
	public boolean suspendUser(Long userId, int days);
	public ReportedDetailDto getDetail(String targetType, Long targetId);
	public void processReport(String targetType, Long targetId, Long userId, String action, Boolean suspend, Integer days, Long adminId);
	public List<ReportedBoardDto> selectReportedBoards();
	public List<ReportedCommentDto> selectReportedComments();
}
