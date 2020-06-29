package com.bjh.web.controller.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bjh.web.controller.entity.Join;

public class JoinService {
	public int insertJoin(Join join) {
		int result = 0;
		
		String sql = "INSERT INTO USER1(USERID, USERPASSWORD, USERNAME, USERGENDER, USEREMAIL) VALUES(?,?,?,?,?)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"newlec","snrntka12");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, join.getUserID());
			st.setString(2, join.getUserPassword());
			st.setString(3, join.getUserName());
			st.setString(4, join.getUserGender());
			st.setString(5, join.getUserEmail());
			
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
