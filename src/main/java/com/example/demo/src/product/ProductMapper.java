package com.example.demo.src.product;

import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.product.model.entity.Review;
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

    List<GetProductRes> getTodayMore();

    List<GetProductRes> getCategoryProducts(int categoryId);

    List<GetProductRes> getSearchProducts(String word);

    GetDetailRes getProductDetail(int productId);

    List<Review> getProductReviews(int productId);

    List<ProductKeyword> getProductKeywords(int productId);

    List<Comment> getProductComments(int productId);

    Maker getMakerInfo(int productId);

    List<GetOptionRes> getProductOptions(int productId);

    int addView(@Param("userId") int userId, @Param("productId") int productId);
    int updateUserResent(@Param("userId") int userId, @Param("productId") int productId);
}
