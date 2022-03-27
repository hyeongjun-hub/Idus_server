package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.entity.Address;
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

    public int getPoint(int userId) throws BaseException {
        try{
            int point = userMapper.getPoint(userId);
            return point;
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
    }

    public List<GetCouponRes> getCoupons(int userId) throws BaseException{
        try{
            List<GetCouponRes> getCouponRes = userMapper.getCoupons(userId);
            return getCouponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetPresentRes> getPresents(int userId) throws BaseException{
        try{
            List<GetPresentRes> getPresentRes = userMapper.getPresents(userId);
            return getPresentRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Address> getAddress(int userId) throws BaseException{
        try{
            List<Address> getAddressRes = userMapper.getAddress(userId);
            return getAddressRes;
        }
        catch (Exception exception) {
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
