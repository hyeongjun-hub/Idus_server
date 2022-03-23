package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Maker {
    private int makerId;
    private String makerProfileImageUrl;
    private String makerName;
    private double star;
    private int reviewCount;
    private int likeCount;
    private int followerCount;
    private int supportCount;
    private String profileDetail;
}
