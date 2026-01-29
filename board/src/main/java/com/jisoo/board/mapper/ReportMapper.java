package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jisoo.board.domain.ReportVo;

@Mapper
public interface ReportMapper {
	 public int countPendingReports(); 
	 public int insertReport(ReportVo reportVo);
}
