package com.bjh.web.controller.admin.list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.service.BoardService;

@WebServlet("/admin/notice/a-list")
public class AdminBoardListController extends HttpServlet{
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
		List<Board> list = service.getBoardList(field, query, page);
		int count = service.getBoardCount(field, query);
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/a-list.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String[] openIds = req.getParameterValues("open-id");
		String[] delIds = req.getParameterValues("del-id");
		String cmd = req.getParameter("cmd");
		String ids_ = req.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		BoardService service = new BoardService();
		
		switch(cmd) {
		case "ÀÏ°ý°ø°³":
			
			List<String> oids = Arrays.asList(openIds);
			List<String> cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			
			service.pubBoardAll(oids, cids);
			
			break;
		case "ÀÏ°ý»èÁ¦":
			int[] ids1 = new int[delIds.length];
			for(int i=0; i<delIds.length; i++)
				ids1[i] = Integer.parseInt(delIds[i]);
			int result = service.deleteBoardAll(ids1);
			break;
		}
		
		res.sendRedirect("a-list");
	}
}
