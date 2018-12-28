package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.favoride.application.JourneysService;
import com.favoride.application.UserService;
import com.favoride.domain.Journey;
import com.favoride.domain.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

/**
 * ***********************************************************************
 * Nom ...........: JourneysController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Journeys.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class JourneysController implements Initializable {
	
	// L'AnchorPane qui accueille la scene
	@FXML 
	private AnchorPane myPane;
	
	// ComboBox definissant le role de l'utilisateur (conducteur - passager)
	@FXML 
	private ComboBox<String> cbStatus;
	
	// ComboBox definissant l'intervalle de temps utile a la recherche (passe - en cours - a venir)
	@FXML 
	private ComboBox<String> cbTime;
	
	// TableView contenant les resultats de la recherche
	@FXML 
	private TableView<Journey> tbJourneys;
	
	private UserService userService;
	
	private JourneysService journeysService;
	
	private List<Journey> journeysAsConductor;
	
	private List<Journey> journeysAsPassenger;
 	
	// Trajet pour lequel il faut afficher les details
	private Journey selectedJourney;
	
	public JourneysController() {
		
		this.userService = UserService.getInstance();
		this.journeysService = JourneysService.getInstance();
		// On recupere les trajets qui concernent l'utilisateur
		this.journeysAsConductor = this.journeysService.getJourneysAsConductor(this.userService.getUser().getId());
		this.journeysAsPassenger = this.journeysService.getJourneysAsPassenger(this.userService.getUser().getId());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Initialisation des ComboBox
		this.cbStatus.getItems().setAll("Conducteur", "Passager");
		this.cbTime.getItems().setAll("Passé", "En cours", "A venir");
		this.cbStatus.setValue("Conducteur");
		this.cbTime.setValue("A venir");
		
		// Initialisation de la TableView
		TableColumn<Journey, User> driverColumn = new TableColumn<Journey, User>("Conducteur");
		driverColumn.setCellValueFactory(new PropertyValueFactory<>("driver"));
		TableColumn<Journey, String> departureColumn = new TableColumn<Journey, String>("Départ");
		departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
		TableColumn<Journey, String> arrivalColumn = new TableColumn<Journey, String>("Arrivée");
		arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		TableColumn<Journey, LocalDateTime> dateColumn = new TableColumn<Journey, LocalDateTime>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn<Journey, Double> rateColumn = new TableColumn<Journey, Double>("Prix");
		rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
		TableColumn<Journey, Integer> seatsColumn = new TableColumn<Journey, Integer>("Places");
		seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
		
		tbJourneys.getColumns().add(driverColumn);
		tbJourneys.getColumns().add(departureColumn);
		tbJourneys.getColumns().add(arrivalColumn);
		tbJourneys.getColumns().add(dateColumn);
		tbJourneys.getColumns().add(rateColumn);
		tbJourneys.getColumns().add(seatsColumn);
	 
		// On recupere le trajet selectionne selon la rangee cliquee
		tbJourneys.setRowFactory(tv -> {
            TableRow<Journey> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
 
                    this.selectedJourney = row.getItem();
                }
            });
            return row;
        });
		
		switchJourneys();
	}
	
	// Changement des parametres de recherche selon le temps
	@FXML 
	public void onCbTimeAction() {
		
		switchJourneys();
	}
	
	// Changement des parametres de recherche selon le statut
	@FXML 
	public void onCbStatusAction() {
		
		switchJourneys();
	}
	
	// Extrait les trajets selon les criteres de recherche
	private void switchJourneys() {
		
		String status = this.cbStatus.getValue();
		String time = this.cbTime.getValue();
		int id = this.userService.getUser().getId();
		
		this.tbJourneys.getItems().clear();
				
		if("Conducteur".equals(status) && "Passé".equals(time)) {
			
			this.tbJourneys.getItems().setAll(this.journeysAsConductor.stream()
					.filter(j -> j.getDate().isBefore(LocalDateTime.now()))
					.collect(Collectors.toList()));
			
		} else if ("Conducteur".equals(status) && "En cours".equals(time)) {
			
			this.tbJourneys.getItems().setAll(this.journeysAsConductor.stream()
					.filter(j -> j.getDate().isEqual(LocalDateTime.now()))
					.collect(Collectors.toList()));
			
		} else if ("Conducteur".equals(status) && "A venir".equals(time)) {
			
			this.tbJourneys.getItems().setAll(this.journeysAsConductor.stream()
					.filter(j -> j.getDate().isAfter(LocalDateTime.now()))
					.collect(Collectors.toList()));
			
		} else if ("Passager".equals(status) && "Passé".equals(time)) {
						
			this.tbJourneys.getItems().setAll(this.journeysAsPassenger.stream()
					.filter(j -> j.getDate().isBefore(LocalDateTime.now()))
					.collect(Collectors.toList()));
			
		} else if ("Passager".equals(status) && "En cours".equals(time)) {
						
			this.tbJourneys.getItems().setAll(this.journeysAsPassenger.stream()
					.filter(j -> j.getDate().isEqual(LocalDateTime.now()))
					.collect(Collectors.toList()));
			
		} else if ("Passager".equals(status) && "A venir".equals(time)) {

			this.tbJourneys.getItems().setAll(this.journeysAsPassenger.stream()
					.filter(j -> j.getDate().isAfter(LocalDateTime.now()))
					.collect(Collectors.toList()));
		}
	}
	
	// Redirige sur la vue des details d'un trajet
	@FXML 
	private void getDetails() {
	
		if(this.selectedJourney != null) {
			
			try {
				this.journeysService.setSelectedJourney(this.selectedJourney);
				AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/JourneysDetails.fxml"));
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
	}
}
