package com.example.demo.src.user.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserReq {
    @NotNull(message = "유저 식별자를 입력하세요.")
    private int userId;
    private String userName;
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{8})$", message = "전화번호 형식을 확인해주세요.")
    private String phone;
    private String profileImageUrl;
    private String birthday;
    @Length(max = 1, message = "'M' 또는 'F'를 입력하세요.")
    private String gender;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String identityTest;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String fingerPrint;
    @Length(max = 1, message = "'Y' 또는 'N'을 입력하세요.")
    private String alarm;
}
