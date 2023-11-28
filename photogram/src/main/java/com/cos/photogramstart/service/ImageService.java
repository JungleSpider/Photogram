package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	
	@org.springframework.transaction.annotation.Transactional(readOnly =true)
	public Page<Image> 이미지스토리(int principalId,Pageable pageable){
		
		Page<Image> images = imageRepository.mStory(principalId,pageable);

		//images에 좋아요 상태 담아가야함
		images.forEach((image)->{
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like) ->{
				if(like.getUser().getId() == principalId) { //해당 이미지에 좋아요한 사람들을 찾아서 내가 좋아요 한사람인지 비교
					image.setLikeState(true);
				}
			});
		});
		
		return images;
		
	}
	
	@Value("${file.path}")  // yml 파일에 있는 파일 주소
	private String uploadFolder;
	
	@Transactional // 서비스단에서 DB에 변화를 줄때는 꼭 걸어야함
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID(); // uuid란 
		String imageFileName = uuid +"_"+imageUploadDto.getFile().getOriginalFilename(); //실제 파일 이름이 들어감 ex) 1.jpg 
		
		
		
		Path imageFilPath = Paths.get(uploadFolder + imageFileName);
		
		//통신, I/O 일어날때 예외가 발생 할 수도 있음 
		try {
			Files.write(imageFilPath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		
		Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
		imageRepository.save(image);

		
	}
	
	@org.springframework.transaction.annotation.Transactional(readOnly =true)
	public List<Image> 인기사진() {
		
			
		
		
		return imageRepository.mPopular();
	}
}















