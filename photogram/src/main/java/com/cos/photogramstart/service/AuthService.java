package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service  // 트랜잭션 관리를 해줌
public class AuthService {
	
	private final UserRepository userResository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	@Transactional  // 함수가 실행 종료될때 까지 트랜잭션 관리를해줌  // 그래서 write(Insert,Update,Delete 할때 검) 
	public User 회원가입(User user) {  // 여기 user 는 외부에서 통신을 통해 입력받은거 
		
		//회원가입진행
		
		//패스워드 암호화 진행 
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); // 관리자는 ROLE_ADMIN
		
		User userEntity = userResository.save(user);
		// 여기 userEntity는 데이터베이스에 있는 데이터를 user에 담은거 
		return userEntity;
	}

}
