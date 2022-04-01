package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.request.PatchReviewReq;
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
        if (userId != reviewMapper.getId(postReviewReq.getSmallCartId())) {
            throw new BaseException(INVALID_USER_JWT);
        }
        // smallCart 의 status 확인
        if (!reviewMapper.getSmallCartStatus(postReviewReq.getSmallCartId()).equals("N")) {
            throw new BaseException(INVALID_SMALL_CART);
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
        if (result == 0) {
            throw new BaseException(UPDATE_FAIL_CART_STATUS);
        }
        int reviewId = postReviewReq.getReviewId();
        try{
            return new PostReviewRes(reviewId, postReviewReq.getContent());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void editReview(int userId, PatchReviewReq patchReviewReq) throws BaseException {
        if (userId != reviewMapper.getUserId(patchReviewReq.getReviewId())) {
            throw new BaseException(INVALID_USER_JWT);
        }
        if (!reviewMapper.getReviewStatus(patchReviewReq.getReviewId()).equals("Y")){
            throw new BaseException(INVALID_REVIEW_STATUS);
        }
        int result = reviewMapper.editReview(patchReviewReq);
        if (result == 0) {
            throw new BaseException(UPDATE_FAIL_REVIEW);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public void delReview(int userId, int reviewId) throws BaseException {
        if (userId != reviewMapper.getUserId(reviewId)) {
            throw new BaseException(INVALID_USER_JWT);
        }
        int result = reviewMapper.delReview(reviewId);
        if (result == 0) {
            throw new BaseException(DELETE_FAIL_REVIEW);
        }
    }

}