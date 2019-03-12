package time.store.service.ServiceImp;

import java.sql.SQLException;
import java.util.List;

import time.store.dao.CategoryDao;
import time.store.dao.daoImp.CategoryDaoImp;
import time.store.domain.Category;
import time.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> getAllCats() throws SQLException {
		CategoryDao dao = new CategoryDaoImp();
		
		
		return dao.getAllCats();
	}

}
