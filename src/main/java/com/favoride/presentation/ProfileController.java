package com.favoride.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import com.favoride.application.UserService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

/**
 * ***********************************************************************
 * Nom ...........: ProfileController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Profile.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class ProfileController implements Initializable {

	@FXML 
	private Label lblTitle;
	
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
	
	private UserService loginService;
	
	public ProfileController() {
		
		this.loginService = UserService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// On initialise les labels avec les informations de l'utilisateur
		this.lblNames.setText(this.loginService.getUser().getFirstName() + " " + this.loginService.getUser().getLastName());
		this.lblEmailAddress.setText(this.loginService.getUser().getEmailAddress());
		this.lblPhoneNumber.setText(this.loginService.getUser().getPhoneNumber());
		this.lblBio.setText(this.loginService.getUser().getBio());
		this.lblAge.setText(this.loginService.getUsersAge() + " ans");
		
		lblTitle.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblTitle, 0.0);
		AnchorPane.setRightAnchor(lblTitle, 0.0);
		lblTitle.setAlignment(Pos.CENTER);
		
		lblNames.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblNames, 0.0);
		AnchorPane.setRightAnchor(lblNames, 0.0);
		lblNames.setAlignment(Pos.CENTER);
		
		lblEmailAddress.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblEmailAddress, 0.0);
		AnchorPane.setRightAnchor(lblEmailAddress, 0.0);
		lblEmailAddress.setAlignment(Pos.CENTER);
		
		lblPhoneNumber.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblPhoneNumber, 0.0);
		AnchorPane.setRightAnchor(lblPhoneNumber, 0.0);
		lblPhoneNumber.setAlignment(Pos.CENTER);
		
		lblAge.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblAge, 0.0);
		AnchorPane.setRightAnchor(lblAge, 0.0);
		lblAge.setAlignment(Pos.CENTER);
		
		lblBio.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblBio, 125.0);
		AnchorPane.setRightAnchor(lblBio, 125.0);
		lblBio.setAlignment(Pos.CENTER);
		lblBio.setWrapText(true);
		lblBio.setTextAlignment(TextAlignment.JUSTIFY);
	}
}
