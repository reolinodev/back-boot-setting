package com.back.api.domain;

import com.back.api.domain.common.ValidationGroups.CodeAllGroup;
import com.back.api.domain.common.ValidationGroups.CodeGrpAllGroup;
import com.back.api.domain.common.ValidationGroups.CodeGrpPartGroup;
import com.back.api.domain.common.ValidationGroups.CodePartGroup;
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
@ApiModel(description = "코드")
public class Code {

    @ApiModelProperty(example = "코드 아이디")
    public int code_id;

    @NotEmpty(groups = { CodePartGroup.class, CodeAllGroup.class }, message = "코드 그룹 아이디를 입력해주세요.")
    @ApiModelProperty(example = "코드 그룹 아이디")
    public int code_grp_id;

    @NotEmpty(groups = { CodePartGroup.class, CodeAllGroup.class }, message = "코드 명을 입력해주세요.")
    @Size(groups = { CodePartGroup.class, CodeAllGroup.class },max=15, message = "최대 15자로 입력해주세요")
    @ApiModelProperty(example = "코드 명")
    public String code_nm;

    @NotEmpty(groups = {CodeAllGroup.class }, message = "코드 값을 입력해주세요.")
    @Size(groups = {CodeAllGroup.class },max=10, message = "최대 10자로 입력해주세요")
    @ApiModelProperty(example = "코드 값")
    public String code_val;

    @Size(groups = { CodePartGroup.class, CodeAllGroup.class },max=165, message = "최대 165자로 입력해주세요")
    @ApiModelProperty(example = "비고")
    public String bigo;

    @Size(groups = { CodePartGroup.class, CodeAllGroup.class },max=10, message = "최대 10자로 입력해주세요")
    @ApiModelProperty(example = "순서")
    public String ord;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "수정자")
    public int updated_id;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}
