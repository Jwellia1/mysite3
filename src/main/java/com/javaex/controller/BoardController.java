package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//계시판 글쓰기
	@RequestMapping(value="/board/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("BoardController.write()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		
		boardService.addBoard(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	//계시판 리스트 페이징 기능 포함
	@RequestMapping(value="/board/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam (value="crtPage", required = false, defaultValue = "1") int crtPage, Model model) {
		System.out.println("BoardController.list3()");
		
		Map<String, Object> pMap=boardService.getList3(crtPage);
		model.addAttribute("pMap",pMap);
		
		System.out.println(pMap);
		
		/*
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prec", prec);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		*/
		
		return "board/list3";
	}
	
	@RequestMapping(value="/board/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="keyword", required = false, defaultValue = "") String keyword , Model model) {
		System.out.println("BoardController.list2()");
		System.out.println(keyword);
		List<BoardVo>boardList=boardService.getList2(keyword);
		//System.out.println(boardList);
		model.addAttribute("boardList",boardList);
		
		return "board/list";
	}
		
	
	@RequestMapping(value="/board/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value="keyword", required = false, defaultValue = "") String keyword ,Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVo>boardList=boardService.getList();
		model.addAttribute("boardList", boardList);//임의로 만든 이름"boardList" boardList를 불러옴
		
		return "board/list";
		
		
	}
	

}
