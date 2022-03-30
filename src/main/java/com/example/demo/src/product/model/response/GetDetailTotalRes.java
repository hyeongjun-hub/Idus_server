package com.example.demo.src.product.model.response;

import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.review.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailTotalRes {
    private GetDetailRes getDetailRes;
    private List<Review> review;
    private List<ProductKeyword> productKeyword;
    private List<Comment> comment;
    private Maker maker;
}
