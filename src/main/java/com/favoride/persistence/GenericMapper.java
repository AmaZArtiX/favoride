package com.favoride.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import net.rakugakibox.util.YamlResourceBundle;

/**
 * ***********************************************************************
 * Nom ...........: GenericMapper.java
 * Description ...: Classe abstraite regroupant les attributs utiles au 
 * ...............: traitement des donnees pour les classe de type Mapper
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public abstract class GenericMapper {

	// connexion
	protected Connection conn;
	// bundle contenant les requetes SQL du fichier queries.yaml
	protected ResourceBundle bundle;
	// statement
	protected Statement statement;
	// preparedStatement
	protected PreparedStatement preparedStatement;
	
	public GenericMapper() {
		
		// on accede a la connexion courante
		this.conn = ConnectionManager.getInstance().getConnection();
		// on accede au fichier yaml contenant les requetes SQL
		this.bundle = ResourceBundle.getBundle("queries/queries", YamlResourceBundle.Control.INSTANCE);
		
		try {
			// on cree on nouveau statement
			this.statement = conn.createStatement();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
