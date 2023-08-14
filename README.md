# 📕알고리즘 온라인 저지

<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

![1](https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/d6b5086b-3e0a-4d7c-8d05-815641f0b357)<br/>




# *Description*<br/>
**<br/>프로젝트 명 : 알고리즘 온라인 저지**




- 분포해있는 여러가지 곡들의 코멘트를 달고 평가를 내릴수있다
- 내린 평가를 바탕으로 순위를 매길수있다
- 다양한 검색조건 (종합, DLC별)으로 확인할수 있다
- 배포를 통해 유저들의 건의사항(추가사항, 변경사항 등)를 수용하고 개선한다

<br/>👦 개인 프로젝트

🕛 프로젝트 제작 기간 : 2023/02/01 ~ 2023/05/01 (3개월)

🧑‍🤝‍🧑 참여 인원 1명 (개인)

🔍 사용 툴 IntellJ, AWS

<br/>
<br/>
<br/>
<br/>

# *Skills*<br/>
**FrontEnd**
- HTML5
- CSS
- JQUERY
- JS


**BackEnd**
- Spring Boot 3.0.x<br/>
- MySQL<br/>
- Mybatis<br/>
- 타임리프<br/><br/><br/>


**Etc**
- AWS(RDS, EC2)<br/>
- IntellJ<br/><br/><br/>



# *Information*<br/>

**ERD**

<br/>
<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/2cdd6e21-f92e-4e6d-b5da-ce22fce1bd94.png" width="700" height="400">
<br/>
<br/>
<br/>


- 최대한 정규화를 통해 여러 이상현상들을 제어
- 인덱스에 대한 개념 확립
- 1:1, 1:N, N:1, N:N 등 여러가지 테이블 관계 표기
<br/>
<br/>

프로젝트 제작과정에 쓰인 ERD로써 **ERD Cloud**를 사용하여 만들었습니다

해당 주소 : https://www.erdcloud.com/d/6Te5fmKs5DpDtHL7o

데이터베이스 테이블을 설계하면서 인덱스와 트랜잭션, Lock등의 다양한 용어를 접하면서 공부하고

책은 데이터베이스 개론을 읽고 공부하며 블로그에 공부내용을 정리하였습니다

**인덱스 : https://coco16.tistory.com/22**

**Lock : https://coco16.tistory.com/27**

**트랜잭션 : https://coco16.tistory.com/25**




<br/>

---

<br/>




**디렉터리구조**

![directery](https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/5c27f378-59db-47c4-8be9-bfb40edf12b1)
<br/>
<br/>
<br/>

디렉터리구조는 계층형 구조로써 Controller와 Service, Config, Valid와 같이 비슷한 관심사를 가진 로직들을 한곳에 모아 관리하였습니다

어느 기능이 어디에있는지 찾을수있나를 주의하며 구조를 만들고 좀더 나은방법이 없나 찾아보고

레이어드 아키텍처 패턴에 대해서 알게되고 공부내용은 블로그에 작성하였습니다 

**공부내용 : https://coco16.tistory.com/26**



<br/>

---

<br/>

**오류메세지 국제화**


<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/a50d5f65-6d87-4628-81db-1eb23922a9a8.png" width="700" height="700">
<br/>
<br/>
<br/>


메세지 국제화를 통해 한곳에서 관리할수 있도록 만들고, 추후 변경사항 및 추가되었을때 간편하게 변경가능



<br/>

---

<br/>

**컴파일**

<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/b8b00b9a-1c8b-44ff-a1c6-f47b13f5ece0.png" width="700" height="700">


<br/>
<br/>
<br/>

알고리즘은 서버에서 받게되고 언어에 해당되는 컴파일러 및 인터프리터가 받아 컴파일을 수행하게된다



<br/>


**악성코드 방지**


<br/>
<br/>
<br/>

잘못된 코드가 넘겨지는걸 방지하기 위한 방지책

1. 글자수제한
   코드 글자수 제한으로 비정상적으로 긴 코드가 컴파일 되는것을 방지 또한 알고리즘 최적화를 위해 글자수를 줄이는 방식으로
   좀더 고민을 하게 만드는 부수적인 효과도 기대할수 있음

2. 웹서버와 컴파일 서버 분리
   웹서버에는 다양한 비즈니스 로직과 민감데이터(DB, 설정정보 등)이 들어있기 때문에 해당서버에서 컴파일은 위험하다고 판단
   컴파일 서버를 분리시켜 해당서버에서는 오로지 컴파일만 수행하며
   그외에 다른기능이나 다른정보를 일절 저장하지않는다

3. 특정문자 필터링
   exit, runtime 등 알고리즘 검증하는 요소에 있어서 필요없는 Method는 필터링

3-1. IP 자동차단
   필터링 된 문자는 악의적인 요소가 있다고 판단되기 때문에 일정횟수 초과시 해당계정 차단



<br/>

---

<br/>


# *Link*<br/>

**결과물 영상**
**Notion : https://www.notion.so/janghyeonchul/3771d59cbf324bc8b2cf37ce0bcccdd9**
