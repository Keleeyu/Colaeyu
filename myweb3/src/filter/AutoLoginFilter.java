package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.dao.UserDao;
import model.vo.User;


/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter("/Login.html")
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
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
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		Cookie[] cookies = httpServletRequest.getCookies();
		HttpSession session = ((HttpServletRequest) request).getSession();
		boolean bool = false;
		String name ="";
		String userName= "";
		String password="";
		if (cookies !=null && cookies.length>0) {
			System.out.println(cookies.length);
			for(Cookie c : cookies) {
				name = c.getName();
				System.out.println(name);
				if("userName".equals(name)) {
					userName = c.getValue();
					bool = true;
					System.out.println(userName);
				}
				if("password".equals(name)) {
					password = c.getValue();
					bool = true;
					System.out.println(password);
				}
			}
		}
		if(bool) {
			User user = UserDao.get(userName);
			if(user == null){
				System.out.println("userName equmpty");
			}else {
				if(password.equals(user.getPassword())) {
					System.out.println(user.getUserName());
					session.setAttribute("currentUser", user);
					request.getRequestDispatcher("/maintest.jsp").forward(request, response);
					chain.doFilter(request, response);
				}else {
					chain.doFilter(request, response);
				}
			}
		}else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
