package time.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import time.store.domain.PageModel;
import time.store.domain.Product;
import time.store.service.ProductService;
import time.store.service.ServiceImp.ProductServiceImp;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	public String findProductById(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		// 获取到要查商品信息的id
		String pid = request.getParameter("pid");
		System.out.println(pid);
		// 调用service层进行查找商品信息
		ProductService productService = new ProductServiceImp();
		Product product = productService.findProductById(pid);
		// 将查找到的商品放入request中，返回到商品信息页面
		request.setAttribute("product", product);
		return "jsp/product_info.jsp";
	}
	
	// 获取商品分类分页的信息
	public String findProductsByCidWithPage(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		String numStr = request.getParameter("num");
		int num = Integer.parseInt(numStr);
 		String cid = request.getParameter("cid");
 		// 调用service层方法获取num页的内容
 		ProductService productService = new ProductServiceImp();
 		PageModel page = productService.findProductsByCidWithPage(num,cid);
 		request.setAttribute("page", page);
 		return "jsp/product_list.jsp";
	}	
}

