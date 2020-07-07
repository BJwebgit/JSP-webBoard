package com.bjh.web.controller.admin.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.entity.BoardCmt;
import com.bjh.web.controller.service.BoardCmtService;
import com.bjh.web.controller.service.BoardService;

@WebServlet("/admin/notice/a-detail")
public class AdminDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		BoardCmtService cmt_service = new BoardCmtService();		
		List<BoardCmt> list = cmt_service.getBoardCmtList(id);
		
		BoardService service = new BoardService();
		Board board = service.getBoard(id);
		
		req.setAttribute("n", board);
		req.setAttribute("list", list);
		req.setAttribute("id", id);
		req.setAttribute("userID", userID);
		
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/a-detail.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		if(userID == null) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter script = res.getWriter();
			script.println("<script>");
			script.println("alert('로그인 후 이용가능합니다')");
			script.println("location.href = 'login'");
			script.println("</script>");
			script.flush();
		}
		else {
			String comment = req.getParameter("comment");
			int boardId = Integer.parseInt(req.getParameter("id"));
			
			BoardCmt boardcmt = new BoardCmt();
			boardcmt.setCmt(comment);
			boardcmt.setWriterId(userID);;
			boardcmt.setBoardId(boardId);;
			
			BoardCmtService service = new BoardCmtService();
			int result = service.insertCmt(boardcmt);
			String url = "a-detail?id="+boardId;
			res.sendRedirect(url);
		}
	}
}
