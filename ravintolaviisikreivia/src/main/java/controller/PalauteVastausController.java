package controller;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.IPalaute;
import model.Kayttaja;
import model.Palaute;
import model.Tyontekija;
import view.App;

public class PalauteVastausController {
	@FXML
	private Label nimi;

	@FXML
	private Label numero;

	@FXML
	private Label aihe;

	@FXML
	private TextArea teksti;

	@FXML
	private TextArea vastaus;

	@FXML
	private Button vastausnappi;

	@FXML
	private Label kirjoitus;

	@FXML
	private Pane sisalto;

	@FXML
	private Label lukumaara;

	@FXML
	private Label vastaaja;

	@FXML
	private Button tiedot;

    @FXML
    private Label vastattu;
	
	boolean asiakas;

	private Palaute palaute;

	@FXML
	public void avaaTiedot() {

		myListener.onClickListener(palaute);

	}

	private IPalaute myListener;

	public void setData(Palaute palaute, IPalaute myListener) {
		this.palaute = palaute;
		this.myListener = myListener;

		aihe.setText(palaute.getAihe());
		nimi.setText(palaute.getNimiMerkki());
		teksti.setText(palaute.getViesti());

		numero.setText(String.valueOf(palaute.getTunnus()));

		if (palaute.getVastaus() != null) {

			String aihetulos = palaute.getVastaus().getAihe();

			String vastaustulos = palaute.getViesti();

			String nimitulos = palaute.getVastaus().getAsiakas().getAsiakasNimimerkki();

			String tekstitulos = palaute.getVastaus().getViesti();

			vastaus.setText(vastaustulos);

			aihe.setText(aihetulos);

			nimi.setText(nimitulos);

			teksti.setText(tekstitulos);

			vastaaja.setText(palaute.getTyontekija().getTyontekijaNimiMerkki());

			vastaus.setEditable(false);
			sisalto.setVisible(false);

		}

	}

	/**
	 * FXMLLoader kutsuu tätä metodia, kun alustus on valmis Palautteiden alustus
	 */
	@FXML
	void initialize() {

		try {

			ResourceBundle budle = App.getResourceBundle();

			tiedot.setText(budle.getString("info"));

			aihe.setText(budle.getString("subject1"));
			
			nimi.setText(budle.getString("name"));
			
			vastattu.setText(budle.getString("respond"));
			
			vastaaja.setText(budle.getString("is.not"));
			
			vastausnappi.setText(budle.getString("submit.reply"));
			
			kirjoitus.setText(budle.getString("remaining.posts"));

		} catch (Exception e) {
			System.err.println("Kieli resursseja ei löydetty!");
			System.exit(0);

		}

		System.out.println("alustetaan palautteet");
		vastausnappi.setDisable(true);
		Kayttaja kayttaja = Kayttaja.getInstance();
		asiakas = kayttaja.onkoAsiakasSessio();

		vastausnappi.setOnAction((ActionEvent event) -> {

			if (vastaus.getText() != null && !vastaus.getText().trim().isEmpty()) {
				kayttaja.vieVastaus(vastaus.getText(), this.palaute);

				System.out.println(this.palaute.getAsiakas().asiakasID + " id");

				vastaus.setEditable(false);
				sisalto.setVisible(false);

				Tyontekija tyontekija = kayttaja.tuoTyontekija();

				vastaaja.setText(tyontekija.getTyontekijaNimiMerkki());
			}

		});

		if (asiakas) {
			vastaus.setEditable(false);
			sisalto.setVisible(false);

		} else {
			vastaus.setEditable(true);
			sisalto.setVisible(true);

		}

		vastaus.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("textfield changed from " + oldValue + " to " + newValue);

			if (newValue.length() == 501) {
				vastaus.setText(oldValue);
			}

			int maara = 500 - newValue.length();

			System.out.println("määrä " + maara);

			String tulos = String.valueOf(maara);

			if (newValue.length() > 0) {
				vastausnappi.setDisable(false);
			} else {
				vastausnappi.setDisable(true);
			}

			lukumaara.setText(tulos);

		});

	}
}
