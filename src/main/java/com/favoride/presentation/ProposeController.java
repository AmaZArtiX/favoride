package com.favoride.presentation;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.favoride.application.CityService;
import com.favoride.application.ProposeService;
import com.favoride.application.UserService;
import com.favoride.domain.Journey;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * ***********************************************************************
 * Nom ...........: ProposeController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Propose.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class ProposeController implements Initializable {
	
	private ProposeService journeyService;
	
	private UserService userService;
	
	private CityService cityService;
	
	@FXML 
	private TextField lblDeparture;
	
	@FXML 
	private TextField lblArrival;
	
	@FXML 
	private Label lblStatus;
	
	@FXML 
	private Spinner<Integer> seatsSpinner;
	
	@FXML 
	private Spinner<Double> rateSpinner;
	
	@FXML 
	private DatePicker datePicker;
	
	@FXML
	private ComboBox<String> cbHours;
	
	@FXML 
	private ComboBox<String> cbMinutes;
	
	private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 3, 1);
	 
	private SpinnerValueFactory<Double> rateFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, 10, 0.5);

	public ProposeController() {
		
		this.journeyService = ProposeService.getInstance();
		this.userService = UserService.getInstance();
		this.cityService = CityService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// On initialise les objets de saisie
		TextFields.bindAutoCompletion(this.lblDeparture, this.cityService.getCitiesToArray());
		TextFields.bindAutoCompletion(this.lblArrival, this.cityService.getCitiesToArray());
		
		this.seatsSpinner.setValueFactory(valueFactory);
		this.rateSpinner.setValueFactory(rateFactory);
		this.seatsSpinner.setEditable(false);
		this.rateSpinner.setEditable(false);
		this.datePicker.setEditable(false);
		
		
		this.cbHours.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00");
		this.cbMinutes.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09");
		
		for(int i = 10; i < 60; i++) 
			this.cbMinutes.getItems().add(i+"");
		
		this.cbHours.setValue("08");
		this.cbMinutes.setValue("00");
	}	
	
	/**
	 * Retourne vrai si au moins un champ est vide
	 * @return
	 */
	public boolean areFieldsEmpty() {
		
		return "".equals(this.lblDeparture.getText()) || "".equals(this.lblArrival.getText()) || this.datePicker.getValue() == null;
	}
	
	public void sendJourney() {
		
		// Les champs sont remplis
		if(!areFieldsEmpty()) {
			
			// Les villes existent
			if(this.cityService.isValidCity(this.lblDeparture.getText()) && this.cityService.isValidCity(this.lblArrival.getText())) {
				
				String departure = this.lblDeparture.getText();
				String arrival = this.lblArrival.getText();
				
				// La ville de depart est differente de celle d'arrivee
				if(!departure.equals(arrival)) {
					
					LocalDateTime date = convertToLocalDateTime(this.datePicker.getValue(), this.cbHours.getValue(), this.cbMinutes.getValue());
					
					// L'horaire est valide
					if(date.isAfter(LocalDateTime.now())) {
						
						// On insere le trajet dans la base de donnees
						if(this.journeyService.insertJourney(new Journey(this.userService.getUser(), 
								 departure,
								 arrival,
								 date,
								 this.rateSpinner.getValue(),
								 this.seatsSpinner.getValue()))) {
							
							this.lblStatus.setText("Votre trajet a bien été enregistré.");
							clearFields();
						}
						else 
							this.lblStatus.setText("Une erreur s'est produite lors de la création de votre trajet.");
					}
					else 
						this.lblStatus.setText("Vous ne pouvez pas sélectionnier une date déjà passée.");
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

	/**
	 * Convertie les donnees saisies (date, heure, minutes) en un objet de type LocalDateTime
	 * @param date
	 * @param hours
	 * @param minutes
	 * @return
	 */
	public LocalDateTime convertToLocalDateTime(LocalDate date, String hours, String minutes) {
		
		LocalTime time = LocalTime.of(Integer.parseInt(hours), Integer.parseInt(minutes));
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}
	
	/**
	 * Reinitialise les champs
	 */
	public void clearFields() {
		
		this.lblArrival.clear();
		this.lblDeparture.clear();
		this.datePicker.setValue(null);	
		this.cbHours.setValue("08");
		this.cbMinutes.setValue("00");
	}
}

