package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.response.GetTodayRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public void addView(int userId, int productId) throws BaseException {
        try{
            int result = productMapper.addView(userId, productId);
            if(result == 0){
                throw new BaseException(CREATE_FAIL_VIEW);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void updateUserResent(int userId, int productId) throws BaseException {
        try{
            int result = productMapper.updateUserResent(userId, productId);
            if(result == 0){
                throw new BaseException(UPDATE_FAIL_USER_RESENT);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
