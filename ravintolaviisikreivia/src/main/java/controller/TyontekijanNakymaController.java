package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Asiakas;
import model.Kayttaja;
import model.Tyontekija;
import view.App;

public class TyontekijanNakymaController {

    @FXML
    private Label kayttajanimi;

    @FXML
    private Button ulos;

    @FXML
    private Button ruokaListaBtn;

    @FXML
    private Button palauteBtn;

    @FXML
    private Button tyontekijaBtn;

    @FXML
    private Button varausBtn;
    
    
    private Kayttaja kayttaja;
    
    @FXML
    private void ruokaListaBtn() throws IOException {
   		App.setRoot("ruokalistaHallinta");
   	}
    
    @FXML
   	private void tyontekijaBtn() throws IOException {
   		App.setRoot("employee");
   	}
    
    
    @FXML
   	private void palauteBtn() throws IOException {
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	if (kayttaja.tuoTyontekija() == null) {
    		App.setRoot("palaute");
    	} else {
    		App.setRoot("palautesivu");
    	}
    	
   
   	}
    
    @FXML
   	private void varausBtn() throws IOException {
   		App.setRoot("varausHallinta");
   	}
    @FXML
    void initialize() {
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	Asiakas asiakas = kayttaja.tuoAsiakas();
    	
    	Tyontekija tyontekija = kayttaja.tuoTyontekija();
    	
    	if (asiakas != null) {
    		kayttajanimi.setText(asiakas.getAsiakasNimimerkki());
    	} else {
    		kayttajanimi.setText(tyontekija.getTyontekijaNimiMerkki());
    	}
    	
    	
    	
    }
    
    
    @FXML
    public void kirjauduUlos() throws IOException {
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	kayttaja.setAsiakas(null);
    	kayttaja.setTyontekija(null);
    	
    	App.setRoot("home");
    	
    }
    
    @FXML
    private void editori() throws IOException {

    	App.setRoot("poytalista");
    }
    

}

