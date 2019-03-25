package time.store.dao;

import java.sql.SQLException;
import java.util.List;

import time.store.domain.Category;

public interface AdminCategoryDao {

	List<Category> findAllCategory() throws SQLException;

}
