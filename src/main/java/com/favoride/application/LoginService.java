package com.favoride.application;

import java.sql.SQLException;

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
}
