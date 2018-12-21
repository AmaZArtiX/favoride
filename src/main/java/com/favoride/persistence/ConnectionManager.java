package com.favoride.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import net.rakugakibox.util.YamlResourceBundle;

public class ConnectionManager {

	public static ConnectionManager instance = null;
	
	private Connection conn;
	
	private static String url;
	private static String username;
	private static String password;
	
	public ConnectionManager() {
		
		ResourceBundle bundle = ResourceBundle.getBundle("config/config", YamlResourceBundle.Control.INSTANCE);
		this.url = bundle.getString("config.url");
		this.username = bundle.getString("config.username");
		this.password = bundle.getString("config.password");
		openConnection();
	}
	
	public static ConnectionManager getInstance() {
		
		if(instance == null) {
			
			instance = new ConnectionManager();
		}
		
		return instance;
	}
	
	public void openConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (Exception e) {
			
			System.out.println("Connexion à la base impossible...");
		}
	}
	
	public void closeConnection() {
		
		try {
			
			if(conn != null)
				conn.close();
		}
		catch (Exception e) {
			
		}
	}
	
	public Connection getConnection() {
		
		return conn;
	}
	
}
