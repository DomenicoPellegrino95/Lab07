package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
    
	Model model;
	public void setModel(Model model){
		this.model = model ;
	}
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
		inputNumeroLettere.clear();
		inputParola.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		int i=Integer.parseInt(inputNumeroLettere.getText());
		model.createGraph(i);
		txtResult.setText("grafo fatto \n");
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		String risultato= model.findMaxDegree();
		txtResult.setText(risultato);
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		String risultato="";
		for (String s: model.displayNeighbours(inputParola.getText())){
			risultato+=s+"\n";
		}
		txtResult.setText(risultato);
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}