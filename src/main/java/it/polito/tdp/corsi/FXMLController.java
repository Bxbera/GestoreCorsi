/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	String input = txtPeriodo.getText();
    	int periodo = 0;
    	// è buona norma avere dei controlli in input, ad esempio se è effettivamente un intero
    	
    	try {
    		periodo = Integer.parseInt(input);
    	} catch (NumberFormatException nfe) {
    		txtRisultato.setText("Inserted Value is not a integer");
    		return;
    	}
    	
    	if(periodo < 1 || periodo > 2) {
    		txtRisultato.setText("Not inserted 1 or 2");
    		return; 
    	}
    	
    	List<Corso> result = new ArrayList<>();
    	result = model.getCorsiByPeriodo(periodo);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato " + result.size() + "corsi. \n");
    	
    	for(Corso c: result) {
    		txtRisultato.appendText("" + c + "\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	// faccio io
    	String input = txtPeriodo.getText();
    	int periodo = 0;
    	// è buona norma avere dei controlli in input, ad esempio se è effettivamente un intero
    	
    	try {
    		periodo = Integer.parseInt(input);
    	} catch (NumberFormatException nfe) {
    		txtRisultato.setText("Inserted Value is not a integer");
    		return;
    	}
    	
    	if(periodo < 1 || periodo > 2) {
    		txtRisultato.setText("Not inserted 1 or 2");
    		return; 
    	}
    	
    	// ho aggiunto solo questa parte alla fine
    	Map<Corso, Integer> result = new HashMap<>();
    	result = model.getCorsiIscritti(periodo);
    	
    	txtRisultato.clear();
    	
    	for(Corso c : result.keySet()) {
    		txtRisultato.appendText("Al corso: " + c + " risultano iscritti:  " + result.get(c) + " studenti\n");
    	}
    	
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	String codins = this.txtCorso.getText();
    	if(codins.isEmpty()) {
    		txtRisultato.setText("Inserire il codice di un corso");
    		return;
    	}
    	
    	txtRisultato.clear();
    	for(Divisione d : this.model.getDivisioneStudentiCorso(codins)) {
    		txtRisultato.appendText(d.getcDS() + " " + d.getnStudenti() + "\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	String codins = this.txtCorso.getText();
    	if(codins.isEmpty()) {
    		txtRisultato.setText("Inserire il codice di un corso");
    		return;
    	}
    	
    	List<Studente> risultato = new ArrayList<Studente>();
    	risultato = this.model.getIscrittiCorso(codins);
    	
    	txtRisultato.clear();
    	
    	for(Studente s : risultato) {
    		txtRisultato.appendText("Al corso: " + codins + " risulta iscritto:  " + s+ "\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}