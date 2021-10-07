package com.back.repository;

import java.util.ArrayList;
import java.util.List;
import com.back.api.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapper {
    
    public UserDto findById(Integer id) {
        System.out.println("findById실행");
        UserDto result = new UserDto();
        return result;
    }

    public int save(UserDto userDto) {
        System.out.println("save실행");
        return 1;
    }

    public int deleteById(Integer id) {
        System.out.println("deleteById실행");
        return 1;
    }

    public List<UserDto> findAll(UserDto userDto) {
        System.out.println("findAll실행");
        List<UserDto> list = new ArrayList<>();
        return list;
    }

    public int update(UserDto userDto){
        System.out.println("update실행");
        return 1;
    }; 
}

