package com.example.demo.src.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    private int pointListId;
    private String createdAt;
    private String productName;
    private int usePoint;
    private String pointString;
}
