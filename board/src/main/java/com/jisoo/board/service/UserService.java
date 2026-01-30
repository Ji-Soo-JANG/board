package com.jisoo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.domain.UserVo;

@Service
public interface UserService {
	
	public boolean isLoginIdAvailable(String id);
	public boolean existsLoginId(String id);
	public boolean signup(UserSignupDto dto);
	public boolean checkPassword(Long userId, String pw);
	public UserVo findByUserId(Long userId);
	public boolean updateUserInfo(Long userId, String nickname);
	public boolean updatePassword(Long userId, String password);
	public int countTodayUser();
}
