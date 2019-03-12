package time.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import time.store.domain.Category;
import time.store.domain.Product;
import time.store.service.CategoryService;
import time.store.service.ProductService;
import time.store.service.ServiceImp.CategoryServiceImp;
import time.store.service.ServiceImp.ProductServiceImp;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ProductService productService = new ProductServiceImp();
		List<Product> newsProducts = productService.findNews();
		List<Product> hotProducts = productService.findHots();
		request.setAttribute("newsProducts", newsProducts);
		request.setAttribute("hotProducts", hotProducts);
		return "/jsp/index.jsp";
	}
}
