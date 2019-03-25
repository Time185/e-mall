package time.store.service.ServiceImp;

import java.sql.SQLException;
import java.util.List;

import time.store.dao.AdminCategoryDao;
import time.store.dao.daoImp.AdminCategoryDaoImp;
import time.store.domain.Category;
import time.store.service.AdminCategoryService;

public class AdminCategoryServiceImp implements AdminCategoryService {

	@Override
	public List<Category> findAllCategory() throws SQLException {
		AdminCategoryDao dao = new AdminCategoryDaoImp();
		
		return dao.findAllCategory();
	}
	
}
