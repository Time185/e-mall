package time.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import time.store.domain.UserBean;
import time.store.service.UserService;
import time.store.service.ServiceImp.UserServiceImp;
import time.store.utils.MailUtils;
import time.store.utils.MyBeanUtils;
import time.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	
	// 注册页面跳转 
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回要跳转的页面
		return "/jsp/register.jsp";
	}
	
	// 登录界面的跳转 
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回要跳转的页面
		return "/jsp/login.jsp";
	}
	
	// 用户进行注册页面
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建userBean对象 
		UserBean userBean = new UserBean();
		// 将提交的数据封装到userBean中。
		MyBeanUtils.populate(userBean, request.getParameterMap());
		// 调用业务层的功能
		UserService userService = new UserServiceImp();
		
		try {
			// 注册成功！
			userService.userRegist(userBean);
			// 发送激活邮件,向用户邮箱发送激活码
			MailUtils.sendMail(userBean.getEmail(), userBean.getCode() );
			request.setAttribute("msg", "用户注册成功，请激活！");
		} catch (Exception e) {
			// 注册失败
			request.setAttribute("mag", "用户注册失败，请重新注册！");
		}
 		return "/jsp/info.jsp";
	}
	
	// 注册用户用于激活
	public String active (HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String code = request.getParameter("code");
		UserService userService = new UserServiceImp();
		boolean flag = userService.activeUser(code);
		if(flag) {
			request.setAttribute("msg", "用户激活成功，请登录！");
			return "/jsp/login.jsp";
		}else {
			request.setAttribute("msg", "用户激活失败，请重新注册！");
			return "/jsp/info.jsp";
		}		
	}
	
	// 用户用于登录
	public String userLogin(HttpServletRequest request,HttpServletResponse response)  {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService userService = new UserServiceImp();
		try {
			UserBean userBean = userService.login(username,password);
			request.getSession().setAttribute("loginUser", userBean);
			response.sendRedirect("/StoreV2/index.jsp");
			return null;
		} catch (Exception e) {
			// 获取抛出异常的时存储的消息
			String msg = e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
		
	}
	// 用户退出
	public String quit (HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("loginUser");
		return "/jsp/index.jsp";
	}
}
