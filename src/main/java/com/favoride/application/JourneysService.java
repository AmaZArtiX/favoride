package com.favoride.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.domain.User;
import com.favoride.persistence.JourneyMapper;

/**
 * ***********************************************************************
 * Nom ...........: JourneysService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant la consultation des trajets
 * ...............: (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class JourneysService {

	private static JourneysService instance = null;
	
	private JourneyMapper journeyMapper;
	
	// Le trajet a consulter
	private Journey selectedJourney;
	
	private JourneysService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static JourneysService getInstance() {
		
		if(instance == null)
			instance = new JourneysService();
		
		return instance;
	}
	
	/**
	 * Retourne la liste de trajets
	 * @return
	 */
	/*public List<Journey> getJourneys() {
		
		return journeys;
	}*/
	
	public List<Journey> getJourneysAsConductor(int id) {
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getJourneysAsConductor(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
		}
		
		return journeys;
	}
	
	public List<Journey> getJourneysAsPassenger(int id) {
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getJourneysAsPassenger(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
		}
		
		return journeys;
	}
	
	/**
	 * Supprime un trajet de la base de donnees
	 * @param id Le trajet a supprimer
	 * @return La confirmation de suppression
	 */
	public boolean deleteJourney(int id) {
		
		try {
			this.journeyMapper.deleteJourney(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Retourne le trajet selectionne pour obtenir ses details
	 * @param j
	 */
	public void setSelectedJourney(Journey j) {
		
		this.selectedJourney = j;
	}
	
	/**
	 * Definit le trajet pour lequel il faut obtenir les details
	 * @return
	 */
	public Journey getSelectedJourney() {
		
		return this.selectedJourney;
	}
}
