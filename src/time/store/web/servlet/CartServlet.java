package time.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import time.store.domain.Cart;
import time.store.domain.CartItem;
import time.store.domain.Product;
import time.store.service.CartService;
import time.store.service.ProductService;
import time.store.service.ServiceImp.CartServiceImp;
import time.store.service.ServiceImp.ProductServiceImp;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	public String addCartItemToCart(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		// 首先获取session中的Cart,如果不存在，则新建一个存入session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(null == cart) {
			// 创建Cart对象		
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		CartService cartService = new CartServiceImp();
		// 根据pid和num获取到购物项的的信息
		CartItem cartItem = cartService.findCartItemById(request.getParameter("pid"),request.getParameter("quantity"));
		// 将购购物项添加到容器中
		cart.addCart(cartItem);
		// 将cart存入session   不能放到request  容易失效
		System.out.println(cart.getTotal() + "------------");
		
		return "/jsp/cart.jsp";
	}
	public String removeCartItem(HttpServletRequest request,HttpServletResponse response) {
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeCart(request.getParameter("pid"));
		return "/jsp/cart.jsp";
	}
	// 清空购物车
	public String clearCart(HttpServletRequest request,HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clearCart();
		return "/jsp/cart.jsp";
	}
}
