package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	

	public List<BoardVo> getList2(String keyword) {
		System.out.println("BoardService.getList2()");
		System.out.println(keyword);

		List<BoardVo> boardList = boardDao.selectList2(keyword);
		System.out.println(boardList);

		return boardList;

	}

	// 글쓰기
	public int addBoard(BoardVo boardVo) {
		System.out.println("BoardService.addBoard()");

		int count = boardDao.insertBoard(boardVo);
		return count;
	}
	//게시판 리스트 : 페이징 포함
	public Map<String, Object> getList3(int crtPage){
		System.out.println("BoardService.getList3()");
		System.out.println(crtPage);
		
		//현재페이지 음수면 1페이지로 처리
		crtPage = (crtPage >=1) ? crtPage : (crtPage = 1);
				
		//5
		//1-5, 6-10
		//rownum 번호를 구해야 함
		//startRnum, endRnum
		
		//페이지당 글 갯수
		int listCnt = 10;
		//시작글 번호           1     *   5     + 1
		int startRnum = (crtPage-1)* listCnt + 1;
		
		//끝 글번호           1  +   5   - 1
		int endRum = (startRnum+listCnt)-1;
		
		List<BoardVo> boardList = boardDao.selectList3(startRnum, endRum);
		//페이징 계산
		
		///////////////////////////////////////////////////////////////////////
		//페이징 계산
		///////////////////////////////////////////////////////////////////////
		//전체 글갯수
		int totalCount = 123;
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		//1  --> 1~5
		//2  --> 1~5
		//3  --> 1~5
		//4  --> 1~5
		//5  --> 1~5
		//6  --> 6~10
		//10  --> 6~10
							//1  /  5  --> 0.2를 0으로 보낸다. --> 0 * 5 ==> 5
		int endPageBtnNo = (int)Math.ceil(crtPage/(double) pageBtnCount) * pageBtnCount;
		//시작버튼
		int startPageBtnNo = (endPageBtnNo - pageBtnCount)+1;
		
		//다음 화살표 결과가 true, false로 반환
		boolean next = false;
		
		if(endPageBtnNo * listCnt < totalCount) {//10*10<123
			next=true;
		}else {
			next=false;
			
			//끝 버튼 번호 endPageBtnNo 다시계산
			endPageBtnNo=(int)Math.ceil(totalCount/(double)listCnt);
		}
		//이전 화살표
		boolean prev = false;
		
		if(startPageBtnNo !=1) {
			prev = true;
		}
		//map으로 만들기
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		return pMap;
	}
	// 글쓰기
	public List<BoardVo> getList() {
		System.out.println("BoardService.getList()");

		// Dao selectList(); 호출
		List<BoardVo> boardList = boardDao.selectList();
		System.out.println(boardList);

		return boardList;
	
	}

}
