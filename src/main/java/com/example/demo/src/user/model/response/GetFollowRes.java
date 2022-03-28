package com.example.demo.src.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFollowRes {
    private int followId;
    private String thumbnailImageUrl;
    private String makerProfileImageUrl;
    private String makerName;
    private String profileDetail;
}
