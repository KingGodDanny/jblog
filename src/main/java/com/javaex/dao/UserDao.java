package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao.userInsert()");
		
		int count = sqlSession.insert("user.userInsert", userVo);
		
		return count;
		
	}
	
	
	// id로 회원정보 가져오기(중복체크용)
	public UserVo selectUser(String id) {
		System.out.println("[UserDao.selectUser()]");
		System.out.println(id);

		return sqlSession.selectOne("user.selectUserById", id);
	}
	
	
	//로그인 사용자 정보가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser(회원로그인)");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		
		System.out.println("유저다오의: " + authUser);
		
		return authUser;
	}
	
	
	
	
	
	
	
	
	
}
