package native_jdbc_programing.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Title;
import native_jdbc_programing.util.JdbcUtil;

public class TransactionService {

	public String transAddTitlAndDepartment(Title title, Department dept) { // 합쳐서 쓰기
		String titleSql = "insert into title values (?,?)";
		String deptSql = "insert into department values (?,?,?)";

		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		String res = "";
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			tPstmt.setString(2, title.getTname());
			tPstmt.executeUpdate();

			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptNo());
			dPstmt.setString(2, dept.getDeptName());
			dPstmt.setInt(3, dept.getFloor());
			dPstmt.executeUpdate();

			con.commit(); // 완료 후 커밋 필수
			res = "commit";
		} catch (SQLException e) {
			res ="rollback";
			rollback(con); // 에러 발생시 데이터베이스 롤백 호출
		} finally {
			System.out.println(res);
			closeUtil(con, tPstmt, dPstmt);

		}
		return res;

	}

	public int transRemoveTitleAndDepartment(Title title, Department dept) {
		String titleSql = "delete from title where tno = ?";
		String deptSql = "delete from department where deptno = ?";

		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		
		int res = 0;

		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			System.out.println("res >"+res);
			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			res = tPstmt.executeUpdate();
			System.out.println("res >"+res);

			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptNo());
			res += dPstmt.executeUpdate();
			System.out.println("res >"+res);
			
			if(res==2) {
				con.commit(); // 완료 후 커밋 필수
			}else {
				throw new SQLException();
			}
			System.out.println("commit()");
		} catch (SQLException e) {
			System.out.println("rollback");
			rollback(con); // 에러 발생시 데이터베이스 롤백 호출
		} finally {
			closeUtil(con, tPstmt, dPstmt);
		}
		return res;
	}

	public void closeUtil(Connection con, PreparedStatement tPstmt, PreparedStatement dPstmt) {
		try {
			con.setAutoCommit(true);
			if(tPstmt != null)	tPstmt.close();
			if(dPstmt != null)	dPstmt.close();
			if(con != null)	con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback(Connection con) {
		try {
			con.rollback(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
