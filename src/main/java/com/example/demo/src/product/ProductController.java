package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.product.model.entity.Review;
import com.example.demo.src.product.model.response.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductProvider productProvider;
    private final ProductService productService;

    /**
     * 19. 투데이 탭 조회 API
     * @return BaseException<List<GetTodayRes>>
     */
    @GetMapping("/today")
    public BaseResponse<List<GetTodayRes>> getTodayProducts() throws BaseException {
        List<GetTodayRes> getTodayRes = productProvider.getTodayProducts();
        return new BaseResponse<>(getTodayRes);
    }

    /**
     * 20. 실시간 탭 조회 API
     * @return BaseException<List<GetLiveRes>>
     */
    @GetMapping("/live")
    public BaseResponse<List<GetLiveRes>> getLiveProducts() throws BaseException {
        List<GetLiveRes> getLiveRes = productProvider.getLiveProducts();
        return new BaseResponse<>(getLiveRes);
    }

    /**
     * 21. NEW 탭 조회 API
     * @return BaseException<List<GetLiveRes>>
     */
    @GetMapping("/new")
    public BaseResponse<List<GetNewRes>> getNewProducts() throws BaseException {
        List<GetNewRes> getLiveRes = productProvider.getNewProducts();
        return new BaseResponse<>(getLiveRes);
    }

    /**
     * 25. 오늘의 작품 더보기 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/today/more")
    public BaseResponse<List<GetProductRes>> getTodayMore() throws BaseException {
        List<GetProductRes> getTodayMore = productProvider.getTodayMore();
        return new BaseResponse<>(getTodayMore);
    }

    /**
     * 26. 카테고리 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/categories")
    public BaseResponse<List<GetProductRes>> getCategoryProducts(@RequestParam("categoryId") int categoryId) throws BaseException {
        List<GetProductRes> getCategoryProducts = productProvider.getCategoryProducts(categoryId);
        return new BaseResponse<>(getCategoryProducts);
    }

    /**
     * 27. 검색 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/search")
    public BaseResponse<List<GetProductRes>> getSearchProducts(@RequestParam("word") String word) throws BaseException {
        List<GetProductRes> getSearchProducts = productProvider.getSearchProducts(word);
        return new BaseResponse<>(getSearchProducts);
    }

    /**
     * 28. 작품 상세 조회 API
     * @return BaseException<GetDetailTotalRes>>
     */
    @GetMapping("/{productId}")
    public BaseResponse<GetDetailTotalRes> getProductDetail(@PathVariable("productId") int productId) throws BaseException {
        GetDetailRes getDetailRes = productProvider.getProductDetail(productId);
        List<Review> getReview = productProvider.getProductReviews(productId);
        List<ProductKeyword> getProductKeyword = productProvider.getProductKeywords(productId);
        List<Comment> getComment = productProvider.getProductComments(productId);
        Maker getMaker = productProvider.getMakerInfo(productId);
        GetDetailTotalRes getDetailTotalRes = new GetDetailTotalRes(getDetailRes, getReview, getProductKeyword, getComment, getMaker);
        return new BaseResponse<>(getDetailTotalRes);
    }

    /**
     * 29. 작품 옵션 조회 API
     * @return BaseException
     */
    @GetMapping("/{productId}/option")
    public BaseResponse<List<GetOptionRes>> getProductOptions(@PathVariable("productId") int productId) throws BaseException {
        List<GetOptionRes> getProductOptions = productProvider.getProductOptions(productId);
        return new BaseResponse<>(getProductOptions);
    }
}
