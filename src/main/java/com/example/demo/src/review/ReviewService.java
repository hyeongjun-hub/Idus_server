package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostOwnerReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public PostReviewRes createReview(int userId, PostReviewReq postReviewReq) throws BaseException {
            // userId와 postReviewReq의 smallCartId로 얻은 userId가 같은지 확인
            if(userId != reviewMapper.getUserId(postReviewReq)){
                throw new BaseException(INVALID_USER_JWT);
            }
            reviewMapper.createReview(userId, postReviewReq);
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