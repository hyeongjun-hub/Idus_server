package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchCartReq {
    @NotNull(message = "장바구니 식별자를 입력하세요.")
    private int cartId;
    @NotNull(message = "작은 장바구니 식별자를 입력하세요.")
    private int smallCartId;
    private int count;
    private String request;
}
