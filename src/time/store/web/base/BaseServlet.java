package time.store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = null;
		String method = request.getParameter("method");
		System.out.println(method);
		if(null == method || "".equals(method) || method.trim().equals("")) {
			method = "execute";
		}
		Class clazz = this.getClass();
		Method md = null;
		try {
			// 获取clazz上名称为method的方法
			md = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
		
			if(null != md) {
				// 调用找到的方法
				path = (String) md.invoke(this, request,response);
				
				if(null != path) {
					
					request.getRequestDispatcher(path).forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// 默认方法
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		return null;
		
	}
	
	
}
