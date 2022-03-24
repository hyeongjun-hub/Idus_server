package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cart.model.request.PostCartReq;
import com.example.demo.src.cart.model.request.PostOrderDetailReq;
import com.example.demo.src.cart.model.request.PostSmallCartReq;
import com.example.demo.src.cart.model.response.GetCartRes;
import com.example.demo.src.cart.model.response.PostCartRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartProvider cartProvider;
    private final CartService cartService;
    private final JwtService jwtService;

    /**
     * 33. 장바구니에 작품담기 API
     *
     * @return BaseResponse<PostCartRes>
     */
    // body에 productId, productOptionId의 List, 수량, isDirectOrder, price, deliveryTip 를 받음
    // userId와 매칭하는 status=Y인 Cart 존재 여부 확인 없으면 생성 -> cartId return (TODO: isDirectOrder 처리)
    // 받은 cartId + 수량으로 새로운 smallCart생성 후 smallCartId return (TODO: productID에 따라 List 처리)
    // 받은 smallCartId로 여러 orderDetail생성
    // 최종적으로 cartId, smallCartId return
    @PostMapping("/new")
    public BaseResponse<PostCartRes> createCart(@Valid @RequestBody PostCartReq postCartReq) throws BaseException {
        int userId = jwtService.getUserId();
        int cartId = cartService.createCart(userId, postCartReq);
        PostSmallCartReq postSmallCartReq = new PostSmallCartReq(0, cartId, postCartReq.getProductId(), postCartReq.getCount(), postCartReq.getPrice(), postCartReq.getDeliveryTip());
        int smallCartId = cartService.createSmallCart(postSmallCartReq);
        PostOrderDetailReq postOrderDetailReq = new PostOrderDetailReq(postCartReq.getProductOptionId(), smallCartId);
        cartService.createOrderDetail(postOrderDetailReq);
        PostCartRes postCartRes = new PostCartRes(cartId, smallCartId);
        return new BaseResponse<>(postCartRes);
    }

    /**
     * 34. 장바구니 조회 API
     *
     * @return BaseResponse<List<GetCartRes>>
     */
    @GetMapping("")
    public BaseResponse<GetCartRes> getCart() throws BaseException {
        int userId = jwtService.getUserId();
        GetCartRes getCartRes = cartProvider.getCart(userId);
        return new BaseResponse<>(getCartRes);
    }

    /**
     * 장바구니 삭제 API
     *
     * @param userCartId
     * @return BaseResponse<String>
     */
    @PatchMapping("{userCartId}/delete")
    public BaseResponse<String> delCart(@PathVariable int userCartId) throws BaseException {
        cartService.delCart(userCartId);
        String result = "";
        return new BaseResponse<>(result);
    }

}
