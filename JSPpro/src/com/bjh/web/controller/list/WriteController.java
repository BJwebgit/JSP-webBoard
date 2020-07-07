package com.bjh.web.controller.list;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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

@WebServlet("/notice/write")
public class WriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
			req.setAttribute("n", userID);
			req.getRequestDispatcher("/WEB-INF/view/notice/write.jsp").forward(req, res);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userID = (String)session.getAttribute("userID");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String username = req.getParameter("username");
		String open = req.getParameter("open");
		
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
		int result = service.insertBoard(board);
		
		if(userID.equals("firstID"))
			res.sendRedirect("/admin/notice/a-list");
		else
			res.sendRedirect("list");
	}
}
