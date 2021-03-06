package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		System.out.println(userVo);
		
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	
	//로그인폼
	@RequestMapping(value = "/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		System.out.println(userVo);
		
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			
			return "redirect:/";
		} else {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	
	
	//로그아웃
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//세션 종료
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	//아이디 중복체크 (ajax)
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("컨트롤러의 중복체크: " + id);
		
		boolean state = userService.getUser(id);
		
		System.out.println(state);
		
		return state;
	}
	 
}
