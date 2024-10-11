package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Varaus;
import model.Asiakas;
import model.DataValidation;
import model.Kayttaja;
import view.App;

public class VarausMuokkausController {
	
	 private ObservableList<Integer> maara = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
	 private ObservableList<LocalTime> alku = FXCollections.observableArrayList(LocalTime.of(10, 00),LocalTime.of(10,30),LocalTime.of(11,00), LocalTime.of(11,30), LocalTime.of(12, 00),LocalTime.of(12, 30),LocalTime.of(13, 00),LocalTime.of(13, 30),LocalTime.of(14,00),LocalTime.of(14, 30),LocalTime.of(15, 00),LocalTime.of(15, 30),LocalTime.of(16, 00),LocalTime.of(16, 30),LocalTime.of(17, 00),LocalTime.of(17, 30),LocalTime.of(18, 00),LocalTime.of(18, 30),LocalTime.of(19,00), LocalTime.of(19,30),LocalTime.of(20, 00),LocalTime.of(20, 30),LocalTime.of(21, 00),LocalTime.of(21, 30));
	 private ObservableList<LocalTime> loppu = FXCollections.observableArrayList(LocalTime.of(10,30),LocalTime.of(11,00), LocalTime.of(11,30), LocalTime.of(12, 00),LocalTime.of(12, 30),LocalTime.of(13, 00),LocalTime.of(13, 30),LocalTime.of(14,00),LocalTime.of(14, 30),LocalTime.of(15, 00),LocalTime.of(15, 30),LocalTime.of(16, 00),LocalTime.of(16, 30),LocalTime.of(17, 00),LocalTime.of(17, 30),LocalTime.of(18, 00), LocalTime.of(18, 30),LocalTime.of(19,00), LocalTime.of(19,30),LocalTime.of(20, 00),LocalTime.of(20, 30),LocalTime.of(21, 00),LocalTime.of(21, 30), LocalTime.of(22, 00));
  @FXML
  private Button takaisin;

  
  @FXML
  private Button editBtn;

  @FXML
  private DatePicker dateID;

  @FXML
  private ChoiceBox<LocalTime> aloitusaika;
  
  @FXML
  private ChoiceBox<LocalTime> lopetusaika;
  
  @FXML
  private Label dateLabel;

  @FXML
  private Label startLabel;

  @FXML
  private Label endLabel;

  @FXML
  private Label peopleLabel;
  
  @FXML
  private Label tiedot;
  
  @FXML
  private TextArea lisatiedot;

  @FXML
  private ChoiceBox<Integer> henkilomaara;
  

  @FXML
  private Button varaaNappula;
  
  int varausID;
  
  Asiakas varausAsiakas;
  
  @FXML
  private void initialize() {
	aloitusaika.setItems(alku);
	lopetusaika.setItems(loppu);
	henkilomaara.setItems(maara);
		
	  
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
  private void takaisin() throws IOException {
	  	System.out.println("takaisin.");
	  	Stage stage = (Stage) takaisin.getScene().getWindow();
	  	stage.setScene(App.getScene());
	    App.setRoot("kayttajanNakyma");
  }
  /**
   * Varauksen tekeminen
   */
  @FXML
  public void editBtnClicked() {
	   	boolean date = DataValidation.datePickerIsNull(dateID, dateLabel, "Päivämäärä vaaditaan");
		
	   	boolean start = DataValidation.choiceBoxIsNull(aloitusaika, startLabel, "Aloitusaika vaaditaan");
	   	
	   	boolean end = DataValidation.choiceBoxIsNull(lopetusaika, endLabel, "Lopetusaika vaaditaan");
	   	
	   	boolean people = DataValidation.choiceBoxIsNull(henkilomaara, peopleLabel, "Henkilöiden määrä vaaditaan");
	   	
	   	
	   	// yhteys kayttaja-luokkaan
	   	Kayttaja kayttaja = Kayttaja.getInstance();
	   	
	   	LocalDate paiva = dateID.getValue();
	   	
	   	LocalTime alku = aloitusaika.getSelectionModel().getSelectedItem();
	
	   	LocalTime loppu = lopetusaika.getSelectionModel().getSelectedItem();
	   	
	   	int maara = henkilomaara.getSelectionModel().getSelectedItem();
	   
	   	String tiedot = lisatiedot.getText();
	  
	   	if (loppu.isAfter(alku)) {
	   		kayttaja.editVaraus(varausID, paiva, alku, loppu, maara,tiedot, varausAsiakas, null, null);
	   }else {
		   	System.out.println("varaus ei onnistunut");
		   	startLabel.setText("Tarkista aika!");
		   	endLabel.setText("Tarkista aika!");
	   }
  }
  
  public void setVarausData(Varaus varaus) {
	   aloitusaika.getSelectionModel().select(varaus.getAlkamisAjankohta());
	   lopetusaika.getSelectionModel().select(varaus.getPaattymisAjankohta());
	   dateID.setValue(varaus.getPaiva());
	   henkilomaara.getSelectionModel().select(varaus.getHenkiloMaara()-1);
	   lisatiedot.setText(varaus.getLisaTiedot());
	   varausID = varaus.getVarausID();
	   varausAsiakas = varaus.getAsiakasID();
  }

}
