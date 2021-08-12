package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	//회원가입하기
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		
		//회원가입
		int count = userDao.userInsert(userVo);
		
		//블로그생성을 위한 회원가입정도 넘겨주기
		blogDao.insertBlog(userVo);
		
		//카테고리생성을 위한 정보 넘겨주기
		categoryDao.insertCategory(userVo);
		
		return count;
	}
	
	
	// 회원가입폼 id 중복체크
	public boolean getUser(String id) {
		System.out.println("[UserService.getUser()]");

		UserVo userVo = userDao.selectUser(id);

		if (userVo == null) { // db에 없는 경우 --> 사용가능한 아이디
			return true;
		} else { // db에 있는 경우 --> 이미 사용중인 아이디입니다.
			return false;
		}

	}
	
	
	//로그인하여 사용자 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserService.getUser()");
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
		
	}
	
	
}
