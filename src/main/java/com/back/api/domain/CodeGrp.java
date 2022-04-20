package com.back.api.domain;

import com.back.api.domain.common.ValidationGroups.CodeGrpGroup1;
import com.back.api.domain.common.ValidationGroups.CodeGrpGroup2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "코드 그룹")
public class CodeGrp {

    @ApiModelProperty(example = "코드 그룹 아이디")
    @NotEmpty(groups = { CodeGrpGroup2.class }, message = "코드 그룹 아이디를 입력해주세요.")
    private int code_grp_id;

    @ApiModelProperty(example = "코드 그룹 명")
    @NotEmpty(groups = { CodeGrpGroup1.class, CodeGrpGroup2.class }, message = "코드 그룹 명을 입력해주세요.")
    @Size(groups = { CodeGrpGroup1.class, CodeGrpGroup2.class },max=15, message = "최대 15자로 입력해주세요")
    private String code_grp_nm;

    @NotEmpty(groups = { CodeGrpGroup1.class }, message = "코드 그룹 값을 입력해주세요.")
    @Size(groups = { CodeGrpGroup1.class }, max=10, message = "최대 10자로 입력해주세요")
    @ApiModelProperty(example = "코드 그룹 값")
    private String code_grp_val;

    @ApiModelProperty(example = "생성시간")
    private String created_at;

    @ApiModelProperty(example = "수정시간")
    private String updated_at;

    @ApiModelProperty(example = "수정자")
    private int updated_id;

    @ApiModelProperty(example = "사용여부")
    private String use_yn;
}
