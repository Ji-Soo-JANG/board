package com.jisoo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jisoo.board.domain.ReportVo;
import com.jisoo.board.domain.ReportedBoardDto;

@Mapper
public interface ReportMapper {
	 public int countPendingReports(); 
	 public int insertReport(ReportVo reportVo);
	 public List<ReportVo> selectReportsById(@Param("targetType") String targetType, @Param("targetId") Long targetId);
	 public int updateByTarget(@Param("status") String status, @Param("targetType") String targetType, @Param("targetId") Long targetId, @Param("adminId") Long adminId);
}
