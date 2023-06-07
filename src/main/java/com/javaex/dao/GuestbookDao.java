package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int addGuestBook(GuestbookVo guestbookVo) {
		return sqlSession.insert("guestbook.addGuestBook", guestbookVo);
	}
	public List<GuestbookVo> getAddList(){
		return sqlSession.selectList("guestbook.getAddList");
	}
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return sqlSession.delete("guestbook.deleteGuestbook",guestbookVo);
	
	}
	//방멱록 등록시 사용
	public int insertGuest(GuestbookVo guestbookVo) {
		System.out.println("guestbookDao.insertGuest()");
		System.out.println(guestbookVo);
		
		int count=sqlSession.insert("guestbook.insertGuest", guestbookVo);//guestbook.xml로부터 namespace = guestbook . id=insertGuest
		System.out.println(guestbookVo);
		
		return count;
	}
	//ajax 방명록 등록 후 no로 글 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("GuestbookDao.selectGuest()");
		System.out.println(no);
		
		GuestbookVo guestbookVo=sqlSession.selectOne("guestbook.selectGuest", no);
		System.out.println(guestbookVo);
		
		return guestbookVo;
	}
	
	
	

}