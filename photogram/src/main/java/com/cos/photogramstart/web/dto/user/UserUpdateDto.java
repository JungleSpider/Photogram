package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String name;  //필수
	@NotBlank
	private String password; //필수
	
	// 나머지는 필수 아님
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	//필수가 아닌 것들까지 전부 ntt로만들면 위험 , 코드 수정예정
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
				
		
	}	
}
