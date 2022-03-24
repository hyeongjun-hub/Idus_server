package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.request.PostCartReq;
import com.example.demo.src.cart.model.request.PostCreateCartReq;
import com.example.demo.src.cart.model.request.PostOrderDetailReq;
import com.example.demo.src.cart.model.request.PostSmallCartReq;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartMapper cartMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public int createCart(int userId, PostCartReq postCartReq) throws BaseException {
        try{
            // 중복 체크
            if(cartMapper.checkCart(userId) == 1){
                return cartMapper.getCartId(userId);
            }
            int cartId = 0;
            PostCreateCartReq postCreateCartReq = new PostCreateCartReq(userId, cartId, postCartReq.getIsDirectOrder());
            int result = cartMapper.createCart(postCreateCartReq);
            if (result == 0) {
                throw new BaseException(CREATE_FAIL_CART);
            }
            return postCreateCartReq.getCartId();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public int createSmallCart(PostSmallCartReq postSmallCartReq) throws BaseException {
        try{
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
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void createOrderDetail(PostOrderDetailReq postOrderDetailReq) throws BaseException {
        try{
            int smallCartId = postOrderDetailReq.getSmallCartId();
            for (int i = 0; i < postOrderDetailReq.getProductOptionId().size(); i++) {
                int result = cartMapper.createOrderDetail(postOrderDetailReq.getProductOptionId().get(i), smallCartId);
                if (result == 0) {
                    throw new BaseException(CREATE_FAIL_CART);
                }
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public void delCart(int userCartId) throws BaseException {
        try{
            cartMapper.delCart(userCartId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
