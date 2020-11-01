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

import model.dao.ProvinceCityDao;
import model.dao.UserDao;
import model.vo.Province;
import model.vo.User;

/**
 * Servlet implementation class AjaxRegister
 */
@WebServlet("/register.do")
public class AjaxRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String chrName = request.getParameter("chrName");
		String email = request.getParameter("email");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String password = request.getParameter("password");
		
		System.out.println(email);
		
		Map<String,Object> map = new HashMap<String,Object>();
		UserDao userdao = new UserDao();
		ProvinceCityDao provincedao = new ProvinceCityDao();
		
		int provinceCode = Integer.parseInt(province);
		int cityCode = Integer.parseInt(city);
		String provinceName = provincedao.getProvince(provinceCode);
		String cityName = provincedao.getCity(cityCode);
		User user = new User();
		String name = userdao.check(userName);
		if(name == null)
		{
			userdao.set(userName,password,chrName,provinceName,cityName,email);
		}
		user = userdao.get(userName);

		if(user != null) {
			map.put("code",0);
		}
		else {
			map.put("code",1);
		}
		String jsonStr = new Gson().toJson(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}

}
