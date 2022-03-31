package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.response.GetProductRes;
import com.example.demo.src.user.model.entity.Address;
import com.example.demo.src.user.model.entity.Gift;
import com.example.demo.src.user.model.entity.Point;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.response.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class UserProvider {
    private final UserMapper userMapper;

    public List<User> getUsers() throws BaseException{
        try{
            return userMapper.getUsers();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetUserRes getUser(int userId) throws BaseException {
        if (!userMapper.getUserStatus(userId).equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        try {
            return userMapper.getUser(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetGradeRes getGrade(int userId) throws BaseException {
        if (!userMapper.getUserStatus(userId).equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        try {
            GetGradeRes getGradeRes =  userMapper.getGrade(userId);
            // 금손 일시
            if(getGradeRes.getGradeId() == 4){
                getGradeRes.setCurrentMonthLast("다음 등급이 없습니다.");
            }
            return getGradeRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getUserPoint(int userId) throws BaseException {
        try{
            return userMapper.getUserPoint(userId);
        } catch (Exception ignored) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Point> getPointList(int userId) throws BaseException {
        try{
            List<Point> pointList = userMapper.getPoint(userId);
            return pointList;
        } catch (Exception ignored) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCouponRes> getCoupons(int userId) throws BaseException{
        try{
            List<GetCouponRes> getCouponRes = userMapper.getCoupons(userId);
            return getCouponRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getLikeProducts(int userId) throws BaseException{
        try{
            return userMapper.getLikeProducts(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetFollowRes> getFollowMakers(int userId) throws BaseException{
        try{
            List<GetFollowRes> getFollowRes = userMapper.getFollowMakers(userId);
            return getFollowRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Address> getAddress(int userId) throws BaseException{
        try{
            List<Address> getAddressRes = userMapper.getAddress(userId);
            return getAddressRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Gift> getTakeGift(int userId) throws BaseException{
        try{
            List<Gift> getTakeGiftList = userMapper.getTakeGift(userId);
            return getTakeGiftList;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Gift> getGiveGift(int userId) throws BaseException{
        try{
            List<Gift> getGiveGiftList = userMapper.getGiveGift(userId);
            return getGiveGiftList;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
