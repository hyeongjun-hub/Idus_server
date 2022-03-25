package com.example.demo.src.order.model.response;

import com.example.demo.src.order.model.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class GetOrderDetailRes {
    private int orderListId;
    private List<OrderDetail> orderDetailList;
    private String taker;
    private String phone;
    private String address;
    private String paymentMethodName;
    private int priceSum;
    private int deliveryTipAll;
    private String isSupport;
}
