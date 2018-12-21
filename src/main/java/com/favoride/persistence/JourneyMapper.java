package com.favoride.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.domain.User;

public class JourneyMapper extends GenericMapper {

	public static JourneyMapper instance = null;
	
	private UserMapper userMapper;
	
	private JourneyMapper() {
		
		super();
		this.userMapper = UserMapper.getInstance();
	}
	
	public static JourneyMapper getInstance() {
		
		if(instance == null)
			instance = new JourneyMapper();
		
		return instance;
	}
	
	public void insertJourney(Journey journey) throws SQLException {
				
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("insert.journey"));
		preparedStatement.setInt(1, journey.getDriver().getId());
		preparedStatement.setString(2, journey.getDeparture());
		preparedStatement.setString(3, journey.getArrival());
		preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(journey.getDate()));
		preparedStatement.setDouble(5, journey.getRate());
		preparedStatement.setInt(6, journey.getSeats());
		preparedStatement.executeUpdate();		
	}
	
	public List<Journey> findJourneysByCriterias(int id, String departure, String arrival, LocalDateTime date, Double rate) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journeys.by.criterias"));
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, departure);
		preparedStatement.setString(3, arrival);
		preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(date));
		preparedStatement.setDouble(5, rate);
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	private Journey createJourney(ResultSet rs) throws SQLException {
		
		Journey journey = new Journey();
		journey.setId(rs.getInt("jrn_id"));
		journey.setDriver(this.userMapper.findUserById(rs.getInt("usr_id")));
		journey.setDeparture(rs.getString("jrn_departure"));
		journey.setArrival(rs.getString("jrn_arrival"));
		journey.setDate(rs.getTimestamp("jrn_date").toLocalDateTime());
		journey.setRate(rs.getDouble("jrn_rate"));
		journey.setSeats(rs.getInt("jrn_seats"));
		return journey;
	}
	
	public void decrementSeats(int id) throws SQLException {
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("update.journey.decrement.seats"));
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}
	
	public void addPassenger(int journeyId, int passengerId) throws SQLException {
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("insert.passenger"));
		preparedStatement.setInt(1, journeyId);
		preparedStatement.setInt(2, passengerId);
		preparedStatement.executeUpdate();
	}
	
	public List<Journey> getPastJourneysConductor(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.past.conductor"));
		preparedStatement.setInt(1, id);
		preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<Journey> getNowJourneysConductor(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.now.conductor"));
		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<Journey> getFutureJourneysConductor(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.future.conductor"));
		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<Journey> getPastJourneysPassenger(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.past.passenger"));
		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<Journey> getNowJourneysPassenger(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.now.passenger"));
		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<Journey> getFutureJourneysPassenger(int id) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.future.passenger"));
		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	public List<User> getPassengers(int id) throws SQLException{
		
		List<User> passengers = new ArrayList<User>();
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.passengers"));
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			
			passengers.add(this.userMapper.findUserById(rs.getInt("usr_id")));
		}
		
		return passengers;
	}
}
