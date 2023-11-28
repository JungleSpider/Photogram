package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 걸린 애들에 대한 생성자를 만들어줌 DI(의존성 주입)할때 사용 
@Controller // 1. IoC 등록 2. 파일을 return하는 컨트롤러  
public class AuthController {
	
	
	private final AuthService authService;
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입버튼 -> /auth/signup -> auth/signin
	@PostMapping("/auth/signup")
	public  String signup(@Valid SignupDto signupDto,BindingResult bindingResult) {  //@ResponseBody이 붙으면 @Controller 여도 데이터를 응답함 
		
//		log.info(signupDto.toString());
		
			User user = signupDto.toEntity();
//			log.info(user.toString());
			authService.회원가입(user);
			
			//System.out.println(userEntity);
			
			return "auth/signin";

	}
	
	
}
