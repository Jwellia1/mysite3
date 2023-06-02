package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;//sqlSession을 거처 받은 Dao데이터를 넘겨받아야 하기 때문에 Dao를 매핑
	
	
	
	/*회원등록*/
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		
		int count=userDao.insertUser(userVo);
		
		return count;
	}
	
	
	/* 로그인 */
	public UserVo login(UserVo userVo) {
		System.out.println("UserService.login()");
		
		UserVo authUser = userDao.selectUser(userVo);
		 
		return authUser;
	}

	/*수정폼*/
	public UserVo modifyForm(int no) {
		System.out.println("UserService.modifyForm()");
		
		UserVo authUser=userDao.selectUser(no);
		return authUser;
		
	
	}
	//id check
	public UserVo idcheck(String id) {
		System.out.println("UserService.idcheck()");
		System.out.println(id);
		UserVo userVo=userDao.selectUser(id);
		
		return userVo;
		
		
	}
	
	

}
