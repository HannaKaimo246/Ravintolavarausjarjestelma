package controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.DataValidation;
import model.Kayttaja;
import model.Palaute;
import view.App;

public class PalauteController {


	private Palaute palaute;
	
    @FXML
    private BorderPane palautesivu;

    @FXML
    private GridPane palautelomake;

    @FXML
    private TextField aihe;

    @FXML
    private TextArea viesti;

    @FXML
    private Button lahetaNappi;

    @FXML
    private Button takaisinNappi;
    
    @FXML
    private Label aiheLabel;

    @FXML
    private Label viestiLabel;
    
    @FXML
    private Label viestiLabel2;
    
    @FXML
    private Text tulos;
    
    ResourceBundle bundle = App.getResourceBundle();
    
    @FXML
    private void takaisin() throws IOException {
        App.setRoot("asiakasNakyma");
    }
    
    @FXML
    private void palautelista() throws IOException {
        App.setRoot("palautesivu");
    }
    
    /**
     * FXMLLoader kutsuu tätä metodia, kun alustus on valmis
     */
    @FXML 
    void initialize() {
    	
    	// luodaan vain yksi Asiakas olio
    	Kayttaja kayttaja = Kayttaja.getInstance();
    	
    	kayttaja.alustaPalauteKontrolleri(this);
    
    	
        lahetaNappi.setOnAction((ActionEvent event) -> {
        	
        	boolean alphabet = false;
        	
        	boolean subject = DataValidation.textFieldIsNull(aihe, aiheLabel, bundle.getString("subject.required"));
        	if(subject == false) {
        		alphabet = DataValidation.textAlphabet(aihe, aiheLabel, bundle.getString("alphabets"));
        	}
        	boolean message = DataValidation.textAreaIsNull(viesti, viestiLabel, bundle.getString("message.required"));
        
        	
        	if(subject == false && alphabet == true && message == false) {
            // Luodaan asiakkaan palaute
        	kayttaja.viePalauteAsiakas(aihe.getText(), viesti.getText());
            
            viesti.clear();
        	aihe.clear();
        	
        	viestiLabel2.setText(bundle.getString("thank.you.for.feedback"));
        	}
        	
        	
        });
    }
    
    /**
     * Teksti palautteen luonnin onnistuessa
     */
    public void onnistui() {
    	System.out.print(bundle.getString("creation.successful"));
    	tulos.setText(bundle.getString("creation.successful"));
    }
    /**
     * Teksti palautteen luonnin epäonnistuessa
     */
    public void epaonnistui() {
    	System.out.print("Palaute luonti epaonnistui");
    	tulos.setText("Luonti epaonnistui!");
    }

}
