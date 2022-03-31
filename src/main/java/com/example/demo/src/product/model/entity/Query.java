package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    private int categoryId;
    private String delivery;
    private String price;
    private String discountRate;
    private String order;
    private int offset;
}
