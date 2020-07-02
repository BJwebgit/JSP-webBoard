package com.bjh.web.controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.service.LoginService;

@WebServlet("/notice/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/notice/login.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userID = req.getParameter("userID");
		String userPassword = req.getParameter("userPassword");
		
		HttpSession session = req.getSession();
		
		LoginService service = new LoginService();
		int result = service.login(userID, userPassword);
		if(result == 1) {
			session.setAttribute("userID", userID);
		}
		res.sendRedirect("main");
	}
}