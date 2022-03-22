package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.response.GetLiveRes;
import com.example.demo.src.product.model.response.GetNewRes;
import com.example.demo.src.product.model.response.GetProductRes;
import com.example.demo.src.product.model.response.GetTodayRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class ProductProvider {
    private final ProductMapper productMapper;

    public List<GetTodayRes> getTodayProducts() throws BaseException{
        try{
            return productMapper.getTodayProducts();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetLiveRes> getLiveProducts() throws BaseException{
        try{
            return productMapper.getLiveProducts();
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetNewRes> getNewProducts() throws BaseException{
        try{
            List<GetNewRes> getNewRes = productMapper.getNewProducts();
            return getNewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getTodayMore() throws BaseException{
        try{
            List<GetProductRes> getTodayMore = productMapper.getTodayMore();
            return getTodayMore;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getCategoryProducts(int categoryId) throws BaseException{
        try{
            List<GetProductRes> categoryProducts = productMapper.getCategoryProducts(categoryId);
            return categoryProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getSearchProducts(String word) throws BaseException{
        try{
            List<GetProductRes> searchProducts = productMapper.getSearchProducts(word);
            return searchProducts;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
