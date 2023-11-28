package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;  // 시리얼은 객체를 구분할때 씀
	
	private Map<String, String> errorMap;
	
	
	public CustomValidationApiException(String message) {
		super(message);		
	}
	
	public CustomValidationApiException(String message,Map<String, String> errorMap ) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public Map<String,String> getErrorMap(){
		return errorMap;
	}
	
}
