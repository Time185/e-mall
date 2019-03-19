package time.store.service;

import time.store.domain.Order;
import time.store.domain.PageModel;
import time.store.domain.UserBean;

public interface OrderService {

	void saveOrder(Order order) throws Exception;

	PageModel findMyOrdersWithPage(int curNum, UserBean user) throws Exception;

}
