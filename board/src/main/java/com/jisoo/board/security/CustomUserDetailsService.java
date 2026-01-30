package com.jisoo.board.security;

import java.util.Date;

import org.springframework.security.authentication.DisabledException;
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
//		System.out.println("userDetailsService");
//		System.out.println("receive username: " + username);
		UserVo user = mapper.findByLoginId(username);
//		System.out.println("user: " + user);
		System.out.println(user.getStatus());
		if (user == null) throw new UsernameNotFoundException("not found");

	    if ("BANNED".equals(user.getStatus())) {
	        throw new DisabledException("banned");
	    }

	    if ("SUSPENDED".equals(user.getStatus())
	        && user.getSuspendedUntil() != null
	        && user.getSuspendedUntil().after(new Date())) {
	        throw new DisabledException("suspended");
	    }
	    return new SecurityUser(user);
	}

}
