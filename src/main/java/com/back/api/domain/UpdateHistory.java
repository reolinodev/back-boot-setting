package com.back.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "업데이트 이력")
public class UpdateHistory {

    @ApiModelProperty(example = "메뉴 아이디")
    public int user_id; // 사용자 아이디

    @ApiModelProperty(example = "메뉴 아이디")
    public String api; // api

    @ApiModelProperty(example = "메뉴 아이디")
    public String created_at; //생성시간
    
}
