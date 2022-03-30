package com.example.demo.src.review;

import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import org.apache.ibatis.annotations.Mapper;
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

    int getUserId(int userId);

    int editReview(PatchReviewReq patchReviewReq);

    int delReview(int reviewId);
}
