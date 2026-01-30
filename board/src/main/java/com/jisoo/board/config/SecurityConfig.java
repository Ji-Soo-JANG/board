package com.jisoo.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // springfilterをfilterchainに登録
public class SecurityConfig {

	// https://github.com/spring-projects/spring-security-samples/blob/main/servlet/spring-boot/java/hello-security-explicit/src/main/java/example/SecurityConfiguration.java
	// bean登録
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth.antMatchers("/login", "/auth/**", "/signup/**").permitAll()
						.antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()

				)
				// .formLogin(); // ログイン処理フィルター登録
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("inputId")
						.passwordParameter("inputPw").defaultSuccessUrl("/", true)
						.failureHandler((request, response, exception) -> {
							String msg = exception.getMessage();

						    if (exception instanceof BadCredentialsException) {
						        response.sendRedirect("/login?error=bad_credentials");
						        return;
						    }

						    if ("suspended".equalsIgnoreCase(msg)) {
						        response.sendRedirect("/login?error=suspended");
						        return;
						    }

						    response.sendRedirect("/login?error=unknown");
							
						    /*
						     * System.out.println("===== LOGIN FAIL =====");
						     * System.out.println("Exception class : " + exception.getClass().getName());
						     * System.out.println("Message         : " + exception.getMessage());
						     *	
							 *
							 * パスワード 
							 * ===== LOGIN FAIL ===== Exception class :
							 * org.springframework.security.authentication.BadCredentialsException Message :
							 * 자격 증명에 실패하였습니다.
							 * 
							 * 一時停止 
							 * ===== LOGIN FAIL ===== Exception class :
							 * org.springframework.security.authentication.
							 * InternalAuthenticationServiceException Message : suspended
							 */

							response.sendRedirect("/login?error=true");
						}))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
		return http.build();
	}

	//
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
