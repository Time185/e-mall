package time.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;

import net.sf.json.JSONArray;
import time.store.domain.Category;
import time.store.service.CategoryService;
import time.store.service.ServiceImp.CategoryServiceImp;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	public String getAllCats(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		CategoryService categoryService = new CategoryServiceImp();
		List<Category> list = categoryService.getAllCats();
		// 将全部分类转换成JSON格式的数据
		String jsonStr = JSONArray.fromObject(list).toString();
		// 将全部分类信息响应到客户端,告诉客户端响应数据是json格式
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		return null;
		
	}

}
