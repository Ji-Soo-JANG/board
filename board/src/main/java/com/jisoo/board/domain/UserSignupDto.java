package com.jisoo.board.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserSignupDto {
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;

	@NotBlank
	private String nickname;
}
