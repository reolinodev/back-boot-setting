package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "코드 그룹")
public class CodeGrp {

    @ApiModelProperty(example = "코드 그룹 아이디")
    private int code_grp_id;

    @ApiModelProperty(example = "코드 그룹 명")
    private String code_grp_nm;

    @ApiModelProperty(example = "생성시간")
    private String created_at;

    @ApiModelProperty(example = "수정시간")
    private String updated_at;

    @ApiModelProperty(example = "수정자")
    private int updated_id;

    @ApiModelProperty(example = "사용여부")
    private String use_yn;
}
