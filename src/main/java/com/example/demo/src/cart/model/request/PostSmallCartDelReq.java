package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSmallCartDelReq {
    @NotNull(message = "작은 장바구니 식별자 배열을 입력하세요.")
    private List<Integer> smallCartId;
}
