package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.response.GetLiveRes;
import com.example.demo.src.product.model.response.GetNewRes;
import com.example.demo.src.product.model.response.GetProductRes;
import com.example.demo.src.product.model.response.GetTodayRes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 22. 카테고리 작품 조회 API
     * @return BaseException<List<GetProductRes>>
     */
    @GetMapping("/categories")
    public BaseResponse<List<GetProductRes>> getCategoryProducts(@RequestParam("categoryId") int categoryId) throws BaseException {
        List<GetProductRes> getCategoryProducts = productProvider.getCategoryProducts(categoryId);
        return new BaseResponse<>(getCategoryProducts);
    }

}
