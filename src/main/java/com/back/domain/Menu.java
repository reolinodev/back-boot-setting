package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu {

    public int menu_id; //메뉴 아이디

    public String menu_nm; //메뉴 명

    public int menu_lv; //메뉴 레벨

    public int parent_id; //부모 아이디

    public String menu_url; //url

    public int ord; //순서

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public String updated_id; //수정자

    public String use_yn; //사용여부
}
