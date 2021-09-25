package com.back.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "사용자")
public class User {
    
    private String id;
    
	@ApiModelProperty(example = "이름")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min=2, max=10, message = "최소 2자에서 10자사이로 입력해주세요") 
    private String name;

    @ApiModelProperty(example = "이메일")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.") 
    private String email;

    @ApiModelProperty(example = "생년월일")
    @Pattern(regexp = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))", message = "생년월일 형식이 올바르지 않습니다. ex)19000101 ") 
    private String birth;

    @ApiModelProperty(example = "비밀 번호")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(max=20, message = "최대 20자를 넘길수 없습니다,") 
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;
    
    @ApiModelProperty(example = "휴대폰 번호")
    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "올바른 휴대폰번호 형식이 아닙니다. ex) 01011112222")
    private String phone;
}