package com.back.controller;

import com.back.api.domain.User;
import com.back.api.service.UserService;
import com.back.service.LoginService;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {
    /*******************************************************************************************************************
     * [로그인 컨트롤러 - 로그인 화면 및 관련 로직 정의
     * 1.로그인 화면 : /login
     ******************************************************************************************************************/

    private final LoginService loginService;

    /**
     * 로그인 화면
     */
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public ModelAndView loginView(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/login");
        return mav;
    }

    /**
     * 회원가입 화면
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ModelAndView loginProc(User user, HttpServletRequest request) throws NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();

        String msg = "";

        int countLoginId = loginService.checkLoginId(user);
        if(countLoginId == 0){
            msg = "ID does not exist.";
            mav.addObject("msg", msg);
            mav.setViewName("pages/login");
            return mav;
        }

        int countUserPw = loginService.checkUserPw(user);
        if(countUserPw == 0){
            msg = "Passwords do not match.";
            mav.addObject("msg", msg);
            mav.setViewName("pages/login");
            return mav;
        }


        //todo 사용자의 기본적인 사항을 조회한후 세션에 저장한다.

        //todo 메인 페이지로 이동한다.

        mav.addObject("msg", msg);
        mav.setViewName("pages/login");
        //            mav.setViewName("redirect:/login");


        return mav;
    }


}
