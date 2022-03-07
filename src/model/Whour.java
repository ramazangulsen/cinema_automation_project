package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import helper.*;

public class Whour {
	
	private int id,film_id;
	private String film_name,wdate,status;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Whour(int id, int film_id, String film_name, String wdate, String status) {
		super();
		this.id = id;
		this.film_id = film_id;
		this.film_name = film_name;
		this.wdate = wdate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFilm_id() {
		return film_id;
	}
	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	public String getFilm_name() {
		return film_name;
	}
	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	}
