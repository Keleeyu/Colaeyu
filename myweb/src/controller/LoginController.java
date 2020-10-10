package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import model.dao.ResourceDao;
import model.dao.UserDao;
import model.vo.User;
import model.vo.Resource;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode 	= request.getParameter("vcode"); 
		String checkBox = request.getParameter("checkBox");

		HttpSession session = request.getSession();
		String saveVcode = (String) session.getAttribute("verifyCode");
		
		String forwardPath = "";
		if(!vcode.equalsIgnoreCase(saveVcode)) {
			request.setAttribute("info", "抱歉,您输入的验证码不正确！");
			forwardPath ="/error.jsp";
		}
		else {
			User user = UserDao.get(userName);
			/*Resource resource = null;
			List<Resource> resources = ResourceDao.get(userName);
			resource = resources.get(0);
			System.out.println(resource.getUrl());*/
			if(user == null) {
				request.setAttribute("info", "抱歉,您上输入的用户名不存在!");
				forwardPath = "/error.jsp";
			}
			else {
				if(!user.getPassword().equals(password)) {
					request.setAttribute("info", "抱歉,您输入的密码不正确!");
					forwardPath = "/error.jsp";
				}
				else {
					if(checkBox != null)
					{
						String str = userName+"&"+password;
						Encoder encoder = Base64.getEncoder();
						String encodeToString = encoder.encodeToString(str.getBytes());
						Cookie cookie = new Cookie("loginInfo",encodeToString);
						cookie.setMaxAge(60*60*24*7);
						response.addCookie(cookie);
					}
					session.setAttribute("currentUser", user);
					forwardPath = "/maintest.jsp";
				}
			}
		}
		RequestDispatcher reqDispatcher = this.getServletContext().getRequestDispatcher(forwardPath);
		reqDispatcher.forward(request, response);
	}

}
