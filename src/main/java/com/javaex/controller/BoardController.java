package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam(value="no", required = false, defaultValue = "")String title, Model model) {
		System.out.println("BoardController.read()");
		System.out.println(title);
		BoardVo authVo=boardService.getRead(title);
		
		return "board/read";
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
