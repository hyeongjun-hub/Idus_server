package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostOwnerReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public PostReviewRes createReview(int userId, PostReviewReq postReviewReq) throws BaseException {
        // userId와 postReviewReq의 smallCartId로 얻은 userId가 같은지 확인
        if (userId != reviewMapper.getUserId(postReviewReq.getSmallCartId())) {
            throw new BaseException(INVALID_USER_JWT);
        }
        int orderListId = reviewMapper.getOrderListId(postReviewReq.getSmallCartId());
        int productId = reviewMapper.getProductId(postReviewReq.getSmallCartId());
        postReviewReq.setOrderListId(orderListId);
        postReviewReq.setProductId(productId);
        int result = reviewMapper.createReview(postReviewReq);
        if (result == 0) {
            throw new BaseException(CREATE_FAIL_REVIEW);
        }
        result = reviewMapper.updateSmallCartStatus(postReviewReq.getSmallCartId());
        if (result == 0){
            throw new BaseException(UPDATE_FAIL_CART_STATUS);
        }
        int reviewId = postReviewReq.getReviewId();
        return new PostReviewRes(reviewId, postReviewReq.getContent());

    }


    @Transactional(rollbackFor = {BaseException.class})
    public void delReview(int reviewId) throws BaseException {
        try{
            reviewMapper.delReview(reviewId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editReview(int reviewId, PatchReviewReq patchReviewReq) throws BaseException {
        try{
            reviewMapper.editReview(reviewId, patchReviewReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}