package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.response.GetCartRes;
import com.example.demo.src.cart.model.response.GetSmallCartRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartMapper cartMapper;

    public GetCartRes getCart(int userId) {
        if (cartMapper.checkCart(userId) == 0) {
            return new GetCartRes(0,new ArrayList<>(),0,0);
        }
        int cartId = cartMapper.getCartId(userId);
        GetCartRes getCartRes = cartMapper.getCart(userId);
        List<GetSmallCartRes> smallCart = cartMapper.getSmallCart(cartId);
        getCartRes.setSmallCart(smallCart);
        return getCartRes;
    }

}
