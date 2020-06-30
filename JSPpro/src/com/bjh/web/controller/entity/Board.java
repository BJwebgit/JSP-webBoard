package com.bjh.web.controller.entity;

import java.util.Date;

public class Board {
	private int id;
	private String title;
	private Date regdate;
	private String writerId;
	private String files;
	private String content;
	private Boolean pub;
	
	public Board() {
		
	}

	public Board(int id, String title, Date regdate, String writerId, String files, String content, Boolean pub) {
		super();
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.writerId = writerId;
		this.files = files;
		this.content = content;
		this.pub = pub;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getPub() {
		return pub;
	}

	public void setPub(Boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", regdate=" + regdate + ", writerId=" + writerId + ", files="
				+ files + ", content=" + content + ", pub=" + pub + "]";
	}
}
