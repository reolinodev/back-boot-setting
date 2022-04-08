package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "권한")
public class Auth {

    @ApiModelProperty(example = "권한 아이디")
    public int auth_id;

    @ApiModelProperty(example = "권한 명")
    public String auth_nm;

    @ApiModelProperty(example = "권한 구분")
    public String auth_role;

    @ApiModelProperty(example = "순서")
    public int ord;

    @ApiModelProperty(example = "비고")
    public String bigo;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "수정자")
    public String updated_id;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
