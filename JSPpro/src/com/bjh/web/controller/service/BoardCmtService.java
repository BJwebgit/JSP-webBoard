package com.bjh.web.controller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjh.web.controller.entity.Board;
import com.bjh.web.controller.entity.BoardCmt;

public class BoardCmtService {

	public int insertCmt(BoardCmt boardcmt) {
		int result = 0;
		
		String sql = "INSERT INTO BOARDCMT(CMT, WRITER_ID, BOARD_ID) VALUES(?,?,?)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, boardcmt.getCmt());
			st.setString(2, boardcmt.getWriterId());
			st.setInt(3, boardcmt.getBoardId());
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public List<BoardCmt> getBoardCmtList(int id){
		
		List<BoardCmt> list = new ArrayList<>();
		
		String sql = "SELECT * FROM BOARDCMT WHERE BOARD_ID=? ORDER BY REGDATE DESC";	
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){	
				int nid = rs.getInt("ID");
				String cmt = rs.getString("CMT");
				Date regdate =rs.getDate("REGDATE");
				String writerId =rs.getString("WRITER_ID");
				int boardId = rs.getInt("BOARD_ID");
				
				BoardCmt boardcmt = new BoardCmt(
						nid,
						cmt,
						regdate,
						writerId,
						boardId
					);
				list.add(boardcmt);
			}
		    rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int deleteBoardCmt(int cmtid) {
		int result = 0;
		
		String sql = "DELETE FROM BOARDCMT WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, cmtid);
			result = st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
