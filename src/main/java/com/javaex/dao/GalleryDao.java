package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public GalleryVo viewModal(String savename) {
		System.out.println("GalleryDao.viewModal()");
		System.out.println(savename);
		
		GalleryVo galleryPh=(GalleryVo) sqlSession.selectList("gallery.viewModal", savename);
		
		return galleryPh;
	}
	
	public int insert(String orgname) {
		System.out.println("GalleryDao.insert()");
		System.out.println(orgname);
		
		int no= sqlSession.insert(orgname);
		
		return no;
	}
	

}
