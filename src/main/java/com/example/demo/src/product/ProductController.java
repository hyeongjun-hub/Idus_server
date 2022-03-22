package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.response.GetTodayRes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
