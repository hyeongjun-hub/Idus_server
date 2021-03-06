package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.entity.Comment;
import com.example.demo.src.product.model.entity.Maker;
import com.example.demo.src.product.model.entity.ProductKeyword;
import com.example.demo.src.product.model.entity.Query;
import com.example.demo.src.review.model.entity.Review;
import com.example.demo.src.product.model.request.GetOptionReq;
import com.example.demo.src.product.model.response.*;
import com.example.demo.src.user.model.response.GetCouponRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class ProductProvider {
    private final ProductMapper productMapper;

    public List<GetTodayRes> getTodayProducts() throws BaseException {
        try {
            return productMapper.getTodayProducts();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetLiveRes> getLiveProducts() throws BaseException {
        try {
            return productMapper.getLiveProducts();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetLiveRes> getLiveProducts(int page) throws BaseException {
        try {
            return productMapper.getLiveProductsWithPage((page - 1) * 10);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetNewRes> getNewProducts() throws BaseException {
        try {
            List<GetNewRes> getNewRes = productMapper.getNewProducts();
            return getNewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetNewRes> getNewProducts(int page) throws BaseException {
        try {
            List<GetNewRes> getNewRes = productMapper.getNewProductsWithPage((page - 1) * 10);
            return getNewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getFirstBuy() throws BaseException {
        try {
            List<GetProductRes> getFirstBuy = productMapper.getFirstBuy();
            return getFirstBuy;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getFirstBuy(int page) throws BaseException {
        try {
            List<GetProductRes> getFirstBuy = productMapper.getFirstBuyWithPage((page - 1) * 10);
            return getFirstBuy;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getRelate(int userId) throws BaseException {
        try {
            List<GetProductRes> getRelate = productMapper.getRelateProducts(userId);
            return getRelate;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getRelate(int userId, int page) throws BaseException {
        try {
            List<GetProductRes> getRelate = productMapper.getRelateProductsWithPage(userId, (page - 1) * 10);
            return getRelate;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getMaybe(int userId) throws BaseException {
        try {
            int productId = productMapper.getProductId(userId);
            List<GetProductRes> getMaybe = productMapper.getMaybeProducts(productId);
            return getMaybe;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getMaybe(int userId, int page) throws BaseException {
        try {
            int productId = productMapper.getProductId(userId);
            List<GetProductRes> getMaybe = productMapper.getMaybeProductsWithPage(productId, (page - 1) * 10);
            return getMaybe;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getTodayMore() throws BaseException {
        try {
            List<GetProductRes> getTodayMore = productMapper.getTodayMore();
            return getTodayMore;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getTodayMore(int page) throws BaseException {
        try {
            List<GetProductRes> getTodayMore = productMapper.getTodayMoreWithPage((page - 1) * 10);
            return getTodayMore;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getCategoryProducts(int categoryId) throws BaseException {
        try {
            List<GetProductRes> categoryProducts = productMapper.getCategoryProducts(categoryId);
            return categoryProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getCategoryProducts(int categoryId, int page) throws BaseException {
        try {
            List<GetProductRes> categoryProducts = productMapper.getCategoryProductsWithPage(categoryId, (page - 1) * 10);
            return categoryProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getCategoryProducts(Query query) throws BaseException {
        try {
            List<GetProductRes> categoryProducts = productMapper.getCategoryProductsWithPageAndFilter(query);
            return categoryProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getSearchProducts(String word) throws BaseException {
        try {
            List<GetProductRes> searchProducts = productMapper.getSearchProducts(word);
            return searchProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getSearchProducts(String word, int page) throws BaseException {
        try {
            List<GetProductRes> searchProducts = productMapper.getSearchProductsWithPage(word, (page - 1) * 10);
            return searchProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetDetailRes getProductDetail(int productId) throws BaseException {
        try {
            GetDetailRes getDetailRes = productMapper.getProductDetail(productId);
            return getDetailRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Review> getProductReviews(int productId) throws BaseException {
        try {
            List<Review> getReviews = productMapper.getProductReviews(productId);
            return getReviews;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Review> getProductReviews(int productId, int page) throws BaseException {
        try {
            List<Review> getReviews = productMapper.getProductReviewsWithPage(productId, (page - 1) * 5);
            return getReviews;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ProductKeyword> getProductKeywords(int productId) throws BaseException {
        try {
            List<ProductKeyword> getKeyword = productMapper.getProductKeywords(productId);
            return getKeyword;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Comment> getProductComments(int productId) throws BaseException {
        try {
            List<Comment> getComment = productMapper.getProductComments(productId);
            return getComment;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<Comment> getProductComments(int productId, int page) throws BaseException {
        try {
            List<Comment> getComment = productMapper.getProductCommentsWithPage(productId, (page - 1) * 5);
            return getComment;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Maker getMakerInfo(int productId) throws BaseException {
        try {
            Maker getMakerInfo = productMapper.getMakerInfo(productId);
            return getMakerInfo;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetOptionRes> getProductOptions(int productId) throws BaseException {
        List<GetOptionRes> getOptionRes = productMapper.getOptionInit(productId);
        for (GetOptionRes a : getOptionRes) {
            a.setOption(productMapper.getOptionInfo(new GetOptionReq(a.getTitle(), productId)));
        }
        try {
            return getOptionRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCouponRes> getProductCoupons(int productId) throws BaseException {
        try {
            List<GetCouponRes> getCouponRes = productMapper.getProductCoupons(productId);
            return getCouponRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
