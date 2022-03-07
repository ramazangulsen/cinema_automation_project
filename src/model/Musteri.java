package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helper.Helper;

public class Musteri extends user {

	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Musteri() {
		
	}

	public Musteri(int id, String name, String password, String userID, String type) {
		super(id, name, password, userID, type);
		
	}
	
	public boolean register(String UserID, String password, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		
		String query = "INSERT INTO user" + "(UserID,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE password='" + password + "'");
			while(rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu kullanýcý adý kullanýlmaktadýr.");
				break;
				
			}
			
			if(!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, UserID);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "Musteri");
				preparedStatement.executeUpdate();
				key = 1;
			}
			


		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else
			return false;
		

	}

}
