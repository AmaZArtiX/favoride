package com.favoride;
	
import com.favoride.persistence.ConnectionManager;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 * ***********************************************************************
 * Nom ...........: Main.java
 * Description ...: Classe principale de l'application
 * ...............:
 * ...............:
 * Auteur(s) .....: SIMON BACQUET & YACINE CHTAIRI
 * Version .......: 1.0
 ***********************************************************************
 */

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		// Lancement de la scene vers la fenetre de connexion
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Favoride");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("/images/logo.png"));
			primaryStage.show();
			// On ferme la connexion a la base de donnees quand la fermeture de la fenetre est demandee
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              ConnectionManager.getInstance().closeConnection();
		          }
		      });        
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Impossible de lancer la fenêtre de connexion...");
			
			// Affichage d'une alerte d'erreur
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Favoride");
			alert.setHeaderText("Une erreur s'est produite...");
			alert.setContentText("Impossible de lancer la fenêtre de connexion.");
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
} 