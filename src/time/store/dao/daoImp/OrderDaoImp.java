package time.store.dao.daoImp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import time.store.dao.OrderDao;
import time.store.domain.Order;
import time.store.domain.OrderItem;
import time.store.domain.Product;
import time.store.domain.UserBean;
import time.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public void saveOrder(Connection con, Order order) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orders values (? , ? , ? , ? , ? , ? , ? , ?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),
				order.getTelephone(),order.getUser().getUid()};
		qr.update(con, sql, params);
	}

	@Override
	public void saveOrderItem(Connection con, OrderItem orderItem) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values (? , ? , ? , ? , ?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {orderItem.getItmid(),orderItem.getQuantity(),orderItem.getTotal(),orderItem.getProduct().getPid(),
				orderItem.getOrder().getOid()};
		qr.update(con, sql, params);
	}

	@Override
	public int getTotalOrderByUid(UserBean user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)qr.query("select count(*) from orders where uid=?", new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	public List findMyOrdersWithPage(int startIndex, int pageSize, UserBean user) throws SQLException, IllegalAccessException, InvocationTargetException {
		// 这里用来获取该用户当前页所有的订单
		String sql = "select * from orders where uid = ? limit ? , ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
		// 这里准备将订单中的订单项添加到Order中。
		for (Order order : list) {
			sql = "select * from orderitem o ,product p where o.pid = p.pid and oid = ?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), order.getOid());
			// 遍历list
			for (Map<String , Object> map : list02) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				// 1. 创建时间类型转换器
				DateConverter dt = new DateConverter();
				// 2. 设置转换格式
				dt.setPattern("yyyy-MM--dd");
				// 3.注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
				
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				order.getList().add(orderItem);				
			}
		}
		return list;		
	}

}
