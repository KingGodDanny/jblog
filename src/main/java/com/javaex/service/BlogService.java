package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	
	//아이디를 통해 Blog 하나가져오기
	public BlogVo getBlog(String id) {
		System.out.println("블로그서비스랑 아이디: " + id);
		
		BlogVo blogVo = blogDao.getBlog(id);
		
		return blogVo;
	}
}
