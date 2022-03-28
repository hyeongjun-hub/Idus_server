package com.example.demo.src.user;

import com.example.demo.src.product.model.response.GetProductRes;
import com.example.demo.src.user.model.entity.Address;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getUsers();

    GetUserRes getUser(int userId);
    GetGradeRes getGrade(int userId);

    int createUser(PostUserReq postUserReq);

    String getPlatform(String email);

    // 중복 check
    int checkPhone(String phone);
    int checkEmail(String email);

    // edit User
    int editEmail(int userId, PatchUserReq user);
    int editUserName(int userId, PatchUserReq user);
    int editProfileImageUrl(int userId, PatchUserReq user);
    int editBirthday(int userId, PatchUserReq user);
    int editGender(int userId, PatchUserReq user);
    int editFingerPrint(int userId, PatchUserReq user);
    int editAlarm(int userId, PatchUserReq user);
    int editPhone(int userId, PatchUserReq user);

    int delUser(PostUserDelReq postUserDelReq);

    User getLoginUser(PostLoginReq postLoginReq);

    int getUserIdByEmail(String userEmail);

    int getPoint(int userId);

    List<GetCouponRes> getCoupons(int userId);

    List<GetProductRes> getLikeProducts(int userId);

    List<GetPresentRes> getPresents(int userId);

    List<Address> getAddress(int userId);

    int createAddress(PostAddressReq postAddressReq);

    // address set
    void setAddressInfo(int addressId, PostUserReq postUserReq);

    // edit Address
    int editAddress(PatchAddressReq patchAddressReq);
    int editAddressPhone(PatchAddressReq patchAddressReq);
    int editTaker(PatchAddressReq patchAddressReq);

    String getUserStatus(int userId);

    void logout(int userId);

    void updateIsLogin(int userId);

}
