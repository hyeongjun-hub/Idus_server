package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.review.model.entity.Review;
import com.example.demo.src.product.model.response.*;
import com.example.demo.src.user.model.response.GetCouponRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductProvider productProvider;
    private final ProductService productService;
    private final JwtService jwtService;

    /**
     * 18. 오늘의 작품 조회 API
     * @return BaseException<List<GetTodayRes>>
     */
    @GetMapping("/today")
    public BaseResponse<List<GetTodayRes>> getTodayProducts() throws BaseException {
        List<GetTodayRes> getTodayRes = productProvider.getTodayProducts();
        return new BaseResponse<>(getTodayRes);
    }

    /**
     * 19. 실시간 탭 조회 API
     * @return BaseException<List<GetLiveRes>>
     */
    @GetMapping("/live")
    public BaseResponse<List<GetLiveRes>> getLiveProducts(@RequestParam(required = false) Integer page) throws BaseException {
        List<GetLiveRes> getLiveRes;
        if(page != null) getLiveRes = productProvider.getLiveProducts(page);
        else getLiveRes = productProvider.getLiveProducts();
        return new BaseResponse<>(getLiveRes);
    }

    /**
     * 20. NEW 탭 조회 API
     * @return BaseException<List<GetLiveRes>>
     */
    @GetMapping("/new")
    public BaseResponse<List<GetNewRes>> getNewProducts(@RequestParam(required = false) Integer page) throws BaseException {
        List<GetNewRes> getLiveRes;
        if(page != null) getLiveRes = productProvider.getNewProducts(page);
        else getLiveRes = productProvider.getNewProducts();
        return new BaseResponse<>(getLiveRes);
    }

    /**
     * 21. 첫 구매 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/first-buy")
    public BaseResponse<List<GetProductRes>> getFirstBuy(@RequestParam(required = false) Integer page) throws BaseException {
        List<GetProductRes> getFirstBuy;
        if(page != null) getFirstBuy = productProvider.getFirstBuy(page);
        else getFirstBuy = productProvider.getFirstBuy();
        return new BaseResponse<>(getFirstBuy);
    }

    /**
     * 22. 내가 본 작품의 연관 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/relate")
    public BaseResponse<List<GetProductRes>> getRelate(@RequestParam(required = false) Integer page) throws BaseException {
        int userId = jwtService.getUserId();
        List<GetProductRes> getFirstBuy;
        if(page != null) getFirstBuy = productProvider.getRelate(userId, page);
        else getFirstBuy = productProvider.getRelate(userId);
        return new BaseResponse<>(getFirstBuy);
    }

    /**
     * 23. 유저가 좋아할만한 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/maybe")
    public BaseResponse<List<GetProductRes>> getMaybe(@RequestParam(required = false) Integer page) throws BaseException {
        if(jwtService.getJwt() == null){
            List<GetProductRes> getTodayMore;
            if (page != null) getTodayMore = productProvider.getTodayMore(page);
            else getTodayMore = productProvider.getTodayMore();
            return new BaseResponse<>(getTodayMore);
        }
        int userId = jwtService.getUserId();
        List<GetProductRes> getFirstBuy;
        if(page != null) getFirstBuy = productProvider.getMaybe(userId, page);
        else getFirstBuy= productProvider.getMaybe(userId);
        return new BaseResponse<>(getFirstBuy);
    }

    /**
     * 24. 오늘의 작품 더보기 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/today/more")
    public BaseResponse<List<GetProductRes>> getTodayMore(@RequestParam(required = false) Integer page) throws BaseException {
        List<GetProductRes> getTodayMore;
        if (page != null) getTodayMore = productProvider.getTodayMore(page);
        else getTodayMore = productProvider.getTodayMore();
        return new BaseResponse<>(getTodayMore);
    }

    /**
     * 25. 카테고리 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/categories")
    public BaseResponse<List<GetProductRes>> getCategoryProducts(@RequestParam("categoryId") int categoryId, @RequestParam(value = "page", required = false) Integer page) throws BaseException {
        List<GetProductRes> getCategoryProducts = null;
        if(page != null) getCategoryProducts = productProvider.getCategoryProducts(categoryId, page);
        else getCategoryProducts = productProvider.getCategoryProducts(categoryId);
        return new BaseResponse<>(getCategoryProducts);
    }

    /**
     * 26. 검색 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/search")
    public BaseResponse<List<GetProductRes>> getSearchProducts(@RequestParam("word") String word, @RequestParam(value = "page", required = false) Integer page) throws BaseException {
        List<GetProductRes> getSearchProducts;
        if (page != null) getSearchProducts = productProvider.getSearchProducts(word, page);
        else getSearchProducts = productProvider.getSearchProducts(word);
        return new BaseResponse<>(getSearchProducts);
    }

    /**
     * 27. 작품 상세 조회 API
     * @return BaseException<GetDetailTotalRes>>
     */
    @GetMapping("/{productId}")
    public BaseResponse<GetDetailTotalRes> getProductDetail(@PathVariable("productId") int productId) throws BaseException {
        if(jwtService.getJwt() != null){
            int userId = jwtService.getUserId();
            productService.addView(userId, productId);
            productService.updateUserResent(userId, productId);
        }
        GetDetailRes getDetailRes = productProvider.getProductDetail(productId);
        List<Review> getReview = productProvider.getProductReviews(productId);
        List<ProductKeyword> getProductKeyword = productProvider.getProductKeywords(productId);
        List<Comment> getComment = productProvider.getProductComments(productId);
        Maker getMaker = productProvider.getMakerInfo(productId);
        GetDetailTotalRes getDetailTotalRes = new GetDetailTotalRes(getDetailRes, getReview, getProductKeyword, getComment, getMaker);
        return new BaseResponse<>(getDetailTotalRes);
    }

    /**
     * 28. 작품 옵션 조회 API
     * @return BaseException<List<GetOptionsRes>>
     */
    @GetMapping("/{productId}/option")
    public BaseResponse<List<GetOptionRes>> getProductOptions(@PathVariable("productId") int productId) throws BaseException {
        List<GetOptionRes> getProductOptions = productProvider.getProductOptions(productId);
        return new BaseResponse<>(getProductOptions);
    }

    /**
     * 29. 작품 찜 API
     * @return BaseException<String></String>
     */
    @PostMapping("/{productId}/like")
    public BaseResponse<String> likeProduct(@PathVariable("productId") int productId) throws BaseException {
        int userId = jwtService.getUserId();
        String likeResult = productService.likeProduct(userId, productId);
        return new BaseResponse<>(likeResult);
    }

    /**
     * 30. 작품 쿠폰 조회 API
     * @return BaseException
     */
    @GetMapping("/{productId}/coupon")
    public BaseResponse<List<GetCouponRes>> getProductCoupons(@PathVariable("productId") int productId) throws BaseException {
        List<GetCouponRes> getCouponRes = productProvider.getProductCoupons(productId);
        return new BaseResponse<>(getCouponRes);
    }

    /**
     * 31. 작품 쿠폰 받기 API
     * @return BaseException<String></String>
     */
    @PostMapping("/{productCouponId}")
    public BaseResponse<String> takeCoupon(@PathVariable("productCouponId") int productCouponId) throws BaseException {
        int userId = jwtService.getUserId();
        String result = productService.takeCoupon(userId, productCouponId);
        return new BaseResponse<>(result);
    }

    /**
     * 32. 구매후기 더보기 조회 API
     * @return BaseException<List<Review>>
     */
    @GetMapping("/{productId}/review")
    public BaseResponse<List<Review>> getReviewMore(@PathVariable("productId") int productId, @RequestParam(value = "page", required = false) Integer page) throws BaseException {
        List<Review> getReview = productProvider.getProductReviews(productId, page);
        return new BaseResponse<>(getReview);
    }

    /**
     * 33. 이전 댓글 더보기 조회 API
     * @return BaseException<GetDetailTotalRes>>
     */
    @GetMapping("/{productId}/comment")
    public BaseResponse<List<Comment>> getProductDetail(@PathVariable("productId") int productId , @RequestParam(value = "page", required = false) Integer page) throws BaseException {
        List<Comment> getComment = productProvider.getProductComments(productId, page);
        return new BaseResponse<>(getComment);
    }

}
