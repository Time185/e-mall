package time.store.service;

import java.sql.SQLException;
import java.util.List;

import time.store.domain.PageModel;
import time.store.domain.Product;

public interface ProductService {

	List<Product> findNews() throws SQLException;

	List<Product> findHots() throws SQLException;

	Product findProductById(String pid) throws SQLException;

	PageModel findProductsByCidWithPage(int num, String cid) throws SQLException;

}
