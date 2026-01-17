package com.jisoo.board.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jisoo.board.domain.UserVo;
import com.jisoo.board.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserMapper mapper;
	
	public CustomUserDetailsService(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("userDetailsService");
		System.out.println("receive username: " + username);
		UserVo user = mapper.findByLoginId(username);
		System.out.println("user: " + user);
		if (user == null) throw new UsernameNotFoundException("not found");
	    return new SecurityUser(user);
	}

}
