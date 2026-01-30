package com.jisoo.board.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.domain.UserVo;
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
	public boolean isLoginIdAvailable(String userId) {
		return userMapper.existsByLoginId(userId) == 0;
	}

	@Override
	public boolean signup(UserSignupDto dto) {
        if (userMapper.existsByLoginId(dto.getLoginId()) > 0) {
            return false;
        }
        
        String password = passwordEncoder.encode(dto.getPassword());
        
        userMapper.insertUser(dto.getLoginId(), password, dto.getNickname());
        return true;
	}

	@Override
	public boolean existsLoginId(String userId) {
		return userMapper.existsByLoginId(userId) > 0;
	}

	@Override
	public boolean checkPassword(Long userId, String pw) {
		String encodedPassword = userMapper.findPasswordByUserId(userId);
        return passwordEncoder.matches(pw, encodedPassword);
	}

	@Override
	public UserVo findByUserId(Long userId) {
		UserVo userVo = userMapper.findByUserId(userId);
		return userVo;
	}

	@Override
	public boolean updateUserInfo(Long userId, String nickname) {
		int update = userMapper.updateUserInfo(userId, nickname);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(Long userId, String password) {
		String newPassword = passwordEncoder.encode(password);
		int update = userMapper.updatePassword(userId, newPassword);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int countTodayUser() {
		int count = userMapper.countTodayUser();
		return count;
	}
}
