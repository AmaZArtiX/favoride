package com.favoride.presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.favoride.application.UserService;
import com.favoride.domain.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * ***********************************************************************
 * Nom ...........: LoginController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Login.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class LoginController implements Initializable {

	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtUserAddress;
	
	@FXML
	private PasswordField txtPassword;
	
	private UserService loginService;
	
	public LoginController() {
		
		this.loginService = UserService.getInstance();
	}
	
	/**
	 * Connecte un utilisateur a l'application si toutes les conditions sont remplies
	 * @param event
	 */
	public void login(ActionEvent event) {
		
		if(!areFieldsEmpty()) {
			
			try {
				
				if(this.loginService.findUserByEmailAddressAndPassword(this.txtUserAddress.getText(), this.txtPassword.getText()) != null) {
					
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
				}
				 else {
					 
					 this.lblStatus.setText("Oups! Identifiants incorrects.");
				}
			
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				// Affichage d'une alerte d'erreur
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Favoride");
				alert.setHeaderText("Une erreur s'est produite...");
				alert.setContentText("Impossible de vous connecter à l'application.");
				alert.showAndWait();
			}
			
		} else {
			
			this.lblStatus.setText("Tous les champs doivent être complétés.");
		}
	}
	
	/**
	 * Retourne vrai si les champs sont vides
	 * @return
	 */
	public boolean areFieldsEmpty() {
		
		return "".equals(this.txtUserAddress.getText()) || this.txtPassword.getText().length() == 0;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// On initialise les labels
		lblStatus.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblStatus, 0.0);
		AnchorPane.setRightAnchor(lblStatus, 0.0);
		lblStatus.setAlignment(Pos.CENTER);
	}
}
