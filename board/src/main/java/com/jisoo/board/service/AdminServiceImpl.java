package com.jisoo.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.mapper.AdminMapper;
import com.jisoo.board.mapper.BoardMapper;
import com.jisoo.board.mapper.UserMapper;


@Service
public class AdminServiceImpl implements AdminService {
	private AdminMapper adminMapper;
	private BoardMapper boardMapper;
	private UserMapper userMapper;
	
	public AdminServiceImpl(AdminMapper adminMapper, BoardMapper boardMapper, UserMapper userMapper) {
		this.adminMapper = adminMapper;
		this.boardMapper = boardMapper;
		this.userMapper = userMapper;
	}

	@Override
	public boolean deleteBoard(Long boardId) {
		int delete = adminMapper.deleteBoard(boardId);
		if(delete == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean recoveryBoard(Long boardId) {
		int recovery = adminMapper.recoveryBoard(boardId);
		if(recovery== 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean writeNotice(BoardVo boardVo) {
		boardVo.setIsNotice('Y');
		int write = boardMapper.insertBoard(boardVo);
		if(write == 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<UserVo> searchUsers(String keyword, String role, int page) {
		int limit = 10;
		int offset = (page - 1) * limit;

		List<UserVo> users = userMapper.findUsers(keyword, role, offset, limit);
		
		return users;
	}

	@Override
	public boolean changeRole(Long userId, String role) {
		int update = userMapper.updateRole(userId, role);
		if(update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean suspendUser(Long userId, int days) {
	    LocalDateTime until = LocalDateTime.now().plusDays(days);
		int update = userMapper.suspendUser(userId, until);
		if(update == 1) {
			return true;
		}		return false;
	}
}
