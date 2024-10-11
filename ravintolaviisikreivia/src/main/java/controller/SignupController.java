package controller;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.DataValidation;
import model.Kayttaja;
import view.App;

public class SignupController {

	private boolean isRegisterationInfoComplete = false;

	@FXML
	private Text tulos;

	@FXML
	private Button signupBtn;

	@FXML
	private Button takaisin;

	@FXML
	private TextField etuNimi;

	@FXML
	private TextField sukuNimi;

	@FXML
	private TextField puhelinnumero;

	@FXML
	private TextField sahkoposti;

	@FXML
	private TextField tunnusTextField;

	@FXML
	private PasswordField salasana;

	@FXML
	private PasswordField salasanaUudestaan;

	@FXML
	private Label etuNimiLabel;

	@FXML
	private Label sukuNimiLabel;

	@FXML
	private Label puhelinNumeroLabel;

	@FXML
	private Label sahkopostiLabel;

	@FXML
	private Label salasanaLabel;

	@FXML
	private Label reSalasanaLabel;

	@FXML
	private Label tunnusLabel;
	
	 ResourceBundle bundle = App.getResourceBundle();

	@FXML
	private void takaisin() throws IOException {
		System.out.println("takaisin.");
		App.setRoot("home");
	}

	

	/**
	 * Asiakkaan rekisteröinnin tekeminen
	 * 
	 * @param event
	 * @throws IOException
	 * @throws InterruptedException 
	 */
   
   
	@FXML
	void signupButtonClicked(ActionEvent event) throws IOException, InterruptedException {
			
		boolean alphabet = false;
		boolean numeric = false;
		boolean tyypi = false;
		boolean tunnusPituus = false;
		boolean pituus = false;
		boolean pwsMuoto = false;
		boolean repws = false;

		boolean etunimi = DataValidation.textFieldIsNull(etuNimi, etuNimiLabel, bundle.getString("write.your.firstname1"));
		if (etunimi == false) {
			alphabet = DataValidation.textAlphabet(etuNimi, etuNimiLabel, bundle.getString("alphabets"));
		}

		boolean sukunimi = DataValidation.textFieldIsNull(sukuNimi, sukuNimiLabel, bundle.getString("write.your.firstname1"));
		if (sukunimi == false) {
			alphabet = DataValidation.textAlphabet(sukuNimi, sukuNimiLabel,
					bundle.getString("alphabets"));
		}

		boolean pNumero = DataValidation.textFieldIsNull(puhelinnumero, puhelinNumeroLabel, bundle.getString("phone.number"));
		if (pNumero == false) {
			numeric = DataValidation.textNumeric(puhelinnumero, puhelinNumeroLabel, bundle.getString("numbers"));
		}

		boolean sPosti = DataValidation.textFieldIsNull(sahkoposti, sahkopostiLabel, bundle.getString("email.address"));
		if (sPosti == false) {
			tyypi = DataValidation.emailFormat(sahkoposti, sahkopostiLabel,
					 bundle.getString("email.format"));
		}

		boolean tunnus = DataValidation.textFieldIsNull(tunnusTextField, tunnusLabel, bundle.getString("username1"));
		if (tunnus == false) {
			pituus = DataValidation.dataLength(tunnusTextField, tunnusLabel, bundle.getString("username.length"), 5);
		}

		boolean pws = DataValidation.textFieldIsNull(salasana, salasanaLabel, bundle.getString("password"));
		if (pws == false) {

			pituus = DataValidation.dataLength(salasana, salasanaLabel, bundle.getString("password.length"), 8);

		}
		if (!salasanaUudestaan.getText().equals(salasana.getText())) {
		
			reSalasanaLabel.setText(bundle.getString("password.confirm1"));
		}
		else {
			reSalasanaLabel.setText("");
		if (etunimi == false && sukunimi == false && alphabet == true && pNumero == false
				&& numeric == true & sPosti == false && tyypi == true && tunnus == false && pituus == true
				&& pws == false) {
			Kayttaja kayttaja = Kayttaja.getInstance();
			kayttaja.luoAsiakas(etuNimi.getText(), sukuNimi.getText(), puhelinnumero.getText(), sahkoposti.getText(),
					salasana.getText(), salasanaUudestaan.getText(), tunnusTextField.getText());

			etuNimi.clear();
			sukuNimi.clear();
			sahkoposti.clear();
			puhelinnumero.clear();
			salasana.clear();
			salasanaUudestaan.clear();
			tunnusTextField.clear();
			
			tulos.setText(bundle.getString("result"));
			
			//TimeUnit.SECONDS.sleep(2);
			
			//App.setRoot("home");
			
			
		}

		}

	}
	
}
