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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataValidation;
import model.Kayttaja;
import model.Ruoka;
import model.Tyontekija;
import model.Varaus;
import view.App;

public class TyontekijaHallintaController implements Initializable {

	@FXML
	private Button takaisinBtn;

	@FXML
	private TableView<Tyontekija> tyontekijaTaulukko;

	@FXML
	private TableColumn<Tyontekija, Integer> idSarake;

	@FXML
	private TableColumn<Tyontekija, String> nimiMerkkiSarake;

	@FXML
	private TableColumn<Tyontekija, String> rooliSarake;

	@FXML
	private TextField idTextField;

	@FXML
	private TextField nimimerkkiTextField;

	@FXML
	private TextField rooliTextField;

	@FXML
	private PasswordField salasanaTextField;

	@FXML
	private PasswordField resalasanaTextField;

	@FXML
	private Label nimimerkkiLabel;

	@FXML
	private Label rooliLabel;

	@FXML
	private Label salasanaLabel;

	@FXML
	private Label resalasanaLabel;

	@FXML
	private Button lisaaBtn;

	@FXML
	private Button muokkaBtn;

	@FXML
	private Button poistaBtn;

	@FXML
	private Label tyontekijaVahvistus;

	ResourceBundle bundle = App.getResourceBundle();

	Kayttaja kayttaja = Kayttaja.getInstance();

	private List<Tyontekija> lista = new ArrayList<>();

	private Tyontekija valittuTyontekija = null;

	/**
	 * Työntekijän luominen
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void lisaaBtn(ActionEvent event) throws IOException {

		boolean alphabet = false;
		boolean salasanaPituus = false;

		boolean nimi = DataValidation.textFieldIsNull(nimimerkkiTextField, nimimerkkiLabel,
				bundle.getString("employee.username"));

		boolean rooli = DataValidation.textFieldIsNull(rooliTextField, rooliLabel, bundle.getString("employee.role"));
		if (rooli == false) {
			alphabet = DataValidation.textAlphabet(rooliTextField, rooliLabel, bundle.getString("alphabets"));
		}

		boolean pws = DataValidation.textFieldIsNull(salasanaTextField, salasanaLabel,
				bundle.getString("employee.password"));
		if (pws == false) {
			salasanaPituus = DataValidation.dataLength(salasanaTextField, salasanaLabel,
					bundle.getString("password.length"), 8);
		}

		boolean repws = DataValidation.textFieldIsNull(resalasanaTextField, resalasanaLabel,
				"Salasanan vahvistus vaaditaan");
		if (!resalasanaTextField.getText().equals(salasanaTextField.getText())) {
			resalasanaLabel.setText("Väärä salasana");
		}

		if (nimi == false && rooli == false && pws == false && alphabet == true && salasanaPituus == true
				&& repws == false) {
			// yhteys kayttaja luokkaan

			kayttaja.luoTyontekija(rooliTextField.getText(), salasanaTextField.getText(),
					nimimerkkiTextField.getText());

			nimimerkkiTextField.clear();
			salasanaTextField.clear();
			resalasanaTextField.clear();
			rooliTextField.clear();

			tyontekijaVahvistus.setText(bundle.getString("employee.result"));

			
		}

	}

	/**
	 * Työntekijöiden listaaminen
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tyontekijaTaulukko.getItems().clear();

		Tyontekija tyontekija = null;

		List<Tyontekija> tulos = Kayttaja.tietokanta.haeTyontekijat(tyontekija);

		lista.addAll(tulos);

		final ObservableList<Tyontekija> tyontekijalista = FXCollections.observableArrayList(lista);

		idSarake.setCellValueFactory(new PropertyValueFactory<Tyontekija, Integer>("tyontekijaID"));

		nimiMerkkiSarake.setCellValueFactory(new PropertyValueFactory<Tyontekija, String>("tyontekijaNimiMerkki"));

		rooliSarake.setCellValueFactory(new PropertyValueFactory<Tyontekija, String>("rooli"));

		tyontekijaTaulukko.setItems(tyontekijalista);


		// Listen for selection changes and show the employee's details when changed.

		tyontekijaTaulukko.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> idTextField.setText(String.valueOf(idSarake.getCellData(newValue))));

		tyontekijaTaulukko.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> nimimerkkiTextField.setText(nimiMerkkiSarake.getCellData(newValue)));

		tyontekijaTaulukko.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> rooliTextField.setText(rooliSarake.getCellData(newValue)));
		
		

	}


	/**
	 * Työntekijän tietojen muokkaus
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void muokkaBtn(ActionEvent event) throws IOException {

		if (idTextField.getText().isEmpty() && nimimerkkiTextField.getText().isEmpty()
				&& rooliTextField.getText().isEmpty()) {
			tyontekijaVahvistus.setText(bundle.getString("employee.result2"));
		} else {

			Tyontekija tyontekija = new Tyontekija(Integer.valueOf(String.valueOf(idTextField.getText())),
					String.valueOf(rooliTextField.getText()), String.valueOf(salasanaTextField.getText()),
					String.valueOf(nimimerkkiTextField.getText()));
			kayttaja.tietokanta.updateEmployee(tyontekija);

			idTextField.clear();
			nimimerkkiTextField.clear();
			rooliTextField.clear();

			tyontekijaVahvistus.setText(bundle.getString("employee.result1"));


		}
	}

	/**
	 * Työntekijän tietojen poitaaminen
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void poitoBtn(ActionEvent event) throws IOException {

		if (idTextField.getText().isEmpty() && nimimerkkiTextField.getText().isEmpty()
				&& rooliTextField.getText().isEmpty()) {
			tyontekijaVahvistus.setText(bundle.getString("employee.result3"));
		} else {

			Tyontekija tyontekija = new Tyontekija(Integer.valueOf(String.valueOf(idTextField.getText())),
					String.valueOf(rooliTextField.getText()), String.valueOf(salasanaTextField.getText()),
					String.valueOf(nimimerkkiTextField.getText()));
			kayttaja.tietokanta.deleteEmployee(tyontekija);

			idTextField.clear();
			nimimerkkiTextField.clear();
			rooliTextField.clear();

			tyontekijaVahvistus.setText(bundle.getString("employee.result3"));


		}
	}

	/**
	 * Siirtyminen takaisin käyttäjän näkymään
	 * 
	 * @throws IOException
	 */
	@FXML
	private void takaisin() throws IOException {
		System.out.println("takaisin.");
		App.setRoot("tyontekijaNakyma");

	}

}
