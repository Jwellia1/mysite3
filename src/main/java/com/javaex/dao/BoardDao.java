package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
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

}
