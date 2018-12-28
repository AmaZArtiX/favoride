package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.favoride.application.UserService;

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

/**
 * ***********************************************************************
 * Nom ...........: MainController.java
 * Description ...: Classe permettant la gestion de la logique de la vue 
 * ...............: Main.fxml
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class MainController implements Initializable {

    @FXML
    private AnchorPane showPane;
    
    private UserService userService;
    
    public MainController() {
    	
    	this.userService = UserService.getInstance();
    }
   
    // On initialise l'AnchorPane avec la vue de profil
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
	
	/**
	  * Redirige vers la vue de profil
	  * @param event
	  */
	 @FXML
	 private void btnPanel1Action(ActionEvent event) throws IOException {
		 
		 try {
			changeAnchor("/view/Profile.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
	 /**
	  * Redirige vers la vue de proposition de trajets
	  * @param event
	  */
	 @FXML
	 private void btnPanel2Action(ActionEvent event) throws IOException {
		 
		 try {
				changeAnchor("/view/Propose.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 
	 /**
	  * Redirige vers la vue de recherche de trajets
	  * @param event
	  */
	 @FXML
	 private void btnPanel3Action(ActionEvent event) {
		 
		 try {
			changeAnchor("/view/Search.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * Redirige vers la vue des trajets
	  * @param event
	  */
	 @FXML
	 private void btnPanel4Action(ActionEvent event) {
		 
		 try {
			changeAnchor("/view/Journeys.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * Deconnecte un utilisateur et redirige vers la page de connexion
	  * @param event
	  */
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
	 
	 /**
	  * Modifie le contenu de l'AnchorPane
	  * @param anchor
	  * @throws IOException
	  */
	 private void changeAnchor(String anchor) throws IOException {
		 
		 AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource(anchor));
		 AnchorPane.setLeftAnchor(pnlOne, 0.0);
		 AnchorPane.setTopAnchor(pnlOne, 0.0);
	     AnchorPane.setRightAnchor(pnlOne, 0.0);
	     AnchorPane.setBottomAnchor(pnlOne, 0.0);
	     showPane.getChildren().setAll(pnlOne);
	 }
}
