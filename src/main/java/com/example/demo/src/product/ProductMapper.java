package com.example.demo.src.product;

import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.review.model.entity.Review;
import com.example.demo.src.product.model.request.GetOptionReq;
import com.example.demo.src.product.model.request.PostLikeProductReq;
import com.example.demo.src.product.model.response.*;
import com.example.demo.src.user.model.response.GetCouponRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    List<GetTodayRes> getTodayProducts();

    List<GetLiveRes> getLiveProducts();
    List<GetLiveRes> getLiveProductsWithPage(@Param("offset")int offset);

    List<GetNewRes> getNewProducts();
    List<GetNewRes> getNewProductsWithPage(int offset);

    List<GetProductRes> getFirstBuy();
    List<GetProductRes> getFirstBuyWithPage(int offset);

    List<GetProductRes> getRelateProducts(int userId);
    List<GetProductRes> getRelateProductsWithPage(@Param("userId")int userId, @Param("offset")int offset);

    int getProductId(int userId);

    List<GetProductRes> getMaybeProducts(int productId);
    List<GetProductRes> getMaybeProductsWithPage(@Param("productId")int productId, @Param("offset") int offset);

    List<GetProductRes> getTodayMore();
    List<GetProductRes> getTodayMoreWithPage(int offset);

    List<GetProductRes> getCategoryProducts(int categoryId);
    List<GetProductRes> getCategoryProductsWithPage(@Param("categoryId")int categoryId, @Param("offset") int offset);

    List<GetProductRes> getSearchProducts(String word);
    List<GetProductRes> getSearchProductsWithPage(@Param("word")String word, @Param("offset")int offset);

    GetDetailRes getProductDetail(int productId);

    List<Review> getProductReviews(int productId);

    List<ProductKeyword> getProductKeywords(int productId);

    List<Comment> getProductComments(int productId);

    Maker getMakerInfo(int productId);

    List<GetOptionRes> getOptionInit(int productId);

    List<GetOptionDetailRes> getOptionInfo(GetOptionReq getOptionReq);

    int addView(@Param("userId") int userId, @Param("productId") int productId);

    int updateUserResent(@Param("userId") int userId, @Param("productId") int productId);

    int checkLike(@Param("userId") int userId, @Param("productId") int productId);

    int likeProduct(@Param("userId") int userId, @Param("productId") int productId);

    String getLikeStatus(@Param("userId") int userId, @Param("productId") int productId);

    int setLikeStatus(PostLikeProductReq postLikeProductReq);

    List<GetCouponRes> getProductCoupons(int productId);

    // check coupon
    int checkCoupon(@Param("userId") int userId, @Param("productCouponId") int productCouponId);

    int takeCoupon(@Param("userId") int userId, @Param("productCouponId") int productCouponId);
}
