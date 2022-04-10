# RC 4기 Rising Test, Idus_A 
데이터저장소 : [firebase](gs://idus-80a9a.appspot.com/) <br>
JDBC template -> MyBatis
* mybatis의 특징
  * 쉬운 접근성과 코드의 간결함
    * JDBC의 모든 기능을 Mybatis가 대부분 제공한다.
    * 복잡한 JDBC코드를 걷어내며 깔끔한 소스코드를 유지할 수 있다.
    * 수동적인 파라미터 설정과 쿼리 결과에 대한 맵핑 구문을 제거할 수 있다.
  * SQL문과 프로그래밍 코드의 분리
    * SQL에 변경이 있을 때마다 자바 코드를 수정하거나 컴파일하지 않아도 된다.
  * 다양한 프로그래밍 언어로 구현가능
    Java, C#, .NET, Ruby
[출처](https://byul91oh.tistory.com/232)
  
## 2022-03-19 진행상황(1일차)
* 로컬 서버 구축
* git 연동
* 데이터베이스에 새로운 스키마 추가(idusDB)
  * Datagrip과 연동
* ERD 설계

## 2022-03-20 진행상황(2일차)
* ERD 설계 완료
  ![](https://user-images.githubusercontent.com/77392219/159211206-0745dabf-7e12-45d9-ad7d-640721967318.png)
  * [AqueryTool 링크 (읽기전용)](https://aquerytool.com/aquerymain/index/?rurl=61c36d0c-50c8-4842-a357-d41aff6251e7&)
  * Password : 54dqy6
* Datagrip에 쿼리문 작성
  * Dummy data 입력
* local/prod server(AWS EC2)에서 api 호출 확인
* user domain의 우선순위 api들 구축 (100%)
  * user join 
  * login
  * 휴대폰인증
    * naver cloud platform의 sens api를 처음으로 사용해 보았다.
    * [sens 가이드](https://api.ncloud-docs.com/docs/ai-application-service-sens-smsv2#%EB%A9%94%EC%8B%9C%EC%A7%80%EB%B0%9C%EC%86%A1)
  * kakao login api 구축
* api 명세서 작성

## 2022-03-21 진행상황(3일차)
* api list up
  ![](https://user-images.githubusercontent.com/77392219/159756088-0bd30417-84f9-4f9b-a9e1-f916017b066e.png)
  * [구글 스프레드 시트 링크 (읽기전용)](https://docs.google.com/spreadsheets/d/1AMx5SBhu-A5ioxLCpQpxCRIaPJfoBZN-5JOt1nCq71w/edit?usp=sharing)
  * 먼저 domain 별로 개발할 모든 api를 나열하고 우선순위에 따라 파랑: 우선순위 높음, 하양: 우선순위 중간, 회색: 우선순위 낮음으로 정리
* user domain의 우선순위 api들 구축 (100%)
  * 유저 정보 조회 api
  * 유저 삭제 api
  * 유저 로그아웃 api
* category domain api 구축 (100%)
  * 카테고리 조회 api
* api 명세서 업데이트

## 2022-03-22 진행상황(4일차)
* product domain의 우선순위 api들 구축 (100%)
  * 투데이 탭 조회 api
  * 실시간 탭 조회 api
  * new 탭 조회 api
* 임의의 dummy data 생성 (maker, product)

## 2022-03-23 진행상황(5일차)
* product domain의 우선순위 api들 구축(100%)
  * 카테고리 작품 조회 api
  * 검색 작품 조회 api
  * 작품 상세 조회 api
* front로 부터 받은 image와 어플 정보에 따라 dummy data 입력 (product, maker, category) (100%)
* 1차 피드백 진행
  * DB
    * DB 설계는 프론트엔드 개발자와 상의하면서 설계 
    * data type을 더욱 신중하게 선택 -> 이유없이 고르지 말 것
      * Y or N 인 값들은 boolean으로 처리
      * 각 테이블 id 이름은 id로 통일
      * double은 decimal로 처리 
      * 컬럼이 많이 생기는 테이블은 int unsigned 에서 BIGINT로 변경
    * 보통 camelCase 보단 snake_case로 컬렴명을 생성 (다음부터)
    * Enum 처리
  * api 명세서
    * 발생할 수 있는 exception 상상해서 설계 후 error status, message 작성
  * 질문
    * 깃에서 commit & pr을 이 정도로 빈번하게 사용하나 -> commit 단위는 잘게 쪼갤수록 좋음
    * 하나의 view에서 api를 여러개 호출하는 것 vs api 하나에 모든 정보를 담는 것?
      -> 프론트엔드 개발자와 보통 상의, api 하나에 모든 정보를 담는 것을 선호함
```
  mysql 은 boolean을 tinyint(1)로 받고 데이터에 1, 0을 저장함.
  검색 결과 char(1) 와 tinyint(1)의 차이가 거의 없고 enum 타입이 가장 빨랐음
  따라서 boolean으로 바꾸는 작업은 하지 않기로 결정
 ```
참고 : [stackOverFlow](https://stackoverflow.com/questions/2023476/which-is-faster-char1-or-tinyint1-why) <br>
참고 : [mysql ENUM을 사용하지 말아야할 8가지 이유](https://velog.io/@leejh3224/%EB%B2%88%EC%97%AD-MySQL%EC%9D%98-ENUM-%ED%83%80%EC%9E%85%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-8%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0)
    
## 2022-03-24 진행상황(6일차)
* ec2,rds 보안그룹 변경
  * ssh를 나의 ip에서만 접속할 수 있도록 변경
  * rds의 3306 포트를 내 ip와 ec2의 보안그룹이 접속할 수 있도록 인바운드 규칙을 변경
  * aws db brute force 해킹방지, 위치 이동 시에는 번거롭지만 인바운드 규칙을 재설정하기로 함
* cart domain의 우선순위 api 구축(100%)
  * 장바구니 작품 담기 api
  * 장바구니 조회 api
* req validation exception의 상태코드를 4500 -> 2500 으로 변경
* secret 파일 수정
  * 모든 commit 에서 secret을 삭제
  * .gitignore 추가
  * 기본적인 실수를 범하고 말았다. .gitignore에 포함되어 있기에 github에 secret 파일이 올려지지 않은 줄 알았다. 하지만 내 착각이었고 git rm -r --cached .을 이용해 github에 있는 .gitignore파일들을 삭제 할 수 있었다.
* null값 수정
  * 결과값에 null값을 포함해서 나오면 데이터를 다루기 어렵기 때문에 데이터베이스에 애초에 null을 잘 저장하지 않는 것이 좋다. 
  * 따라서 IFNULL 문법을 통해 query문들을 수정했고 database의 dafault 값들을 null에서 ''으로 바꾸는 작업을 하였다.
* 1차 위클리 스크럼
![image](https://user-images.githubusercontent.com/77392219/161232232-99a7acaf-45df-42bf-9c2e-13e180e6a7ca.png)
  * UI와 user domain api 연동확인
  * 느낀점: 처음으로 내가 만든 api가 사용되는 것을 확인하여서 감명깊었음

## 2022-03-25 진행상황(7일차)
* order domain의 우선순위 api 구축(100%)
  * 주문하기 api
  * 유저 주문 조회 api
  * 주문 상세 조회 api
* 장바구니가 없는 상태에서 장바구니를 조회할 때 결과값 수정
  * 프론트와 상의해서 결과를 냄 -> 빈 object를 return
* 첫구매 할인 상품 데이터 삽입 (product)

## 2022-03-26 진행상황(8일차)
* review domain의 우선순위 api 구축(100%)
  * 유저 구매후기 조회 api
  * 구매후기 작성 api
* issue: review를 한 번만 작성할 수 있다는 생각에 주문내역과 review 테이블을 연결하였는데 그러다보니 review에서 작성한 product 정보를 따로 가져올 수 가 없었다 
  * resolve: review 테이블을 smallCart와 연결하여 각 smallCart에 review를 달고 review를 달면 smallCart status 값을 'R'로 변경하도록 로직을 짰고, 가져올때는 'R'인 값만 가져올 수 있도록 하였다.
* productOption result 값 수정
  * front 분이 title별로 데이터 값을 나눠 달라고 요청하였기 때문에 수정

## 2022-03-27 진행상황(9일차)
* api 명세서 수정
* postman 정리
* 데이터 작업 (최대한 실제데이터와 같도록 database의 데이터들을 채워넣음)

## 2022-03-28 진행상황(10일차)
* user domain의 중간순위 api 구축(100%)
  * 등급 조회 api
  * 유저 주소 조회 api (배송지 관리)
  * 유저 쿠폰 조회 api
  * 유저 찜 박품 목록 조회 api
  * 유저 팔로우 작가 목록 조회 api
* 2차 위클리 스크럼
![image](https://user-images.githubusercontent.com/77392219/161231952-be2723bb-5d05-4a7c-85cd-1af71186bec8.png)
  * product domain api 연동 확인
  * 1차 피드백과 계획 공유
  * 느낀점: 굉장히 배려를 많이 해주시고 소통이 잘되어서 원활하게 개발할 수 있었음

## 2022-03-29 진행상황(11일차)
* product domain의 모든 api 구축(100%)
  * 첫 구매 작품 조회 api
  * 내가 본 작품의 연관 작품 조회 api
  * 유저가 좋아할만한 작품 조회 api
  * 오늘의 작품 더보기 조회 api
  * 작품 찜 api

## 2022-03-30 진행상황(12일차)
* 2차 피드백
  * tinyint(1)과 char(1) 둘 중 하나를 고를 때 근거가 부족
  * JDBC template vs myBatis vs jpa + 근거
  * 코드리뷰 
    * 자바스럽게 메소드를 잘게 쪼개자
    * setter 쓰지 말고 생성자 overloading
    * 반복되는 코드는 interceptor 나 filter, AOP..
    * 책 추천
      * 이펙티브 자바, 클린코드
    * s3 추천
* cart domain 모든 api 구축(100%)
  * 장바구니 수정 api
    * 수량을 바꾸면 장바구니의 전체 가격이 수정되도록 로직 추가
  * 장바구니 삭제 api
* review domain 모든 api 구축(100%)
  * 구매후기 수정 api
  * 구매후기 삭제 api

## 2022-03-31 진행상황(13일차)
* user domain 모든 api 구축(100%)
  * 유저 적립금 이용 내역 조회 api
  * 유저 선물 조회 api
* 주문하기의 req 값으로 isGift를 추가하여 따로 선물하기라는 api를 만들지 않고 주문에 포함시켰다 -> api 인덱스 변경
* product domain 모든 api 구축(100%)
  * 작품 쿠폰 조회 api
  * 작품 쿠폰 받기 api
  * 구매후기 더보기 api
  * 이전 댓글 더보기 api

## 2022-04-01 진행상황(14일차)
* 모든 api 구축
* category 작품들 필터링 구현
  * deliveryTip
  * price
  * discountRate
  * order by
```
myBatis의 <if> 문법으로 필터가 전부 선택되지 않아도 필터링 되도록 처리 할 수 있었다. 
```
* 제출
  * https://drive.google.com/file/d/1HRzNq9OVmKyOZ5VeeWCmxL38OgMtTnSr/view?usp=sharing

## 이후 계획
코드 리팩토링
1. 런타임 에러를 제거
2. java enum을 사용
3. Jackson 어노테이션을 이용해 entity 수정
4. tiny int (1)로 변경
5. 메소드 분할
6. 인터셉터