package filter;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.ResourceDao;
import model.vo.Resource;
import model.vo.User;

public class PermissionFilter implements Filter {

	private String notCheckPath;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req;
		String path = request.getServletPath();
		User user = null;
		int flag = 0;
		List<Resource> resources = new ArrayList();
		
		System.out.println("请求地址url-pattern:"+path);
		
		if(notCheckPath.indexOf(path) == -1) {
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("currentUser");
			
			if(user == null) {
				request.setAttribute("info", "没有权限访问");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
			}else {
				user= (User) session.getAttribute("currentUser");
				resources = ResourceDao.get(user.getUserName());
				for(int i = 0;i< resources.size();i++)
				{
					if(resources.get(i).getUrl().equals(path))
					{
						flag = 1;
					}
				}
				if(flag == 1){
					chain.doFilter(req, resp);
					flag = 0;
				}
				else {
					request.setAttribute("info", "没有权限访问");
					request.getRequestDispatcher("/error.jsp").forward(request, resp);
				}
			}
		
		}else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		notCheckPath = config.getInitParameter("notCheckUri");
	}

}
