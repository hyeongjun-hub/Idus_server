package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateCartReq {
    private int userId;
    private int cartId;
    private String isDirectOrder;
}
