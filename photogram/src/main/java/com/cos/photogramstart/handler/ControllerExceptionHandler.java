package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController		// 낚아채고 응답할때 '데이터'를 응답할거
@ControllerAdvice  // 이거 붙이면 모든 예외처리를 낚아챔
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하는 모든 exception을낚아챔
	public String validationException(CustomValidationException e) {
		// CMRespDto, Script 비교
		// 1. 클라이언트한태 응답할 때는 Script가 좋음
		// 2. Ajax통신 - CMResDto
		// 3. Android 통신 - CMRespDto
		if(e.getErrorMap()== null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
		
	}
	
	@ExceptionHandler(CustomException.class) // RuntimeException이 발동하는 모든 exception을낚아챔
	public String Exception(CustomException e) {
		
		return Script.back(e.getMessage());
	}
	
	
	@ExceptionHandler(CustomValidationApiException.class) 
	public ResponseEntity<?> validationException(CustomValidationApiException e) {
		

		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class) 
	public ResponseEntity<?> apiException(CustomApiException e) {
		

		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
	}
	
}
 