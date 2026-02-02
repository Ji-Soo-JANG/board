package com.jisoo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jisoo.board.domain.ReportedBoardDto;
import com.jisoo.board.domain.ReportedCommentDto;

@Mapper
public interface AdminMapper {
	public int deleteBoard(Long boardId);
	public int recoveryBoard(Long boardId);
	public List<ReportedBoardDto> selectReportsByType();
	public List<ReportedCommentDto> selectReportedComments();
}
