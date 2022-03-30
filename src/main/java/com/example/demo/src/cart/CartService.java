package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.request.*;
import com.example.demo.src.cart.model.response.GetSmallCartRes;
import com.example.demo.src.cart.model.response.PostCartRes;
import com.example.demo.src.user.model.response.PostUserDelRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartMapper cartMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public int createCart(int userId, PostCartReq postCartReq) throws BaseException {
        // 중복 체크
        // 있으면 return
        if (cartMapper.checkCart(userId) == 1) {
            return cartMapper.getCartId(userId);
        }
        // 없으면 새로운 Cart 만들기
        int cartId = 0;
        PostCreateCartReq postCreateCartReq = new PostCreateCartReq(userId, cartId, postCartReq.getIsDirectOrder());
        int result = cartMapper.createCart(postCreateCartReq);
        if (result == 0) {
            throw new BaseException(CREATE_FAIL_CART);
        }
        System.out.println("postCreateCartReq.getCartId() = " + postCreateCartReq.getCartId());
        return postCreateCartReq.getCartId();
    }

    @Transactional(rollbackFor = {BaseException.class})
    public int createSmallCart(PostSmallCartReq postSmallCartReq) throws BaseException {
        int result = cartMapper.createSmallCart(postSmallCartReq);
        if (result == 0) {
            throw new BaseException(CREATE_FAIL_CART);
        }
        result = cartMapper.updateCartPrice(postSmallCartReq.getCartId(), postSmallCartReq.getPrice());
        if (result == 0) {
            throw new BaseException(UPDATE_FAIL_PRICE);
        }
        result = cartMapper.updateDeliveryTip(postSmallCartReq.getCartId(), postSmallCartReq.getDeliveryTip());
        if (result == 0) {
            throw new BaseException(CREATE_FAIL_DELIVERY_TIP);
        }
        return postSmallCartReq.getSmallCartId();
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void createOrderDetail(PostOrderDetailReq postOrderDetailReq) throws BaseException {
        int smallCartId = postOrderDetailReq.getSmallCartId();
        for (int i = 0; i < postOrderDetailReq.getProductOptionId().size(); i++) {
            int result = cartMapper.createOrderDetail(postOrderDetailReq.getProductOptionId().get(i), smallCartId);
            if (result == 0) {
                throw new BaseException(CREATE_FAIL_CART);
            }
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public PostCartRes editCart(int userId, PatchCartReq patchCartReq) throws BaseException {
        // userId 유효성 확인
        if(cartMapper.getUserId(patchCartReq) != userId){
            throw new BaseException(INVALID_USER_JWT);
        }
        // status 값 확인
        if (!cartMapper.getCartStatus(patchCartReq).equals("Y")) {
            throw new BaseException(POST_CART_STATUS_NOT_Y);
        }
        if (!cartMapper.getSmallCartStatus(patchCartReq.getSmallCartId()).equals("Y")) {
            throw new BaseException(POST_SMALL_CART_STATUS_NOT_Y);
        }
        // 수량 변경일 때
        if (patchCartReq.getCartId() > 0) {
            editCount(patchCartReq);
        }
        // 요청사항 변경일 때
        if (patchCartReq.getRequest() != null) {
            editRequest(patchCartReq);
        }
        try {
            return new PostCartRes(patchCartReq.getCartId(), patchCartReq.getSmallCartId());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 하나의 가격을 구하고 이전 count와 곱한 값을 original price
    // 하나의 가격과 지금 count를 곱한 값을 price
    // smallCart -> price로 대체
    @Transactional(rollbackFor = {BaseException.class})
    public void editCount(PatchCartReq patchCartReq) throws BaseException {
        int smallCartId = patchCartReq.getSmallCartId();
        int onePrice = cartMapper.getOnePrice(smallCartId);
        int originalPrice = cartMapper.getPrice(smallCartId);
        int changePrice = onePrice * patchCartReq.getCount();
        // count와 price 업데이트
        patchCartReq.setPrice(changePrice);
        int result = cartMapper.editCount(patchCartReq);
        if(result == 0){
            throw new BaseException(EDIT_FAIL_CART_COUNT);
        }
        // cart의 priceAll 업데이트
        int price = cartMapper.getPriceByCount(smallCartId, onePrice);
        result = cartMapper.updateCartPriceByCount(patchCartReq.getCartId(), originalPrice, price);
        if(result == 0){
            throw new BaseException(EDIT_FAIL_CART_PRICE);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void editRequest(PatchCartReq patchCartReq) throws BaseException {
        int result = cartMapper.editRequest(patchCartReq);
        if(result == 0){
            throw new BaseException(EDIT_FAIL_CART_REQUEST);
        }
    }

    @Transactional(rollbackFor = {BaseException.class, MethodArgumentNotValidException.class})
    public void delCart(int userId, PostSmallCartDelReq postSmallCartDelReq) throws BaseException {
        int cartId = cartMapper.getCartId(userId);
        List<Integer> smallCartList = postSmallCartDelReq.getSmallCartId();
        for (int smallCartId : smallCartList) {
            if(userId != cartMapper.getUserIdBySmallCartId(smallCartId)){
                throw new BaseException(INVALID_USER_JWT);
            }
            if(!cartMapper.getSmallCartStatus(smallCartId).equals("Y")){
                throw new BaseException(POST_SMALL_CART_STATUS_NOT_Y);
            }
            int price = -cartMapper.getPrice(smallCartId);
            int deliveryTip = -cartMapper.getDeliveryTip(smallCartId);
            int result = cartMapper.delCart(smallCartId);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_SMALL_CART_STATUS);
            }
            result = cartMapper.updateCartPrice(cartId, price);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_PRICE);
            }
            result = cartMapper.updateCartDeliveryTip(cartId, deliveryTip);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_CART_DELIVERY_TIP);
            }
        }
        if(cartMapper.checkSmallCart(cartId) == 0){
            int result = cartMapper.cleanCart(cartId);
            if (result == 0) {
                throw new BaseException(UPDATE_FAIL_CART_CLEAN);
            }
        }
    }
}
