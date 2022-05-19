package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자 권한")
public class UserAuthEntity extends UserAuth {

    @ApiModelProperty(example = "사용자 아이디")
    public int user_id;

    @ApiModelProperty(example = "로그인 아이디")
    public String login_id;

    @ApiModelProperty(example = "이메일")
    public String email;

    @ApiModelProperty(example = "이름")
    public String user_nm;

    @ApiModelProperty(example = "이름 항목")
    public int[] user_arr;
}
