package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 전체글 갯수
	public int totalCount() {
		System.out.println("BoardDao.totalCount()");
		
		int bMap=sqlSession.selectOne("board.totalCount");
		
		return bMap;
	}
	
	//게시판 리스트 : 페이지 포함
	public List<BoardVo> selectList3(int startRnum, int endRnum){
		System.out.println("BoardDao.selectList3()");
		
		System.out.println(startRnum + "  " + endRnum);
		
		Map<String, Integer> bMap= new HashMap<String, Integer>();
		bMap.put("startRnum", startRnum);
		bMap.put("endRnum", endRnum);
		
		List<BoardVo> boardList=sqlSession.selectList("board.selectList3", bMap);
		
		System.out.println(boardList);
		return boardList;
	}
	
	public int hitCountJH(int no) {
		System.out.println("BoardDao.hitCountJH()");
		System.out.println(no);
		
		sqlSession.update("BoardDao.hitCountJH", no);
		return no;
		
	}
	
	public BoardVo selectRead(String title) {
		System.out.println("BoardDao.read()");
		System.out.println(title);
		
		BoardVo authVo=sqlSession.selectOne("board.selectRead", title);
		
		return authVo;
	}
	
	public List<BoardVo> selectList2(String keyword){
		System.out.println("BoardDao.selectList2()");
		System.out.println(keyword);
		
		List<BoardVo> boardList=sqlSession.selectList("board.selectList2", keyword);
		System.out.println(boardList);
		
		return boardList;
		
	}
	
	public List<BoardVo> selectList() {
		System.out.println("BoardDao.selectList()");
		
		List<BoardVo>boardList=sqlSession.selectList("board.selectList");
		System.out.println(boardList);
		
		return boardList;
	}

	/* 글저장 */
	public int insertBoard(BoardVo boardVo) {
		System.out.println("BoardDao.insertBoard()");

		int count = sqlSession.insert("board.insert", boardVo);
		return count;
	}
	
	
}
