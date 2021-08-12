package com.javaex.dao;

import java.util.List;

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
	
	
	////카테고리 리스트 가져오기
	public List<CategoryVo> getCate(String id) {
		
		List<CategoryVo> cateList = sqlSession.selectList("category.cateSelect" ,id);
		System.out.println("xml갓다온 리스트 제발: " + cateList);
		
		
		return cateList;
	}
	
	
	//카테고리 추가하기!!
	public int writeCate(CategoryVo categoryVo) {
//		System.out.println("이곳은 카테다오 카테추가: " + categoryVo);
		
		
		int count = sqlSession.insert("category.writeCategory", categoryVo);
//		System.out.println("성공한거니?: " + count);
		
		return count;
	}
	
	
	//cateNo를 통하여 카테고리 추가한거 가져오기
	public CategoryVo selectCategory(int cateNo) {
		
		CategoryVo resultCateVo = sqlSession.selectOne("category.selectCategory", cateNo);
		System.out.println("xml갓다온 카테추가한 리절보: " + resultCateVo);
		
		return resultCateVo;
		
	}
	
	
	//카테고리 삭제
	public int remove(int cateNo) {
		
		int count = sqlSession.delete("category.remove", cateNo);
		
		System.out.println("삭제카운트: " + count);
		
		return count;
	}
	
	//포스트 카운트 가져오기
	public CategoryVo countSelect(int cateNo) {
		CategoryVo pCount = sqlSession.selectOne("category.countSelect", cateNo);
		System.out.println(pCount.getpCount());
		
		return pCount;
	}
}
