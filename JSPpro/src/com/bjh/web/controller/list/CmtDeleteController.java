package com.bjh.web.controller.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.service.BoardCmtService;

@WebServlet("/notice/cmtdelete-board")
public class CmtDeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		int cmtid = Integer.parseInt(req.getParameter("cmtid"));
		String id = req.getParameter("id");
		
		BoardCmtService cmt_service = new BoardCmtService();
		int result = cmt_service.deleteBoardCmt(cmtid);
		
		if(userID.equals("firstID")) {
			String url = "/admin/notice/a-detail?id="+ id;
			res.sendRedirect(url);
		}
		else {
			String url = "detail?id="+ id;
			res.sendRedirect(url);
		}
	}
}
