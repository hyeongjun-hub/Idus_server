package com.example.demo.src.order.model.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrderReq {
    private int orderListId; // not required
    @NotNull(message = "장바구니 식별자를 입력해주세요")
    private int cartId;
    @NotNull(message = "작은 장바구니 식별자를 입력해주세요")
    private List<Integer> smallCartId;
    private int couponId;
    @NotNull(message = "결제방법 식별자를 입력해주세요")
    private int paymentMethodId;
    @NotBlank(message = "받는 분을 입력해주세요.")
    private String taker;
    @NotBlank(message = "받는 분 전화번호를 입력해주세요.")
    private String phone;
    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
    @PositiveOrZero(message = "0 또는 양수를 입력하세요.")
    private int usePoint;
    @Length(max = 1, message = "한 글자만 입력가능합니다.")
    @NotBlank(message = "후원 여부를 입력해주세요.")
    private String isSupport;
    @PositiveOrZero(message = "0 또는 양수를 입력하세요.")
    @NotNull(message = "총 작품금액을 입력해주세요.")
    private int priceSum;
    @PositiveOrZero(message = "0 또는 양수를 입력하세요.")
    @NotNull(message = "최종금액을 입력해주세요.")
    private int finalPrice;
    @Length(max = 1, message = "한 글자만 입력가능합니다.")
    @NotBlank(message = "1회용 안심번호 여부를 입력하세요")
    private String isProtectNum;
    @Length(max = 1, message = "한 글자만 입력가능합니다.")
    private String isGift;
    private int takerId;
    private String message;
    @PositiveOrZero(message = "0 또는 양수를 입력하세요.")
    @NotNull(message = "적립금을 입력해주세요.")
    private int rewardPoint;
}
