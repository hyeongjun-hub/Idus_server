package com.example.demo.src.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUserReq {
    @NotBlank(message = "이름을 입력하세요.")
    private String userName;
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호 길이를 확인해주세요")
    private String password;
    @NotBlank(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{8})$", message = "전화번호 형식을 확인해주세요.")
    private String phone;
    private String platform;
    private int userId;
}
