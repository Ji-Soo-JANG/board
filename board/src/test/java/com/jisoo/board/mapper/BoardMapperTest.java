package com.jisoo.board.mapper;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.UserVo;

@SpringBootTest
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Disabled
	@Test
	void insertTest() {
		UserVo userVo = userMapper.findByUserId(20L);
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("title");
		boardVo.setContent("content");
		boardVo.setWriterId(userVo.getUserId());
		boardVo.setWriterName(userVo.getLoginId());
		
		int result = boardMapper.insertBoard(boardVo);
		System.out.println("result: " + result);
	}
	
	@Test
	void findAllTest() {
		List<BoardVo> list = boardMapper.selectAllBoards();
		System.out.println(list);
	}
}
