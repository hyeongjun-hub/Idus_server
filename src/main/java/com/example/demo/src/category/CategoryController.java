package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.response.GetCategoryRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryProvider categoryProvider;

    /**
     * 17. 카테고리 조회 API
     *
     * @return BaseResponse<List<GetCategoryRes>>
     */
    @GetMapping("") // (GET) /categories
    public BaseResponse<List<GetCategoryRes>> getCategories() throws BaseException {
        List<GetCategoryRes> getCategoriesRes;
        getCategoriesRes = categoryProvider.getCategories();
        return new BaseResponse<>(getCategoriesRes);
    }

}
