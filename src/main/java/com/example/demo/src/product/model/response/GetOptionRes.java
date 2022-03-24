package com.example.demo.src.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOptionRes {
    private int productOptionId;
    private int productId;
    private String title;
    private String content;
    private int addPrice;
}
