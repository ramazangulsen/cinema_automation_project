package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class Hall {
	private int id;
	private String h_name;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Hall() {
		
		
	}
	
	
	
	public Hall(int id, String s_name) {
		super();
		this.id = id;
		this.h_name = s_name;
	}
	
	
	public ArrayList<Hall> getList() throws SQLException {
		ArrayList<Hall> list = new ArrayList<>();
		Hall obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM film.hall");
			while(rs.next()) {
				obj = new Hall();
				obj.setId(rs.getInt("id"));
				obj.setH_name(rs.getString("h_name"));
				list.add(obj);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			st.close();
			rs.close();
			con.close();
			
		}
		
		return list;

	}
	
	public Hall getFetch(int id) {
		Connection con = conn.connDb();
		Hall h= new Hall();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM hall WHERE id =" + id);
			while (rs.next()) {
				h.setId(rs.getInt("id"));
				h.setH_name(rs.getString("h_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h;
	}
	
	
	public boolean addHall(String h_name) throws SQLException {
		String query = "INSERT INTO hall" + "(h_name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz

			preparedStatement.setString(1, h_name);
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
	
	
	public boolean deleteHall(int id) throws SQLException {
		String query = "DELETE FROM hall WHERE ID = ?";
		boolean key = false;
		Connection con = conn.connDb();
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setInt(1, id);			
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
	
	
	public boolean updateHall(int id, String h_name) throws SQLException {
		String query = "UPDATE hall SET h_name = ? WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setString(1, h_name);
			preparedStatement.setInt(2, id);
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
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String s_name) {
		this.h_name = s_name;
	}
}
