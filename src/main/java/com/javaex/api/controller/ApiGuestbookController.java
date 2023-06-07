package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.MyGuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.JsonResult;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private MyGuestbookService guestbookService;
	
	//방명록 첫페이지(ajax 로 리스트 출력)
	@RequestMapping(value="/api/guestbook/addList2", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList2() {
		System.out.println("ApiGuestbookController.addList2()");
		
		return "guestbook/ajaxList2";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method= {RequestMethod.GET, RequestMethod.POST})
	public JsonResult list(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList=guestbookService.getAddList();
		System.out.println(guestbookList);
		
		JsonResult jsonResult=new JsonResult();
		jsonResult.success(guestbookList);
		
		return jsonResult;
	}
	
	@ResponseBody//jaxon
	@RequestMapping(value="/api/guestbook/add2", method= {RequestMethod.GET, RequestMethod.POST})
	public JsonResult add2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add2()");
		System.out.println(guestbookVo);
		
		GuestbookVo guestVo = guestbookService.addGuest(guestbookVo);//GuestbookService로 부터 addGuest를 불러와서 guestbookVo를 가져온다.
		//jsonResult에 담기
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);
		
		return jsonResult;
	}
	@RequestMapping(value="/api/guestbook/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("ApiGuestbookController.addList()");
		
		List<GuestbookVo> guestbookList=guestbookService.getAddList();
		model.addAttribute("guestbookList", guestbookList);
		
		return "guestbook/ajaxList";
	}
	
	//ajax 등록
	@ResponseBody//jaxon
	@RequestMapping(value="/api/guestbook/add", method= {RequestMethod.GET, RequestMethod.POST})
	public JsonResult add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		System.out.println(guestbookVo);
		
		GuestbookVo guestVo = guestbookService.addGuest(guestbookVo);//GuestbookService로 부터 addGuest를 불러와서 guestbookVo를 가져온다.
		//jsonResult에 담기
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);
		
		return jsonResult;
	}
	
	//ajax 삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbook/remove", method= {RequestMethod.GET, RequestMethod.POST})
	public JsonResult remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(guestbookVo);
		
		int count = guestbookService.deleteGuestbook(guestbookVo);
		System.out.println(count);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(count);
		
		return jsonResult;
		
	}
	
	
}
