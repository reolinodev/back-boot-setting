package com.back.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    /*******************************************************************************************************************
     * [로그인 컨트롤러 - 로그인 화면 및 관련 로직 정의
     * 1.로그인 화면 : /login
     ******************************************************************************************************************/

    /**
     * 회원가입 화면
     */
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public ModelAndView loginView(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/login");
        return mav;
    }


}
