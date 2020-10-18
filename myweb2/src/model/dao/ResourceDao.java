package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.vo.Resource;


public class ResourceDao {
	public static List<Resource> get(String userName) {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Connect Resource Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no resource");
			}
			String sql = "select * from t_resource where resourceId in (select resourceId from t_role_resource where roleId in (select roleId from t_user_role where userName=?))";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,userName);
			ResultSet rs = pst.executeQuery();
			while(rs!=null)
			{
				rs.next();
				Resource resource = new Resource();
				resource = new Resource(rs.getInt("resourceId"),rs.getString("resourceName"),rs.getString("url"));
				resources.add(resource);
				
			}
			con.close();
			
		}catch(Exception e) {
			
		}
		return resources;
	}
}
