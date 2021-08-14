package com.javaex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	//블로그 들어가기
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController.main()");
		System.out.println("블록컨트롤: " + id);
		
		BlogVo blogVo = blogService.getBlog(id);
		
		System.out.println("블록서비스" + blogVo);
		
		//로그인 안한사람 걸러내기
		if(blogVo != null) {
			model.addAttribute("blogVo", blogVo);
			
			return "/blog/blog-main";
			
		} else {
			return "error/403";
		}
		
	}
	
	
	//내블로그 관리를 눌렀을때
	@RequestMapping(value = "/{id}/admin/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public String basicModify(@PathVariable("id") String id, Model model) {
		System.out.println("블록컨트롤BASIC과 아이디: " + id);
		
		BlogVo blogVo = blogService.getBlog(id);
		
		model.addAttribute("blogVo", blogVo);
		
		
		return "blog/admin/blog-admin-basic";
	}
	
	
	//기본설정변경 (블로그제목,로고이미지)
	@RequestMapping(value = "/{id}/admin/basic/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String basicUpload(@ModelAttribute BlogVo blogVo,
							  @RequestParam("file") MultipartFile file,
							  @PathVariable("id") String id) {
		System.out.println("블컨 업로드: " + blogVo);
		
		blogService.upload(file , blogVo);		//id가 블로그Vo에 담기는걸 확인, 서비스에서 다같이 묶을예정
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	
	//관리에서 카테고리를 눌렀을때
	@RequestMapping(value = "/{id}/admin/category", method = {RequestMethod.GET, RequestMethod.POST})
	public String category(@PathVariable("id") String id, Model model) {
		System.out.println("여기 카테고리 가져올거다");
		
		
		BlogVo blogVo = blogService.getBlog(id);
		
		model.addAttribute("blogVo", blogVo);
		
		
		return "blog/admin/blog-admin-cate";
	}
	
	
}
