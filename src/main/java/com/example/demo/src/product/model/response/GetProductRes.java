package com.example.demo.src.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductRes {
    private int productId;
    private int categoryId;
    private String makerName;
    private String productName;
    private String thumbnailImageUrl;
    private int price;
    private double discountRate;
    private String tag;
    private double star;
    private int reviewCount;
    private String review;
    private String vip;
}
