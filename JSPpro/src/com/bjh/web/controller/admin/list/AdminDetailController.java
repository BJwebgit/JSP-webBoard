package com.bjh.web.controller.admin.list;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.service.BoardService;

@WebServlet("/admin/notice/a-detail")
public class AdminDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		BoardService service = new BoardService();
		Board board = service.getBoard(id);
		req.setAttribute("n", board);
		
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/a-detail.jsp").forward(req, res);
	}
}
