package com.favoride.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PanelTwoController {

	@FXML
	private Label lbl;
	
	public void setText() {
		lbl.setText("Titi");
	}
}
