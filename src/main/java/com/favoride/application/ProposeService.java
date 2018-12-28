package com.favoride.application;

import java.sql.SQLException;

import com.favoride.domain.Journey;
import com.favoride.persistence.JourneyMapper;

/**
 * ***********************************************************************
 * Nom ...........: ProposeService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant la proposition d'un trajet
 * ...............: (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class ProposeService {

	public static ProposeService instance = null;
		
	private JourneyMapper journeyMapper;
	
	private ProposeService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static ProposeService getInstance() {
		
		if(instance == null)
			instance = new ProposeService();
		
		return instance;
	}
	
	/**
	 * Insere un trajet dans la base de donnees
	 * @param journey
	 * @return
	 */
	public boolean insertJourney(Journey journey) {
		
		try {
			
			this.journeyMapper.insertJourney(journey);
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
