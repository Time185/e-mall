package time.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import time.store.domain.UserBean;

/**
 * Servlet Filter implementation class PriviledgeFilter
 */
@WebFilter({ "/jsp/order_info.jsp", "/jsp/cart.jsp", "/jsp/order_list.jsp" })
public class PriviledgeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PriviledgeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 需要进行转换成HttpServletRequest
		HttpServletRequest req = (HttpServletRequest)request;
		UserBean  user =(UserBean)req.getSession().getAttribute("loginUser");
		if(null != user) {
			chain.doFilter(request, response);			
		}else {
			req.setAttribute("msg", "请用户登录之后再进行访问！");
			req.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
