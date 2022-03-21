package com.example.demo.src.user.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRes {
    private int userId;
    private String jwt;
}
