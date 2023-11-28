package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity //해당파일로 시큐리티를 활성화
@Configuration //IOC
public class SecurityConfig{
	 
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		//super 삭제시 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
		http.csrf().disable();
		http.authorizeRequests()	
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated() //인증이 필요한 페이지는 authenticated() 인증이 필요함
			.anyRequest().permitAll()  // 나머지는 다 허용
			.and()  
			.formLogin()  //폼 로그인 페이지 
			.loginPage("/auth/signin") // GET 폼로그인 페이지는 여기
			.loginProcessingUrl("/auth/signin")  // POST -> 스프링 시큐리티가 로그인 프로세스를 진행함
			.defaultSuccessUrl("/") // 성공하면  "/"로 가게함
			.and()
			.oauth2Login()  // form로그인도 하고, oauth2로그인도 할거임
			.userInfoEndpoint() // 최종응답을 회원정보를 바로 받을 수 있음
			.userService(oAuth2DetailsService);	//
		return http.build();
	}

}

