package com.favoride.application;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.persistence.JourneyMapper;

/**
 * ***********************************************************************
 * Nom ...........: SearchService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant la recherche de trajets
 * ...............: (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class SearchService {

	public static SearchService instance = null;
	
	// Liste de trajets trouves
	private List<Journey> journeys;
	
	private JourneyMapper journeyMapper;
	
	private SearchService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static SearchService getInstance() {
		
		if(instance == null)
			instance = new SearchService();
		
		return instance;
	}
	
	/**
	 * Remplit la liste de trajets avec les trajets trouves depuis la base de donnees
	 * @param id Le conducteur
	 * @param departure La ville de depart
	 * @param arrival La ville d'arrivee
	 * @param date La date de depart
	 * @param rate Le prix maximum
	 * @return La confirmation de recherche
	 */
	public boolean getResultFromSearch(int id, String departure, String arrival, LocalDateTime date, Double rate) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.findJourneysByCriterias(id, departure, arrival, date, rate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Retourne la liste de trajets trouves
	 * @return
	 */
	public List<Journey> getJourneys() {
		
		return this.journeys;
	}
	
	/**
	 * Ajoute un utilisateur a un trajet et decremente le nombre de places
	 * @param journeyId L'id du trajet
	 * @param passengerId L'id de l'utilisateur
	 * @return La confirmation d'ajout
	 */
	public boolean addPassenger(int journeyId, int passengerId) {
		
		try {
			this.journeyMapper.addPassenger(journeyId, passengerId);
			this.journeyMapper.decrementSeats(journeyId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur s'est produite lors de l'ajout du passager.");
			return false;
		}
		
		return true;
	}
}
