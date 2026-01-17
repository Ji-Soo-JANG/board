package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jisoo.board.domain.UserVo;

@Mapper
public interface UserMapper {
	 @Select("select 1 from dual")
	 int ping();
	 
	 public int checkId(@Param("userId") String userId);
	 public boolean insertUser(@Param("userId") String userId, @Param("userPw") String userPw, @Param("nickname") String nickname);
	 public UserVo findByLoginId(@Param("loginId") String loginId);
}
