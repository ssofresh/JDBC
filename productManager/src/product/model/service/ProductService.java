package product.model.service;

import java.sql.*;
import java.util.ArrayList;

import product.model.dao.ProductDao;
import product.model.dto.Product; 
import static common.JDBCTemplate.*;
public class ProductService {

	private ProductDao pdao = new ProductDao();
	public ProductService() {}
	
	public ArrayList<Product> selectAll() {
		Connection conn = getConnection();
		ArrayList<Product> pList = pdao.selectAll(conn);
		close(conn);
		
		return pList;
	}
}
