package com.javaex.vo;

public class CategoryVo {
	
	//필드
	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	private int pCount;
	
	//생성자
	public CategoryVo() {
	}
	
	public CategoryVo(String id, String cateName, String description) {
		super();
		this.id = id;
		this.cateName = cateName;
		this.description = description;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int pCount) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.pCount = pCount;
	}

	//메소드(게터세터)
	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getpCount() {
		return pCount;
	}

	public void setpCount(int pCount) {
		this.pCount = pCount;
	}

	
	//메소드(일반)
	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description
				+ ", regDate=" + regDate + ", pCount=" + pCount + "]";
	}

	
	
	
}
