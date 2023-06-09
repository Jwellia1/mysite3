package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class MyGuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public int addGuestBook(GuestbookVo guestbookVo) {
		
		return guestbookDao.addGuestBook(guestbookVo);
	}
	//방명록 리스트 가져오기
	public List<GuestbookVo> getAddList() {
		System.out.println("MyGuestbookService.getAddList()");//addList에 실질적으로 데이터를 입력했을 때 출력된다.
		return guestbookDao.getAddList();
	}
	//ajax 방명록  등록때 사용
		public GuestbookVo addGuest(GuestbookVo guestbookVo) {
			System.out.println("GuestbookService.addGuest()");
			
			//글등록  no확인
			int count  = guestbookDao.insertSelectKey(guestbookVo);
			int no = guestbookVo.getNo();
			
			//no 글가져오기
			GuestbookVo guestVo = guestbookDao.selectGuest(no);
			
			return guestVo;
			
		}
	
	//방명록 삭제시
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return guestbookDao.deleteGuestbook(guestbookVo);
	}

	
}