package com.favoride.application;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.persistence.JourneyMapper;

public class FindJourneyService {

	public static FindJourneyService instance = null;
	
	private List<Journey> journeys;
	
	private JourneyMapper journeyMapper;
	
	private FindJourneyService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	public static FindJourneyService getInstance() {
		
		if(instance == null)
			instance = new FindJourneyService();
		
		return instance;
	}
	
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
	
	public List<Journey> getJourneys() {
		
		return this.journeys;
	}
	
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
