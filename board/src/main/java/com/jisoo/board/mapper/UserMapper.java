package com.jisoo.board.mapper;

import java.time.LocalDateTime;
import java.util.List;

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
	 public int countTodayUser();
	 public List<UserVo> findUsers(  @Param("keyword") String keyword, @Param("role") String role,
			 						 @Param("offset") int offset,
			 						 @Param("limit") int limit);
	 public int updateRole(Long userId, String role);
	 public int suspendUser(@Param("userId") Long userId, @Param("until") LocalDateTime until);
	 public int banUser(@Param("userId") Long userId);
	 public int unsuspendUser(@Param("userId") Long userId);
}
