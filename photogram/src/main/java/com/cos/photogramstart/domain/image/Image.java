package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블을 생성
public class Image {
	@Id  // primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라감 ex) 오라클 = 시퀀스 전략
	private int id;
	private String caption;
	private String postImageUrl; // 사진을 전송받아서 그사진ㅇ르 서버에 특정 폴더에 저장 -DB에 저장된 경로를 insert
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	//이미지 좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")
	private List<Likes> likes;
	
	@Transient //DB에 컬럼이 만들어지지 않음
	private boolean likeState;
	
	@Transient //DB에 컬럼이 만들어지지 않음
	private int likeCount;
	
	//댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")
	private List<Comment> comments;
	
	private LocalDateTime createDate;
	
	
	@PrePersist  // DB에 저장되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}


//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postimageUrl=" + postimageUrl 
//				+ ", createDate=" + createDate + "]";
//	}
}
