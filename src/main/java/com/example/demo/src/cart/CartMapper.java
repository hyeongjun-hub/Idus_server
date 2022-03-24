package com.example.demo.src.cart;

import com.example.demo.src.cart.model.request.PostCreateCartReq;
import com.example.demo.src.cart.model.request.PostSmallCartReq;
import com.example.demo.src.cart.model.response.GetCartRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CartMapper {
    int checkCart(int userId);

    int getCartId(int userId);

    int createCart(PostCreateCartReq postCreateCartReq);

    int createSmallCart(PostSmallCartReq postSmallCartReq);

    int updateCartPrice(@Param("cartId") int cartId, @Param("price") int price);

    int updateDeliveryTip(@Param("cartId") int cartId, @Param("deliveryTip") int deliveryTip);

    int createOrderDetail(@Param("productOptionId") int productOptionId, @Param("smallCartId") int smallCartId);

    GetCartRes getCart(int userId);

    void delCart(int userCartId);

}
