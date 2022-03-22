package com.example.demo.src.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetLiveRes {
    private int productId;
    private String makerName;
    private String productName;
    private String thumbnailImageUrl;

    private double star;
    private int reviewCount;
    private String review;
}
