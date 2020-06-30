package com.bjh.web.controller.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.service.BoardService;

@WebServlet("/notice/list")
public class BoardListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String field_ = req.getParameter("f");
		String query_ = req.getParameter("q");
		String page_ = req.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";
		if(query_ != null && !query_.equals("")){
			query = query_;
		}
		
		int page = 1;
		if(page_ != null && !page_.equals("")){
			page = Integer.parseInt(page_);
		}
		
		BoardService service = new BoardService();
		List<Board> list = service.getNoticePubList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		
		req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, res);
	}
}
