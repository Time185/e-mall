package time.store.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import time.store.domain.Order;
import time.store.domain.OrderItem;
import time.store.domain.UserBean;

public interface OrderDao {

	void saveOrder(Connection con, Order order) throws SQLException;

	void saveOrderItem(Connection con, OrderItem orderItem) throws SQLException;

	int getTotalOrderByUid(UserBean user) throws SQLException;

	List findMyOrdersWithPage(int startIndex, int pageSize, UserBean user) throws SQLException, IllegalAccessException, InvocationTargetException;

}
