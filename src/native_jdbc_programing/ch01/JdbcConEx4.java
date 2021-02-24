package native_jdbc_programing.ch01;

import java.sql.Connection; // 이걸로 임포트
import java.sql.DriverManager; //모든건 java.sql 임포트
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.util.JdbcUtil;

/**
 * JDBC try - catch - resource 종료문 미사용 2021.02.16
 */
public class JdbcConEx4 {

	public static void main(String[] args) {
		ArrayList<Department> list = null;


		String sql = "select deptno, deptname, floor from Department";

		// 2. 데이터베이스 커넥션 생성 , Statement 생성 , 쿼리 실행
		try (
				Connection con = JdbcUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
			) 
			{

			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 5. 쿼리 실행 결과 출력
			list = new ArrayList<Department>();
			while (rs.next()) {
				list.add(getDepartment(rs));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driber Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("결과");
		for (Department d : list) {
			System.out.println(d);
		}

	}

	private static Department getDepartment(ResultSet rs) throws SQLException { // 클래스 불러서 돌리기
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");

		return new Department(deptNo, deptName, floor);
	}

}
