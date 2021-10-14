package com.back.repository;

import java.util.ArrayList;
import java.util.List;

import com.back.api.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapper {
    
    public User findById(Integer id) {
        System.out.println("findById실행");
        
        User data =  new User();
        data.setId(1);
        data.setEmail("aaa@gmail.com");
        data.setName("AAA");
        data.setPassword("12345");
        data.setPhone("01011112222");
        data.setBirth("19000101");
        return data;
    }

    public int save(User userDto) {
        System.out.println("save실행");
        return 1;
    }

    public int deleteById(Integer id) {
        System.out.println("deleteById실행");
        return 1;
    }

    public List<User> findAll(User userDto) {
        System.out.println("findAll실행");
        
        List<User> list = new ArrayList<>();
        User data =  new User();
        data.setId(1);
        data.setEmail("aaa@gmail.com");
        data.setName("AAA");
        data.setPassword("12345");
        data.setPhone("01011112222");
        data.setBirth("19000101");
        list.add(data);
        
        User data2 =  new User();
        data2.setId(2);
        data2.setEmail("bbb@gmail.com");
        data2.setName("BBB");
        data2.setPassword("67890");
        data2.setPhone("01033334444");
        data2.setBirth("20001231");
        list.add(data2);

        return list;
    }

    public int update(User userDto){
        System.out.println("update실행");
        return 1;
    }; 
}

