package com.example.demo.src.product;

import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.product.model.entity.Review;
import com.example.demo.src.product.model.request.GetOptionReq;
import com.example.demo.src.product.model.request.PostLikeProductReq;
import com.example.demo.src.product.model.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    List<GetTodayRes> getTodayProducts();

    List<GetLiveRes> getLiveProducts();

    List<GetNewRes> getNewProducts();

    List<GetProductRes> getFirstBuy();

    List<GetProductRes> getRelateProducts(int userId);

    int getProductId(int userId);

    List<GetProductRes> getMaybeProducts(int productId);

    List<GetProductRes> getTodayMore();

    List<GetProductRes> getCategoryProducts(int categoryId);

    List<GetProductRes> getSearchProducts(String word);

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
}
