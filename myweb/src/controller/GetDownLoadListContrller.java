package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DownLoadDao;
import model.dao.UserDao;
import model.vo.DownLoad;
import model.vo.User;

/**
 * Servlet implementation class GetDownLoadListContrller
 */
@WebServlet("/getDownloadList.do")
public class GetDownLoadListContrller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDownLoadListContrller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DownLoad download;
		int id = 1;
		for(;id<=3;id++)
		{
			download = DownLoadDao.get(id);
		
			System.out.println(download);
			request.setAttribute("file"+id, download);
		//System.out.println(download.getImage());
			System.out.println(download.getPath());
		}

		
		RequestDispatcher reqDispatcher = this.getServletContext().getRequestDispatcher("/download.jsp");
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
