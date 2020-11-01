package model.dao;
import model.vo.City;
import model.vo.Email;
import model.vo.Province;
import model.vo.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
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
	
	public static String getEmail(String userName) {
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
				System.out.println("no user");
			}
			String sql = "select email from t_user where userName=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,userName);
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
	
	public static List<Map<String,Object>> get(int pageSize,int pageNumber) {
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		String sql = null;
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
			
			sql = "select * from t_user LIMIT ?,?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setLong(1,(pageNumber - 1) * pageSize);
			pst.setLong(2,pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs!=null)
			{
				rs.next();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userName",rs.getString("userName"));
				map.put("password",rs.getString("password"));
				map.put("chrName",rs.getString("chrName"));
				map.put("province",rs.getString("province"));
				map.put("city",rs.getString("city"));
				map.put("email",rs.getString("email"));
				maps.add(map);
				//user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"));
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return maps;
	}
	
	
	
	public static List<Map<String,Object>> search(String userName,String chrName,String email,String province) {
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
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
			PreparedStatement pst;
			if(userName!=null && chrName == null && email == null && province == null){
				String sql = "select * from t_user where userName=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
			}
			else if(userName==null && chrName != null && email == null && province == null) {
				String sql = "select * from t_user where chrName=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,chrName);
			}
			else if(userName==null && chrName == null && email != null && province == null) {
				String sql = "select * from t_user where email=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,email);
			}
			else if(userName==null && chrName == null && email == null && province != null) {
				String sql = "select * from t_user where province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,province);
			}
			else if(userName!=null && chrName != null && email == null && province == null) {
				String sql = "select * from t_user where userName=? and chrName=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,chrName);
			}
			else if(userName!=null && chrName == null && email != null && province == null) {
				String sql = "select * from t_user where userName=? and email=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,email);
			}
			else if(userName!=null && chrName == null && email == null && province != null) {
				String sql = "select * from t_user where userName=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,province);
			}
			else if(userName==null && chrName != null && email != null && province == null) {
				String sql = "select * from t_user where chrName=? and email=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,chrName);
				pst.setNString(2,email);
			}
			else if(userName==null && chrName != null && email == null && province != null) {
				String sql = "select * from t_user where chrName=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,chrName);
				pst.setNString(2,province);
			}
			else if(userName==null && chrName == null && email != null && province != null) {
				String sql = "select * from t_user where email=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,email);
				pst.setNString(2,province);
			}
			else if(userName!=null && chrName != null && email != null && province == null) {
				String sql = "select * from t_user where userName=? and chrName=? and email=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,chrName);
				pst.setNString(3,email);
			}
			else if(userName!=null && chrName != null && email == null && province != null) {
				String sql = "select * from t_user where userName=? and chrName=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,chrName);
				pst.setNString(3,province);
			}
			else if(userName==null && chrName != null && email != null && province != null) {
				String sql = "select * from t_user where chrName=? and email=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,chrName);
				pst.setNString(2,email);
				pst.setNString(3,province);
			}
			else if(userName!=null && chrName != null && email != null && province != null) {
				String sql = "select * from t_user where userName=? and chrName=? and email=? and province=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,userName);
				pst.setNString(2,chrName);
				pst.setNString(3,email);
				pst.setNString(4,province);
			}
			else{
				String sql = "select * from t_user";
				pst = (PreparedStatement) con.prepareStatement(sql);
			}
			ResultSet rs = pst.executeQuery();
			while(rs!=null)
			{
				rs.next();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userName",rs.getString("userName"));
				map.put("password",rs.getString("password"));
				map.put("chrName",rs.getString("chrName"));
				map.put("province",rs.getString("province"));
				map.put("city",rs.getString("city"));
				map.put("email",rs.getString("email"));
				maps.add(map);
				//user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("chrName"));
			}
			con.close();
		}
		catch (Exception e) {
			
		}
		return maps;
	}
	
	public static boolean delete(String ids) {
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
			PreparedStatement pst;
			String sql;
			ids = ids.substring(0, ids.length() - 1);
	        System.out.println(ids);
	        String[] split = ids.split("/");
	        for (String out : split) {
	            //System.out.println("数据-->>>" + out);
	        	sql = "delete from t_user where userName=?";
	            pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,out);
				pst.executeUpdate();
				sql = "SET foreign_key_checks = 0";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.executeUpdate();
				sql = "DELETE from t_user_role  where userName=?";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.setNString(1,out);
				pst.executeUpdate();
			    sql = "SET foreign_key_checks = 1";
				pst = (PreparedStatement) con.prepareStatement(sql);
				pst.executeUpdate();
	        }
			con.close();
		}
		catch (Exception e) {
			
		}
		return true;
	}
	public static boolean updata(String userName,String chrName,String password,String email,String province,String city) {
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
			String sql = "UPDATE t_user SET chrName =?,city =?,province =?, password =?,email=? WHERE userName =?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setNString(1,chrName);
			pst.setNString(4,password);
			pst.setNString(5,email);
			pst.setNString(3,province);
			pst.setNString(2,city);
			pst.setNString(6,userName);
			pst.executeUpdate();
			con.close();
		}
		catch (Exception e) {
			
		}
		return true;
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
