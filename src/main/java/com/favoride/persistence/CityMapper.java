package com.favoride.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************************
 * Nom ...........: CityMapper.java
 * Description ...: Classe de type Mapper permettant d'accéder aux donnees
 * ...............: concernant les villes (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class CityMapper extends GenericMapper {

	private static CityMapper instance = null;
	
	private CityMapper() {
		
		super();
	}
	
	/**
	 * Retourne l'instance de la classe
	 * @return
	 */
	public static CityMapper getInstance() {
		
		if(instance == null)
			instance = new CityMapper();
		
		return instance;
	}
	
	/**
	 * Recupere les villes de la base de donnees
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllCities() throws SQLException {
		
		List<String> cities = new ArrayList<String>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.city"));
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			
			cities.add(rs.getString("ct_name"));
		}
		
		return cities;
	}
}
