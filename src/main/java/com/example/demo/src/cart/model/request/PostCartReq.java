package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCartReq {
    private int productId;
    private List<Integer> productOptionId;
    private int count;
    private int price;
    private int deliveryTip;
    private String isDirectOrder; // TODO: 바로 구매
}
