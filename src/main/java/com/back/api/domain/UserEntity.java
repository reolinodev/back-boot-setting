package com.back.api.domain;

import com.back.api.domain.common.Param;
import com.back.api.domain.common.ValidationGroups.UserGroup1;
import com.back.api.domain.common.ValidationGroups.UserGroup2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자 엔티티")
public class UserEntity extends User {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "사용여부")
    private String use_yn_nm;
}
