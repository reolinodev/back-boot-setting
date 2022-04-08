package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자")
public class User {

    @ApiModelProperty(example = "사용자 아이디")
    public int user_id;

    @ApiModelProperty(example = "로그인 아이디")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    public String login_id;

    @ApiModelProperty(example = "이메일")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    public String email;

    @ApiModelProperty(example = "사용자 이름")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    public String user_nm;

    @ApiModelProperty(example = "사용자 패스워드")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(max=20, message = "최대 20자를 넘길수 없습니다,")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    public String user_pw;

    @ApiModelProperty(example = "생성 시간")
    public String created_at;

    @ApiModelProperty(example = "수정 시간")
    public String updated_at;

    @ApiModelProperty(example = "마지막 로그인 시간")
    public String last_login_at;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
