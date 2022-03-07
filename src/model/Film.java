package model;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import helper.DBConnection;

public class Film {
	public ArrayList<Film> filmler;
	int ID,id;
	String Film_adi,Tur,Status;
	String film_name,wdate,status2,h_name;
	private DefaultTableModel filmModel = null;
	private Object[] filmData = null;
	DBConnection conn2 = new DBConnection();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFilm_name() {
		return film_name;
	}


	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}


	public String getStatus2() {
		return status2;
	}


	public void setStatus2(String status2) {
		this.status2 = status2;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		this.Status = status;
	}


	public Film(int iD, String film_adi, String tur) {
		super();
		ID = iD;
		this.Film_adi = film_adi;
		this.Tur = tur;
	}


/*	public void FilmBilgiCek() {
		filmModel = new DefaultTableModel();
		Object[] colFilmName = new Object[4];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colFilmName[0] = "ID";
		colFilmName[1] = "Flm_adi";
		colFilmName[2] = "Tur";
		colFilmName[3] = "Status";
		filmModel.setColumnIdentifiers(colFilmName);
		filmData = new Object[4];
		for(int i=0; i<filmler.size();i++) {
			filmData[0] = filmler.get(i).getID();
			filmData[1] = filmler.get(i).getFilm_adi();
			filmData[2] = filmler.get(i).getTur();
			filmData[3] = filmler.get(i).getStatus();
			filmModel.addRow(filmData); }
	}*/
	
	
	public Film() {
		
	}		
	


/*	public boolean addFilmhour(int film_id, String film_name, String wdate) throws SQLException {
		int key = 0;
		int count = 0;
		Connection con = conn2.connDb();
		
		String query = "INSERT INTO filmhour" + "(film_id,film_name,wdate) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM filmhour WHERE status='A' AND film_id = " + film_id + " AND wdate= '"  + wdate + "'");
			while(rs.next()) {
				count++;
				break;
			}
			
			if(count==0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, film_id);
				preparedStatement.setString(2, film_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				
			}
			
			key = 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else
			return false;
		

	} */

	
	public boolean addWhour(int ID, String Film_adi, String wdate) throws SQLException {
		int key = 0;
		int count = 0;
		Connection con = conn2.connDb();
		String query = "INSERT INTO filmhour" + "(film_id,film_name,wdate) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			String a = "SELECT * FROM filmhour WHERE status='A' AND film_id =" + ID + " AND wdate='"+ wdate + "'";
			rs = st.executeQuery(a);
			while(rs.next()) {
				count++;
				break;
			}
			
			if(count==0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, ID);
				preparedStatement.setString(2, Film_adi);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				
			}
			
			key = 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if(key==1)
			return true;
		else
			return false;
		

	}
	
	
	public ArrayList<Film> getFilmList() throws SQLException {
		ArrayList<Film> list = new ArrayList<>();
		Film obj;
		Connection con = conn2.connDb();	
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM filmler"); 
			while(rs.next()) {
				obj = new Film();
				obj.setID(rs.getInt("ID"));
				obj.setFilm_adi(rs.getString("Film_adi"));
				obj.setTur(rs.getString("Tur"));
				obj.setStatus(rs.getString("Status"));
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
	
	
	public ArrayList<Film> getSeansFilmList() throws SQLException {
		ArrayList<Film> list = new ArrayList<>();
		Film obj;
		Connection con = conn2.connDb();	
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM filmhour"); 
			while(rs.next()) {
				obj = new Film();
				obj.setId(rs.getInt("id"));
				obj.setFilm_name(rs.getString("Film_name"));
				obj.setWdate(rs.getString("wdate"));
				obj.setStatus2(rs.getString("Status"));
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
	
	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public boolean deleteFilm(int ID) throws SQLException {
		String query = "DELETE FROM filmler WHERE ID = ?";
		boolean key = false;
		Connection con = conn2.connDb();	
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setInt(1, ID);			
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

	public boolean addFilmm(int film_id , int hall_id) throws SQLException {		//salona filmi eklemek için
		String query = "INSERT INTO worker" + "(film_id,hall_id) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;		// bir sinema salonunda ayný kiþiyi iki defa görevli göstermemiz için sorgulama yapdýk
		Connection con = conn2.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE hall_id=" + hall_id + " AND film_id=" + film_id);
			while (rs.next()) {
				count++;
			}
			
			if(count == 0) {
		    preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setInt(1, film_id);
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
	
	public boolean addFilm( String Film_adi, String Tur) throws SQLException { 	//film ekleme iþlemleri için
		boolean key = false;
		
	
		String query = "INSERT INTO filmler" + "(Film_adi,Tur) VALUES" + "(?,?)";
		Connection con = conn2.connDb();

		try {
			st = con.createStatement();
	//		rs = st.executeQuery("SELECT * FROM  WHERE status='A' AND ID =" + ID + " AND Film_adi='" + Film_adi + "'");
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz

			preparedStatement.setString(1, Film_adi);
			preparedStatement.setString(2, Tur);
			preparedStatement.executeUpdate();
			key=true;
			
			

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
		

	}
	
	
	public boolean updateFilm(int ID, String film_adi) throws SQLException {
		String query = "UPDATE filmler SET film_adi = ? WHERE ID = ?";
		boolean key = false;
		Connection con = conn2.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query); //gönderilen parametreler veri tabaný ile karþýlaþtýrýlýr hata olup olmadýðýný da key yardýmý ile kontrol ederiz
			preparedStatement.setString(1, film_adi);
			preparedStatement.setInt(2, ID);
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

public int getID() {
	return ID;
}


public void setID(int iD) {
	this.ID = iD;
}


public String getFilm_adi() {
	return Film_adi;
}


public void setFilm_adi(String film_adi) {
	this.Film_adi = film_adi;
}


public String getTur() {
	return Tur;
}


public void setTur(String tur) {
	this.Tur = tur;
}

}
