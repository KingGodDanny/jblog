package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private BlogService blogService;
	
	// 관리에서 글작성을 눌렀을때
	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String post(@PathVariable("id") String id, Model model) {
		System.out.println("여기는 포스트포워드전이다: " + id);

		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);

		List<PostVo> postList = postService.getPost(id);

		model.addAttribute("postList", postList);

		return "/blog/admin/blog-admin-write";
	}
	
	//포스트 등록하기
	@RequestMapping(value = "/{id}/admin/basic/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String postWrite(@ModelAttribute PostVo postVo) {
		System.out.println("포스트라이트: "+ postVo);
		
		postService.postWrite(postVo);
		
		return null;
	}
		
}
