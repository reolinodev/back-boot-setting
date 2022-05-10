package com.back.api.domain.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Param {

    @ApiModelProperty(example = "검색어")
    private String search_str;

    @ApiModelProperty(example = "페이지별 항목수")
    private int page_per;

    @ApiModelProperty(example = "현재 페이지")
    private int current_page;

    @ApiModelProperty(example = "시작 인덱스")
    private int start_idx;

    public void setStart_idx(int page_per, int current_page) {
        this.start_idx = page_per * (current_page - 1);
    }

}
