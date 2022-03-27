package com.example.demo.src.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewReq {
    @NotNull(message = "작은 장바구니를 입력하세요.")
    private int smallCartId;
    private int orderListId;
    private int productId;
    @NotBlank(message = "리뷰 내용을 입력하세요.")
    private String content;
    private String imageUrl;
    private int star;
    private int reviewId;
}
