# RC 4기 Rising Test, Idus_a 
  
## 2022-03-19 진행상황(1일차)
* 로컬 서버 구축
* git 연동
* 데이터베이스에 새로운 스키마 추가(idusDB)
  * Datagrip과 연동
* ERD 설계

## 2022-03-20 진행상황(2일차)
* ERD 설계 완료
  ![](https://user-images.githubusercontent.com/77392219/159211206-0745dabf-7e12-45d9-ad7d-640721967318.png)
  * AqueryTool 링크 (읽기전용) : URL : https://aquerytool.com/aquerymain/index/?rurl=61c36d0c-50c8-4842-a357-d41aff6251e7&
  * Password : 54dqy6
* Datagrip에 쿼리문 작성
  * Dummy data 입력
* local/prod server에서 api 호출 확인
* user join, login, 휴대폰인증, kakao login api 구축
* api 명세서 작성

## 2022-03-21 진행상황(3일차)
* api list up
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
* product domain의 우선순위 api들 구축(60%)
  * 카테고리 작품 조회 api
  * 검색 작품 조회 api
  * 작품 상세 조회 api
* cart domain의 우선순위 api 구축(0%)
  * 장바구니 작품 담기 api
  * 장바구니 조회 api
* front로 부터 받은 image와 어플 정보에 따라 dummy data 입력 (product) (100%)
