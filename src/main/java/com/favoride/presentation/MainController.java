package com.favoride.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@FXML
    private Button btnPanel1;
    @FXML
    private Button btnPanel2;
    @FXML
    private Button btnPanel3;
    @FXML
    private AnchorPane showPane;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		AnchorPane pnlOne;
		try {
			pnlOne = FXMLLoader.load(this.getClass().getResource("/view/PanelOne.fxml"));
			 AnchorPane.setLeftAnchor(pnlOne, 0.0);
			    AnchorPane.setTopAnchor(pnlOne, 0.0);
			    AnchorPane.setRightAnchor(pnlOne, 0.0);
			    AnchorPane.setBottomAnchor(pnlOne, 0.0);
			    showPane.getChildren().setAll(pnlOne);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	 @FXML
	    private void btnPanel1Action(ActionEvent event) throws IOException {
		 AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/view/PanelOne.fxml"));
		    AnchorPane.setLeftAnchor(pnlOne, 0.0);
		    AnchorPane.setTopAnchor(pnlOne, 0.0);
		    AnchorPane.setRightAnchor(pnlOne, 0.0);
		    AnchorPane.setBottomAnchor(pnlOne, 0.0);
		    showPane.getChildren().setAll(pnlOne);
	    }
	 
	 @FXML
	 private void btnPanel2Action(ActionEvent event) throws IOException {
		 AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("/view/PanelTwo.fxml"));
		    AnchorPane.setLeftAnchor(pnlOne, 0.0);
		    AnchorPane.setTopAnchor(pnlOne, 0.0);
		    AnchorPane.setRightAnchor(pnlOne, 0.0);
		    AnchorPane.setBottomAnchor(pnlOne, 0.0);
		    showPane.getChildren().setAll(pnlOne);
	    }

	
}
