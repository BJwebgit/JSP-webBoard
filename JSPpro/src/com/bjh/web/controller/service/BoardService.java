package com.bjh.web.controller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjh.web.controller.entity.Board;

public class BoardService {
	public List<Board> getBoardPubList(String field, String query, int page) {
		List<Board> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (" + 
				"    SELECT ROWNUM NUM, N.* " + 
				"    FROM (SELECT * FROM BOARD WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" + 
				")" + 
				"WHERE PUB=1 AND NUM BETWEEN ? AND ?";	
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){	
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate =rs.getDate("REGDATE");
				String writerId =rs.getString("WRITER_ID");
				String files =rs.getString("FILES");
				String content =rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				Board board = new Board(
						id,
						title,
						regdate,
						writerId,
						files,
						content,
						pub
					);
				list.add(board);
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

	public int getBoardCount() {
		return getBoardCount("title", "");
	}
	public int getBoardCount(String field, String query) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM (" + 
				"    SELECT ROWNUM NUM, N.* " + 
				"    FROM (SELECT * FROM BOARD WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" + 
				")";	
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			if(rs.next())
				count = rs.getInt("count");
			
		    rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int insertBoard(Board board) {
		int result = 0;
		
		String sql = "INSERT INTO BOARD(TITLE, WRITER_ID, CONTENT, FILES, PUB) VALUES(?,?,?,?,?)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, board.getTitle());
			st.setString(2, board.getWriterId());
			st.setString(3, board.getContent());
			st.setString(4, board.getFiles());
			st.setBoolean(5, board.getPub());
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
	
	public Board getBoard(int id) {
		Board board = null;
		
		String sql = "SELECT * FROM BOARD WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){	
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate =rs.getDate("REGDATE");
				String writerId =rs.getString("WRITER_ID");
				String files =rs.getString("FILES");
				String content =rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				board = new Board(
						nid,
						title,
						regdate,
						writerId,
						files,
						content,
						pub
					);
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
		
		return board;
	}

	public int ckPassword(String userID, String userPassword) {
		int result = 0;
		
		String sql = "SELECT USERPASSWORD FROM USER1 WHERE USERID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userID);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword))
					return 1;
				else
					return 0;
			}
			st.close();
			con.close();
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int changePassword(String userID, String nextPwd) {
		int result = 0;
		
		String sql = "UPDATE USER1 SET USERPASSWORD=? WHERE USERID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nextPwd);
			st.setString(2, userID);
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

	public int updateBoard(Board board, int id) {
		int result = 0;
		
		String sql = "UPDATE BOARD SET TITLE=?, WRITER_ID=?, CONTENT=?, FILES=?, PUB=? WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, board.getTitle());
			st.setString(2, board.getWriterId());
			st.setString(3, board.getContent());
			st.setString(4, board.getFiles());
			st.setBoolean(5, board.getPub());
			st.setInt(6, id);
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

	public int deleteBoard(int id) {
		int result = 0;
		
		String sql = "DELETE FROM BOARD WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
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

	public int pubBoardAll(int[] oids, int[] cids){
		List<String> oidsList = new ArrayList<>();
		for(int i=0; i<oids.length; i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<>();
		for(int i=0; i<cids.length; i++)
			oidsList.add(String.valueOf(cids[i]));

		return pubBoardAll(oidsList, cidsList);
	}
	
	public int pubBoardAll(List<String> oids, List<String> cids){
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		return pubBoardAll(oidsCSV, cidsCSV);
	}
	
	public int pubBoardAll(String oidsCSV, String cidsCSV){
		int result = 0;
		
		String sqlOpen = String.format("UPDATE BOARD SET PUB=1 WHERE ID IN (%s)", oidsCSV);
		String sqlClose = String.format("UPDATE BOARD SET PUB=0 WHERE ID IN (%s)", cidsCSV);
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			Statement stOpen = con.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			Statement stClose = con.createStatement();
			result += stClose.executeUpdate(sqlClose);
			
			stOpen.close();
			stClose.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteBoardAll(int[] ids) {
		int result = 0;
		
		String params = "";
		for(int i=0; i<ids.length; i++) {
			params += ids[i];
			if(i < ids.length-1)
				params += ",";
		}
		String sql = String.format("DELETE FROM BOARD WHERE ID IN (%s)", params);
		System.out.println(sql);
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
			
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

	public List<Board> getBoardList(){
		
		return getBoardList("title", "", 1);
	}
	public List<Board> getBoardList(int page){
		
		return getBoardList("title", "", page);
	}
	public List<Board> getBoardList(String field, String query, int page){
		
		List<Board> list = new ArrayList<>();
		
		String sql = "SELECT * FROM (" + 
				"    SELECT ROWNUM NUM, N.* " + 
				"    FROM (SELECT * FROM BOARD WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N" + 
				")" + 
				"WHERE NUM BETWEEN ? AND ?";	
		
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){	
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate =rs.getDate("REGDATE");
				String writerId =rs.getString("WRITER_ID");
				String files =rs.getString("FILES");
				String content =rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				Board board = new Board(
						id,
						title,
						regdate,
						writerId,
						files,
						content,
						pub
					);
				list.add(board);
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
}
