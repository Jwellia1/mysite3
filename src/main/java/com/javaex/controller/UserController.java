package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.JsonResult;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;//UserService로 부터 받은 최종 데이터를 넘겨받기 위해 매핑
	
	/*회원정보 수정폼*/
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController.modifyForm()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		//UserVo server가 구동 될 때 마다 힙 영역에 새로운 코드의 UserVo를 생성
		//때문에 "authUser"로 불러 왔으나 실제로는 0x888과 같은 코드가 온 것이다.
		//그래서 코드로 불러온 것을 (UserVo)라고 캐스팅 한다. 
		int no = authUser.getNo();
		
		UserVo userVo=userService.modifyForm(no);
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}


	
	/*로그인 폼*/
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";//view로 리턴하는 단계
	}
	
	
	/*로그인*/
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		
		
		UserVo authUser= userService.login(userVo);
		System.out.println(authUser);
		
		if(authUser !=null) {//로그인 성공시
			System.out.println("로그인 성공");
			//세션에 저장
			session.setAttribute("authUser", authUser);//session은 setAttribute메서드의 일회성의 url유지정보인데 return주소값을 불러오는 동안 authUser값이(로그인에 성공한 사용자의 정보) 유지된다.
			//메인으로 리다이렉트
			return "redirect:/main";
		}else {
			System.out.println("로그인 실패");
			return "user/loginForm?result=fail";
		}
		
		
	}
	
	//회원가입 완료
	@RequestMapping(value="/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		System.out.println(userVo);
		int count = userService.join(userVo);
		if(count>0) {
			return "user/joinOk";
		}else {
			return "redirect:/user/joinForm";
		}
		
	}
	
	//회원가입 폼
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
		
	}
	/*로그아웃*/
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	//회원가입 id체크
	@ResponseBody
	@RequestMapping(value="/idcheck", method= {RequestMethod.GET, RequestMethod.POST})
	public JsonResult idcheck(@RequestParam("id") String id) {
		System.out.println("UserService.idcheck()");
		
		boolean data=userService.idcheck(id);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(data);
		
		//jsonResult.fail("통신오류");
		
		/*
		 * getter setter 사용시
		jsonResult.setResult("success");
		jsonResult.setData(data);
		
		jsonResult.setResult("fail");
		jsonResult.getFailMsg("통신오류");
		*/
		System.out.println(jsonResult);
		//UserVo userVo = userService.idcheck(id);
		
		return jsonResult;//글자만 반환하는 것(ajax방식)json으로 전송
	}
	

}
