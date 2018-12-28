package com.favoride.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import net.rakugakibox.util.YamlResourceBundle;

/**
 * ***********************************************************************
 * Nom ...........: ConnectionManager.java
 * Description ...: Classe permettant la connexion a la base de donnees
 * ...............: (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class ConnectionManager {

	public static ConnectionManager instance = null;
	
	private Connection conn;
	
	private static String url;
	private static String username;
	private static String password;
	
	public ConnectionManager() {
		
		// Acces au fichier .yaml contenant les informations de connexion a la base de donnees
		ResourceBundle bundle = ResourceBundle.getBundle("config/config", YamlResourceBundle.Control.INSTANCE);
		this.url = bundle.getString("config.url");
		this.username = bundle.getString("config.username");
		this.password = bundle.getString("config.password");
		openConnection();
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static ConnectionManager getInstance() {
		
		if(instance == null) {
			
			instance = new ConnectionManager();
		}
		
		return instance;
	}
	
	/**
	 * Ouvre la connexion a la base de donnees
	 */
	public void openConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Connexion à la base impossible...");
			
			// Affichage d'une alerte d'erreur
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Favoride");
			alert.setHeaderText("Une erreur s'est produite...");
			alert.setContentText("Connexion à la base impossible.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Ferme la connexion a la base de donnees
	 */
	public void closeConnection() {
		
		try {
			
			if(conn != null)
				conn.close();
		}
		catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Fermeture de la connexion à la base impossible...");
			
			// Affichage d'une alerte d'erreur
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Favoride");
			alert.setHeaderText("Une erreur s'est produite...");
			alert.setContentText("Fermeture de la connexion à la base impossible.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Retourne la connexion courante
	 * @return
	 */
	public Connection getConnection() {
		
		return conn;
	}
}
