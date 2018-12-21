package com.favoride.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Journey {

	public int id;
	public User driver;
	public String departure;
	public String arrival;
	public LocalDateTime date;
	public Double rate;
	public int seats;
	public List<User> passengers;
	
	public Journey() {
		
	}
	
	public Journey(User driver, String departure, String arrival, LocalDateTime date, Double rate, int seats) {
		this.driver = driver;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.rate = rate;
		this.seats = seats;
	}

	public Journey(int id, User driver, String departure, String arrival, LocalDateTime date, Double rate, int seats) {
		this.id = id;
		this.driver = driver;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.rate = rate;
		this.seats = seats;
		passengers = new ArrayList<User>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<User> passengers) {
		this.passengers = passengers;
	}
	
	public void addPassenger(User user) {
		this.passengers.add(user);
	}
}
