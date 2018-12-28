package com.favoride.application;

import java.sql.SQLException;

import java.time.LocalDate;
import java.time.Period;

import com.favoride.domain.User;
import com.favoride.persistence.UserMapper;

/**
 * ***********************************************************************
 * Nom ...........: UserService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant un utilisateur (Singleton)
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class UserService {
	
	private static UserService instance = null;
	
	private SecurityService securityService;
	
	private UserMapper userMapper;
	// utilisateur connecte
	private User user;
	
	private UserService() {
		
		this.securityService = SecurityService.getInstance();
		this.userMapper = UserMapper.getInstance();
	}
	
	/**
	 * Retourne l'instance courante de la classe
	 * @return
	 */
	public static UserService getInstance() {
		
		if(instance == null)
			instance = new UserService();
		
		return instance;
	}
	
	/**
	 * Retourne l'utilisateur trouve par le mapper selon son adresse et son mot de passe
	 * @param emailAddress
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User findUserByEmailAddressAndPassword(String emailAddress, String password) throws SQLException {
	
		try {
			this.user = userMapper.findUserByEmailAddressAndPassword(emailAddress, this.securityService.encrypt(password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.user;
	}
	
	/**
	 * Retourne l'age de l'utilisateur pour l'afficher depuis le profil
	 * @return
	 */
	public int getUsersAge() {
		
		return Period.between(this.user.getBirthYear(), LocalDate.now()).getYears();
	}
	
	/**
	 * Retourne l'utilisateur connecte
	 * @return
	 */
	public User getUser() {
		
		return this.user;
	}
	
	/**
	 * Definit le nouvel utilisateur connecte
	 * @param user
	 */
	public void setUser(User user) {
		
		this.user = user;
	}
}
