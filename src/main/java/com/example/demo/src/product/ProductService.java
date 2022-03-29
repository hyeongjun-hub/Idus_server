package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.request.PostLikeProductReq;
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
        int result = productMapper.addView(userId, productId);
        if (result == 0) {
            throw new BaseException(CREATE_FAIL_VIEW);
        }
    }

    public void updateUserResent(int userId, int productId) throws BaseException {
        int result = productMapper.updateUserResent(userId, productId);
        if (result == 0) {
            throw new BaseException(UPDATE_FAIL_USER_RESENT);
        }
    }

    public String likeProduct(int userId, int productId) throws BaseException {
        // likeList에서 중복 검사
        String likeResult = "";
        if(productMapper.checkLike(userId, productId) == 1) {
            if(productMapper.getLikeStatus(userId, productId).equals("Y")){
                productMapper.setLikeStatus(new PostLikeProductReq(userId, productId, "N"));
                likeResult = "작품 찜 취소.";
            }else{
                productMapper.setLikeStatus(new PostLikeProductReq(userId, productId, "Y"));
                likeResult = "작품을 찜했습니다.";
            }
        }
        else {int result = productMapper.likeProduct(userId, productId);
            if (result == 0){
            throw new BaseException(UPDATE_FAIL_USER_RESENT);
            }
            likeResult = "작품을 찜했습니다.";
        }
        return likeResult;
    }

}
