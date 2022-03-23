package com.example.demo.src.cart;

import com.example.demo.src.cart.model.request.PostAddAdditionalCartReq;
import com.example.demo.src.cart.model.request.PostCartReq;
import com.example.demo.src.cart.model.request.PostCreateCartReq;
import com.example.demo.src.cart.model.response.GetCartRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    int createCart(PostCreateCartReq postCreateCartReq);

    List<GetCartRes> getCart(int userCartId);

    void delCart(int userCartId);

}
