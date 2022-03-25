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

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderMapper orderMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public int createOrder(int userId, PostOrderReq postOrderReq) throws BaseException {
        try{
            // cart의 유효성 검사
            if(!orderMapper.getCartStatus(postOrderReq.getCartId()).equals("Y")){
                throw new BaseException(POST_CART_STATUS_NOT_Y);
            }
            // smallCart의 유효성 검사
            if(!orderMapper.getCartStatus(postOrderReq.getSmallCartId()).equals("Y")){
                throw new BaseException(POST_SMALL_CART_STATUS_NOT_Y);
            }
            // 주문내역 생성
            orderMapper.createOrder(postOrderReq);
            int orderListId = postOrderReq.getOrderListId();
            // cart의 status를 N으로 변경
            int result = orderMapper.updateCartStatus(postOrderReq.getCartId());
            if(result == 0){
                throw new BaseException(UPDATE_FAIL_CART_STATUS);
            }
            // smallCart의 status를 N으로 변경
            result = orderMapper.updateSmallCartStatus(postOrderReq.getSmallCartId());
            if(result == 0){
                throw new BaseException(UPDATE_FAIL_SMALL_CART_STATUS);
            }
            // coupon의 status를 N으로 변경
            result = orderMapper.updateCouponStatus(postOrderReq.getCouponId());
            if(result == 0){
                throw new BaseException(UPDATE_FAIL_COUPON_STATUS);
            }
            // User의 point를 적립
            result = orderMapper.updatePoint(userId, postOrderReq.getUsePoint());
            if(result == 0){
                throw new BaseException(UPDATE_FAIL_USER_POINT);
            }
            return orderListId;
        } catch(Exception exception){
            logger.warn(exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
