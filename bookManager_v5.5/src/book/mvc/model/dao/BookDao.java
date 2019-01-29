package book.mvc.model.dao;

import static common.JDBCTemp.*;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import book.exception.BookException;
import book.mvc.model.vo.Book;

public class BookDao {
	private Properties prop = new Properties();
	
	public BookDao() throws BookException {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		}
	}
	
	public int insertBook(Connection conn, Book b) throws BookException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setDate(4, b.getPublishDate());
			pstmt.setInt(5, b.getPrice());
			
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BookException("도서 추가 실패!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateBook(Connection conn, Book b) throws BookException {
		int result=0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("update");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getPrice());
			pstmt.setInt(2, b.getBookId());
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException(b.getBookId()+" 도서번호의 도서 가격 수정 실패!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBook(Connection conn, int bookId) throws BookException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException(bookId + " 도서번호의 도서 삭제 실패!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Book> selectList(Connection conn) throws BookException {
		ArrayList<Book> bList = new ArrayList<Book>();
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				
				bList.add(b);
			}
			
			if(bList.size()==0) {
				throw new BookException("조회된 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}
	
	public HashMap<Integer, Book> selectMap(Connection conn) throws BookException {
		HashMap<Integer, Book> bMap = new HashMap<Integer, Book>();
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				
				bMap.put(b.getBookId(), b);
			}
			
			if(bMap.size() == 0)
				throw new BookException("조회된 정보가 존재하지 않습니다.");
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bMap;
	}
	
	public ArrayList<Book> selectTitleList(Connection conn, String title) throws BookException {
		ArrayList<Book> bList = new ArrayList<Book>();
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+title+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				
				bList.add(b);
			}
			if(bList.size()==0) {
				throw new BookException("조회된 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}
	
	public HashMap<Integer, Book> selectTitleMap(Connection conn, String title) throws BookException {
		HashMap<Integer, Book> bMap = new HashMap<Integer, Book>();
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+title+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				
				bMap.put(b.getBookId(), b);
			}
			if(bMap.size()==0) {
				throw new BookException("조회된 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bMap;
	}
	
	public Book selectBook(Connection conn, int bookId) throws BookException {
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
			}
			
			if(b == null) 
				throw new BookException(bookId + "도서번호의 조회된 정보가 없습니다.");
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
}
