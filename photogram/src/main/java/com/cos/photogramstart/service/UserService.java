package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId); // 1은 페이지 주인 -1은 주인이 아님
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeState == 1);
		dto.setSubscribeCount(subscribeCount);
		
		//프로필 사진 하트개수 체크
		userEntity.getImages().forEach(image->{
			image.setLikeCount(image.getLikes().size());
		});
		
		return dto;
	}
	 
	@Transactional
	public User 회원수정(int id, User user) {
		
		
		//1. 영속화
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
				return new CustomValidationApiException("찾을 수 없는 id입니다");
		}); 
		
		//2. 영속화된 오브젝트를 수정 -데티체킹(업데티트완료)
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
		//더티체킹이 일어나서 업데이트가 완료됨
	}
	
	@Value("${file.path}")  // yml 파일에 있는 파일 주소
	private String uploadFolder;
	
	@org.springframework.transaction.annotation.Transactional
	public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
		
		UUID uuid = UUID.randomUUID(); // uuid란 
		String imageFileName = uuid +"_"+profileImageFile.getOriginalFilename(); //실제 파일 이름이 들어감 ex) 1.jpg 
		
		Path imageFilPath = Paths.get(uploadFolder + imageFileName);
		
		//통신, I/O 일어날때 예외가 발생 할 수도 있음 
		try {
			Files.write(imageFilPath, profileImageFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		User userEntity = userRepository.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		userEntity.setProfileImageUrl(imageFileName);
		
		return userEntity;
	}// 트렌젝셔널 더티체킹으로 업데이트 됨
	
}














