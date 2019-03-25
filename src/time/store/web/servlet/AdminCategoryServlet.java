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
import time.store.service.AdminCategoryService;
import time.store.service.ServiceImp.AdminCategoryServiceImp;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
	public String findAllCategory(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		AdminCategoryService adminService = new AdminCategoryServiceImp();
		List<Category> list = adminService.findAllCategory();
		
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
	}
}
