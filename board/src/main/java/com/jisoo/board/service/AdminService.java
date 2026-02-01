package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.ReportedBoardDetailDto;
import com.jisoo.board.domain.ReportedBoardDto;
import com.jisoo.board.domain.UserVo;

@Service
public interface AdminService {
	public boolean deleteBoard(Long boardId);
	public boolean recoveryBoard(Long boardId);
	public boolean writeNotice(BoardVo boardVo);
	public List<UserVo> searchUsers(String keyword, String role, int page);
	public boolean changeRole(Long userId, String role);
	public boolean suspendUser(Long userId, int days);
	public List<ReportedBoardDto> selectReportedBoards();
	public ReportedBoardDetailDto getDetail(Long boardId);
	public void processReport(String action, Long boardId, Long userId, Boolean suspend, Integer days, Long adminId);
}
