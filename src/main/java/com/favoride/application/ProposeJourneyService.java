package com.favoride.application;

import java.sql.SQLException;

import com.favoride.domain.Journey;
import com.favoride.persistence.JourneyMapper;

public class ProposeJourneyService {

	public static ProposeJourneyService instance = null;
		
	private JourneyMapper journeyMapper;
	
	private ProposeJourneyService() {
		
		this.journeyMapper = JourneyMapper.getInstance();
	}
	
	public static ProposeJourneyService getInstance() {
		
		if(instance == null)
			instance = new ProposeJourneyService();
		
		return instance;
	}
	
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
