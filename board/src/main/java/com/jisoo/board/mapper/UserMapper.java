package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	 @Select("select 1 from dual")
	 int ping();
	 
	 public int checkId(@Param("userId") String userId);
	 public boolean insertUser(@Param("userId") String userId, @Param("userPw") String userPw);
}
