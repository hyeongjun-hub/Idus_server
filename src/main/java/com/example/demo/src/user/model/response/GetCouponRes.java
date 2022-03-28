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
    private String couponName;
    private double rate;
    private int amount;
    private int priceMin;
    private int remainingDay;
    private String startDate;
    private String endDate;
    private String status;
}
