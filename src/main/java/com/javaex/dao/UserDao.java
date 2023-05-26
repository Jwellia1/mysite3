package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {//src/main/resources/mybatis/configuration.xml의 mappers/user.xml에 대한 데이터를 불러오기 위해 만든다. 
	
	@Autowired
	private SqlSession sqlSession;//DB 사이에 Mybatis를 통한 sqlSession으로 부터 필요한 Session을 불러와야 하기 때문
	
	List<UserVo> getUserList(){
		
	}
	
	/* 로그인 세션저장용 회원정보 가져오기 */
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		UserVo authUser=sqlSession.selectOne("user.selectUser", userVo);//user.xml에 namespace="user".selet id="selectUser"
		//selectOne 하나의 정보를 가져오는 것으로 login시 한사람의 정보를 입력하기 때문이다.
		return authUser;
	}
	
	//필드
	//생성자
	/*회원가입*/
	public int insertUser(UserVo userVo) {//회원가입 
		
		System.out.println("UserDao.insertUser()");
		System.out.println(userVo);
		
		int count=sqlSession.insert("user.insert", userVo);//user.xml에 namespace="user".insert id="insert"
		
		System.out.println(count);
		
		return count;
	}
	/*회원정보 수정폼 정보 1명 가져오기*/
	public UserVo selectUser(int no) {
		System.out.println("UserService.modifyForm()");
		System.out.println(no);
		
		UserVo userVo = sqlSession.selectOne("user.selectUserByNo", no);
		
		return userVo;
	}
	

}
