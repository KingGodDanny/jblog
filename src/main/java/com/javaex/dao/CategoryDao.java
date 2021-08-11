package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//회원정보받아서 카테고리만들기
	public void insertCategory(UserVo userVo) {
		
		String id = userVo.getId();
		String cateName = "미분류";
		String description = "기본으로 만들어지는 카테고리 입니다.";
		
		CategoryVo categoryVo = new CategoryVo(id, cateName, description);
		
		sqlSession.insert("category.insertCategory", categoryVo);
		
	}
}
