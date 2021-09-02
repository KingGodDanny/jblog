package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	
	//아이디를 통해 블로그관리를 하는것!
	public BlogVo getBlogAdmin(String id) {
		System.out.println("블로그서비스랑 아이디: " + id);
		
		BlogVo blogVo = blogDao.getBlog(id);
		
		return blogVo;
	}
	
	
	//2021-08-15 by대근
	//아이디를 통해 각종 필요한 정보들을 불러모을 서비스!
	public Map<String, Object> getBlogMain(String id) {
		System.out.println("블로그서비스랑 아이디: " + id);
		
		
		//블로그 가져오기
		BlogVo blogVo = blogDao.getBlog(id);
		System.out.println("blogVo " + blogVo);
		//카테고리리스트 가져오기
		List<CategoryVo> cateList = categoryDao.getCate(id);
		System.out.println("서비스(cateList) : " + cateList);
		//포스트리스트 가져오기
		List<PostVo> postList = postDao.getPost(id);
		System.out.println("서비스(포스트 리스트)): " + postList);	//포카 15
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		
		bMap.put("blogVo", blogVo);
		bMap.put("cateList", cateList);
		bMap.put("postList", postList);
		
		return bMap;
	}
	
	
	
	//블로그 업로드!
	public void upload(MultipartFile file, BlogVo blogVo) {
		System.out.println("블록써파일과 보Vo");
		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("파일싸이즈: " + fileSize);
		
		if(fileSize > 0) {	//saveName으로 하려했으나 if밖으로 나가면 밑에 filePath에 오류가난다.
		
		// 파일 업로드 처리
		String saveDir = "C:\\javaStudy\\upload\\";

		// 원래 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("오알지: " + orgName);
		
		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		
		// 저장파일이름(관리 때문에 겹치지 않는 새이름을 부여해야한다.)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//BlogVo에 saveName 담아주기
		blogVo.setLogoFile(saveName);
		
		
		// 파일패스(경로)
		String filePath = saveDir + saveName;

		
		// 파일을 서버의 하드디스크에 저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath); // 위치와 파일이름이 함께있는 filePath를써줘야한다.
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileDate);
			bout.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		blogDao.upload(blogVo);
		
			
		} else{
			
		blogDao.uploadTitle(blogVo);
			
			
		}
		
	}
}
