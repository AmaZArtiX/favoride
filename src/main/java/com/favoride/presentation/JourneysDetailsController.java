package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.favoride.application.JourneysService;
import com.favoride.application.UserService;
import com.favoride.domain.Journey;
import com.favoride.domain.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * ***********************************************************************
 * Nom ...........: JourneysDetailsController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: JourneysDetails.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class JourneysDetailsController implements Initializable {

	private UserService userService;
	
	private JourneysService journeysService;
	
	// Le trajet selectionne
	private Journey journey;
	
	// TableView contenant les passagers
	@FXML 
	private TableView<User> tbPassengers;
	
	@FXML 
	private Label lblTitle;
	
	@FXML 
	private Label lblConductor;
	
	@FXML 
	private Label lblDeparture;
	
	@FXML 
	private Label lblArrival;
	
	@FXML 
	private Label lblRate;
	
	@FXML
	private Label lblDate;
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private Button btnDelete;
	
	@FXML 
	private AnchorPane myPane;
	
	
	public JourneysDetailsController() {
		
		this.journeysService = JourneysService.getInstance();
		this.userService = UserService.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		this.journey = this.journeysService.getSelectedJourney();
		
		// On initialise la visibilite du bouton de suppression d'un trajet
		if(this.userService.getUser().getId() == this.journey.getDriver().getId() && this.journey.getDate().isAfter(LocalDateTime.now()))
			this.btnDelete.setVisible(true);
		else 
			this.btnDelete.setVisible(false);
		
		// On initialise la TableView
		TableColumn<User, String> firstName = new TableColumn<User, String>("Prénom");
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		TableColumn<User, String> lastName = new TableColumn<User, String>("Nom");
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<User, LocalDate> age = new TableColumn<User, LocalDate>("Date de naissance");
		age.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
		TableColumn<User, String> emailAddress = new TableColumn<User, String>("Adresse e-mail");
		emailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		TableColumn<User, String> phoneNumber = new TableColumn<User, String>("Numéro de tél.");
		phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		
		tbPassengers.getColumns().add(firstName);
		tbPassengers.getColumns().add(lastName);
		tbPassengers.getColumns().add(age);
		tbPassengers.getColumns().add(emailAddress);
		tbPassengers.getColumns().add(phoneNumber);
	 
		tbPassengers.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            return row;
        });

		tbPassengers.getItems().setAll(journey.getPassengers());
		
		// On initialise les Labels
		this.lblConductor.setText("Conducteur : " + this.journey.getDriver().toString());
		this.lblDeparture.setText("Départ : " + this.journey.getDeparture());
		this.lblArrival.setText("Arrivée : " + this.journey.getArrival());
		this.lblDate.setText("Date de départ : " + this.journey.getDate().toString());
		this.lblRate.setText("Prix : " + this.journey.getRate().toString() + "€");
		
		lblTitle.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblTitle, 0.0);
		AnchorPane.setRightAnchor(lblTitle, 0.0);
		lblTitle.setAlignment(Pos.CENTER);
		
		lblConductor.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblConductor, 0.0);
		AnchorPane.setRightAnchor(lblConductor, 0.0);
		lblConductor.setAlignment(Pos.CENTER);
		
		lblDeparture.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblDeparture, 0.0);
		AnchorPane.setRightAnchor(lblDeparture, 0.0);
		lblDeparture.setAlignment(Pos.CENTER);
		
		lblArrival.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblArrival, 0.0);
		AnchorPane.setRightAnchor(lblArrival, 0.0);
		lblArrival.setAlignment(Pos.CENTER);
		
		lblDate.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblDate, 0.0);
		AnchorPane.setRightAnchor(lblDate, 0.0);
		lblDate.setAlignment(Pos.CENTER);
		
		lblRate.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblRate, 0.0);
		AnchorPane.setRightAnchor(lblRate, 0.0);
		lblRate.setAlignment(Pos.CENTER);
		
		lblStatus.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblStatus, 0.0);
		AnchorPane.setRightAnchor(lblStatus, 0.0);
		lblStatus.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Supprime un trajet dans la base de donnees
	 */
	@FXML
	private void deleteJourney() {
		
		if(this.journeysService.deleteJourney(this.journey.getId())) {
			
			// On retourne sur la vue des trajets quand la suppression est effective
			try {
				AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/Journeys.fxml"));
				AnchorPane.setLeftAnchor(pane, 0.0);
				AnchorPane.setTopAnchor(pane, 0.0);
			    AnchorPane.setRightAnchor(pane, 0.0);
			    AnchorPane.setBottomAnchor(pane, 0.0);
				AnchorPane showPane = (AnchorPane) myPane.getParent();
				showPane.getChildren().setAll(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else 
			this.lblStatus.setText("Une erreur s'est produite lors de la suppression du trajet.");
	}
}
