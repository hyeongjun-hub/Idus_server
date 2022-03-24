package com.example.demo.src.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSmallCartRes {
    private int smallCartId;
    private String makerName;
    private String thumbnailImageUrl;
    private String productName;
    private int amount;
    private String productOption;
    private int price;
    private int count;
    private int deliveryTip;
    private int tipFreeMin;
    private String request;
}
