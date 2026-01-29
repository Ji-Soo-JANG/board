package com.jisoo.board.service;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.ReportVo;

@Service
public interface ReportService {
	public int countPendingReport();
	public boolean reportBoard(ReportVo reportVo);
}
