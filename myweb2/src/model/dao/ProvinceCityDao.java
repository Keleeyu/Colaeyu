package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.vo.City;
import model.vo.DownLoad;
import model.vo.Province;
import model.vo.Resource;

public class ProvinceCityDao {

	public ArrayList<Province> queryProvince() {
		ArrayList<Province> provinces = new ArrayList<Province>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Conncet Province Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no province");
			}
			String sql = "select * from t_province";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs!=null)
			{
				rs.next();
				Province province = new Province();
				province = new Province(rs.getInt("provinceCode"),rs.getString("provinceName"));
				provinces.add(province);
			}
			con.close();
		}
		catch(Exception e) {
			
		}
		return  provinces;
	}

	public ArrayList<City> queryCity(String provinceCode) {
		ArrayList<City> citys = new ArrayList<City>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Conncet City Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no city");
			}
			String sql = "SELECT * FROM t_city WHERE cityCode IN (SELECT cityCode FROM t_province_city WHERE provinceCode=?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(provinceCode));
			ResultSet rs = pst.executeQuery();
			while(rs!=null)
			{
				rs.next();
				City city = new City();
				city = new City(rs.getInt("cityCode"),rs.getString("cityName"));
				citys.add(city);
			}
			con.close();
		}
		catch(Exception e) {
			
		}
		return citys;
	}

	public String getProvince(int provinceCode) {
		String provinceName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Conncet Province Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no province");
			}
			String sql = "select provinceName from t_province where provinceCode=?";
			
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, provinceCode);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				provinceName = rs.getString("provinceName");
			}
			con.close();
		}
		catch(Exception e) {
			
		}
		return  provinceName;
	}

	public String getCity(int cityCode) {
		String cityName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usersql?useuricode=true&character=utf-8","root","");
			if(con != null)
			{
				System.out.println("Conncet Province Ok");//检查一下是否关联上MySQL
			}
			else
			{
				System.out.println("no province");
			}
			String sql = "select cityName from t_city where cityCode=?";
			
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, cityCode);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				cityName = rs.getString("cityName");
			}
			con.close();
		}
		catch(Exception e) {
			
		}
		return  cityName;
	}

}
