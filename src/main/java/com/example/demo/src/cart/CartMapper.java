package com.example.demo.src.cart;

import com.example.demo.src.cart.model.request.PatchCartReq;
import com.example.demo.src.cart.model.request.PostCreateCartReq;
import com.example.demo.src.cart.model.request.PostSmallCartReq;
import com.example.demo.src.cart.model.response.GetCartRes;
import com.example.demo.src.cart.model.response.GetSmallCartRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    // 해당 user의 status Y 인 cart 있는지 확인
    int checkCart(int userId);

    int getCartId(int userId);

    int createCart(PostCreateCartReq postCreateCartReq);

    int createSmallCart(PostSmallCartReq postSmallCartReq);

    int updateCartPrice(@Param("cartId") int cartId, @Param("price") int price);

    int updateDeliveryTip(@Param("cartId") int cartId, @Param("deliveryTip") int deliveryTip);

    int createOrderDetail(@Param("productOptionId") int productOptionId, @Param("smallCartId") int smallCartId);

    GetCartRes getCart(int userId);

    List<GetSmallCartRes> getSmallCart(int cartId);

    int getUserId(PatchCartReq patchCartReq);

    String getCartStatus(PatchCartReq patchCartReq);
    String getSmallCartStatus(PatchCartReq patchCartReq);

    int editCount(PatchCartReq patchCartReq);

    int getPriceByCount(PatchCartReq patchCartReq);

    int updateCartPriceByCount(@Param("cartId") int cartId, @Param("originalPrice") int originalPrice,  @Param("price") int price);

    int editRequest(PatchCartReq patchCartReq);

    int getUserIdBySmallCartId(int smallCartId);

    int delCart(int smallCartId);


}
