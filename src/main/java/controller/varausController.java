package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.DataValidation;
import model.Kayttaja;
import model.Koordinaatit;
import model.Poyta;
import model.Varaus;
import view.App;

public class varausController {
	    
	 private ObservableList<Integer> maara = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
	 private ObservableList<LocalTime> alku = FXCollections.observableArrayList(LocalTime.of(10, 00),LocalTime.of(10,30),LocalTime.of(11,00), LocalTime.of(11,30), LocalTime.of(12, 00),LocalTime.of(12, 30),LocalTime.of(13, 00),LocalTime.of(13, 30),LocalTime.of(14,00),LocalTime.of(14, 30),LocalTime.of(15, 00),LocalTime.of(15, 30),LocalTime.of(16, 00),LocalTime.of(16, 30),LocalTime.of(17, 00),LocalTime.of(17, 30),LocalTime.of(18, 00),LocalTime.of(18, 30),LocalTime.of(19,00), LocalTime.of(19,30),LocalTime.of(20, 00),LocalTime.of(20, 30),LocalTime.of(21, 00),LocalTime.of(21, 30));
	 private ObservableList<LocalTime> loppu = FXCollections.observableArrayList(LocalTime.of(10,30),LocalTime.of(11,00), LocalTime.of(11,30), LocalTime.of(12, 00),LocalTime.of(12, 30),LocalTime.of(13, 00),LocalTime.of(13, 30),LocalTime.of(14,00),LocalTime.of(14, 30),LocalTime.of(15, 00),LocalTime.of(15, 30),LocalTime.of(16, 00),LocalTime.of(16, 30),LocalTime.of(17, 00),LocalTime.of(17, 30),LocalTime.of(18, 00), LocalTime.of(18, 30),LocalTime.of(19,00), LocalTime.of(19,30),LocalTime.of(20, 00),LocalTime.of(20, 30),LocalTime.of(21, 00),LocalTime.of(21, 30), LocalTime.of(22, 00));
    @FXML
    private Button takaisin;

    private Poyta valittuPoytaTyyppi;
    
    private Koordinaatit valittuPoyta;

    @FXML
    private Text valittu;
    
    @FXML
    private Button reserveBtn;

    @FXML
    private DatePicker dateID;

    @FXML
    private ChoiceBox<LocalTime> aloitusaika;
    
    @FXML
    private ChoiceBox<LocalTime> lopetusaika;
    
    @FXML
    private TextArea lisatiedot;

    @FXML
    private ChoiceBox<Integer> henkilomaara;
    

    @FXML
    private Button varaaNappula;
    
    @FXML
    private Label dateLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label peopleLabel;
    
    @FXML
    private Text tietojaLisaa;
    
    @FXML
    private Label varausVahvistus;
    
    ResourceBundle bundle = App.getResourceBundle();
    
   
    
    @FXML
    private void initialize() {
  	aloitusaika.setItems(alku);
  	lopetusaika.setItems(loppu);
	henkilomaara.setItems(maara); 
	
	Kayttaja kayttaja = Kayttaja.getInstance();
	
	Varaus luonnos = kayttaja.getVarausLuonnos();
	
	if (luonnos != null) {
		
		dateID.setValue(luonnos.getPaiva());	
		
		aloitusaika.setValue(luonnos.getAlkamisAjankohta());
		
		lopetusaika.setValue(luonnos.getPaattymisAjankohta());
		
		henkilomaara.setValue(luonnos.getHenkiloMaara());
		
		
	}

	
    }

 
   
	@FXML
	private void signinBtn() throws IOException {
		App.setRoot("signin");
	}
	
	@FXML
	private void signupBtn() throws IOException {
		App.setRoot("signUp");
	}

	@FXML
	private void valitsePoyta() throws IOException {
		System.out.println("valitse poyta.");
	    App.setRoot("poytalista");  
	    
	    Kayttaja kayttaja = Kayttaja.getInstance();
	 
	    LocalTime aloitusaika2 = null;
	    
	    LocalTime lopetusaika2 = null;
	    
	    int henkilomaara2 = 1;
	    
	    String tieto = null;
	    
	    LocalDate paiva;
	    
	    if (!aloitusaika.getSelectionModel().isEmpty()) aloitusaika2 = aloitusaika.getSelectionModel().getSelectedItem();  else aloitusaika2 = null;
	    
	    if (!lopetusaika.getSelectionModel().isEmpty()) lopetusaika2 = lopetusaika.getSelectionModel().getSelectedItem();  else lopetusaika2 = null;
	    	
	    if (!henkilomaara.getSelectionModel().isEmpty()) henkilomaara2 = henkilomaara.getSelectionModel().getSelectedItem();  else henkilomaara2 = 1;
	    
	    if (!tietojaLisaa.equals(null)) tieto = tietojaLisaa.getText();
	    
	    if (dateID.getValue() != null) paiva = dateID.getValue();  else paiva = null;
	    
	    kayttaja.tallennaVarausLuonnos(paiva, aloitusaika2, lopetusaika2, henkilomaara2, tieto);

	}

    @FXML
    private void takaisin() throws IOException {
    	System.out.println("takaisin.");
        App.setRoot("asiakasNakyma");  
    }
    /**
     * Varauksen tekeminen
     * @param event
     * @throws IOException
     */
    @FXML
    void reserveButtonClicked(ActionEvent event) throws IOException {
    	boolean date = DataValidation.datePickerIsNull(dateID, dateLabel, bundle.getString("date.required"));
    	
    	boolean start = DataValidation.choiceBoxIsNull(aloitusaika, startLabel, bundle.getString("start.time.required"));
    	
    	boolean end = DataValidation.choiceBoxIsNull(lopetusaika, endLabel, bundle.getString("end.time.required"));
    	
    	boolean people = DataValidation.choiceBoxIsNull(henkilomaara, peopleLabel, bundle.getString("end.time.required"));
    	
    	if (date || start || end || people ||  valittuPoyta == null) {
    		return;
    	}
    	
    	// yhteys kayttaja luokkaan
    	Kayttaja kayttaja = Kayttaja.getInstance();
    	
    	LocalDate paiva = dateID.getValue();
    	
    	LocalTime alku = aloitusaika.getSelectionModel().getSelectedItem();

    	LocalTime loppu = lopetusaika.getSelectionModel().getSelectedItem();
    	
    	int maara = henkilomaara.getSelectionModel().getSelectedItem();
    
    	String tiedot = tietojaLisaa.getText();

        if (loppu.isAfter(alku)) {
	    	kayttaja.teeVaraus(paiva, alku, loppu, maara,tiedot, valittuPoytaTyyppi, valittuPoyta);
	    	dateID.getEditor().clear();
	    	aloitusaika.setValue(null);
	    	lopetusaika.setValue(null);
	    	henkilomaara.setValue(null);
	    	
	    	
	    	varausVahvistus.setText(bundle.getString("booking.confirmation"));
    	}else {
	    	System.out.println("varaus ei onnistunut");
	    	startLabel.setText(bundle.getString("check.the.time"));
	    	endLabel.setText(bundle.getString("check.the.time"));
    	}
   
  }
    
   public void setData(Koordinaatit koordinaatit) {
    	
    	valittu.setText(String.valueOf(koordinaatit.getKoordinaattiTunnus()));
    	
    	tietojaLisaa.setText(String.valueOf(koordinaatit.getPoyta().getPoydanTyyppi()));
    	
    	Poyta muunnos = koordinaatit.getPoyta();
    	
    	valittuPoytaTyyppi = muunnos;
    	
    	valittuPoyta = koordinaatit;
    	
    }

 }

