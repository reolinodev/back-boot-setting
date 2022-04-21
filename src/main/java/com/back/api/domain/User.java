package com.back.api.domain;

import com.back.api.domain.common.ValidationGroups.CodeGroup1;
import com.back.api.domain.common.ValidationGroups.CodeGroup2;
import com.back.api.domain.common.ValidationGroups.UserGroup1;
import com.back.api.domain.common.ValidationGroups.UserGroup2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(groups = { UserGroup1.class, UserGroup2.class }, message = "Please enter ID.")
    @Size(groups = { UserGroup1.class }, max=50, message = "Please enter up to 50 characters.")
    public String login_id;

    @ApiModelProperty(example = "사용자 이름")
    @NotEmpty(groups = { UserGroup1.class }, message = "Please enter name.")
    @Size(groups = { UserGroup1.class }, max=15, message = "Please enter up to 15 characters.")
    public String user_nm;

    @ApiModelProperty(example = "사용자 패스워드")
    @NotEmpty(groups = { UserGroup1.class, UserGroup2.class }, message = "Please enter password.")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", groups = { UserGroup1.class, UserGroup2.class }
//        , message = "The password must be between 8 and 20 characters, containing at least one uppercase letter, lowercase letter, number, and special symbol.")
    public String user_pw;

    @ApiModelProperty(example = "이메일")
    @NotEmpty(groups = { UserGroup1.class }, message = "Please enter email.")
    @Email(groups = { UserGroup1.class }, message = "Email format is incorrect.")
    public String email;

    @ApiModelProperty(example = "휴대폰")
    @NotEmpty(groups = { UserGroup1.class }, message = "Please enter phone number.")
    @Pattern(groups = { UserGroup1.class }, regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$"
        , message = "Only 10 to 11 digits can be entered.")
    public String cell_phone;

    @ApiModelProperty(example = "생성 시간")
    public String created_at;

    @ApiModelProperty(example = "수정 시간")
    public String updated_at;

    @ApiModelProperty(example = "마지막 로그인 시간")
    public String last_login_at;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
