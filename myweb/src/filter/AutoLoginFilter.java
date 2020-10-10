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
		boolean bool = false;
		if (cookies!=null&&cookies.length>0) {
			for(Cookie c : cookies) {
				String name = c.getName();
				System.out.println(name);
				if("loginInfo".equals(name)) {
					System.out.println("----");
					bool = true;
				}
			}
			if(bool) {
				request.getRequestDispatcher("/maintest.jsp").forward(request, response);
				chain.doFilter(request, response);
			}else {
				request.getRequestDispatcher("/Login.html").forward(request, response);
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
