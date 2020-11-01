package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CreateVerificationCodeImage;

/**
 * Servlet implementation class CreateVerfifyCodeImageServlet
 */
@WebServlet("/CreateVerfifyCodeImage")
public class CreateVerfifyCodeImageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CreateVerificationCodeImage createVCodeImage = new CreateVerificationCodeImage();
		
		String vCode = createVCodeImage.createCode();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("verifyCode", vCode);
		
		response.setContentType("img/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expries", 0);
		
		BufferedImage image = createVCodeImage.CreateImage(vCode);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();
	}



}
