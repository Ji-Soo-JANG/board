package com.jisoo.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jisoo.board.domain.ReportVo;

@SpringBootTest
public class ReportServiceTest {
	
	@Autowired
	private ReportService reportService;
	
	@Test
	void insertBoardReportTest() {
		ReportVo reportVo = new ReportVo(null, "BOARD", 12L, 22L, "SPAM", "test", null, null, null, null);
		System.out.println("reportVo: " + reportVo);
		System.out.println("insert: " + reportService.reportBoard(reportVo));
	}
	
}
