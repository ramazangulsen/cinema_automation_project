package model;
import java.sql.*;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class yonetici{ 
	private int id;
	String name,password,UserID,type;
	
	DBConnection conn = new DBConnection();	
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	

	
	public yonetici(int id, String name, String password, String userID, String type) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.UserID = userID;
		this.type = type;
	}
	
	public yonetici () {
		
	}
	
	
	
	
	public ArrayList<user> getHallCalisanList(int hall_id) {
		ArrayList<user> list = new ArrayList<>();
		user obj;
			
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.ID,u.UserID,u.name,u.type,u.password FROM worker w LEFT JOIN user u ON w.user_id=u.id WHERE hall_id="+ hall_id); //worker tablosundaki seçim yaparken user databaseinde bulunan isim parola tip gibi seçenekleri görmemize yarar. bu sayede biz hangi salonda hangi eleman var isim ve tipleri ile biliriz.
			while(rs.next()) {
				obj = new user(rs.getInt("u.ID"),rs.getString("userID"),rs.getString("u.name"),rs.getString("u.type"),rs.getString("u.password"));
				list.add(obj);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;

	}
	
	
	public ArrayList<Film> getHallFilmList(int hall_id) {
		ArrayList<Film> list = new ArrayList<>();
		Film obj;
			
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT f.ID,f.Film_adi,f.Tur FROM worker w LEFT JOIN filmler f ON w.film_id=f.ID WHERE hall_id="+ hall_id); //worker tablosundaki seçim yaparken user databaseinde bulunan isim parola tip gibi seçenekleri görmemize yarar. bu sayede biz hangi salonda hangi eleman var isim ve tipleri ile biliriz.
			while(rs.next()) {
				obj = new Film(rs.getInt("f.ID"),rs.getString("film_adi"),rs.getString("f.Tur"));
				list.add(obj);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;

	}
	
	public ArrayList<user> getFilmList(int hall_id) {
		ArrayList<user> list = new ArrayList<>();
		user obj;
			
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM filmler"); //worker tablosundaki seçim yaparken user databaseinde bulunan isim parola tip gibi seçenekleri görmemize yarar. bu sayede biz hangi salonda hangi eleman var isim ve tipleri ile biliriz.
			while(rs.next()) {
				obj = new user(rs.getInt("u.ID"),rs.getString("userID"),rs.getString("u.name"),rs.getString("u.type"),rs.getString("u.password"));
				list.add(obj);
				
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;

	}


	
	public boolean updateCalisan(int ID,String UserID, String password, String name) throws SQLException {
		String query = "UPDATE user SET name = ?, UserID = ?, password = ? WHERE ID = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, UserID);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, ID);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	public boolean addHallFilm(int film_id , int hall_id) throws SQLException {
		String query = "INSERT INTO worker" + "(film_id,hall_id,h_name) VALUES" + "(?,?,?)";
		boolean key = false;
		int count = 0;		// bir sinema salonunda ayný filmi iki defa görevli göstermemiz için sorgulama yapdýk
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE hall_id=" + hall_id + " AND film_id=" + film_id );
			while (rs.next()) {
				count++;
			}
			
			if(count == 0) {
		    preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setInt(1,film_id);
			preparedStatement.setInt(2, hall_id);
			preparedStatement.executeUpdate();
			
			}
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
