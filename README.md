# 📕 알고리즘 온라인 저지

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

![1](https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/d6b5086b-3e0a-4d7c-8d05-815641f0b357)

<br/>

# *Description*

**프로젝트 명 : 알고리즘 온라인 저지**

백준, 프로그래머스처럼 **온라인에서 알고리즘 문제를 풀고 채점받는 서비스**입니다.

제출한 코드가 서버에서 어떻게 컴파일되는지, 그 과정에 어떤 보안 취약점이 생기는지
직접 확인해보고 싶어서 만들게 되었습니다.

- 문제를 선택하고 **Java / C / Python** 중 원하는 언어로 코드를 제출할 수 있다
- 제출한 코드는 서버에서 컴파일되어 **테스트 케이스 입·출력과 대조**되고, 전부 통과해야 정답 처리된다
- 정답 시 포인트가 지급되고, 누적 포인트로 **랭킹**이 매겨진다
- 관리자는 문제와 테스트 케이스를 등록·수정할 수 있다
- 게시판과 댓글, 알림으로 풀이를 공유하고 질문할 수 있다
- 신뢰할 수 없는 코드를 실행하는 서비스이므로 **악성코드 방지 장치**를 단계별로 두었다

<br/>

👦 개인 프로젝트

🕛 프로젝트 제작 기간 : 2023/02/01 ~ 2023/05/01 (3개월)
&nbsp;&nbsp;&nbsp;&nbsp;※ 이후 커밋은 버그 수정 및 보강 작업입니다

🧑‍🤝‍🧑 참여 인원 : 1명 (개인)

🔍 사용 툴 : IntelliJ, AWS

<br/>
<br/>

# *Skills*

**FrontEnd**
- HTML5
- CSS
- jQuery
- JavaScript
- Thymeleaf

**BackEnd**
- Java 17
- Spring Boot 3.1.x
- Spring Security
- MyBatis
- MySQL

**Etc**
- AWS (RDS, EC2)
- IntelliJ

<br/>
<br/>

# *Information*

**ERD**

<br/>
<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/2cdd6e21-f92e-4e6d-b5da-ce22fce1bd94.png" width="700" height="400">
<br/>
<br/>

- 최대한 정규화를 통해 여러 이상현상들을 제어
- 인덱스에 대한 개념 확립
- 1:1, 1:N, N:1, N:N 등 여러가지 테이블 관계 표기

<br/>

프로젝트 제작과정에 쓰인 ERD로써 **ERD Cloud**를 사용하여 만들었습니다

해당 주소 : https://www.erdcloud.com/d/6Te5fmKs5DpDtHL7o

데이터베이스 테이블을 설계하면서 인덱스와 트랜잭션, Lock 등의 다양한 용어를 접하면서 공부하고

책은 데이터베이스 개론을 읽고 공부하며 블로그에 공부내용을 정리하였습니다

- **인덱스 : https://coco16.tistory.com/22**
- **Lock : https://coco16.tistory.com/27**
- **트랜잭션 : https://coco16.tistory.com/25**

<br/>

---

<br/>

**디렉터리 구조**

![directery](https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/5c27f378-59db-47c4-8be9-bfb40edf12b1)

<br/>

디렉터리 구조는 계층형 구조로써 Controller와 Service, Config, Valid와 같이 비슷한 관심사를 가진 로직들을 한곳에 모아 관리하였습니다

어느 기능이 어디에 있는지 찾을 수 있나를 주의하며 구조를 만들고 좀 더 나은 방법이 없나 찾아보고

레이어드 아키텍처 패턴에 대해서 알게되고 공부내용은 블로그에 작성하였습니다

**공부내용 : https://coco16.tistory.com/26**

<br/>

---

<br/>

**오류메세지 국제화**

<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/a50d5f65-6d87-4628-81db-1eb23922a9a8.png" width="700" height="700">

<br/>

메세지 국제화를 통해 한곳에서 관리할 수 있도록 만들고, 추후 변경사항 및 추가되었을 때 간편하게 변경 가능

<br/>

---

<br/>

**컴파일**

<img src = "https://github.com/JangHyeonChul/AlgorithmServer/assets/74286316/b8b00b9a-1c8b-44ff-a1c6-f47b13f5ece0.png" width="700" height="700">

<br/>

제출된 코드는 언어에 해당하는 컴파일러 및 인터프리터가 받아 컴파일을 수행하게 됩니다

```text
[브라우저] 코드 + 언어 + 문제번호
     │
     ▼
[웹 서버] CompileValidator ─ 길이/금지어 검증
     │
     ▼
          CompileService
              ├ JAVA   ── HTTP POST ──▶ [컴파일 서버 :8081]
              ├ C      ── 프로세스 실행
              └ PYTHON ── 프로세스 실행
     │
     ▼
  테스트 케이스 input/output 대조 (하나라도 틀리면 실패)
     │
     ▼
  채점 이력 저장 + 포인트 지급
```

<br/>

**악성코드 방지**

<br/>

잘못된 코드가 넘겨지는 걸 방지하기 위한 방지책

**1. 글자수 제한**

코드 글자수 제한으로 비정상적으로 긴 코드가 컴파일 되는 것을 방지.
또한 알고리즘 최적화를 위해 글자수를 줄이는 방식으로 좀 더 고민을 하게 만드는 부수적인 효과도 기대할 수 있음

**2. 웹 서버와 컴파일 서버 분리**

웹 서버에는 다양한 비즈니스 로직과 민감 데이터(DB, 설정정보 등)가 들어있기 때문에 해당 서버에서 컴파일은 위험하다고 판단.
컴파일 서버를 분리시켜 해당 서버에서는 오로지 컴파일만 수행하며, 그 외에 다른 기능이나 다른 정보를 일절 저장하지 않는다

**3. 특정 문자 필터링**

`exit`, `runtime` 등 알고리즘을 검증하는 데 필요 없는 Method는 필터링

**4. IP 자동 차단**

필터링 된 문자는 악의적인 요소가 있다고 판단되기 때문에 일정 횟수 초과 시 해당 계정 차단

<br/>

---

<br/>

# *Link*

**결과물 영상**

**Notion : https://www.notion.so/janghyeonchul/3771d59cbf324bc8b2cf37ce0bcccdd9**

**기획 문서 : https://JangHyeonChul.github.io/projects/algorithm-server/01-planning/**
