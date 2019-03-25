package time.store.service.ServiceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import time.store.dao.OrderDao;
import time.store.dao.daoImp.OrderDaoImp;
import time.store.domain.Order;
import time.store.domain.OrderItem;
import time.store.domain.PageModel;
import time.store.domain.UserBean;
import time.store.service.OrderService;
import time.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	@Override
	public void saveOrder(Order order) throws SQLException  {
		// 这里有一个严重的问题   如果存储订单成功 而订单项没有存储成功  那么就会有系统的漏洞  
		// 解决方法  就是利用数据库的事务功能    确保要么修改全都成功  要么全都失败
		// 获取连接
		Connection con = null;
		try {
			// 获取连接
			con = JDBCUtils.getConnection();
			// 开启事务
			con.setAutoCommit(false);
			// 保存订单
			OrderDao dao = new OrderDaoImp();
			// 将订单存入数据库中
			dao.saveOrder(con , order);
			for(OrderItem orderItem : order.getList()) {
				// 将订单中的订单项存入数据库中
				dao.saveOrderItem(con , orderItem);			
			}
			// 完成之后  进行提交
			con.commit();
		} catch (Exception e) {
			// 回滚
			con.rollback();
		} 
		
	}

	@Override
	public PageModel findMyOrdersWithPage(int curNum, UserBean user) throws Exception {
		// 获取用户订单的总数
		OrderDao dao = new OrderDaoImp();
		int totalOrder = dao.getTotalOrderByUid(user);
		
		// 创建PageModel
		PageModel pm = new PageModel(curNum, totalOrder, 3);
		// 调用dao层获取PageModel
		List list = dao.findMyOrdersWithPage(pm.getStartIndex(), pm.getPageSize(), user);
		pm.setList(list);
		
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

}
