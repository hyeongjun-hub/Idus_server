package com.example.demo.src.user;

import com.example.demo.src.user.model.entity.KaKaoUser;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.*;
import com.example.demo.utils.KaKaoLoginService;
import com.example.demo.utils.SmsAuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;
    private final SmsAuthService smsAuthService;

    /**
     * 1. 전체 유저 조회 API
     * 회원 번호 및 이메일 검색 조회 API
     * @return BaseResponse<List < GetUserRes>>
     */
    @GetMapping("/all") // (GET) 127.0.0.1:9000/users/all
    public BaseResponse<List<User>> getUsers() throws BaseException {
        List<User> getUsersRes = userProvider.getUsers();
        return new BaseResponse<>(getUsersRes);
    }

    /**
     * 2. inApp 유저 생성 API (회원가입)
     * @param postUserReq
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @PostMapping("/join")  // (POST) 127.0.0.1:9000/users
    public BaseResponse<PostUserRes> join(@Valid @RequestBody PostUserReq postUserReq) throws BaseException {
        PostUserRes postUserRes = userService.createUser(postUserReq);
        return new BaseResponse<>(postUserRes);
    }

    /**
     * 3. 휴대폰 인증번호 발송 API
     * @param postPhoneAuthReq
     * @return
     */
    @ResponseBody
    @PostMapping("/auth/phone")
    public BaseResponse<PostPhoneAuthRes> sendMessage(@Valid @RequestBody PostPhoneAuthReq postPhoneAuthReq) throws BaseException {
        PostPhoneAuthRes postPhoneAuthRes = smsAuthService.sendPhoneAuth(postPhoneAuthReq.getPhone()); // 문자 발송
        return new BaseResponse<>(new PostPhoneAuthRes(postPhoneAuthRes.getPhone(), postPhoneAuthRes.getAuthNumber()));
    }

    /**
     * 4. 이메일 로그인 API
     * @param postLoginReq
     * @return BaseResponse<PostLoginRes>
     */
    @PostMapping("/login")  // (POST) 127.0.0.1:9000/users/login
    public BaseResponse<PostUserRes> login(@Valid @RequestBody PostLoginReq postLoginReq) throws BaseException {
        PostUserRes postUserRes = userService.login(postLoginReq);
        return new BaseResponse<>(postUserRes);
    }

    /**
     * 5. 카카오 로그인, 회원가입 API
     * @param postKaKaoLogin
     * @return BaseResponse<String>>
     */
    @PostMapping("/login/kakao")
    public BaseResponse<PostLoginRes> kaKaoLogin(@RequestBody PostKaKaoLoginReq postKaKaoLogin) throws BaseException {
        if (postKaKaoLogin.getAccessToken() == null || postKaKaoLogin.getAccessToken().isEmpty()) {
            return new BaseResponse<>(AUTH_KAKAO_EMPTY_TOKEN);
        }
        // 액세스 토큰으로 사용자 정보 받아온다.
        KaKaoUser kaKaoUser = KaKaoLoginService.getKaKaoUser(postKaKaoLogin.getAccessToken());
        // 로그인 처리 or 회원가입 진행 후 jwt, userIdx 반환
        PostLoginRes postLoginRes = userService.kaKaoLogin(kaKaoUser);
        return new BaseResponse<>(postLoginRes);
    }

    /**
     * 6. 유저 정보 조회 API
     * @return BaseResponse<GetUserRes>
     */
    @GetMapping("") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetUserRes> getUser() throws BaseException {
        int userId = jwtService.getUserId();
        GetUserRes getUserRes = userProvider.getUser(userId);
        return new BaseResponse<>(getUserRes);
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/detail
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/detail")  // (GET) 127.0.0.1:9000/users/detail
    public BaseResponse<PostUserRes> editUser(@Valid @RequestBody User user) throws BaseException {
        //jwt에서 id 추출.
        int userId = jwtService.getUserId();
        PostUserRes patchUserRes = userService.editUser(userId, user);
        return new BaseResponse<>(patchUserRes);
    }

    /**
     * 회원 삭제 API
     * @return BaseResponse<List < GetUserRes>>
     */
    @PatchMapping("/delete")
    public BaseResponse<String> delUser() throws BaseException {
        //jwt에서 idx 추출.
        int userId = jwtService.getUserId();

        // 상태값 D로 변경
        PostUserDelReq postUserDelReq = new PostUserDelReq(userId);
        userService.delUser(postUserDelReq);

        String result = "";
        return new BaseResponse<>(result);
    }

    /**
     * 회원 포인트조회 API
     * @return BaseResponse<Integer>
     */
    @GetMapping("/point")
    public BaseResponse<Integer> getPoint() throws BaseException {
        int userId = jwtService.getUserId();
        int point = userProvider.getPoint(userId);
        return new BaseResponse<>(point);
    }

    /**
     * 회원 쿠폰조회 API
     * @return BaseResponse<List < GetCouponRes>>
     */
    @GetMapping("/coupon")
    public BaseResponse<List<GetCouponRes>> getCoupons() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetCouponRes> getCouponRes = userProvider.getCoupons(userId);
        return new BaseResponse<>(getCouponRes);
    }

    /**
     * 회원 선물조회 API
     * @return BaseResponse<List < GetPresentRes>>
     */
    @GetMapping("/present")
    public BaseResponse<List<GetPresentRes>> getPresents() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetPresentRes> getPresentRes = userProvider.getPresents(userId);
        return new BaseResponse<>(getPresentRes);
    }

    /**
     * 회원 주소 조회 API
     *
     * @return BaseResponse<List<GetAddressRes>>
     */
    @GetMapping("/address")
    public BaseResponse<List<GetAddressRes>> getAddress() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetAddressRes> getAddressRes = userProvider.getAddress(userId);
        return new BaseResponse<>(getAddressRes);
    }

    /**
     * 회원 주소 수정 API
     * @param addressId
     * @param patchAddressReq
     * @return BaseResponse<String>
     */
    @PatchMapping("/{addressId}/address")
    public BaseResponse<String> editAddress(@PathVariable("addressId") int addressId, @RequestBody PatchAddressReq patchAddressReq) throws BaseException {
        if (jwtService.getUserId() != userService.getUserId(addressId)) {
            return new BaseResponse<>(INVALID_USER_JWT);
        }
        userService.editAddress(addressId, patchAddressReq);
        return new BaseResponse<>("");
    }

    /**
     * 회원 주소 삭제 API
     * @param addressId
     * @return BaseResponse<String>
     */
    @PatchMapping("/{addressId}/address/delete")
    public BaseResponse<String> delAddress(@PathVariable("addressId") int addressId) throws BaseException {
        if (jwtService.getUserId() != userService.getUserId(addressId)) {
            return new BaseResponse<>(INVALID_USER_JWT);
        }
        userService.delAddress(addressId);
        return new BaseResponse<>("");
    }

    @PostMapping("/logout-user")
    public BaseResponse<String> logoutUser() throws BaseException {
        int userId = jwtService.getUserId();
        userService.logoutUser(userId);
//        String token = jwtService.getJwt();
//        if(jwtService.isTokenValid(token)){
//            Date expirationDate = jwtService.getExpirationDate(token);
//            redisTemplate.opsForValue().set(
//                    Constant.REDIS_PREFIX + token, "l",
//                    expirationDate.getTime() - System.currentTimeMillis(),
//                    TimeUnit.MILLISECONDS
//            );
//            logger.info("redis value : " + redisTemplate.opsForValue().get(Constant.REDIS_PREFIX + token));
//        }
        return new BaseResponse<>("");
    }
}
