package native_jdbc_programing.util;

import java.io.IOException;
import java.io.InputStream; // java.io
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

public class JdbcUtil {

	
	public static Connection getConnection() {
		String propertiesPath = "db.properties"; // 키 이름은 무조껀 정해진대로 해야됨  (url,user,password)
		Connection con = null;
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) {// 파일 내용 리소스 파일을 담는것
			
			Properties prop = new Properties(); //key = value 로 저장됨
			
			prop.load(in); // prop로 값을 가져오는것
			
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			
			/*
			System.out.println("prop > " + prop);
			
			for (Entry<Object, Object> e : prop.entrySet()) {// prop.entrySet() 소스 마다 쌍으로 불러오기
				System.out.printf("%s -> %s %n", e.getKey(), e.getValue());
			}
			
			System.out.println();
			
			for (Object key : prop.keySet()) {
				System.out.print(key+" ->");
				System.out.println(prop.get(key));
			}
			*/
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}

}
