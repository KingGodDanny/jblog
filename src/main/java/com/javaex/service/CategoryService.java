package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	
	//카테고리 리스트 가져오기
	public List<CategoryVo> getCate(String id) {
		
		List<CategoryVo> cateList = categoryDao.getCate(id);
		
		return cateList;
		
	}
	
	
	//카테고리 추가하기! & 추가한것 cateNo포함해서 가져오기
	public CategoryVo writeCate(CategoryVo categoryVo) {
		System.out.println("이곳은 카써 카테고리추가다");
		
		//카테고리 추가
		System.out.println("전전전: " + categoryVo);
		int count = categoryDao.writeCate(categoryVo);
		System.out.println("후후후: " + categoryVo);
		
		//cateNo 추출!!
		int cateNo = categoryVo.getCateNo();
		
		//제이슨해줄 글가져오기
		CategoryVo resultCateVo = categoryDao.selectCategory(cateNo); 
		
		return resultCateVo;
	}
	
	
	//카테고리 삭제
	public boolean remove(int cateNo) {
		
		CategoryVo pCount = categoryDao.countSelect(cateNo);
		
		int postCount = pCount.getpCount();
		
		if(postCount == 0) {
			
			categoryDao.remove(cateNo);
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
}
