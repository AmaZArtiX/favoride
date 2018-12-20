package com.favoride.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ProposeController implements Initializable {
	
	@FXML 
	private TextField lblDeparture;
	
	@FXML 
	private TextField lblArrival;
	
	@FXML 
	private DatePicker datePicker;
	
	private String[] cities = {"Lille", "Paris", "Marseille"};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(lblDeparture, cities);
		TextFields.bindAutoCompletion(lblArrival, cities);
	}
}

