package time.store.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import time.store.domain.Cart;
import time.store.domain.CartItem;
import time.store.domain.Order;
import time.store.domain.OrderItem;
import time.store.domain.PageModel;
import time.store.domain.UserBean;
import time.store.service.OrderService;
import time.store.service.ServiceImp.OrderServiceImp;
import time.store.utils.UUIDUtils;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	public String saveOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 首先应该判断用户是否登录    
		UserBean user = (UserBean) request.getSession().getAttribute("loginUser");
		if(null == user) {
			// 说明用户未登录  就无法加入订单中
			request.setAttribute("msg", "您未登录，请登录后使用订单功能！");
			return "/jsp/order_info.jsp";
		}
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 说明用户存在   可以将购物车的商品添加到订单中
		// 创建订单对象  并给订单属性赋值
		Order order = new Order();
		order.setOid(UUIDUtils.getId());
		order.setUser(user);
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		// 遍历购物项中的商品   订单项进行赋值
		for (CartItem cartitem : cart.getCartItems()) {
			// 创建订单项   将所有的订单项都存入订单order中
			OrderItem orderItem = new OrderItem();
			orderItem.setItmid(UUIDUtils.getId());
			orderItem.setOrder(order);
			orderItem.setProduct(cartitem.getProduct());
			orderItem.setQuantity(cartitem.getNum());
			orderItem.setTotal(cartitem.getTotalItem());			
			order.getList().add(orderItem);
		}
		// 调用service层    将order的数据存入orders数据库 中
		OrderService orderService = new OrderServiceImp();
		orderService.saveOrder(order);
		// 将订单order存入request中   用于order_info.jsp页面的显示
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	// 获取我的订单
	public String findMyOrdersWithPage(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		// 获取用户名
		UserBean user = (UserBean)request.getSession().getAttribute("loginUser");
		// 获取当前页码
		int curNum = Integer.parseInt(request.getParameter("num"));
		// 调用service层  查询当前用户订单信息 返回PageModel
		OrderService orderService = new OrderServiceImp();
		PageModel pm = orderService.findMyOrdersWithPage(curNum, user);
		// 将pm存入request.用于页面显示
		System.out.println(pm.getList().size() + "-----------");
		request.setAttribute("page", pm);
		// 转发至我的订单页面
		return "/jsp/order_list.jsp";
	}

}
