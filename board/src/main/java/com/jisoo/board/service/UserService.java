package com.jisoo.board.service;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;

@Service
public interface UserService {
	
	public boolean isLoginIdAvailable(String id);
	public boolean existsLoginId(String id);
	public boolean signup(UserSignupDto dto);
}
