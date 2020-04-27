package com.lec.java.db03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lec.java.db.Query;

// 공통적으로 사용하는 상수들 인터페이스에 담아서 처리.
public class DB03Main implements Query {

	public static void main(String[] args) {
		System.out.println("DB 3 - PreparedStatement");
		// db패키지에 ?로 표현된 곳이 미리 준비해놓는다는 뜻에 PreparedStatement 방식
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// OracleDriver 클래스를 메모리에 로딩
			Class.forName(DRIVER);
			System.out.println("드라이버 클래스 로딩 성공");

			// DB Connection
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			System.out.println("DB Connection 성공");
			
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, 10);
			pstmt.setString(2, "헐크");
			pstmt.setString(3, "2000-10-10");
			int cnt = pstmt.executeUpdate(); //DML 결과값이 정수니깐 int로
			System.out.println(cnt + " 개 행(row) INSERT 성공");
			
			System.out.println();
			System.out.println("UPDATE");
			pstmt.close(); // prepared에서는 미리 준비된 메소드를 호출해서 하기 때문에 클로즈를 시켜주어야한다. 대신 서버에 로딩이 걸릴수도 있으니깐 보통은 쿼리문 3개를 준비해서 쓴다.
			pstmt = conn.prepareStatement(SQL_UPDATE_BIRTHDATE);
			pstmt.setString(1, "2020-01-01");
			pstmt.setInt(2, 10);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "개 행(row) UPDATE 성공");
			
			System.out.println();
			pstmt.close();
			System.out.println("SELECT");
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String no = rs.getString(COL_LABEL_NO);
				String name = rs.getString(COL_LABEL_NAME);
				String birthdate = rs.getString(COL_LABEL_BIRTHDATE);
				System.out.println(no + " | " + name + " | " + birthdate);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // end main()

} // end class DB03Main
