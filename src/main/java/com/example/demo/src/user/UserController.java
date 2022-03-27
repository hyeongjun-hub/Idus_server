package com.example.demo.src.user;

import com.example.demo.src.user.model.entity.Address;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;
    private final SmsAuthService smsAuthService;

    /**
     * 0. 전체 유저 조회 API
     * 회원 번호 및 이메일 검색 조회 API
     * @return BaseResponse<List < GetUserRes>>
     */
    @GetMapping("/all")
    public BaseResponse<List<User>> getUsers() throws BaseException {
        List<User> getUsersRes = userProvider.getUsers();
        return new BaseResponse<>(getUsersRes);
    }

    /**
     * 1. inApp 유저 생성 API (회원가입)
     * @param postUserReq
     * @return BaseResponse<PostUserRes>
     */
    @PostMapping("/join")
    public BaseResponse<PostUserRes> join(@Valid @RequestBody PostUserReq postUserReq) throws BaseException {
        PostUserRes postUserRes = userService.createUser(postUserReq);
        return new BaseResponse<>(postUserRes);
    }

    /**
     * 2. 휴대폰 인증번호 발송 API
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
     * 3. 이메일 로그인 API
     * @param postLoginReq
     * @return BaseResponse<PostLoginRes>
     */
    @PostMapping("/login")
    public BaseResponse<PostUserRes> login(@Valid @RequestBody PostLoginReq postLoginReq) throws BaseException {
        PostUserRes postUserRes = userService.login(postLoginReq);
        return new BaseResponse<>(postUserRes);
    }

    /**
     * 4. 카카오 로그인, 회원가입 API
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
     * 5. 유저 정보 조회 API
     * @return BaseResponse<GetUserRes>
     */
    @GetMapping("") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetUserRes> getUser() throws BaseException {
        int userId = jwtService.getUserId();
        GetUserRes getUserRes = userProvider.getUser(userId);
        return new BaseResponse<>(getUserRes);
    }

    /**
     * 6. 등급 조회 API
     * @return BaseResponse<GetGradeRes>
     */
    @GetMapping("/grade") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetGradeRes> getUserGrade() throws BaseException {
        int userId = jwtService.getUserId();
        GetGradeRes getGradeRes = userProvider.getGrade(userId);
        return new BaseResponse<>(getGradeRes);
    }

    /**
     * 7. 유저 정보 수정 API
     * [PATCH] /users/detail
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PatchMapping("/detail")
    public BaseResponse<PostUserDelRes> editUser(@Valid @RequestBody PatchUserReq user) throws BaseException {
        int userId = jwtService.getUserId();

        PostUserDelRes patchUserRes = userService.editUser(userId, user);
        return new BaseResponse<>(patchUserRes);
    }

    /**
     * 8. 유저 주소 조회 API (배송지 관리)
     * @return BaseResponse<List<Address>>
     */
    @GetMapping("/address")
    public BaseResponse<List<Address>> getAddress() throws BaseException {
        int userId = jwtService.getUserId();
        List<Address> getAddressRes = userProvider.getAddress(userId);
        return new BaseResponse<>(getAddressRes);
    }

    /**
     * 15. 유저 삭제 API
     * @return BaseResponse<PostUserDelRes>
     */
    @PatchMapping("/delete")
    public BaseResponse<PostUserDelRes> delUser() throws BaseException {
        int userId = jwtService.getUserId();

        // 상태값 D로 변경
        PostUserDelReq postUserDelReq = new PostUserDelReq(userId);
        userService.delUser(postUserDelReq);
        PostUserDelRes postUserDelRes = new PostUserDelRes(userId);
        return new BaseResponse<>(postUserDelRes);
    }

    /**
     * 16. 로그아웃 API
     * @return
     * @throws BaseException
     */
    @PostMapping("/logout")
    public BaseResponse<PostUserDelRes> logoutUser() throws BaseException {
        int userId = jwtService.getUserId();
        userService.logoutUser(userId);
        PostUserDelRes logoutRes = new PostUserDelRes(userId);
        return new BaseResponse<>(logoutRes);
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
     * 회원 주소 수정 API
     * @param addressId
     * @param patchAddressReq
     * @return BaseResponse<String>
     */
    @PatchMapping("/{addressId}/address")
    public BaseResponse<String> editAddress(@PathVariable("addressId") int addressId, @RequestBody PatchAddressReq patchAddressReq) throws BaseException {
        userService.editAddress(addressId, patchAddressReq);
        return new BaseResponse<>("");
    }
}
