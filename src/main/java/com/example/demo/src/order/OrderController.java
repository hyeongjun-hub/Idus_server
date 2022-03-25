package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.response.GetCreateOrderRes;
import com.example.demo.src.order.model.response.GetOrderDetailRes;
import com.example.demo.src.order.model.response.GetOrderRes;
import com.example.demo.src.order.model.request.PostOrderReq;
import com.example.demo.utils.JwtService;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderProvider orderProvider;
    private final OrderService orderService;
    private final JwtService jwtService;

    /**
     * 37. 주문하기 API
     *
     * @param postOrderReq
     * @return BaseResponse<Integer>
     */
    @PostMapping("/new")
    public BaseResponse<GetCreateOrderRes> createOrder(@Valid @RequestBody PostOrderReq postOrderReq) throws BaseException {
        int userId = jwtService.getUserId();
        int result = orderService.createOrder(userId, postOrderReq);
        GetCreateOrderRes createOrderRes = new GetCreateOrderRes(result);
        return new BaseResponse<>(createOrderRes);
    }

    /**
     * 39. 유저 주문 조회 API
     *
     * @return BaseResponse<List < GetOrderRes>>
     */
    @GetMapping("/list")
    public BaseResponse<List<GetOrderRes>> getOrders() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetOrderRes> getOrderRes = orderProvider.getOrders(userId);
        return new BaseResponse<>(getOrderRes);
    }

    /**
     * 40. 주문 상세 조회 API
     *
     * @param orderListId
     * @return BaseResponse<List < GetOrderDetailRes > >
     */
    @GetMapping("/{orderListId}")
    public BaseResponse<GetOrderDetailRes> getOrder(@PathVariable("orderListId") int orderListId) throws BaseException {
        int userId = jwtService.getUserId();
        // user 확인
        orderService.checkUser(userId, orderListId);
        GetOrderDetailRes getOrderDetailRes = orderProvider.getOrder(orderListId);
        return new BaseResponse<>(getOrderDetailRes);
    }
}
