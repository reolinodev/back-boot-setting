package com.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		String url = "/swagger-ui/";
		return "redirect:" + url;
	}

	@RequestMapping(value = "/home", method= RequestMethod.GET)
	public ModelAndView  goHome(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		List<String> resultList = new ArrayList<String>();
		resultList.add("AAA");
		resultList.add("BBB");
		resultList.add("CCC");
		mav.addObject("resultList",resultList);
		mav.setViewName("content/home");
		return mav;
	}

	@RequestMapping(value = "/lib", method= RequestMethod.GET)
	public ModelAndView  home2View(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/lib");
		return mav;
	}


}
