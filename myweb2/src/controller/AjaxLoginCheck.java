package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.UserDao;
import model.vo.User;

/**
 * Servlet implementation class AjaxLoginCheck
 */
@WebServlet("/ajaxLoginCheck.do")
public class AjaxLoginCheck extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		
		String flag = request.getParameter("flag");
		System.out.println(userName);
		System.out.println(flag);
			
		HttpSession session = request.getSession();
		
		String saveVcode = (String)session.getAttribute("verifyCode");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(!vcode.equalsIgnoreCase(saveVcode)) {
			map.put("code",1);
			map.put("info","验证码不正确!");
			System.out.println("Error Code\n");
		}else {
			System.out.println("Star\n");
			UserDao userDao = new UserDao();
			User user = userDao.get(userName);
			if(user == null) {
				map.put("code",2);
				map.put("info","用户名不存在");
				System.out.println("No UserName\n");
			}else {
				if(!user.getPassword().equals(password)) {
					map.put("code", 3);
					map.put("info","密码不正确");
					System.out.println("Error PassWord\n");
				}else{
					if(flag!=null) {
						System.out.println("select cookie");
						Cookie cookie1 = new Cookie("userName",userName);
						cookie1.setMaxAge(7*24*24*24);
						response.addCookie(cookie1);
						Cookie cookie2 = new Cookie("password",password);
						response.addCookie(cookie2);
					}
					session.setAttribute("currentUser", user);
					map.put("code",0);
					map.put("info","登陆成功");
					System.out.println("PASS\n");
				}
			}
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
