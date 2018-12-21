package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.favoride.application.JourneysService;
import com.favoride.application.LoginService;
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

public class JourneysController implements Initializable {
	
	@FXML 
	private AnchorPane myPane;
	
	@FXML 
	private ComboBox<String> cbStatus;
	
	@FXML 
	private ComboBox<String> cbTime;
	
	@FXML 
	private TableView<Journey> tbJourneys;
	
	private LoginService userService;
	
	private JourneysService journeysService;
	
	private Journey selectedJourney;
	
	public JourneysController() {
		
		this.userService = LoginService.getInstance();
		this.journeysService = JourneysService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.cbStatus.getItems().setAll("Conducteur", "Passager");
		this.cbTime.getItems().setAll("Passé", "En cours", "A venir");
		this.cbStatus.setValue("Conducteur");
		this.cbTime.setValue("A venir");
		
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
	
	@FXML 
	public void onCbTimeAction() {
		
		switchJourneys();
	}
	
	@FXML 
	public void onCbStatusAction() {
		
		switchJourneys();
	}
	
	private void switchJourneys() {
		
		String status = this.cbStatus.getValue();
		String time = this.cbTime.getValue();
		int id = this.userService.getUser().getId();
		
		if("Conducteur".equals(status) && "Passé".equals(time)) {
			
			this.journeysService.getPastJourneysConductor(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
			
		} else if ("Conducteur".equals(status) && "En cours".equals(time)) {
			
			this.journeysService.getNowJourneysConductor(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
			
		} else if ("Conducteur".equals(status) && "A venir".equals(time)) {
			
			this.journeysService.getFutureJourneysConductor(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
			
		} else if ("Passager".equals(status) && "Passé".equals(time)) {
			
			this.journeysService.getPastJourneysPassenger(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
			
		} else if ("Passager".equals(status) && "En cours".equals(time)) {
			
			this.journeysService.getNowJourneysPassenger(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
			
		} else if ("Passager".equals(status) && "A venir".equals(time)) {
			
			this.journeysService.getPastJourneysPassenger(id);
			this.tbJourneys.getItems().clear();
			this.tbJourneys.getItems().setAll(this.journeysService.getJourneys());
		}
	}
	
	@FXML 
	private void getDetails() {
	
		if(this.selectedJourney != null) {
			
			System.out.println("ok");
			try {
				this.journeysService.setSelectedJourney(this.selectedJourney);
				AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/Details.fxml"));
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
