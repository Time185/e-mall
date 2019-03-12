package time.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import time.store.dao.ProductDao;
import time.store.domain.PageModel;
import time.store.domain.Product;
import time.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> findNews() throws SQLException {
		String sql = "select * from product where pflag=0 order by pdate desc limit 0,9 ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findHots() throws SQLException {
		String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,9 ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		String sql = "select * from product where pid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		
	}

	@Override
	public int findTotalNum(String cid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)qr.query("select count(*) from product where cid=?", new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List<Product> findProductsByCidWithPage(int startIndex, int pageSize, String cid) throws SQLException {
		String sql = "select * from product where cid = ? limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
		
	}

}
