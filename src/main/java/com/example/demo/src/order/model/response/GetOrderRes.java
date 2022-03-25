package com.example.demo.src.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private int orderListId;
    private String createdAt;
    private int finalPrice;
    private String thumbnailImageUrl;
    private String productName;
    private String makerName;
    private String status;
}
