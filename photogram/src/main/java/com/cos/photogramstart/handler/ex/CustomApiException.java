package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;  // 시리얼은 객체를 구분할때 씀
	
	private Map<String, String> errorMap;
	
	
	public CustomApiException(String message) {
		super(message);		
	}
}
