package com.back.api.domain;

import com.back.api.domain.common.Param;
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
public class CodeGrp extends Param {

    @ApiModelProperty(example = "코드 그룹 아이디")
    @NotEmpty(groups = { CodeGrpGroup2.class }, message = "Please enter your code group ID.")
    public int code_grp_id;

    @ApiModelProperty(example = "코드 그룹 명")
    @NotEmpty(groups = { CodeGrpGroup1.class, CodeGrpGroup2.class }, message = "Please enter the code group name.")
    @Size(groups = { CodeGrpGroup1.class, CodeGrpGroup2.class },max=15, message = "Please enter up to 15 characters.")
    public String code_grp_nm;

    @NotEmpty(groups = { CodeGrpGroup1.class }, message = "Please enter the code group value.")
    @Size(groups = { CodeGrpGroup1.class }, max=10, message = "Please enter up to 10 characters.")
    @ApiModelProperty(example = "코드 그룹 값")
    public String code_grp_val;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "수정자")
    public int updated_id;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
