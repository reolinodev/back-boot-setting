package com.back.controller;

import com.back.api.domain.SessionInfo;
import com.back.support.JsonUtil;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Resource
    private SessionInfo sessionInfo;

    /*******************************************************************************************************************
     * [페이지 라우터 - 로그인 화면과 홈 화면을 제외한 모든 페이지 정의]
     * 1.회원가입 화면 : /signUp
     * 2.비밀번호 변경 화면  : /pwChange
     * 3.메인 화면 : /main
     ******************************************************************************************************************/

    /**
     * 회원가입 화면
     */
    @RequestMapping(value = "/signUp", method= RequestMethod.GET)
    public ModelAndView signUpView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/signUp");
        return mav;
    }

    /**
     * 비밀번호 변경 화면
     */
    @RequestMapping(value = "/pwChange", method= RequestMethod.GET)
    public ModelAndView  pwChangeView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/pwChange");
        return mav;
    }

    /**
     * 메인 화면
     */
    @RequestMapping(value = "/main", method= RequestMethod.GET)
    public ModelAndView  mainView(HttpServletRequest request) throws IOException, ParseException {
        //todo 사용자의 세션 정보를 이용해서 화면을 배치 한다. 사용자의 이름과 최상위 권한을 셀렉트 박스로 유지한다.
        //todo 슈퍼 유저는 세팅의 관련된 화면을 보여주며 그 외의 권한은 권한에 관련된 메뉴를 조회해서 navigation을 구성한다.
        //todo 각 권한별로 가장 먼저 호출할 도메인을 설정한다.
        //todo 슈퍼유저일 경우 json에서 해당 메뉴를 가져와서 출력하며 슈퍼 유저가 아니면 DB에서 권한에 맞는 데이터를 가져와 출력한다.

        ModelAndView mav = new ModelAndView();

        int auth_id = sessionInfo.getAuth_id();
        //todo auth_id가 0일경우 세션이 없기 때문에 로그인 화면으로 이동 처리. 아래 조건절도 수정 필요
        if(auth_id == 0 || auth_id == 1){
            mav = JsonUtil.getJsonMenu();
        }else {
            //todo db에서 각 권한에 맞는 메뉴를 조회함
        }

        mav.addObject("userNm",sessionInfo.getUser_nm());
        mav.addObject("loginId",sessionInfo.getLogin_id());
        mav.addObject("authId",sessionInfo.getAuth_id());

        mav.setViewName("content/main");
        return mav;
    }

    /**
     * 사용자 관리 화면
     */
    @RequestMapping(value = "/user/userMng", method= RequestMethod.GET)
    public ModelAndView  userMngView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/user/userMngView");
        return mav;
    }

    /**
     * 권한 관리
     */
    @RequestMapping(value = "/user/authMng", method= RequestMethod.GET)
    public ModelAndView  authMngView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/user/authMngView");
        return mav;
    }


    /**
     * 사용자 권한 관리
     */
    @RequestMapping(value = "/user/authUserMng", method= RequestMethod.GET)
    public ModelAndView  authUserMngView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/user/authUserMngView");
        return mav;
    }

}
