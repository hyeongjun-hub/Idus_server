package com.example.demo.src.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOptionRes {
    private int productId;
    private String title;
    private List<GetOptionDetailRes> option;
}
