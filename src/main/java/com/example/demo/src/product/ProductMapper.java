package com.example.demo.src.product;

import com.example.demo.src.product.model.response.GetLiveRes;
import com.example.demo.src.product.model.response.GetNewRes;
import com.example.demo.src.product.model.response.GetProductRes;
import com.example.demo.src.product.model.response.GetTodayRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    List<GetTodayRes> getTodayProducts();
    List<GetLiveRes> getLiveProducts();
    List<GetNewRes> getNewProducts();
    List<GetProductRes> getCategoryProducts(int categoryId);
}
