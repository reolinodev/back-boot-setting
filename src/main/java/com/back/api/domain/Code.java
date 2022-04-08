package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "권한별 메뉴")
public class Code {

    @ApiModelProperty(example = "코드 아이디")
    public int code_id;

    @ApiModelProperty(example = "코드 그룹 아이디")
    public int code_grp_id;

    @ApiModelProperty(example = "코드 명")
    public String code_nm;

    @ApiModelProperty(example = "비고")
    public String bigo;

    @ApiModelProperty(example = "순서")
    public int ord;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "수정자")
    public String updated_id;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
