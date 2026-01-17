package com.jisoo.board.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.mapper.UserMapper;


@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public boolean isAvailable(String userId) {
//		System.out.println("service-isAvailable");
		return userMapper.checkId(userId) == 0;
	}

	@Override
	public boolean signup(UserSignupDto dto) {
        if (userMapper.checkId(dto.getLoginId()) > 0) {
            return false;
        }
        
        String password = passwordEncoder.encode(dto.getPassword());
        
        userMapper.insertUser(dto.getLoginId(), password, dto.getNickname());
        return true;
	}
}
