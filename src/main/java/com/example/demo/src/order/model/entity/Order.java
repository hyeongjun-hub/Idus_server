package com.example.demo.src.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private int cartId;
    private int addressId;
    private int couponId;
    private int usePoint;
    private String isSupport;
    private int priceSum;
    private int finalPrice;
    private String isProtectNum;
    private String isGift;
    private int takeUserId;
    private String message;
    private int rewardPoint;
    private String createAt;
    private String updateAt;
    private String status;
}
