package com.favoride.presentation;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.favoride.application.JourneysService;
import com.favoride.application.LoginService;
import com.favoride.domain.Journey;
import com.favoride.domain.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class DetailsController implements Initializable {

	private LoginService userService;
	
	private JourneysService journeysService;
	
	private Journey journey;
	
	private List<User> passengers;
	
	@FXML 
	private TableView<User> tbPassengers;
	
	public DetailsController() {
		
		this.journeysService = JourneysService.getInstance();
		this.userService = LoginService.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.journey = this.journeysService.getSelectedJourney();
		
		TableColumn<User, String> firstName = new TableColumn<User, String>("Prénom");
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		tbPassengers.getColumns().add(firstName);

	 
		tbPassengers.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            return row;
        });
		
		passengers = this.journeysService.getPassengers(journey.getId());
		tbPassengers.getItems().setAll(passengers);
	}
}
