package time.store.service;

import java.sql.SQLException;
import java.util.List;

import time.store.domain.Category;

public interface CategoryService {

	List<Category> getAllCats() throws SQLException;

}
