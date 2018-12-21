package com.favoride.presentation;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.favoride.application.LoginService;
import com.favoride.application.ProposeJourneyService;
import com.favoride.domain.Journey;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class ProposeController implements Initializable {
	
	private ProposeJourneyService journeyService;
	private LoginService userService;
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
	
	private String[] cities = {"Fourmies", "Lille", "Paris", "Marseille", "Bordeaux", "Toulouse"};
	
	private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
	 
	private SpinnerValueFactory<Double> rateFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, 10, 0.5);

	public ProposeController() {
		
		this.journeyService = ProposeJourneyService.getInstance();
		this.userService = LoginService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(this.lblDeparture, cities);
		TextFields.bindAutoCompletion(this.lblArrival, cities);
		this.seatsSpinner.setValueFactory(valueFactory);
		this.rateSpinner.setValueFactory(rateFactory);
		this.seatsSpinner.setEditable(false);
		this.rateSpinner.setEditable(false);
		this.datePicker.setEditable(false);
		
		
		this.cbHours.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00");
		this.cbMinutes.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09");
		
		this.cbHours.setValue("08");
		this.cbMinutes.setValue("00");
		
		for(int i = 10; i < 60; i++) 
			this.cbMinutes.getItems().add(i+"");
	}	
	
	public boolean isValidCity(String city) {
		
		return Arrays.asList(this.cities).contains(city);
	}
	
	public boolean areFieldsEmpty() {
		
		return "".equals(this.lblDeparture.getText()) || "".equals(this.lblArrival.getText()) || this.datePicker.getValue() == null;
	}
	
	public void sendJourney() {
		
		if(!areFieldsEmpty()) {
			
			if(isValidCity(this.lblDeparture.getText()) && isValidCity(this.lblArrival.getText())) {
				
				LocalDateTime date = convertToLocalDateTime(this.datePicker.getValue(), this.cbHours.getValue(), this.cbMinutes.getValue());
				
				if(this.journeyService.insertJourney(new Journey(this.userService.getUser(), 
																 this.lblDeparture.getText(),
																 this.lblArrival.getText(),
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
				this.lblStatus.setText("Vous devez renseigner une ville de départ et d'arrivée valide.");
		}
		else 
			this.lblStatus.setText("Tous les champs doivent être remplis.");
	}

	public LocalDateTime convertToLocalDateTime(LocalDate date, String hours, String minutes) {
		
		LocalTime time = LocalTime.of(Integer.parseInt(hours), Integer.parseInt(minutes));
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}
	
	public void clearFields() {
		
		this.lblArrival.clear();
		this.lblDeparture.clear();
		this.datePicker.setValue(null);	
		this.cbHours.setValue("08");
		this.cbMinutes.setValue("00");
	}
}

