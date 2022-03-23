package com.example.demo.src.cart.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int cartId;
    private int userId;
    private String isDirectOrder;
    int priceAll;
    int deliveryTipAll;
    String createdAt;
    String updateAt;
    String status;
}
