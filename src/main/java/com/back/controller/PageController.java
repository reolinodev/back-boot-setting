package com.back.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    /*******************************************************************************************************************
     * [페이지 라우터 - 로그인 화면과 홈 화면을 제외한 모든 페이지 정의]
     * 1.회원가입 화면 : /signUp
     * 2.비밀번호 변경 화면  : /pwChange
     ******************************************************************************************************************/

    /**
     * 회원가입 화면
     */
    @RequestMapping(value = "/signUp", method= RequestMethod.GET)
    public ModelAndView signUpView(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/signUp");
        return mav;
    }

    /**
     * 비밀번호 변경 화면
     */
    @RequestMapping(value = "/pwChange", method= RequestMethod.GET)
    public ModelAndView  pwChangeView(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/pwChange");
        return mav;
    }
}
