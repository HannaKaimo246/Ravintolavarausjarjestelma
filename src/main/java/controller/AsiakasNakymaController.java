package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Asiakas;
import model.Kayttaja;
import model.Tyontekija;
import view.App;

public class AsiakasNakymaController {


    @FXML
    private Button ruokaListaBtn;

    @FXML
    private Button varaaPoytaBtn;

    @FXML
    private Button palauteBtn;


    @FXML
    private Label kayttajanimi;
    
    private Kayttaja kayttaja;

    /**
     *  ruokalista- sivulle siirtyminen
     * @throws IOException, jos siirtyminen ei onnistu
     */
    @FXML
	private void ruokaListaBtn() throws IOException {
		App.setRoot("menu");
	}
    
    /**
     * varaus- sivulle siirtyminen
     * @throws IOException, jos siirtyminen ei onnistu
     */
    @FXML
	private void varaaPoytaBtn() throws IOException {
		
		/*
	   	if (kayttaja.tuoTyontekija() == null) {
	   		App.setRoot("varaus");
	   	}
	   	*/
    	App.setRoot("varaus");
	}
    
    /**
     * asiakkaana siirrytaan palautteiden luomiseen ja tyontekijana palautteiden tarkasteluun
     * @throws IOException, jos siirtyminen ei onnistu
     */
    @FXML
   	private void palauteBtn() throws IOException {
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	if (kayttaja.tuoTyontekija() == null) {
    		App.setRoot("palaute");
    	} else {
    		App.setRoot("palautesivu");
    	}
    	
   
   	}
    
    /**
     * tarkistus tietokannasta, onko sisaankirjautuja asiakas vai tyontekija
     */
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
    
    /**
     * seka asiakkaan, etta tyontekijan kirjautuminen ulos(takaisin paanakymaan siirtyminen)
     * @throws IOException, jos toiminto ei onnistu
     */
    @FXML
    public void kirjauduUlos() throws IOException {
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	kayttaja.setAsiakas(null);
    	kayttaja.setTyontekija(null);
    	
    	App.setRoot("home");
    	
    }
   
}
