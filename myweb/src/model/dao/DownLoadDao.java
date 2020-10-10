package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.vo.DownLoad;

public class DownLoadDao {

	public static DownLoad get(int id) {
		// TODO Auto-generated method stub
		DownLoad download = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("ConncecOk");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no");
			}
			String sql = "select * from t_downloadlist where id=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setLong(1,id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				download = new DownLoad(rs.getInt("id"),rs.getString("name"),rs.getString("path"),rs.getString("description"), rs.getDouble("size"), rs.getInt("star"), rs.getString("image"));
			}
			con.close();
		}
		catch(Exception e) {
			
		}
		return download;
	}

}
