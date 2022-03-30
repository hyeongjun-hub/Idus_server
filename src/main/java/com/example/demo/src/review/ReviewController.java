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
     * 43. 구매후기 수정 API
     *
     * @return BaseResponse<String>
     */
    @PatchMapping("/edit")
    public BaseResponse<String> editReview(@RequestBody PatchReviewReq patchReviewReq) throws BaseException {
        int userId = jwtService.getUserId();
        reviewService.editReview(userId, patchReviewReq);
        return new BaseResponse<>("구매후기 수정이 완료되었습니다.");
    }

    /**
     * 44. 구매후기 삭제 API
     *
     * @return BaseResponse<String>
     */
    @PatchMapping("/{reviewId}/delete")
    public BaseResponse<String> delReview(@PathVariable("reviewId") int reviewId) throws BaseException {
        int userId = jwtService.getUserId();
        reviewService.delReview(userId, reviewId);
        return new BaseResponse<>("구매후기가 삭제되었습니다.");
    }
}
