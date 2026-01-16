package com.jisoo.board.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	public boolean isAvailable(String id);

	public boolean signup(String userId, String userPw);
}
