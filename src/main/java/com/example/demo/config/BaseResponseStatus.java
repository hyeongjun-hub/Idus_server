package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : 클라이언트 Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 이메일 값을 확인해주세요."),
    USERS_STATUS_NOT_Y(false,2011, "삭제된 유저입이니다."),
    AUTH_KAKAO_EMPTY_TOKEN(false, 2012, "유효하지않은 카카오 토큰입니다,"),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_PASSWORD_MIN(false, 2018, "비밀번호를 8자 이상 입력하세요"),
    POST_USERS_NOT_EXISTS_EMAIL(false, 2019, "없는 이메일입니다. 회원가입이 필요합니다."),
    POST_USERS_EXISTS_PHONE(false, 2021, "중복된 전화번호입니다."),

    // [POST] /users/auth/phone
    FAILED_TO_SEND_PHONE_AUTH(false, 2020, "인증번호 전송에 실패하였습니다."),

    // [POST] /reviews
    POST_REVIEWS_EMPTY_CONTENT(false, 2030, "리뷰 내용을 입력해주세요."),

    // address
    POST_ADDRESS_STATUS_NOT_Y(false, 2040, "삭제된 주소입니다."),
    POST_ADDRESS_EXISTS_ADDRESS(false,2041,"중복된 주소입니다."),
    POST_ADDRESS_EXISTS_ADDRESS_NAME(false,2042,"중복된 주소이름입니다."),

    // [Post] /carts
    POST_CART_STATUS_NOT_Y(false, 2050, "유효하지 않은 장바구니입니다."),
    POST_SMALL_CART_STATUS_NOT_Y(false, 2051, "유효하지 않은 작은 장바구니입니다."),

    // Common
    VALIDATION_ERROR(false, 2500, "잘못된 형식입니다."),

    /**
     * 3000 : 클라이언트 Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3500, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),
    USERS_INAPP_EXISTS(false, 3015, "어플로 가입한 정보가 있습니다."),
    FAILED_TO_KAKAO_AUTH(false, 3019, "카카오 유저 정보 조회에 실패하였습니다."),
    FAILED_TO_KAKAO_EMAIL(false, 3020, "카카오 정보에 등록된 이메일이 없습니다. 이메일을 추가 입력해주세요."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[POST] /users
    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),
    CREATE_FAIL_ADDRESS(false, 4013, "회원 주소 생성에 실패하였습니다."),

    //[PATCH] /users
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정에 실패하였습니다."),
    EDIT_FAIL_CONTENT(false, 4015, "유저정보 수정에 실패하였습니다."),

    //[GET] /products
    CREATE_FAIL_VIEW(false, 4016, "조회 테이블 추가에 실패하였습니다."),
    UPDATE_FAIL_USER_RESENT(false, 4017, "유저 최근 작품 업데이트에 실패하였습니다."),

    //[POST] /carts
    GET_CART_NO(false, 4020, "유효한 장바구니가 없습니다."),
    CREATE_FAIL_DELIVERY_TIP(false, 4022, "장바구니 배송비 업데이트에 실패하였습니다."),
    CREATE_FAIL_CART(false, 4020, "장바구니 생성에 실패하였습니다."),
    UPDATE_FAIL_PRICE(false, 4021, "장바구니 가격 업데이트에 실패하였습니다."),

    //[POST] /orders
    UPDATE_FAIL_CART_STATUS(false, 4030, "장바구니 상태 변경에 실패하였습니다."),
    UPDATE_FAIL_SMALL_CART_STATUS(false, 4031, "작은 장바구니 상태 변경에 실패하였습니다."),
    UPDATE_FAIL_COUPON_STATUS(false, 4032, "쿠폰 상태 변경에 실패하였습니다."),
    UPDATE_FAIL_USER_POINT(false, 4033, "유저 적립금 업데이트에 실패하였습니다."),
    UPDATE_FAIL_PRODUCT_ORDER_COUNT(false, 4034, "작품 주문 횟수 업데이트에 실패하였습니다."),
    CREATE_FAIL_SUPPORT(false, 4035, "후원 내역 생성에 실패하였습니다."),

    //[POST] /reviews
    CREATE_FAIL_REVIEW(false, 4040, "구매후기 생성에 실패하였습니다."),
    INVALID_SMALL_CART(false, 4041, "구매한 유저만 후기 작성가능합니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
