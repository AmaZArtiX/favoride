package com.favoride.application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.favoride.domain.User;
import com.favoride.persistence.UserMapper;

public class LoginService {
	
	public static LoginService instance = null;
	
	private UserMapper userMapper;
	private User user;
	
	private LoginService() {
		
		this.userMapper = UserMapper.getInstance();
	}
	
	public static LoginService getInstance() {
		
		if(instance == null)
			instance = new LoginService();
		
		return instance;
	}
	
	
	public User findUserByEmailAddressAndPassword(String emailAddress, String password) throws SQLException {
		
		this.user = userMapper.findUserByEmailAddressAndPassword(emailAddress, password);
		return this.user;
	}
	
	public int getUsersAge() {
		
		return Period.between(this.user.getBirthYear(), LocalDate.now()).getYears();
	}
	
	public User getUser() {
		
		return this.user;
	}
	
	public void setUser(User user) {
		
		this.user = user;
	}
}
