package com.example.demo.src.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPhoneAuthReq {
    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{8})$", message = "전화번호 형식을 확인해주세요.")
    private String phone;
    private String authNumber;
}
