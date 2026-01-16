package com.jisoo.board.service;

import org.springframework.stereotype.Service;

import com.jisoo.board.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public boolean isAvailable(String id) {
		return userMapper.checkId(id) == 0;
	}

	@Override
	public boolean signup(String userId, String userPw) {
		  // 1. ID 중복 최종 확인
        if (userMapper.checkId(userId) > 0) {
            return false;
        }

        // 2. 비밀번호 암호화 (지금은 plain, 다음 단계에서 BCrypt)
        userMapper.insertUser(userId, userPw);
        return true;
	}
}
