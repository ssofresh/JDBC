package product.model.dao;

import java.sql.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

import product.model.dto.Product;
import static common.JDBCTemplate.*;

public class ProductDao {

	private Properties prop = new Properties();
	public ProductDao() {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> selectAll(Connection conn) {
		ArrayList<Product> pList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();

				p.setpId(rset.getString("product_id"));
				p.setpName(rset.getString("p_name"));
				p.setPrice(rset.getInt("price"));
				p.setDescription(rset.getString("description"));
				pList.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pList;
	}
	
	
}
