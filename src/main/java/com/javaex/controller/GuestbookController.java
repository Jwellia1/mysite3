package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.MyGuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {

	@Autowired
	private MyGuestbookService myGuestbookService;

	/* 방명록 리스트 가져오기 */
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {//반드시 Model로 html을 불러와 jsp파일을 전송한다. 
		System.out.println("GuestbookController.list()");

		// 서비스를 통해 모든 방명록 글 가져오기
		List<GuestbookVo> guestbookList = myGuestbookService.getAddList();//myGuestbookService에 getAddList()를 특정 속성 값 없이 넘겨준다.

		// Dispacher Servlet-->jsp에 방명록 글 리스트 전달
		model.addAttribute("guestbookList", guestbookList);

		return "guestbook/addList";
	}
	//방명록 사용자 및 방명록 등록
	@RequestMapping(value="/add", method = { RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.add()");
		System.out.println(guestbookVo);
		
		myGuestbookService.getAddList();
		
		return "guestbook/addList";
	}

	/* 방명록 삭제 폼 */
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteform() {
		System.out.println("GuestbookController.deleteForm()");

		return "guestbook/deleteForm";
	}	
	

	/* 방명록 삭제 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.delete()");
		
		myGuestbookService.deleteGuestbook(guestbookVo);
		return "redirect:/guestbook/addList";
	}
	
}
