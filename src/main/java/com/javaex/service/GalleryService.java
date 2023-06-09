package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public GalleryVo getGalList(String savaname) {
		System.out.println("GalleryService.getGalList()");
		System.out.println(savaname);
		
		GalleryVo galleryPh=galleryDao.viewModal(savaname);
		
		return galleryPh;
	}
	public GalleryVo uploadList(String orgname) {
		System.out.println("GalleryService.uploadList()");
		System.out.println(orgname);
		
		GalleryVo galleryPhr = galleryDao.viewModal(orgname);
		
		return galleryPhr;
	}


}
