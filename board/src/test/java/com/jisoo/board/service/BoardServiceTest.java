package com.jisoo.board.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.mapper.BoardMapper;

@SpringBootTest
public class BoardServiceTest {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	@Disabled
	@Test
	void registerTest() {
		UserVo userVo = userService.findByUserId(20L);
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("title");
		boardVo.setContent("content");
		boardVo.setWriterId(userVo.getUserId());
		boardVo.setWriterName(userVo.getLoginId());
		
		boolean register = boardService.registerBoard(boardVo);
		System.out.println("register: " + register);
	}
	
	@Disabled
	@Test
	void getAllBoardsTest() {
		List<BoardVo> list = boardService.getAllBoards();
		assertFalse(list.isEmpty());
		list.forEach(b ->
		    System.out.printf("[%d] %s (%s)%n",
		        b.getBoardId(), b.getTitle(), b.getWriterName())
		);
	}
	
	@Disabled
	@Test
	void getBoardTest() {
		Long boardId = 30L;
		BoardVo boardVo = boardService.getBoard(boardId);
		System.out.println("boardVo: " + boardVo);
	}

	
	@Test
	void isOwnerTest() {
		Long boardId = 12L;
		Long userId = 20L;
		boolean isOwner = boardService.isOwner(boardId, userId);
		System.out.println("isOwner: " + isOwner);
	}
	
}
