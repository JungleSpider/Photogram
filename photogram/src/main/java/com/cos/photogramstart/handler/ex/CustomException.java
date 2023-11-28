package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;  // 시리얼은 객체를 구분할때 씀
	

	public CustomException(String message) {
		super(message);

		
	}
	
}
