package com.example.demo.src.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCouponRes {
    private int couponId;
    private int couponName;
    private double rate;
    private int amount;
    private int priceMin;
    private String startDate;
    private String endDate;
    private String orderMethod;
    private String status;
}
