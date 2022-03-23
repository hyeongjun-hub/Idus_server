package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int reviewId;
    private int userId;
    private String profileImageUrl;
    @NotBlank(message = "형식을 확인해주세요.")
    private String userName;
    private String updateDate;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private double star;
    @NotBlank(message = "형식을 확인해주세요.")
    private String imageUrl;
    private String content;
    @NotBlank(message = "형식을 확인해주세요.")
    private String productOption; //productOption의 content
}
