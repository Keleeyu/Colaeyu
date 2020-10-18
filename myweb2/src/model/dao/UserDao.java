package model.dao;
import model.vo.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
public class UserDao {
	public static User get(String userName) {
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect User Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no user");
			}
			String sql = "select * from t_user where userName=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,userName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"));
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return user;
	}
	public static String check(String userName) {
		String name = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect User Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no user");
			}
			String sql = "select userName from t_user_role where userName=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,userName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				name= rs.getString("userName");
				if(name!=null)
				System.out.println(name);
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return name;
	}


	public void set(String userName, String password, String chrName, String province, String city, String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect User Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no user");
			}
			String sql = "insert into t_user_role(roleId,userName) VALUES (2,?)";
			
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1, userName);
			pst.executeUpdate();
			sql = "insert into t_user (userName,password,chrName,province,city,email) VALUES (?,?,?,?,?,?)";
			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1, userName);
			pst.setNString(2, password);
			pst.setNString(3, chrName);
			pst.setNString(4, province);
			pst.setNString(5, city);
			pst.setNString(6, email);
			
			pst.executeUpdate();
		
			con.close();
		}
		catch (Exception e) {
			
		}
	}
}
