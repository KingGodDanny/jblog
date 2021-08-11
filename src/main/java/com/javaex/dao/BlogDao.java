package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//회원정보 받아서 블로그에 뿌려주기
	public void insertBlog(UserVo userVo) {
		
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다.";
		String logoFile = "";
		
		BlogVo blogVo = new BlogVo(id, blogTitle, logoFile);
		
		sqlSession.insert("blog.insertBlog", blogVo);
		
	}
	
	
	//ID로 한개의 블로그 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("블로그다오랑 아이디: " + id);
		
		BlogVo blogVo = sqlSession.selectOne("blog.getBlog", id);
		System.out.println(blogVo);
		
		return blogVo;
	}
	
	
	
	
}
