package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jisoo.board.domain.UserVo;

@Mapper
public interface UserMapper {
	 @Select("select 1 from dual")
	 int ping();
	 
	 public int existsByLoginId(@Param("userId") String userId);
	 public boolean insertUser(@Param("userId") String userId, @Param("userPw") String userPw, @Param("nickname") String nickname);
	 public UserVo findByLoginId(@Param("loginId") String loginId);
	 public UserVo findByUserId(@Param("userId") Long userId);
	 public String findPasswordByUserId(@Param("userId") Long userId);
	 public int updateUserInfo(@Param("userId") Long userId, @Param("nickname") String nickname);
	 public int updatePassword(@Param("userId") Long userId, @Param("password") String password);
}
