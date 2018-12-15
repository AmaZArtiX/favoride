package com.favoride.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import net.rakugakibox.util.YamlResourceBundle;

public abstract class GenericMapper {

	protected Connection conn;
	protected ResourceBundle bundle;
	protected Statement statement;
	protected PreparedStatement preparedStatement;
	
	public GenericMapper() {
		
		this.conn = ConnectionManager.getInstance().getConnection();
		this.bundle = ResourceBundle.getBundle("queries/queries", YamlResourceBundle.Control.INSTANCE);
		
		try {
			
			this.statement = conn.createStatement();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
