package com.example.demo.src.review;

import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import com.example.demo.src.review.model.response.PostReviewRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
    List<GetReviewRes> getMyReviews(int userId);

    int getId(int smallCartId);

    int getOrderListId(int smallCartId);

    int getProductId(int smallCartId);

    String getSmallCartStatus(int smallCartId);

    int createReview(PostReviewReq postReviewReq);

    int updateSmallCartStatus(int smallCartId);

    void delReview(int reviewId);

    void editReview(int reviewId, PatchReviewReq patchReviewReq);
}
