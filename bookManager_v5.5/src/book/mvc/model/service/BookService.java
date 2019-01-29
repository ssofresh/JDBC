package book.mvc.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import book.exception.BookException;
import book.mvc.model.dao.BookDao;
import book.mvc.model.vo.Book;

public class BookService {
	private BookDao bdao;
	
	public BookService() throws BookException {
		bdao = new BookDao();
	}
	
	public int insert(Book b) throws BookException {
		Connection conn = getConnection();
		int result = bdao.insertBook(conn, b);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}
	
	public int update(Book b) throws BookException {
		Connection conn = getConnection();
		int result = bdao.updateBook(conn, b);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}
	
	public int delete(int bookId) throws BookException {
		Connection conn = getConnection();
		int result = bdao.deleteBook(conn, bookId);
		if(result > 0)
			commit(conn);
		close(conn);
		return result;
	}
	
	public ArrayList<Book> selectList() throws BookException {
		Connection conn = getConnection();
		ArrayList<Book> bList = bdao.selectList(conn);
		close(conn);
		return bList;
	}
	
	public HashMap<Integer, Book> selectMap() throws BookException {
		Connection conn = getConnection();
		HashMap<Integer, Book> bMap = bdao.selectMap(conn);
		close(conn);
		return bMap;
	}
	
	public ArrayList<Book> selectTitleList(String title) throws BookException {
		Connection conn = getConnection();
		ArrayList<Book> bList = bdao.selectTitleList(conn, title);
		close(conn);
		return bList;
	}
	
	public HashMap<Integer, Book> selectTitleMap(String title) throws BookException {
		Connection conn = getConnection();
		HashMap<Integer, Book> bMap = bdao.selectTitleMap(conn, title);
		close(conn);
		return bMap;
	}
	
	public Book selectBook(int bookId) throws BookException {
		Connection conn = getConnection();
		Book b = bdao.selectBook(conn, bookId);
		close(conn);
		return b;
	}
}
