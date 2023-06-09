package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/list",method= { RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("GalleryController.list()");
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String gList(@RequestParam("file") MultipartFile file, Model model ) {
		System.out.println("GalleryController.gList()");
		System.out.println(file.getOriginalFilename());
		
		String saveName=galleryService.restore(file);
		
		model.addAttribute("savaName", saveName);
		
		return "gallery/list";
	}
	
}
