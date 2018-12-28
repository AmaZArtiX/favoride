package com.favoride.domain;

import java.time.LocalDate;

/**
 * ***********************************************************************
 * Nom ...........: User.java
 * Description ...: Classe caracterisant un utilisateur (methodes et 
 * ...............: attributs)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class User {

	// id
	private int id;
	// adresse e-mail
	private String emailAddress;
	// mot de passe
	private String password;
	// prenom
	private String firstName;
	// nom
	private String lastName;
	// date de naissance
	private LocalDate birthYear;
	// numero de telephone
	private String phoneNumber;
	// sexe
	private String gender;
	// bio
	private String bio;
	
	public User() {
		
	}
	
	public User(int id, String emailAddress, String password, String firstName, String lastName, LocalDate birthYear,
			String phoneNumber, String gender, String bio) {

		this.id = id;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.bio = bio;
	}

	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getEmailAddress() {
		
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public String getFirstName() {
		
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}
	
	public String getLastName() {
		
		return lastName;
	}
	
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}
	
	public LocalDate getBirthYear() {
		
		return birthYear;
	}
	
	public void setBirthYear(LocalDate birthYear) {
		
		this.birthYear = birthYear;
	}
	
	public String getPhoneNumber() {
		
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		
		this.phoneNumber = phoneNumber;
	}
	
	public String getGender() {
		
		return gender;
	}
	
	public void setGender(String gender) {
		
		this.gender = gender;
	}
	
	public String getBio() {
		
		return bio;
	}
	
	public void setBio(String bio) {
		
		this.bio = bio;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
