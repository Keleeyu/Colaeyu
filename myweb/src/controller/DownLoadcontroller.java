package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DownLoadDao;
import model.vo.DownLoad;

/**
 * Servlet implementation class DownLoadcontroller
 */
//@WebServlet("/servlet/DownLoadcontroller")
@WebServlet("/download.do")
public class DownLoadcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idinfo = request.getParameter("Id");
		
		System.out.println(idinfo);
		
		int id = Integer.parseInt(idinfo);
		DownLoad download = DownLoadDao.get(id);//获取id号对应文件的内容
		
		String filepath = download.getPath();
		
		String path = request.getServletContext().getRealPath(filepath);
		
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
		
		InputStream in = new FileInputStream(path);
		int len = 0;
		
		byte[] buffer = new byte[1024];
		
		ServletOutputStream out = response.getOutputStream();
		
		while((len = in.read(buffer)) != -1) {
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
