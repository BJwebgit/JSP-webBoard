package com.bjh.web.controller.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.service.SecessionService;

@WebServlet("/notice/secession")
public class SecessionController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		req.setAttribute("n", userID);
		req.getRequestDispatcher("/WEB-INF/view/notice/secession.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userPassword = req.getParameter("userPassword");
		String checkPassword = req.getParameter("checkPassword");
		
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		if(userPassword.equals(checkPassword)) {
			SecessionService service = new SecessionService();
			int result = service.Secession(userID);
			res.sendRedirect("main");
		}
		else {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter script = res.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 일치하지 않습니다')");
			script.println("location.href = 'secession'");
			script.println("</script>");
			script.flush();
		}
	}
}
