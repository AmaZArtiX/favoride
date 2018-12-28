package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.favoride.application.CityService;
import com.favoride.application.SearchService;
import com.favoride.application.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * ***********************************************************************
 * Nom ...........: SearchController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Search.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class SearchController implements Initializable {
	
	private UserService userService;
	
	private SearchService journeyService;
	
	private CityService cityService;
	
	@FXML
	private AnchorPane myPane;
	
	@FXML 
	private TextField txtDeparture;
	
	@FXML 
	private TextField txtArrival;
	
	@FXML 
	private Spinner<Double> spinnerRate;
	
	@FXML
	private ComboBox<String> cbHours;

	@FXML
	private ComboBox<String> cbMinutes;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Label lblStatus;
	 
	private SpinnerValueFactory<Double> rateFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, 10, 0.5);
	
	public SearchController() {
		
		this.userService = UserService.getInstance();
		this.journeyService = SearchService.getInstance();
		this.cityService = CityService.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(this.txtDeparture, this.cityService.getCitiesToArray());
		TextFields.bindAutoCompletion(this.txtArrival, this.cityService.getCitiesToArray());
		
		this.cbHours.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00");
		this.cbMinutes.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09");
		
		this.cbHours.setValue("08");
		this.cbMinutes.setValue("00");
		
		for(int i = 10; i < 60; i++) 
			this.cbMinutes.getItems().add(i+"");
		
		this.spinnerRate.setValueFactory(rateFactory);
		
		this.spinnerRate.setEditable(false);
		this.datePicker.setEditable(false);
	}
	
	/**
	 * Retourne vrai si au moins un champ est vide
	 * @return
	 */
	public boolean areFieldsEmpty() {
		
		return "".equals(this.txtDeparture.getText()) || "".equals(this.txtDeparture.getText()) || this.datePicker.getValue() == null;
	}
	
	public LocalDateTime convertToLocalDateTime(LocalDate date, String hours, String minutes) {
		
		LocalTime time = LocalTime.of(Integer.parseInt(hours), Integer.parseInt(minutes));
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}
	
	/**
	 * Recherche un trajet selon les criteres renseignes
	 */
	@FXML 
	public void find() {
	
		// Les champs sont remplis
		if(!areFieldsEmpty()) {
			
			// Les villes existent
			if(this.cityService.isValidCity(this.txtDeparture.getText()) && this.cityService.isValidCity(this.txtDeparture.getText())) {
				
				String departure = this.txtDeparture.getText();
				String arrival = this.txtArrival.getText();
				
				// Les villes de depart et d'arrivee sont differentes
				if(!departure.equals(arrival)) {
					
					int id = this.userService.getUser().getId();
					LocalDateTime date = convertToLocalDateTime(this.datePicker.getValue(), this.cbHours.getValue(), this.cbMinutes.getValue());
					Double rate  = this.spinnerRate.getValue();
					
					// L'horaire est valide
					if(date.isAfter(LocalDateTime.now())) {
					
						if(this.journeyService.getResultFromSearch(id, departure, arrival, date, rate)) {
							
							// On redirige vers la page de resultats
							try {
								AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/SearchResult.fxml"));
								AnchorPane.setLeftAnchor(pane, 0.0);
								AnchorPane.setTopAnchor(pane, 0.0);
							    AnchorPane.setRightAnchor(pane, 0.0);
							    AnchorPane.setBottomAnchor(pane, 0.0);
								AnchorPane showPane = (AnchorPane) myPane.getParent();
								showPane.getChildren().setAll(pane);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Une erreur est survenue lors de l'ouverture de la page de recherche.");
							}
						}
						else 
							this.lblStatus.setText("Une erreur s'est produite lors de la recherche.");
					}
					else 
						this.lblStatus.setText("Vous ne pouvez pas sélectionner un date déjà passée.");
				}
				else 
					this.lblStatus.setText("La ville de départ ne peut pas être la ville d'arrivée.");
			}
			else 
				this.lblStatus.setText("Vous devez renseigner une ville de départ et d'arrivée valide.");
		}
		else 
			this.lblStatus.setText("Tous les champs doivent être remplis.");
	}
}
