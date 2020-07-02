package com.bjh.web.controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjh.web.controller.entity.Join;
import com.bjh.web.controller.service.JoinService;

@WebServlet("/notice/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/notice/join.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userID = req.getParameter("userID");
		String userPassword = req.getParameter("userPassword");
		String userName = req.getParameter("userName");
		String userGender = req.getParameter("userGender");
		String userEmail = req.getParameter("userEmail");
		
		Join join = new Join();
		join.setUserID(userID);
		join.setUserPassword(userPassword);
		join.setUserName(userName);
		join.setUserGender(userGender);
		join.setUserEmail(userEmail);
		
		JoinService service = new JoinService();
		int result = service.insertJoin(join);
		System.out.println(result);
		res.sendRedirect("login");
		
	}
}