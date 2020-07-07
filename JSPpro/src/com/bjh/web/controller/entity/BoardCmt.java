package com.bjh.web.controller.entity;

import java.util.Date;

public class BoardCmt {
	private int id;
	private String cmt;
	private Date regdate;
	private String writerId;
	private int boardId;
	
	public BoardCmt() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardCmt(int id, String cmt, Date regdate, String writerId, int boardId) {
		super();
		this.id = id;
		this.cmt = cmt;
		this.regdate = regdate;
		this.writerId = writerId;
		this.boardId = boardId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
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

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "BoardCmt [id=" + id + ", cmt=" + cmt + ", regdate=" + regdate + ", writerId=" + writerId + ", boardId="
				+ boardId + "]";
	}
}
