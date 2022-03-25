package com.example.demo.src.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private String thumbnailImageUrl;
    private String productName;
    private String productOption;
    private int count;
    private String request;
}
