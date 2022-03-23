package com.example.demo.src.product.model.response;

import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.product.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailRes {
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
    private int orderCount;
    private int deliveryTip;
    private int tipFreeMin;
    private int amount;
    private String productInfo;
}
