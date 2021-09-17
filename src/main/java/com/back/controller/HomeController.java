package com.back.controller;

import com.back.domain.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
	@RequestMapping("/home")
	public String home() {
		User user = new User();
		user.setAge(15);
		
		return "Hello, Spring boot"+user.getAge();
	}
}
