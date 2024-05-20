package controller;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Asiakas;
import model.Kayttaja;
import model.Ruoka;
import model.Tyontekija;
import view.App;

public class MenuController implements Initializable {

    @FXML
    private TableView<Ruoka> taulukko;

    @FXML
    private TableColumn<Ruoka, String> nimiSarake;

    @FXML
    private TableColumn<Ruoka, String> kuvausSarake;

    @FXML
    private TableColumn<Ruoka, Double> hintaSarake;

    @FXML
    private Button takaisinbtn;

    @FXML
    private Button signinbtn;

    @FXML
    private Button singnupbtn;
    
    private List<Ruoka> lista = new ArrayList<>();
    
	@FXML
	private void signinBtn() throws IOException {
		App.setRoot("signin");
	}
	
	@FXML
	private void signupBtn() throws IOException {
		App.setRoot("signUp");
	}
    
	/**
	 * käyttäjän näkymään siirtyminen, jos työntekijä ja asiakas = null, siirrytään takaisin päänäkymään
	 * @throws IOException
	 */
    @FXML
    private void takaisin() throws IOException {
    	
    	Kayttaja kayttaja = Kayttaja.getInstance();
    	
    	Tyontekija tyontekija = kayttaja.tuoTyontekija();
    	
    	Asiakas asiakas = kayttaja.tuoAsiakas();
    	
    	if (tyontekija == null && asiakas == null) {
    		   System.out.println("takaisin.");
    	        App.setRoot("home");
    	} else {
    		
    		  App.setRoot("kayttajanNakyma");
    	}
    	
    }
    /**
     * Ruokalistan luominen
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ruoka ruoka = null;
		
		
		List<Ruoka> tulos = Kayttaja.tietokanta.readRuokaKategoria(ruoka, "pizza");
    	
		lista.addAll(tulos);
		
		final ObservableList<Ruoka> ruokaLista = FXCollections.observableArrayList(lista);
		
		
		
			nimiSarake.setCellValueFactory(new PropertyValueFactory<Ruoka,String>("nimi"));
			kuvausSarake.setCellValueFactory(new PropertyValueFactory<Ruoka,String>("kuvaus"));
			hintaSarake.setCellValueFactory(new PropertyValueFactory<Ruoka,Double>("hinta"));
			taulukko.setItems(ruokaLista);
			
			

		}
}
