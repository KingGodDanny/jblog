package com.javaex.controller;

import java.util.List;
import java.util.Map;

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

		Map<String,Object> bMap = blogService.getBlogMain(id);
		
		model.addAttribute("bMap", bMap);

		List<PostVo> postList = postService.getPost(id);

		model.addAttribute("postList", postList);

		return "/blog/admin/blog-admin-write";
	}
	
	//포스트 등록하기
	@RequestMapping(value = "/{id}/admin/basic/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String postWrite(@ModelAttribute PostVo postVo, @PathVariable("id") String id, Model model) {
		System.out.println("포스트라이트: "+ postVo);

		model.addAttribute("blogId", id);
		System.out.println("컨트롤러 모델 :" + model);
		//////
		postService.postWrite(postVo);
		System.out.println("포스트컨트롤러Vo :"+postVo);
		return "redirect:/"+ id +"/admin/writeForm";
	}
		
}
