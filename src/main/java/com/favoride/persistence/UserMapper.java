package com.favoride.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.favoride.domain.User;

public class UserMapper extends GenericMapper {

	public static UserMapper instance = null;
	
	private UserMapper() {
		
		super();
	}
	
	public static UserMapper getInstance() {
		
		if(instance == null)
			instance = new UserMapper();
		
		return instance;
	}
	
	public User findUserByEmailAddressAndPassword(String emailAddress, String password) throws SQLException {
		
		User user = null;
		
		preparedStatement = conn.prepareStatement(this.bundle.getString("select.user.by.address.password"));
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setString(2, password);
		ResultSet rs = preparedStatement.executeQuery();
		
		try {
			
			user = createUser(rs);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User findUserById(int id) throws SQLException {
		
		User user = null;
		
		preparedStatement = conn.prepareStatement(this.bundle.getString("select.user.by.id"));
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		
		try {
			
			user = createUser(rs);
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}
	
	private User createUser(ResultSet rs) throws SQLException {
		
		User user = new User();
		
		while(rs.next()) {
			
			user = new User();
			user.setId(rs.getInt("usr_id"));
			user.setEmailAddress(rs.getString("usr_email_address"));
			user.setFirstName(rs.getString("usr_first_name"));
			user.setLastName(rs.getString("usr_last_name"));
			user.setPassword(rs.getString("usr_password"));
			user.setPhoneNumber(rs.getString("usr_phone_number"));
			user.setBirthYear(rs.getDate("usr_birth_date").toLocalDate());
			user.setGender(rs.getString("usr_gender"));
			user.setBio(rs.getString("usr_bio"));
		}
					
		rs.close();
		
		return user;
	}
}
