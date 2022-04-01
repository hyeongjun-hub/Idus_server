package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.entity.KaKaoUser;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.PatchAddressRes;
import com.example.demo.src.user.model.response.PostLoginRes;
import com.example.demo.src.user.model.response.PostUserDelRes;
import com.example.demo.src.user.model.response.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.sound.midi.Patch;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
        if (this.checkEmail(postUserReq.getEmail()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        String pwd;
        try {
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try {
            postUserReq.setPlatform("inApp");
            userMapper.createUser(postUserReq);
            int userId = postUserReq.getUserId();
            // 빈 주소 3개 생성
            int addressId = 0;
            for (int i = 0; i < 3; i++) {
                PostAddressReq postAddressReq = new PostAddressReq(userId, 0);
                if (i == 0) {
                    addressId = this.createAddress(postAddressReq);
                } else {
                    this.createAddress(postAddressReq);
                }
            }
            userMapper.setAddressInfo(addressId, postUserReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(userId, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserDelRes editUser(int userId, PatchUserReq user) throws BaseException {
        // status 값 확인
        if (!userMapper.getUserStatus(userId).equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        // 이름 변경일 때
        if (user.getUserName() != null) {
            editUserName(userId, user);
        }
        // 이메일 변경일 때
        if (user.getEmail() != null) {
            editEmail(userId, user);
        }
        // 전화번호 변경일 때
        if (user.getPhone() != null) {
            editPhone(userId, user);
        }
        // 프로필 이미지 변경일 때
        if (user.getProfileImageUrl() != null) {
            editProfileImageUrl(userId, user);
        }
        // 생일 변경일 때
        if (user.getBirthday() != null) {
            editBirthday(userId, user);
        }
        // 성별 변경일 때
        if (user.getGender() != null) {
            editGender(userId, user);
        }
        // 생체인식 변경일 때
        if (user.getFingerPrint() != null) {
            editFingerPrint(userId, user);
        }
        // 알람설정 변경일 때
        if (user.getAlarm() != null) {
            editAlarm(userId, user);
        }
        // 생체인증 변경일 때
        if (user.getFingerPrint() != null) {
            editFingerPrint(userId, user);
        }
        try {
            return new PostUserDelRes(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editEmail(int userId, PatchUserReq user) throws BaseException {
        //이메일 중복확인
        if (this.checkEmail(user.getEmail()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        int result = userMapper.editEmail(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editUserName(int userId, PatchUserReq user) throws BaseException {
        int result = userMapper.editUserName(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editProfileImageUrl(int userId, PatchUserReq user) throws BaseException {
        int result = userMapper.editProfileImageUrl(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editBirthday(int userId, PatchUserReq user) throws BaseException {

        int result = userMapper.editBirthday(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }

    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editGender(int userId, PatchUserReq user) throws BaseException {
        int result = userMapper.editGender(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editFingerPrint(int userId, PatchUserReq user) throws BaseException {
        int result = userMapper.editFingerPrint(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editAlarm(int userId, PatchUserReq user) throws BaseException {
        int result = userMapper.editAlarm(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void editPhone(int userId, PatchUserReq user) throws BaseException {
        if (this.checkPhone(user.getPhone()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_PHONE);
        }
        int result = userMapper.editPhone(userId, user);
        if (result == 0) {
            throw new BaseException(EDIT_FAIL_CONTENT);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void delUser(PostUserDelReq postUserDelReq) throws BaseException {
        try {
            userMapper.delUser(postUserDelReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkPhone(String phone) throws BaseException {
        try {
            return userMapper.checkPhone(phone);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException {
        try {
            return userMapper.checkEmail(email);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostUserRes login(PostLoginReq postLoginReq) throws BaseException {
        User user = userMapper.getLoginUser(postLoginReq);
        //이메일 존재여부 확인
        if (this.checkEmail(postLoginReq.getEmail()) != 1) {
            throw new BaseException(POST_USERS_NOT_EXISTS_EMAIL);
        }
        //status 값 확인
        if (!user.getStatus().equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        String encryptPwd;
        try {
            encryptPwd = new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
        //암호화한 비밀번호가 동일한지 확인
        if (user.getPassword().equals(encryptPwd)) {
            int userId = user.getUserId();
            String jwt = jwtService.createJwt(userId);
            //isLogin 값 "Y"로 변경
            userMapper.updateIsLogin(userId);
            return new PostUserRes(userId, jwt);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PostLoginRes kaKaoLogin(KaKaoUser kaKaoUser) throws BaseException {
        int userId;
        String jwt;
//        System.out.println("kaKaoUser = " + kaKaoUser.getEmail());
//        카카오에서 받아온 사용자 정보의 이메일을 가지고 User 테이블에 있는지 확인한다.
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
            PostUserReq kaKaoSignUp = new PostUserReq(kaKaoUser.getUserName(), kaKaoUser.getEmail(), "socialLogin", "", "kaKao", 0);
            userMapper.createUser(kaKaoSignUp); // + KaKao
            userId = kaKaoSignUp.getUserId();
            int addressId = 0;
            // 빈 주소 3개 생성
            for (int i = 0; i < 3; i++) {
                PostAddressReq postAddressReq = new PostAddressReq(userId, 0);
                if (i == 0) {
                    addressId = this.createAddress(postAddressReq);
                } else {
                    this.createAddress(postAddressReq);
                }
            }
            userMapper.setAddressInfo(addressId, kaKaoSignUp);
            // jwt 발급
            jwt = jwtService.createJwt(userId);
        }
        try {
            return new PostLoginRes(userId, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public int createAddress(PostAddressReq postAddressReq) throws BaseException {
        try {
            int result = userMapper.createAddress(postAddressReq);
            if (result == 0) {
                throw new BaseException(CREATE_FAIL_ADDRESS);
            }
            return postAddressReq.getAddressId();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public PatchAddressRes editAddress(PatchAddressReq patchAddressReq) throws BaseException {
        try {
            int result = 0;
            if (patchAddressReq.getAddress() != null) {
                result = userMapper.editAddress(patchAddressReq);
                if (result == 0) {
                    throw new BaseException(EDIT_FAIL_CONTENT);
                }
            }
            if (patchAddressReq.getPhone() != null) {
                result = userMapper.editAddressPhone(patchAddressReq);
                if (result == 0) {
                    throw new BaseException(EDIT_FAIL_CONTENT);
                }
            }
            if (patchAddressReq.getTaker() != null) {
                result = userMapper.editTaker(patchAddressReq);
                if (result == 0) {
                    throw new BaseException(EDIT_FAIL_CONTENT);
                }
            }
            return new PatchAddressRes(patchAddressReq.getAddressId());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void logoutUser(int userId) throws BaseException {
        try {
            userMapper.logout(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
