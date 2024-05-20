package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Asiakas;
import model.Kayttaja;
import model.Ruoka;
import model.Tyontekija;
import view.App;

public class RuokaListaController implements Initializable {

	@FXML
	private Label pizzat;

	@FXML
	private Label hampurilaiset;

	@FXML
	private Label kebabit;

	@FXML
	private Label kala;

	@FXML
	private Label juomat;

	@FXML
	private Button siginin;

	@FXML
	private Button signup;

	@FXML
	private Button takaisin;

	@FXML
	private TableView<Ruoka> ruokaTaulukko;

	@FXML
	private TableColumn<Ruoka, String> nimi;

	@FXML
	private TableColumn<Ruoka, String> kuvaus;

	@FXML
	private TableColumn<Ruoka, Double> hinta;

	Ruoka ruoka = null;


	/**
	 * takaisin- napin toiminta käyttäjän näkymästä
	 * 
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

			App.setRoot("asiakasNakyma");
		}

	}

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
		List<Ruoka> tulos = Kayttaja.tietokanta.readRuokaKategoria(ruoka, kategoriNimi);
		lista.addAll(tulos);

		updateElement(nimi, "nimi");
		updateElement(kuvaus, "kuvaus");
		updateElement(hinta, "hinta");
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

	}

}
