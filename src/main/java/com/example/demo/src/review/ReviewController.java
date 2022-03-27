package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import com.example.demo.src.review.model.response.PostReviewRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;
    private final JwtService jwtService;

    /**
     * 41. 유저 구매후기 조회 API
     *
     * @return BaseResponse<List < GetReviewRes>>
     */
    @GetMapping("")
    public BaseResponse<List<GetReviewRes>> getMyReviews() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetReviewRes> getReviewRes = reviewProvider.getMyReviews(userId);
        return new BaseResponse<>(getReviewRes);
    }

    /**
     * 42. 구매후기 작성 API
     *
     * @return BaseResponse<PostReviewRes>
     */
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) throws BaseException {
        int userId = jwtService.getUserId();
        System.out.println("userId = " + userId);
        PostReviewRes postReviewRes = reviewService.createReview(userId, postReviewReq);
        return new BaseResponse<>(postReviewRes);
    }

    /**
     * 리뷰 삭제 API
     *
     * @param reviewId
     * @return BaseResponse<String>
     */
    @PatchMapping("/{reviewId}/delete")
    public BaseResponse<String> delReview(@PathVariable("reviewId") int reviewId) throws BaseException {
        reviewService.delReview(reviewId);
        return new BaseResponse<>("");
    }

    /**
     * 리뷰 수정 API
     *
     * @param reviewId
     * @param patchReviewReq
     * @return BaseResponse<String>
     */
    @PatchMapping("/{reviewId}")
    public BaseResponse<String> editReview(@PathVariable("reviewId") int reviewId, @RequestBody PatchReviewReq patchReviewReq) throws BaseException {
        reviewService.editReview(reviewId, patchReviewReq);
        return new BaseResponse<>("");
    }
}
