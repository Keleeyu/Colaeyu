package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.UserDao;

/**
 * Servlet implementation class Ajaxsearchinfo
 */
@WebServlet("/searchinfo.do")
public class Ajaxsearchinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String chrName = request.getParameter("chrName");
		String email = request.getParameter("email");
		String province = request.getParameter("province");
		if(userName=="")userName =null;
		if(chrName=="")chrName =null;
		if(email=="")email =null;
		if(province=="")province =null;
		
		System.out.println(userName);
		System.out.println(chrName);
		System.out.println(email);
		System.out.println(province);
		
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		UserDao userdao = new UserDao();
		maps = userdao.search(userName,chrName,email,province);
		
		map.put("rows",maps);
		String jsonStr = new Gson().toJson(map);
		System.out.println(jsonStr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}
}
