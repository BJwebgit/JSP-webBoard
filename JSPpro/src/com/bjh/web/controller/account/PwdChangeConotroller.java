package com.bjh.web.controller.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjh.web.controller.service.BoardService;

@WebServlet("/notice/pwdchange")
public class PwdChangeConotroller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		req.setAttribute("n", userID);
		req.getRequestDispatcher("/WEB-INF/view/notice/pwdchange.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String nowPwd = req.getParameter("userPassword");
		String nextPwd = req.getParameter("nextPassword");
		String checkPwd = req.getParameter("checkPassword");

		System.out.println(nextPwd);
		System.out.println(checkPwd);
		
		if(!nextPwd.equals(checkPwd)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter script = res.getWriter();
			script.println("<script>");
			script.println("alert('새 비밀번호가 일치하지 않습니다')");
			script.println("location.href = 'pwdchange'");
			script.println("</script>");
			script.flush();
		}
		else {
			String userID = (String)session.getAttribute("userID");
			BoardService service = new BoardService();
			int result = service.ckPassword(userID, nowPwd);
			if(result == 1) {
				result = service.changePassword(userID, nextPwd);
				session.invalidate();
				res.sendRedirect("login");
			}
			else {
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter script = res.getWriter();
				script.println("<script>");
				script.println("alert('현재 비밀번호가 일치하지 않습니다')");
				script.println("location.href = 'pwdchange'");
				script.println("</script>");
				script.flush();
			}
		}
	}
}
