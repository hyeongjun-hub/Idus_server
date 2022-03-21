package com.example.demo.src.user.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    @NotBlank(message = "이름을 입력하세요.")
    private String userName;
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호 길이를 확인해주세요.")
    private String password;
    private String gradeId;
    @NotBlank(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{8})$", message = "전화번호 형식을 확인해주세요.")
    private String phone;
    private String profileImageUrl;
    @PositiveOrZero(message = "0 또는 양수만 가능합니다.")
    private int point;
    private String birthday;
    @Length(max = 1, message = "'M' 또는 'F'를 입력하세요.")
    private String gender;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String identityTest;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String isLogin;
    private String platform;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String fingerPrint;
    private int productId;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String alarm;
    private String createdAt;
    private String updatedAt;
    private String status;
}
