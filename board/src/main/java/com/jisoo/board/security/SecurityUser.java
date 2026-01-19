package com.jisoo.board.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jisoo.board.domain.UserVo;

public class SecurityUser implements UserDetails {
	private final UserVo userVo;

	public SecurityUser(UserVo userVo) {
		this.userVo = userVo;
	}

	public Long getUserId() {
		return userVo.getUserId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(userVo.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userVo.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userVo.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
