# photogram

Springboot를 이용한 인스타 클론 코딩입니다.

# Description

- 개발 기간: 2023.11.10 ~ 2023.11.24
- 사용 툴  : SpringToolSuite4
- 사용 기술
  - Spring 4.0,  Apache Tomcat 9.0,  Tiles3, JPA
  - Java,  Ajax,  Git,  MVC Pttern ,spring security
  - MariaDB
# Implementation

- #### 메인화면

  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%ED%94%BC%EB%93%9C.PNG?raw=true"/></p>
  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%EA%B5%AC%EB%8F%85%ED%95%9C%20%EA%B2%8C%EC%8B%9C%EA%B8%80%EB%A7%8C%20%EC%B6%9C%EB%A0%A5.PNG?raw=true"/></p>
  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%ED%8E%98%EC%9D%B4%EC%A7%95.PNG?raw=true"/></p>

  - **게시글**

    1. JpaRepository를 상속받은 ImageRepository를 만들어 로그인한 사용자가 구독한 사람들의 게시글만 최신순으로 출력
    2. Ajax 통신으로 Json형태로 데이터를 가져와 구독한 유저들의 게시글 목록을 페이징 처리와 출력.

  - **댓글, 좋아요**

    1. 댓글의 userId와 현재 로그인중인 아이디가 같을 시 댓글, 좋아요를 작성,취소 할수 있게 구현

------


- #### 회원정보 수정

  <img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%ED%9A%8C%EC%9B%90%EC%A0%95%EB%B3%B4%20%EC%88%98%EC%A0%95.PNG?raw=true" width="370" /><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%ED%9A%8C%EC%9B%90%EC%A0%95%EB%B3%B4%EC%88%98%EC%A0%95%20%EC%BD%94%EB%93%9C.PNG?raw=true" width="370"/>

  - 제출 버튼 클릭 시 각 항목의 폼태그 안의 수정된 데이터들을 serialize로 받아 Ajax통신을 통해 Json 형태로 보내어 수정함  

    
 

------

- #### 로그인, 회원가입

  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%EB%A1%9C%EA%B7%B8%EC%9D%B8.PNG?raw=true"/></p>
  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0.PNG?raw=true"/></p>
  <p align="center"><img src="https://github.com/JungleSpider/Photogram/blob/master/photogram/src/main/resources/file/oauth2.PNG?raw=true"/></p>

  - **회원가입**

    1. 회원가입 폼에 있는 정보들을 DTO에 담에서 JpaRepository의 save메서드로 DB에 Insert한다.
    2. 회원가입시 필요 정보에대한 유효성 체크 완료.

  - **로그인**

    1. SpirngSecurity의 Form Login 와 oauth2Login 기능을 이용하여 기존 로컬 로그인 뿐만 아니라 페이스북을 통해서도 회원가입과 로그인이 처리되게 함 
    2. 페이스북으로 회원 가입 시 비밀번호는 UUID 클래스의 randomUUID() 메소드를 사용해 임의의 값으로 DB에 저장
   
---







