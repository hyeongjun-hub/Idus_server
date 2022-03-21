package com.example.demo.src.category.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String iconUrl;
    private String createdAt;
    private String updatedAt;
    private String status;
}
