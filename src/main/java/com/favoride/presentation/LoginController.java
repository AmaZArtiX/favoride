package com.favoride.presentation;

import java.sql.SQLException;

import com.favoride.application.LoginService;
import com.favoride.domain.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUserAddress;
	
	@FXML
	private PasswordField txtPassword;
	
	private LoginService loginService;
	
	/**
	 * 
	 */
	public LoginController() {
		
		this.loginService = LoginService.getInstance();
	}
	
	/**
	 * 
	 * @param event
	 */
	public void login(ActionEvent event) {
		
		if(!areFieldsEmpty()) {
			
			try {
				isValidUser(this.loginService.findUserByEmailAddressAndPassword(this.txtUserAddress.getText(), this.txtPassword.getText()), event);
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		} else {
			
			this.lblStatus.setText("Tous les champs doivent être complétés.");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean areFieldsEmpty() {
		
		return "".equals(this.txtUserAddress.getText()) || this.txtPassword.getText().length() == 0;
	}
	
	public void isValidUser(User user, ActionEvent event) {
		
		if(user != null) {
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
				Scene scene = new Scene(root, 800, 600);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.hide();
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Impossible de lancer la fenêtre de connexion...");
			}
			
		} else {
			
			this.lblStatus.setText("Oups! Identifiants incorrects.");
		}
	}
}
