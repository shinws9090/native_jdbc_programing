package native_jdbc_programing.ch01;

import java.sql.Connection; // 이걸로 임포트
import java.sql.DriverManager; //모든건 java.sql 임포트
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import native_jdbc_programing.dto.Department;

/**
 * JDBC 프로그램의 전형적인 실행 순서 
 * 2021.02.15
 */
public class JdbcConEx {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null; // 실사용에는 보안이 취약해서 사용불가능함.
		ResultSet rs = null;
		ArrayList<Department> list = null;
		
		try {

			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 데이터베이스 커넥션 생성
			String url = "jdbc:mysql://localhost:3306/mysql_study?useSSL=false";
			String user = "user_mysql_study";
			String password = "rootroot";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("con " + con);

			// 3. Statement 생성
			stmt = con.createStatement(); // 실사용에는 보안이 취약해서 사용불가능함.
			System.out.println("stmt >" + stmt);

			// 4. 쿼리 실행
			String sql = "select deptno, deptname, floor from Department";
			rs = stmt.executeQuery(sql);

			// 5. 쿼리 실행 결과 출력
			list = new ArrayList<Department>();
			System.out.println(list);
			while (rs.next()) {
				list.add(getDepartment(rs));
				
				
//				int deptNo = rs.getInt("deptno");  // 하드코딩
//				String deptName = rs.getString("deptname");
//				int floor = rs.getInt("floor");
				
				
//				Department dept = getDepartment(rs);
//				System.out.println(dept);
			}
			System.out.println(list);

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driber Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 사용한 Statement 종료
			try {rs.close();} catch (SQLException e) {}
			try {stmt.close();} catch (SQLException e) {}
			try {con.close();} catch (SQLException e) {}
			
		}
		
		System.out.println("결과");
		for(Department d : list) {
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
