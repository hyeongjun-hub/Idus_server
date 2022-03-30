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
public class PatchReviewReq {
    private int reviewId;
    @NotBlank(message = "리뷰 내용을 입력하세요.")
    private String content;
    private String imageUrl;
    @NotNull(message = "평점을 입력하세요.")
    private int star;
}
