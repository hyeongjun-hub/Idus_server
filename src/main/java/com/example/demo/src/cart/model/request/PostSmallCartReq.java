package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSmallCartReq {
    private int smallCartId;
    private int cartId;
    private int productId;
    private int count;
    private int price;
    private int deliveryTip;
}
