package time.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import time.store.dao.AdminCategoryDao;
import time.store.domain.Category;
import time.store.utils.JDBCUtils;

public class AdminCategoryDaoImp implements AdminCategoryDao {

	@Override
	public List<Category> findAllCategory() throws SQLException {
		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

}
