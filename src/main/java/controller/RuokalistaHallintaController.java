package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataValidation;
import model.Kayttaja;
import model.Ruoka;
import model.Tyontekija;
import view.App;

public class RuokalistaHallintaController implements Initializable {

	@FXML
	private Label hampurilaiset;

	@FXML
	private Label kebabit;

	@FXML
	private Label kala;

	@FXML
	private Label juomat;

	@FXML
	private Label pizzat;

	@FXML
	private Button takaisin;

	@FXML
	private TableView<Ruoka> ruokaTaulukko;
	
	@FXML
	private TableColumn<Ruoka, Integer> ruokaId;

	@FXML
	private TableColumn<Ruoka, String> nimi;

	@FXML
	private TableColumn<Ruoka, String> kuvaus;

	@FXML
	private TableColumn<Ruoka, Double> hinta;
	
	 @FXML
	 private TableColumn<Ruoka, String> Kategorit;
	 
	 @FXML
	 private TextField idTextField;

	@FXML
	private TextField nimiTextField;

	@FXML
	private TextField kuvausTextField;

	@FXML
	private TextField hintaTextField;

	@FXML
	private TextField kategoiaTextField;

	@FXML
	private Button lisaaBtn;

	@FXML
	private Button poistaBtn;

	@FXML
	private Button muokkaBtn;

	@FXML
	private Label ruokanimiLable;

	@FXML
	private Label ruokakuvausLable;

	@FXML
	private Label ruokahintaLabel;

	@FXML
	private Label ruokakategoriaLabel;

	@FXML
	private Label tulosLabel;
	
	ResourceBundle bundle = App.getResourceBundle();
	
	Kayttaja kayttaja = Kayttaja.getInstance();
	

	@FXML
	private void takaisin() throws IOException {
		App.setRoot("tyontekijaNakyma");
	}

	Ruoka ruoka = null;

	/**
	 * ruokien kategorisointi ruokalistassa(ArrayList)
	 * 
	 * @param <S>
	 * @param <T>
	 */

	public static <S, T> void updateElement(TableColumn<S, T> columnNimi, String kenttaNimi) {
		columnNimi.setCellValueFactory(new PropertyValueFactory<S, T>(kenttaNimi));
	};

	public ObservableList<Ruoka> updateAll(String kategoriNimi) {
		List<Ruoka> lista = new ArrayList<>();
		List<Ruoka> tulos = kayttaja.tietokanta.readRuokaKategoria(ruoka, kategoriNimi);
		lista.addAll(tulos);

		updateElement(ruokaId, "ruokaID");
		updateElement(nimi, "nimi");
		updateElement(kuvaus, "kuvaus");
		updateElement(hinta, "hinta");
		updateElement(Kategorit, "kategoria");
		idTextField.clear();
	   	hintaTextField.clear();
		return FXCollections.observableArrayList(lista);
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// By default
		ruokaTaulukko.setItems(updateAll("pizza"));

		pizzat.setOnMouseClicked(e -> {
			ruokaTaulukko.setItems(updateAll("pizza"));
		});

		kebabit.setOnMouseClicked(e -> {
			ruokaTaulukko.setItems(updateAll("kebab"));
		});

		hampurilaiset.setOnMouseClicked(e -> {
			ruokaTaulukko.setItems(updateAll("hampurilainen"));
		});

		kala.setOnMouseClicked(e -> {
			ruokaTaulukko.setItems(updateAll("kala"));
		});

		juomat.setOnMouseClicked(e -> {
			ruokaTaulukko.setItems(updateAll("juoma"));
		});
		


		
	   // Listen for selection changes and show the meal's details when changed.
		
		ruokaTaulukko.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> idTextField.setText(String.valueOf(ruokaId.getCellData(newValue)))); 
	            
		ruokaTaulukko.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> nimiTextField.setText(nimi.getCellData(newValue)));
		 
		ruokaTaulukko.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> kuvausTextField.setText(kuvaus.getCellData(newValue)));
		
		ruokaTaulukko.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> hintaTextField.setText(String.valueOf(hinta.getCellData(newValue))));
		
		ruokaTaulukko.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> kategoiaTextField.setText(Kategorit.getCellData(newValue)));


	}

	
	/**
     * Aterian poistaminen
     * @param event
     * @throws IOException
     */
    @FXML
   	private void poistaBtn(ActionEvent event) throws IOException {
		
    	if(nimiTextField.getText().isEmpty() && kuvausTextField.getText().isEmpty() && kategoiaTextField.getText().isEmpty() && hintaTextField.getText().isEmpty()) {
    		tulosLabel.setText(bundle.getString("result5"));
    	} else {
    	
    	Ruoka ruokalista = new Ruoka(Integer.valueOf(String.valueOf(idTextField.getText())),String.valueOf(nimiTextField.getText()), String.valueOf(kuvausTextField.getText()) , String.valueOf(kategoiaTextField.getText()), Double.parseDouble(hintaTextField.getText()));
    	kayttaja.tietokanta.deleteAteria(ruokalista);
    	
    		idTextField.clear();
    		nimiTextField.clear();
	   	 	kuvausTextField.clear();
	   	 	kategoiaTextField.clear();
	   	 	hintaTextField.clear();
    	
    	tulosLabel.setText(bundle.getString("result2"));
    	}
    }
    
    
    /**
     * Aterian muokkaus
     * @param event
     * @throws IOException
     */
    @FXML
   	private void muokkaBtn(ActionEvent event) throws IOException {
    	
    	if(nimiTextField.getText().isEmpty() && kuvausTextField.getText().isEmpty() && kategoiaTextField.getText().isEmpty() && hintaTextField.getText().isEmpty()) {
    		tulosLabel.setText(bundle.getString("result4"));
    	} else {
    	
    	Ruoka ruokalista = new Ruoka(Integer.valueOf(String.valueOf(idTextField.getText())),String.valueOf(nimiTextField.getText()), String.valueOf(kuvausTextField.getText()) , String.valueOf(kategoiaTextField.getText()), Double.parseDouble(hintaTextField.getText()));
    	kayttaja.tietokanta.updateMeal(ruokalista);
    	
   
    	
    	idTextField.clear();
		nimiTextField.clear();
   	 	kuvausTextField.clear();
   	 	kategoiaTextField.clear();
   	 	hintaTextField.clear();
    	
    	tulosLabel.setText(bundle.getString("result3"));
    	}
    }
		
	
	/**
     * Aterian luominen
     * @param event
     * @throws IOException
     */
    @FXML
   	private void lisaaBtn(ActionEvent event) throws IOException {

    	boolean alphabet = false;
    	
    	boolean nimi = DataValidation.textFieldIsNull(nimiTextField, ruokanimiLable, bundle.getString("food.name.validate"));
    	
    	boolean kuvaus = DataValidation.textFieldIsNull(kuvausTextField, ruokakuvausLable, bundle.getString("food.description.validate"));
    	
    	boolean hinta = DataValidation.textFieldIsNull(hintaTextField, ruokahintaLabel, bundle.getString("food.price.validate"));
   	 	
   	 	boolean kategoria = DataValidation.textFieldIsNull(kategoiaTextField, ruokakategoriaLabel, bundle.getString("food.category.validate"));
   	 	if(kategoria == false) {
   	 			alphabet = DataValidation.textAlphabet(kategoiaTextField, ruokakategoriaLabel, bundle.getString("food.category.alphabet"));
   	 	}
   	 	
   	 	if(nimi == false && kuvaus == false && hinta == false && alphabet == true && kategoria == false) {
   	 	// yhteys kayttaja luokkaan
   	 		double value = Double.parseDouble(hintaTextField.getText());
    		
   	   	 	kayttaja.luoRuoka(nimiTextField.getText().toUpperCase(), kuvausTextField.getText().toUpperCase(), kategoiaTextField.getText().toUpperCase(), value);
   	    	
   	   	 	idTextField.clear();
   	   	 	hintaTextField.clear();
   	   	 	nimiTextField.clear();
   	   	 	kuvausTextField.clear();
   	   	 	kategoiaTextField.clear();
   	   	 	
   	    	
   	   	 	
   	   	 	tulosLabel.setText(bundle.getString("result1"));
   	 	
   	 	}
    
 
    }
    
   
}
