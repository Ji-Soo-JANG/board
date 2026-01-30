package com.jisoo.board.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.mapper.BoardMapper;

@SpringBootTest
public class AdminSerivceTest {
	@Autowired
	private AdminService adminService;
	@Autowired
	private BoardMapper boardMapper;

	@Disabled
	@Test
	void deleteBaordTest() {
		Long boardId = 14L;
		System.out.println("delete: " + adminService.deleteBoard(boardId));
		System.out.println("select: " + boardMapper.selectBoard(boardId));
	}

	@Disabled
	@Test
	void recoveryBoardTest() {
		Long boardId = 14L;
		System.out.println("recovery: " + adminService.recoveryBoard(boardId));
		System.out.println("select: " + boardMapper.selectBoard(boardId));
	}
	
	@Test
	void writeNoticeTest() {
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("noticeTest");
		boardVo.setContent("noticeTestContent");
		boardVo.setWriterId(23L);
		boardVo.setWriterName("admin");
	
		adminService.writeNotice(boardVo);
		System.out.println("select: " + boardMapper.selectAllBoards());
	}
	
}
