package com.bjh.web.controller.list;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.service.BoardService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
	)

@WebServlet("/notice/update-board")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		BoardService service = new BoardService();
		Board board = service.getBoard(id);
		req.setAttribute("n", board);
		req.setAttribute("id", id);
		
		req.getRequestDispatcher("/WEB-INF/view/notice/update-board.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String open = req.getParameter("open");
		String username = (String)session.getAttribute("userID");

		Part filePart = req.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		InputStream fis =  filePart.getInputStream();
		
		String realPath = req.getServletContext().getRealPath("/upload");
		String filePath = realPath + File.separator + fileName;
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fis.read(buf)) != -1)
			fos.write(buf,0,size);
		
		fos.close();
		fis.close();
		
		boolean pub = false;
		if(open != null)
			pub = true;
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriterId(username);
		board.setFiles(fileName);
		board.setPub(pub);
		
		BoardService service = new BoardService();
		int result = service.updateBoard(board, id);
		
		if(username.equals("firstID"))
			res.sendRedirect("/admin/notice/a-list");
		else
			res.sendRedirect("list");
	}
}
