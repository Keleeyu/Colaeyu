package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.ProvinceCityDao;
import model.vo.City;
import model.vo.Province;

/**
 * Servlet implementation class QueryProvinceCity
 */
@WebServlet("/qureyProvinceCity.do")
public class QueryProvinceCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String provinceCode = request.getParameter("provinceCode");
		String jsonStr = "";
		ProvinceCityDao dao = new ProvinceCityDao();
		if(provinceCode == null){
			ArrayList<Province> list = dao.queryProvince();
			jsonStr = new Gson().toJson(list);
		}else {
			ArrayList<City>list = dao.queryCity(provinceCode);
			jsonStr = new Gson().toJson(list);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
