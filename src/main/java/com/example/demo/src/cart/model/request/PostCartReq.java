package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCartReq {
    private int productId;
    @NotNull(message = "productOptionId를 입력하세요")
    private List<Integer> productOptionId;
    @NotNull(message = "수량을 입력하세요")
    @Positive(message = "양수만 가능합니다.")
    private int count;
    @NotNull(message = "총 작품 금액을 입력하세요")
    @Positive(message = "양수만 가능합니다.")
    private int price;
    @NotNull(message = "배송비를 입력하세요")
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int deliveryTip;
    @NotBlank(message = "'장바구니(N)'인지 '구매하기(Y)'인지 입력하세요")
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String isDirectOrder;
}
