package com.jisoo.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class ThymeleafConfig {
    /**
     * Spring Boot 2.7.18 + thymeleaf-extras-springsecurity5 環境で
     * SpringSecurityDialect が Thymeleaf の TemplateEngine に
     * 自動登録されないケースがある。
     *
     * 【症状】
     * sec:authorize / sec:authentication がそのままHTMLに残る
     * 権限判定がビューで正しく動作しない
     *
     * 【対応】
     * Dialect を Bean として明示的に登録し、
     * Thymeleaf エンジンに Spring Security 用 Dialect を追加する。
     */
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
