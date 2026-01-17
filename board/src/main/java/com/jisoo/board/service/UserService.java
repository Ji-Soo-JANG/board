package com.jisoo.board.service;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;

@Service
public interface UserService {
	
	public boolean isAvailable(String id);

	public boolean signup(UserSignupDto dto);
}
