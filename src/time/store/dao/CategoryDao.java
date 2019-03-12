package time.store.dao;

import java.sql.SQLException;
import java.util.List;

import time.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats() throws SQLException;

}
