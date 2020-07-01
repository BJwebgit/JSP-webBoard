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
import javax.servlet.http.Part;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.service.BoardService;

@MultipartConfig(
	fileSizeThreshold = 1024*1024,
	maxFileSize = 1024*1024*50,
	maxRequestSize = 1024*1024*50*5
)

@WebServlet("/notice/write")
public class WriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/notice/write.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
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
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriterId("BJH");
		board.setFiles(fileName);
		
		BoardService service = new BoardService();
		int result = service.insertNotice(board);
		
		res.sendRedirect("list");
	}
}
