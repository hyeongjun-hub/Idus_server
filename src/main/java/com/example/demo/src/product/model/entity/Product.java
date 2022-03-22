package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productId;
    private int categoryId;
    private int makerId;
    @NotBlank(message = "형식을 확인해주세요.")
    private String ProductName;
    private String thumbNailImageUrl;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int price;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private double discountRate;
    @Length(max = 1, message = "'M' 또는 'F'를 입력하세요.")
    private String tag;
    @Length(max = 1, message = "'M' 또는 'F'를 입력하세요.")
    private String vip;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int orderCount;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int deliveryTip;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int tipFreeMin;
    private int amount;
    private String createdAt;
    private String updatedAt;
    private String status;
}
