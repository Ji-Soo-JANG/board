package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jisoo.board.domain.UserVo;

@Mapper
public interface AdminMapper {
	public int deleteBoard(Long boardId);
	public int recoveryBoard(Long boardId);
}
