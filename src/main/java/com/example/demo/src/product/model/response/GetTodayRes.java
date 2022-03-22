package com.example.demo.src.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTodayRes {
    private int productId;
    private String productName;
    private String thumbnailImageUrl;

    private double star;
    private int reviewCount;
    private String review;
}
