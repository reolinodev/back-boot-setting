package com.back.controller;

import com.back.api.service.UserService;
import com.back.domain.User;
import com.back.api.domain.common.Header;
import com.back.service.LoginService;
import com.back.support.ResponseUtils;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	/*******************************************************************************************************************
	 * [로그인]
	 * 1.로그인 화면 불러오기 : /login.do
	 * 2.로그인 처리 : /loginProc.do
	 ******************************************************************************************************************/

	/**
	 * 로그인화면 불러오기
	 * : 로그인 화면을 불러온다.
	 */
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public ModelAndView  loginview(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pages/login");
		return mav;
	}

	/*******************************************************************************************************************
	 * [회원가입]
	 * 1.회원가입화면 불러오기: /signUp.do
	 * 2.아이디 체크 : /signUpCheck.do
	 * 3.회원가입 처리 : /signUpProc.do
	 ******************************************************************************************************************/

	/**
	 * 회원가입화면 불러오기
	 * : 회원가입 화면을 불러온다.
	 */
	@RequestMapping(value = "/signUp", method= RequestMethod.GET)
	public ModelAndView  signUpView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pages/signUp");
		return mav;
	}


	/**
	 * 아이디 체크
	 * : 회원가입시 기존에 사용하고 있는 아이디 인지 체크
	 */
	@RequestMapping(value = "/signUpIdCheck", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> signUpIdCheck(@RequestBody User user, HttpServletRequest request){
		Map <String,Object> responseMap = new HashMap<>();

		int result = loginService.countByLoginId(user);
		String message = result+" item was viewed";
		String data = "Username is available";
		String code = "ok";
		if(result > 0){
			data = "The ID that already exists";
			code ="fail";
		}

		Header header = ResponseUtils.setHeader(message, code, request);

		responseMap.put("header", header);
		responseMap.put("data", data);

		return new ResponseEntity<> (responseMap, HttpStatus.OK);
	}



	/*******************************************************************************************************************
	 * [비밀번호 변경]
	 * 1.비밀번호 변경 화면 불러오기:/pwChange.do
	 * 2.비밀번호 변경 처리 : /pwChangeProc.do
	 ******************************************************************************************************************/

	/**
	 * 비밀번호 변경화면 불러오기
	 * : 비밀번호 변경화면 불러온다.
	 */
	@RequestMapping(value = "/pwChange", method= RequestMethod.GET)
	public ModelAndView  pwChangeView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pages/pwChange");
		return mav;
	}
}
