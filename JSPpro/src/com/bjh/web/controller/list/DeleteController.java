package com.bjh.web.controller.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.service.BoardCmtService;
import com.bjh.web.controller.service.BoardService;

@WebServlet("/notice/delete-board")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		BoardService service = new BoardService();
		int result = service.deleteBoard(id);
		
		if(userID.equals("firstID"))
			res.sendRedirect("/admin/notice/a-list");
		else
			res.sendRedirect("/notice/list");
	}
}
