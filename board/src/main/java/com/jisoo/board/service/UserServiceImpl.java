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
}
