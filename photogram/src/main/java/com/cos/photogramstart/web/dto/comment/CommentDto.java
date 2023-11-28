package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;


import com.sun.istack.NotNull;

import lombok.Data;

// NotNull = Null값 체크
// NotEmpty = 빈값이거나 null 체크
// NotBlank = 빈값이거나 null 체크 긜고 빈공백(스페이스)까지

@Data
public class CommentDto {
	@NotBlank // 빈값이거나 null 체크 빈 공간까지
	private String content;
	
	@javax.validation.constraints.NotNull
	private Integer imageId;
	
	//toEntity 필없

}
