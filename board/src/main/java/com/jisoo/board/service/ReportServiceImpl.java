package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.CommentVo;
import com.jisoo.board.domain.ReportVo;
import com.jisoo.board.mapper.CommentMapper;
import com.jisoo.board.mapper.ReportMapper;

@Service
public class ReportServiceImpl implements ReportService{
	private ReportMapper reportMapper;
	
	public ReportServiceImpl(ReportMapper reportMapper) {
		this.reportMapper = reportMapper;
	}

	@Override
	public int countPendingReport() {
		int count = reportMapper.countPendingReports();
		return count;
	}

	@Override
	public boolean reportBoard(ReportVo reportVo) {
		int insert = reportMapper.insertReport(reportVo);
		if(insert == 1) {
			return true;
		}
		return false;
	}
	
}
