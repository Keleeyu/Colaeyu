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

import model.dao.PageDao;
import model.dao.UserDao;
import model.vo.Page;
import model.vo.User;

/**
 * Servlet implementation class Ajaxdatatable
 */
@WebServlet("/datatable.do")
public class Ajaxdatatable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		UserDao userdao = new UserDao();
		PageDao pagedao = new PageDao();
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
		int Size = Integer.parseInt(pageSize);
		int Number = Integer.parseInt(pageNumber);
		Page page = pagedao.get(Size, Number);
		//int num = page.getPageNumber();
	
		
		//System.out.println(num);
		maps = userdao.get(Size,Number);
		map.put("rows",maps);
		map.put("total",page.getTotal());
		String jsonStr = new Gson().toJson(map);
		System.out.println(jsonStr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
