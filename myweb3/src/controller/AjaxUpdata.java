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
import model.dao.ProvinceCityDao;
import model.vo.Province;
import model.vo.User;
/**
 * Servlet implementation class AjaxUpdata
 */
@WebServlet("/AjaxUpdata.do")
public class AjaxUpdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUpdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String InuserName = request.getParameter("InuserName");
		String Inpassword = request.getParameter("password");
		String Inprovince = request.getParameter("Inprovince");
		String Incity = request.getParameter("Incity");
		String InchrName = request.getParameter("InchrName");
		String Inemail = request.getParameter("Inemail");
		Map<String,Object> map = new HashMap<String,Object>();
		ProvinceCityDao provincedao = new ProvinceCityDao();
		UserDao userdao = new UserDao();
		String provinceName = null;
		String cityName = null;
		User user = userdao.get(InuserName);
		boolean flag = true;
		if(user != null)
		{
			if(Inprovince!=null) {
				
				int provinceCode = Integer.parseInt(Inprovince);
				provinceName = provincedao.getProvince(provinceCode);
				map.put("checkprovince",0);
			}
			else{
				map.put("checkprovince",1);
				flag = false;
			}
			if(Incity!=null) {
				
				int cityCode = Integer.parseInt(Incity);
				cityName = provincedao.getCity(cityCode);
				map.put("checkcity",0);
			}
			else{
				map.put("checkcity",1);
				flag = false;
			}
			if(InchrName!=null){
				map.put("checkchrName",0);
			}
			else{
				map.put("checkchrName",1);
				flag = false;
			}
			if(Inpassword!=null){
				map.put("checkpassword",0);
			}
			else{
				map.put("checkpassword",1);
				flag = false;
			}
			if(Inemail!=null){
				map.put("checkemail",0);
			}
			else{
				map.put("checkemail",1);
				flag = false;
			}
			map.put("checkuser",0);	
		}
		else 
		{
			flag = false;
			map.put("checkuser",1);	
		}
		if(flag)
		{
			userdao.updata(InuserName, InchrName, Inpassword, Inemail, provinceName, cityName);
			map.put("code",0);
		}else
		{
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
