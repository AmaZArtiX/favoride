package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.favoride.application.FindJourneyService;
import com.favoride.application.LoginService;
import com.favoride.domain.Journey;
import com.favoride.domain.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class SearchResultController implements Initializable {
	
	private FindJourneyService journeyService;
	
	private LoginService userService;
	
	@FXML 
	private Label lblStatus;
	
	@FXML 
	private TableView<Journey> tvJourneys;
	
	@FXML 
	private Button btnSubscribe;
	
	@FXML 
	private AnchorPane myPane;
	
	private Journey selectedJourney;
	
	public SearchResultController() {
		
		this.journeyService = FindJourneyService.getInstance();
		this.userService = LoginService.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//TableColumn<Journey, User> nameColumn = new TableColumn<Journey, Integer>("ID");
		//nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
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
		
		tvJourneys.getColumns().add(driverColumn);
		tvJourneys.getColumns().add(departureColumn);
		tvJourneys.getColumns().add(arrivalColumn);
		tvJourneys.getColumns().add(dateColumn);
		tvJourneys.getColumns().add(rateColumn);
		tvJourneys.getColumns().add(seatsColumn);
	 
        tvJourneys.setRowFactory(tv -> {
            TableRow<Journey> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
 
                    this.selectedJourney = row.getItem();
                }
            });
            return row;
        });
        
        tvJourneys.getItems().addAll(this.journeyService.getJourneys());
        
        if(this.journeyService.getJourneys().isEmpty()) {
        	tvJourneys.setVisible(false);
        	this.btnSubscribe.setVisible(false);
        	this.lblStatus.setText("Aucun résultat n'a été trouvé.");;
        }  	
	}
	
	@FXML
	public void subscribe() {
		
		if(this.selectedJourney != null) {
			
			if(this.journeyService.addPassenger(this.selectedJourney.getId(), this.userService.getUser().getId())){
				
				try {
					AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/view/Find.fxml"));
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
				this.lblStatus.setText("Une erreur s'est produite lors de l'inscription au trajet.");
		}
		else 
			this.lblStatus.setText("Vous devez sélectionner un trajet.");
	}
}
