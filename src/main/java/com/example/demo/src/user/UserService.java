package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.entity.KaKaoUser;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.PostLoginRes;
import com.example.demo.src.user.model.response.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;
    private final JwtService jwtService;


    @Autowired
    public UserService(JwtService jwtService, UserMapper userMapper) {
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
        if(this.checkEmail(postUserReq.getEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            postUserReq.setPlatform("inApp");
            userMapper.createUser(postUserReq);
            int userId = postUserReq.getUserId();
            // 빈 주소 3개 생성
            for(int i=0; i<3; i++){this.createAddress(userId);}
            //jwt 발급.
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(userId, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserRes editUser(int userId, User user) throws BaseException {
        //status 값 확인
        if (!userMapper.getUserStatus(userId).equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        //이메일 중복확인
        if(this.checkEmail(user.getEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(user.getPassword());
            user.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int result = userMapper.editUser(userId, user);
            if(result == 0){
                throw new BaseException(EDIT_FAIL_CONTENT);
            }
            //jwt 발급.
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(userId, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void delUser(PostUserDelReq postUserDelReq) throws BaseException {
        try{
            userMapper.delUser(postUserDelReq);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public int checkEmail(String email) throws BaseException{
        try{
            return userMapper.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkAddress(String address) throws BaseException {
        try {
            return userMapper.checkAddress(address);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkAddressName(String addressName) throws BaseException {
        try {
            return userMapper.checkAddressName(addressName);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserRes login(PostLoginReq postLoginReq) throws BaseException{
        User user = userMapper.getLoginUser(postLoginReq);
        //이메일 존재여부 확인
        if(this.checkEmail(postLoginReq.getEmail()) != 1){
            throw new BaseException(POST_USERS_NOT_EXISTS_EMAIL);
        }
        //status 값 확인
        if (!user.getStatus().equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        String encryptPwd;
        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
        //암호화한 비밀번호가 동일한지 확인
        if(user.getPassword().equals(encryptPwd)){
            int userId = user.getUserId();
            String jwt = jwtService.createJwt(userId);
            //isLogin 값 "Y"로 변경
            userMapper.updateIsLogin(userId);
            return new PostUserRes(userId,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostLoginRes kaKaoLogin(KaKaoUser kaKaoUser) throws BaseException {
        int userId;
        String jwt;
        System.out.println("kaKaoUser = " + kaKaoUser.getEmail());
//        // 카카오에서 받아온 사용자 정보의 이메일을 가지고 User테이블에 있는지 확인한다.
        if (userMapper.checkEmail(kaKaoUser.getEmail()) == 1) {
//            // 해당 이메일이 카카오 가입으로 가입된 계정이 맞는지 확인한다.
            if (userMapper.getPlatform(kaKaoUser.getEmail()).equals("kaKao")) {
//                //카카오 가입 이메일이 맞다면 로그인 처리
                userId = userMapper.getUserIdByEmail(kaKaoUser.getEmail());
                jwt = jwtService.createJwt(userId);
            } else {
                throw new BaseException(USERS_INAPP_EXISTS); // 해당 이메일로 자체 이메일가입한 상태라면 카카오로그인, 가입 X, 자체로그인으로.
            }
        } else { // 가입이 되어 있지 않다면 가입 진행
            PostUserReq kaKaoSignUp = new PostUserReq(kaKaoUser.getUserName(), kaKaoUser.getEmail(),"socialLogin", null, "kaKao" ,"N", 0);
            userMapper.createUser(kaKaoSignUp); // + KaKao
            userId = kaKaoSignUp.getUserId();
            // todo: 빈 주소 3개 생성
            // 빈 주소 3개 생성
            for(int i=0; i<3; i++){this.createAddress(userId);}
            // jwt 발급
            jwt = jwtService.createJwt(userId);
        }
        return new PostLoginRes(userId, jwt);
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void createAddress(int userId) throws BaseException {
        try{
            userMapper.createAddress(userId);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getUserId(int addressId) throws BaseException {
        try{
            int userId = userMapper.getUserId(addressId);
            return userId;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editAddress(int addressId, PatchAddressReq patchAddressReq) throws BaseException {
        String addressStatus = userMapper.getAddressStatus(addressId);
        //address status 값 확인
        if (!addressStatus.equals("Y")) {
            throw new BaseException(POST_ADDRESS_STATUS_NOT_Y);
        }
        //address 중복 확인
        if(this.checkAddress(patchAddressReq.getAddress()) == 1){
            throw new BaseException(POST_ADDRESS_EXISTS_ADDRESS);
        }
        // 주소이름 중복 확인
        if(this.checkAddressName(patchAddressReq.getAddressName()) == 1){
            throw new BaseException(POST_ADDRESS_EXISTS_ADDRESS_NAME);
        }
        try{
            userMapper.editAddress(addressId, patchAddressReq);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void delAddress(int addressId) throws BaseException {
        String addressStatus = userMapper.getAddressStatus(addressId);
        //address status 값 확인
        if (!addressStatus.equals("Y")) {
            throw new BaseException(POST_ADDRESS_STATUS_NOT_Y);
        }
        try{
            userMapper.delAddress(addressId);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void logoutUser(int userId) throws BaseException {
        try{
            userMapper.logout(userId);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
