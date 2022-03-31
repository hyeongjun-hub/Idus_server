package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.model.request.PostOrderReq;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderMapper orderMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public int createOrder(int userId, PostOrderReq postOrderReq) throws BaseException {
        // cart의 유효성 검사
        if (!orderMapper.getCartStatus(postOrderReq.getCartId()).equals("Y")) {
            throw new BaseException(POST_CART_STATUS_NOT_Y);
        }
        // smallCart의 유효성 검사
        List<Integer> smallCartIdList = postOrderReq.getSmallCartId();
        for (int smallCartId : smallCartIdList) {
            if (!orderMapper.getSmallCartStatus(smallCartId).equals("Y")) {
                throw new BaseException(POST_SMALL_CART_STATUS_NOT_Y);
            }
        }
        // 주문내역 생성
        orderMapper.createOrder(postOrderReq);
        int orderListId = postOrderReq.getOrderListId();
        // cart의 status를 N으로 변경
        int result = orderMapper.updateCartStatus(postOrderReq.getCartId());
        if (result == 0) {
            throw new BaseException(UPDATE_FAIL_CART_STATUS);
        }
        // smallCart의 status를 N으로 변경
        for (int smallCartId : smallCartIdList) {
            result = orderMapper.updateSmallCartStatus(smallCartId);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_SMALL_CART_STATUS);
            }
        }
        // coupon의 status를 N으로 변경
        if(postOrderReq.getCouponId() > 0){
            result = orderMapper.updateCouponStatus(postOrderReq.getCouponId());
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_COUPON_STATUS);
            }
        }
        // User의 point를 적립
        if(postOrderReq.getRewardPoint() > 0) {
            result = orderMapper.addUsePoint(orderListId, +postOrderReq.getRewardPoint());
            if (result == 0) {
                throw new BaseException(CREATE_FAIL_USE_POINT);
            }
            result = orderMapper.updatePoint(userId, postOrderReq.getRewardPoint());
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_USER_POINT);
            }
        }
        // User의 point 사용내용 생성
        if(postOrderReq.getUsePoint() > 0) {
            result = orderMapper.addUsePoint(orderListId, -postOrderReq.getUsePoint());
            if (result == 0) {
                throw new BaseException(CREATE_FAIL_USE_POINT);
            }
            // User의 point 차감
            result = orderMapper.updatePoint(userId, -postOrderReq.getUsePoint());
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_USER_POINT);
            }
        }
        // Product의 orderCount +1
        for (int smallCart : smallCartIdList) {
            int productId = orderMapper.getProductId(smallCart);
            result = orderMapper.updateOrderCount(productId);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_PRODUCT_ORDER_COUNT);
            }
        }
        // Support 테이블 컬럼 추가
        if(postOrderReq.getIsSupport().equals("Y")){
            for (int smallCart : smallCartIdList){
                int makerId = orderMapper.getMakerId(smallCart);
                result = orderMapper.addSupport(userId, makerId);
                if (result == 0) {
                  throw new BaseException(CREATE_FAIL_SUPPORT);
                }
            }
        }
        return orderListId;
    }

    public void checkUser(int userId, int orderListId) throws BaseException {
        if(orderMapper.getUserId(orderListId) != userId){
            throw new BaseException(INVALID_USER_JWT);
        }
    }
}
