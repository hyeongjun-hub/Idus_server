# RC 4기 Rising Test, Idus_a 
  
## 2022-03-19 진행상황
* 로컬 서버 구축
* git 연동
* 데이터베이스에 새로운 스키마 추가(idusDB)
  * Datagrip과 연동
* ERD 설계

## 2022-03-20 진행상황
* ERD 설계 완료
  ![](https://user-images.githubusercontent.com/77392219/159211206-0745dabf-7e12-45d9-ad7d-640721967318.png)
  * AqueryTool 링크 (읽기전용) : https://aquerytool.com/aquerymain/index/?rurl=1c24d01e-e735-4d99-b8e7-8908b536e7d9
  * 비밀번호 : 2q5q2u
* Datagrip에 쿼리문 작성
  * Dummy data 입력
* local/prod server에서 api 호출 확인
* user join, login, 휴대폰인증, kakao login api 구축
* api 명세서 작성

## 2022-03-21 진행상황
* api list up
* user domain의 우선순위 api들 구축 (0%)
  * 유저 정보 조회 api
  * 유저 정보 상세 조회 api
  * 유저 정보 수정 api
  * 유저 삭제 api
  * 유저 로그아웃 api
* category doamin api 구축 (0%)
  * 카테고리 조회 api
* products domain의 우선순위 api들 구축 (0%)
  * 투데이 탭 조회 api
  * 실시간 탭 조회 api
  * new 탭 조회 api
