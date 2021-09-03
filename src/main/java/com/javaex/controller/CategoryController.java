package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//리스트 요청
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> cateList(@PathVariable("id") String id, Model model) {
		System.out.println("컨트롤러 아이디" + id);
		
		//메인화면에서 왼쪽하단에 목록이 뿌려져야하니깐 리스트로 메잌!
		List<CategoryVo> cateList = categoryService.getCate(id);
		
		System.out.println("컨트롤러 리스트 : " + cateList);
		
		
		return cateList;
		
	}
	
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value = "{id}/admin/category/write", method = {RequestMethod.GET, RequestMethod.POST})
	public CategoryVo write(@ModelAttribute CategoryVo categoryVo, @PathVariable("id") String id) {
//		System.out.println("이곳은 카컨 카츄다~: " + categoryVo + id);
		
		CategoryVo resultCateVo = categoryService.writeCate(categoryVo);
		
		return resultCateVo;
	}
		
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "{id}/admin/category/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean remove(@RequestParam("cateNo") int cateNo) {
		System.out.println("카컨 카테노: " + cateNo);
		
		boolean result = categoryService.remove(cateNo);
		
		return result;
	}
	
	
}
