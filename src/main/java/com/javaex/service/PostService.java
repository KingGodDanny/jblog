package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	
	//블로그 아이디를 통해 카테정보가져오기
	public List<PostVo> getPost(String id) {
		System.out.println("포써 아이디:" + id);
		
		List<PostVo> postList = postDao.getPost(id);
		
		return postList;
	}
	
	
	////포스트 등록하기
	public void postWrite(PostVo postVo) {
		System.out.println("포스트서비스: " + postVo);
		
		postDao.postWrite(postVo);
		
	}
	
}
