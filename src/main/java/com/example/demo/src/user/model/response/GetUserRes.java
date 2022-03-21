package com.example.demo.src.user.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRes {
    private int userId;
    private String userName;
    private String email;
    private String phone;
    private int couponCount;
    private String gradeId;
    private String iconUrl;
    private String gradeName;
    private String profileImageUrl;
    private int point;
    private String birthday;
    private String gender;
    private String identityTest;
    private String fingerPrint;
    private String alarm;
}
