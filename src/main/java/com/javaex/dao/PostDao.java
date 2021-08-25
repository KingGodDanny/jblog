package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 블로그 아이디를 통해 카테정보가져오기
	public List<PostVo> getPost(String id) {
		System.out.println("포스트다오 아이디:" + id);

		List<PostVo> postList = sqlSession.selectList("post.getPost", id);

		System.out.println("포스트dao: " + postList);

		return postList;
	}
	
	
	////포스트 등록하기
	public void postWrite(PostVo postVo) {
		System.out.println("포스트다오의 라이트");
		
		sqlSession.insert("postWrite", postVo);
		
	}
	

	
	
	
	
}
