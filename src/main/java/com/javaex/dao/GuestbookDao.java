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

	
	//방명록 add
	public List<GuestbookVo> getAddList() {
		System.out.println("GuestbookDao.getAddList()");
		return sqlSession.selectList("guestbook.getAddList");
	}

	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return sqlSession.delete("guestbook.deleteGuestbook", guestbookVo);

	}

	// ajax 방명록 등록 후 no로 글 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("GuestbookDao.selectGuest()");
		System.out.println(no);

		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectGuest", no);
		System.out.println(guestbookVo);

		return guestbookVo;
	}
	
	//ajax 방명록  등록때 사용
		public int insertSelectKey(GuestbookVo guestbookVo) {
			System.out.println("GuestbookDao.insertSelectKey()");
			
			int count = sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
			
			//셀렉트 문 17 번으로 글 가져오기
			return count;
		}

}