package com.example.demo.src.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCartRes {
    private int cartId;
    private List<GetSmallCartRes> smallCart;
    private int priceAll;
    private int deliveryTipAll;
}
