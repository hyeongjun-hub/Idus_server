package com.example.demo.src.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gift {
    private int giftId;
    private String createdAt;
    private String userName;
    private String productName;
    private String thumbnailImageUrl;
    private String makerName;
    private int priceAll;
    private int count;
    private String status;
}
