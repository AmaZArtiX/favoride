package com.favoride.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.domain.User;
import com.favoride.persistence.JourneyMapper;

public class JourneysService {

public static JourneysService instance = null;
	
	private List<Journey> journeys;
	
	private JourneyMapper journeyMapper;
	
	private Journey selectedJourney;
	
	private JourneysService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	public static JourneysService getInstance() {
		
		if(instance == null)
			instance = new JourneysService();
		
		return instance;
	}
	
	public List<Journey> getJourneys() {
		
		return this.journeys;
	}
	
	public boolean getPastJourneysConductor(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getPastJourneysConductor(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public boolean getNowJourneysConductor(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getNowJourneysConductor(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public boolean getFutureJourneysConductor(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getFutureJourneysConductor(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public boolean getPastJourneysPassenger(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getPastJourneysPassenger(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public boolean getNowJourneysPassenger(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getNowJourneysPassenger(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public boolean getFutureJourneysPassenger(int id) {
		
		journeys = new ArrayList<Journey>();
		
		try {
			journeys = this.journeyMapper.getFutureJourneysPassenger(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Une erreur est survenue lors de la recherche");
			return false;
		}
		
		return true;
	}
	
	public List<User> getPassengers(int id) {
		
		List<User> p = new ArrayList<User>();
		
		try {
			p = this.journeyMapper.getPassengers(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return p;
	}
	
	public void setSelectedJourney(Journey j) {
		
		this.selectedJourney = j;
	}
	
	public Journey getSelectedJourney() {
		
		return this.selectedJourney;
	}
	
	
}
