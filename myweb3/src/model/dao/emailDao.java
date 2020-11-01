package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.vo.Email;
import model.vo.User;

public class emailDao {

	public Email get(String emailstr) {
		// TODO Auto-generated method stub
		Email email = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect User Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no email");
			}
			String sql = "select email from t_user where email=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,emailstr);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				email = new Email(rs.getString("email"));
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return email;
	}
	
	public String check(String emailstr,String Inemail) {
		// TODO Auto-generated method stub
		String email = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect User Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no email");
			}
			String sql = "select email from t_user where email!=? and email=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,emailstr);
			pst.setNString(2,Inemail);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				email = rs.getString("email");
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return email;
	}

}
