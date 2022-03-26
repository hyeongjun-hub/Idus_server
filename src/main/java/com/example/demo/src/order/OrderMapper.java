package com.example.demo.src.order;

import com.example.demo.src.order.model.entity.OrderDetail;
import com.example.demo.src.order.model.request.PostOrderReq;
import com.example.demo.src.order.model.response.GetOrderDetailRes;
import com.example.demo.src.order.model.response.GetOrderRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    // check
    String getCartStatus(int cartId);
    String getSmallCartStatus(int smallCartId);

    int createOrder(PostOrderReq postOrderReq);

    // update
    int updateCartStatus(int cartId);
    int updateSmallCartStatus(int smallCartId);
    int updateCouponStatus(int couponId);
    int updatePoint(@Param("userId") int userId, @Param("point") int point);
    int updateOrderCount(int productId);

    int getProductId(int smallCartId);
    int getUserId(int orderListId);

    List<GetOrderRes> getOrders(int userId);

    GetOrderDetailRes getOrder(int orderListId);

    List<OrderDetail> getOrderDetailList(int orderListId);

}
