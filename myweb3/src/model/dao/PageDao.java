package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.vo.Page;
import model.vo.User;

public class PageDao {
	public static Page get(int pageSize,int pageNumber) {
		Page page = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect page Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no user");
			}
			String sql = "SELECT COUNT(*) from t_user";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				System.out.println(rs.getInt("COUNT(*)"));
				page = new Page(pageSize,pageNumber,rs.getInt("COUNT(*)"));
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return page;
	}
}
