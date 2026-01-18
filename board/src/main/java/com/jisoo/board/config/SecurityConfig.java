package com.jisoo.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        http
        	.csrf(csrf -> csrf.disable())
        
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/login", "/auth/**", "/signup/**").permitAll()
            	.anyRequest().authenticated()
                
            )
            //.formLogin(); // ログイン処理フィルター登録
            .formLogin(form -> form
            		.loginPage("/login")
            		.loginProcessingUrl("/login")
            		.usernameParameter("inputId")
            		.passwordParameter("inputPw")
            		.defaultSuccessUrl("/", true)
            	)
            .logout(logout -> logout
            		.logoutUrl("/logout")
            		.logoutSuccessUrl("/login")
            		);
        return http.build();
    }

	// 
	@Bean
		public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
