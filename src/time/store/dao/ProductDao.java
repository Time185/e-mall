package time.store.dao;

import java.sql.SQLException;
import java.util.List;

import time.store.domain.Product;

public interface ProductDao {

	List<Product> findNews() throws SQLException;

	List<Product> findHots() throws SQLException;

	Product findProductById(String pid) throws SQLException;

	int findTotalNum(String cid) throws SQLException;

	List<Product> findProductsByCidWithPage(int startIndex, int pageSize,String cid) throws SQLException;

}
