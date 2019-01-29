package common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import employee.exception.EmployeeException;

//싱글톤(SingleTon) 디자인 패턴
//프로그램 구동 내내 메모리에 한 번만 기록되어서 사용됨
//static 메모리를 사용하게 됨
public class JDBCTemplate {
	
	public static Connection getConnection() throws EmployeeException {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("properties/driver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
			conn.setAutoCommit(false);
			
			if(conn == null)
				throw new EmployeeException("DB 서버 연결 실패!");
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		return conn;
	}
	
	public static void close(Connection conn) throws EmployeeException {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
	}
	
	public static void close(Statement stmt) throws EmployeeException {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
	}
	
	public static void close(ResultSet rset) throws EmployeeException {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
	}
	
	public static void commit(Connection conn) throws EmployeeException {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
	}
	
	public static void rollback(Connection conn) throws EmployeeException {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
	}
	
}
