package com.jisoo.board.service;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public boolean isAvailable(String userId) {
//		System.out.println("service-isAvailable");
		return userMapper.checkId(userId) == 0;
	}

	@Override
	public boolean signup(UserSignupDto dto) {
		  // 1. ID 중복 최종 확인
        if (userMapper.checkId(dto.getLoginId()) > 0) {
            return false;
        }
        
        userMapper.insertUser(dto.getLoginId(), dto.getPassword(), dto.getNickname());
        return true;
	}
}
