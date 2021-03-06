package com.example.demo.src.category.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCategoryRes {
    private int categoryId;
    private String categoryName;
    private String iconUrl;
}
