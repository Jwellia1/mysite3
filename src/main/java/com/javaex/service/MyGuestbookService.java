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
	
	
	
	//방명록 삭제시
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return guestbookDao.deleteGuestbook(guestbookVo);
	}

	// 방명록 등록시
	public GuestbookVo addGuest(GuestbookVo guestbookVo) {
		System.out.println("MyGuestbookService.addGuest()");
		System.out.println(guestbookVo);
		
		//글등록 no확인
		int count=guestbookDao.insertGuest(guestbookVo);//Autowired한 guestbookDao로 부터 insertGuest메서드의 guestbookVo를 불러옴
		int no = guestbookVo.getNo();//guestbookVo에 no를 불러와 int형으로 저장

		GuestbookVo guestVo = guestbookDao.selectGuest(no);//ajax 방명록 등록 후 no로 글 가져오기의 selectGuest로 부터 no를 가져온다.
		
		return guestVo;//no가 아닌 no주소값을 갖고 있는 guestbookVo로 반환해야 컨트롤러에서 정상적으로 받을 수있음

	}

}