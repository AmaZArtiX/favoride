package com.favoride.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.favoride.domain.Journey;
import com.favoride.domain.User;

/**
 * ***********************************************************************
 * Nom ...........: JourneyMapper.java
 * Description ...: Classe de type Mapper permettant d'accéder aux donnees
 * ...............: concernant les trajets (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class JourneyMapper extends GenericMapper {

	private static JourneyMapper instance = null;
	
	private UserMapper userMapper;
	
	public JourneyMapper() {
		
		super();
		this.userMapper = UserMapper.getInstance();
	}
	
	/**
	 * Retourne l'instance de la classe
	 * @return
	 */
	public static JourneyMapper getInstance() {
		
		if(instance == null)
			instance = new JourneyMapper();
		
		return instance;
	}
	
	/**
	 * Insere un trajet dans la base de donnees
	 * @param journey
	 * @throws SQLException
	 */
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
	
	/**
	 * Renvoie une liste de trajets selectionnes selon plusieurs criteres
	 * @param id
	 * @param departure
	 * @param arrival
	 * @param date
	 * @param rate
	 * @return
	 * @throws SQLException
	 */
	public List<Journey> findJourneysByCriterias(int id, String departure, String arrival, LocalDateTime date, Double rate) throws SQLException{
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		// On accede a la requete
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journeys.by.criterias"));
		// Le trajet ne doit pas concerner l'utilisateur
		preparedStatement.setInt(1, id);
		// La ville de depart doit correspondre
		preparedStatement.setString(2, departure);
		// La ville d'arrivee doit correspondre
		preparedStatement.setString(3, arrival);
		// La date doit etre superieure ou egale
		preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(date));
		// Le prix doit etre egal ou inferieur
		preparedStatement.setDouble(5, rate);
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	/**
	 * Cree un objet Trajet selon un objet ResultSet
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Journey createJourney(ResultSet rs) throws SQLException {
		
		Journey journey = new Journey();
		journey.setId(rs.getInt("jrn_id"));
		journey.setDriver(this.userMapper.findUserById(rs.getInt("usr_id")));
		journey.setDeparture(rs.getString("jrn_departure"));
		journey.setArrival(rs.getString("jrn_arrival"));
		journey.setDate(rs.getTimestamp("jrn_date").toLocalDateTime());
		journey.setRate(rs.getDouble("jrn_rate"));
		journey.setSeats(rs.getInt("jrn_seats"));
		journey.setPassengers(getPassengers(rs.getInt("jrn_id")));
		return journey;
	}
	
	/**
	 * Decremente le nombre de places restantes d'un trajet selon son id
	 * @param id
	 * @throws SQLException
	 */
	public void decrementSeats(int id) throws SQLException {
		
		// On accede a la requete
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("update.journey.decrement.seats"));
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}
	
	/**
	 * Insere un passager selon un trajet en base de donnees
	 * @param journeyId
	 * @param passengerId
	 * @throws SQLException
	 */
	public void addPassenger(int journeyId, int passengerId) throws SQLException {
		
		// On accede a la requete
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("insert.passenger"));
		preparedStatement.setInt(1, journeyId);
		preparedStatement.setInt(2, passengerId);
		preparedStatement.executeUpdate();
	}
	
	/**
	 * Supprime un trajet de la base de donnees
	 * @param journeyId
	 * @throws SQLException
	 */
	public void deleteJourney(int journeyId) throws SQLException {
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("delete.journey.by.id"));
		preparedStatement.setInt(1, journeyId);
		preparedStatement.executeUpdate();
	}
	
	/** 
	 * Retourne la liste de passagers pour un trajet
	 * @param id
	 * @return
	 * @throws SQLException
	 */
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
	
	/**
	 * Retourne la liste des trajets d'un utilisateur en tant que conducteur
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Journey> getJourneysAsConductor(int id) throws SQLException {
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.conductor"));
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
	
	/**
	 * Retourne la liste des trajets d'un utilisateur en tant que passager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Journey> getJourneysAsPassenger(int id) throws SQLException {
		
		List<Journey> journeys = new ArrayList<Journey>();
		
		PreparedStatement preparedStatement = conn.prepareStatement(this.bundle.getString("select.journey.passenger"));
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        
		while(rs.next()) {
			
			journeys.add(createJourney(rs));
		}
		
		return journeys;
	}
}
