package com.favoride.application;

import java.sql.SQLException;
import java.util.List;

import com.favoride.persistence.CityMapper;

/**
 * ***********************************************************************
 * Nom ...........: CityService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant la saisie d'une ville
 * ...............: (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class CityService {

	private static CityService instance = null;
	
	private CityMapper cityMapper;
	
	// Liste des villes utiles a la saisie
	private List<String> cities;
	
	private CityService() {
		
		this.cityMapper = CityMapper.getInstance();
		try {
			this.cities = this.cityMapper.getAllCities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static CityService getInstance() {
		
		if(instance == null)
			instance = new CityService();
		
		return instance;
	}
	
	/**
	 * Verifie l'existence d'une ville
	 * @param city
	 * @return
	 */
	public boolean isValidCity(String city) {
		
		return cities.contains(city);
	}
	
	/**
	 * Retourne un tableau de villes (String)
	 * @return
	 */
	public String[] getCitiesToArray() {
		
		return cities.stream().toArray(String[]::new);
	}
}
