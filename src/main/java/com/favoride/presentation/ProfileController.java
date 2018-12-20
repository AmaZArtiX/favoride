package com.favoride.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import com.favoride.application.LoginService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ProfileController implements Initializable {

	@FXML
	private Label lblNames;
	
	@FXML
	private Label lblEmailAddress;
	
	@FXML
	private Label lblPhoneNumber;
	
	@FXML
	private Label lblAge;
	
	@FXML
	private Label lblBio;
	
	private LoginService loginService;
	
	public ProfileController() {
		
		this.loginService = LoginService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.lblNames.setText(this.loginService.getUser().getFirstName() + " " + this.loginService.getUser().getLastName());
		this.lblEmailAddress.setText(this.loginService.getUser().getEmailAddress());
		this.lblPhoneNumber.setText(this.loginService.getUser().getPhoneNumber());
		this.lblBio.setText(this.loginService.getUser().getBio());
		this.lblAge.setText(this.loginService.getUsersAge() + " ans");
	}
}
