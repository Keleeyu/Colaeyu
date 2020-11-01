package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.UserDao;
import model.dao.emailDao;
import model.vo.Email;
import model.vo.User;

/**
 * Servlet implementation class AjaxRegister
 */
@WebServlet("/checkExist.do")
public class AjaxcheckExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		emailDao emaildao = new emailDao();
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		System.out.println(email);
		Map<String,Object> map = new HashMap<String,Object>();
		
		User user = userDao.get(userName);
		String name = userDao.check(userName);
		Email myemail = emaildao.get(email);
		
		if(user == null && name == null){
			System.out.println("no user");
			map.put("checkuser",0);
		}else {
			System.out.println(name);
			map.put("checkuser",1);
		}
		
		if(myemail == null){
			System.out.println("email empty");
			map.put("checkemail",0);
		}
		else {
			System.out.println("email exist");
			map.put("checkemail",1);
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
