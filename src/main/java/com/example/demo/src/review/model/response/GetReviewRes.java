package com.example.demo.src.review.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewRes {
    private int reviewId;
    private String thumbnailImageUrl;
    private String productName;
    private String profileImageUrl;
    private String userName;
    private String updatedAt;
    private int star;
    private String imageUrl;
    private String content;
}
