package com.example.demo.src.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchAddressReq {
    @NotNull(message = "주소 식별자를 입력하세요.")
    private int addressId;
    private String taker;
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{8})$", message = "전화번호 형식을 확인해주세요.")
    private String phone;
    private String address;
}
