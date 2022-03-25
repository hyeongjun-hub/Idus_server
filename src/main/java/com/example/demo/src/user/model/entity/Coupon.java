package com.example.demo.src.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private int couponId;
    private int productCouponId;
    private int userId;
    private String createdAt;
    private String updatedAt;
    private String status;
}
