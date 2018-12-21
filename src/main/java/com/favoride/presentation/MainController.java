package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.favoride.application.LoginService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
    private Button btnPanel1;
    @FXML
    private Button btnPanel2;
    @FXML
    private Button btnPanel3;
    @FXML
    private AnchorPane showPane;
    
    private LoginService userService;
    
    public MainController() {
    	
    	this.userService = LoginService.getInstance();
    }
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			changeAnchor("/view/Profile.fxml");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	}
	
	 @FXML
	 private void btnPanel1Action(ActionEvent event) throws IOException {
		 
		 try {
			changeAnchor("/view/Profile.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
	
	 
	 @FXML
	 private void btnPanel2Action(ActionEvent event) throws IOException {
		 
		 try {
				changeAnchor("/view/Propose.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 
	 @FXML
	 private void btnPanel3Action(ActionEvent event) {
		 
		 try {
			changeAnchor("/view/Find.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @FXML
	 private void btnPanel4Action(ActionEvent event) {
		 
		 try {
			changeAnchor("/view/Journeys.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @FXML
	 private void disconnect(ActionEvent event) {
		 
		 this.userService.setUser(null);
		 try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
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
	 
	 
	 
	 private void changeAnchor(String anchor) throws IOException {
		 
		 AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource(anchor));
		 AnchorPane.setLeftAnchor(pnlOne, 0.0);
		 AnchorPane.setTopAnchor(pnlOne, 0.0);
	     AnchorPane.setRightAnchor(pnlOne, 0.0);
	     AnchorPane.setBottomAnchor(pnlOne, 0.0);
	     showPane.getChildren().setAll(pnlOne);
	 }
}
