package com.back.controller;

import com.back.api.domain.LoginEntity;
import com.back.api.domain.SessionInfo;
import com.back.service.LoginService;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {
    /*******************************************************************************************************************
     * [로그인 컨트롤러 - 로그인 화면 및 관련 로직 정의
     * 1.로그인 화면 : /login
     * 2.로그인 로직 : /login
     * 3.로그아웃 로직 : /logout
     ******************************************************************************************************************/

    private final LoginService loginService;

    @Resource
    private SessionInfo sessionInfo;

    /**
     * 로그인 화면
     */
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public ModelAndView loginView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/login");
        return mav;
    }

    /**
     * 로그인
     * 1. 해당 아이디가 없으면 'ID does not exist' 반환 후 로그인 화면으로 이동
     * 2. 패스워드가 일치하지 않을 경우 'Passwords do not match' 반환 후 로그인 화면으로 이동
     * 3. 해당 유저의 정보를 조회후 세션에 저장
     * 4. 로그인 이력을 저장
     * 5. 메인화면으로 이동
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ModelAndView login(LoginEntity loginEntity, HttpServletRequest request) throws NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();

        String msg = "";

        //아이디 체크
        int countLoginId = loginService.checkLoginId(loginEntity);
        if(countLoginId == 0){
            msg = "ID does not exist.";
            mav.addObject("msg", msg);
            mav.setViewName("pages/login");
            return mav;
        }

        //패스워드 체크
        int countUserPw = loginService.checkUserPw(loginEntity);
        if(countUserPw == 0){
            msg = "Passwords do not match.";
            mav.addObject("msg", msg);
            mav.setViewName("pages/login");
            return mav;
        }

        //세션저장
        LoginEntity loginInfo = loginService.getLoginId(loginEntity);
        setSessionInfo(loginInfo);

        //로그인 내역 기록
        loginEntity.user_id = loginInfo.user_id;
        loginService.inputLoginHistory(loginEntity);
        loginService.updateLastLoginAt(loginEntity);

        mav.setViewName("redirect:/main");
        return mav;
    }

    /**
     * 로그 아웃
     * : 세션을 초기화 후 로그인 화면으로 이동한다.
     */
    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();

        mav.setViewName("redirect:/login");
        return mav;
    }


    /**
     * 로그인 정보 세션에 입력
     */
    private void setSessionInfo(LoginEntity loginEntity) {

        sessionInfo.setLogin_id(loginEntity.getLogin_id());
        sessionInfo.setAuth_id(loginEntity.getAuth_id());
        sessionInfo.setAuth_nm(loginEntity.getAuth_nm());
        sessionInfo.setUser_id(loginEntity.getUser_id());
        sessionInfo.setUser_nm(loginEntity.getUser_nm());
    }
}
