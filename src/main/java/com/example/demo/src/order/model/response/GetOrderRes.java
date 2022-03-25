package com.example.demo.src.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderRes {
    private int orderListId;
    private int smallCartId;
    private String createdAt;
    private int finalPrice;
    private String thumbnailImageUrl;
    private String productName;
    private String makerName;
    private String status;
}
