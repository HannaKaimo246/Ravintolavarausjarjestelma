package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.IPalaute;
import model.Kayttaja;
import model.Palaute;
import view.App;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PalauteSivuController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    @FXML
    private Label luotuaika;

    @FXML
    private Label luotuvastaus;

    @FXML
    private Label etunimi;

    @FXML
    private Button suljetiedot;
    
    @FXML
    private Label sukunimi;

    @FXML
    private Text palautteentiedot;
    
    @FXML
    private Label sahkoposti;
    
    @FXML
    private Label luotup;
    
    @FXML
    private Label puhelinnumero;
    
    @FXML
    private Pane tiedot;

    private List<Palaute> lista = new ArrayList<>();
    private IPalaute kuuntelija;

    private Kayttaja kayttaja;
 
    @FXML
    public void suljeTiedot() {
    	tiedot.setVisible(false);
    }
    /**
     * valitaan palaute, jota halutaan tarkastella
     * @param palaute palaute
     */
    private void valitsePalaute(Palaute palaute) {
 
    	tiedot.setVisible(true);
    	
    	luotuaika.setText(String.valueOf(palaute.getAika()));
    	
    	
    	if (palaute.getVastaus() != null) {
    		luotuvastaus.setText(String.valueOf(palaute.getVastaus().getAika()));
    	} 
    	
    	etunimi.setText(palaute.getAsiakas().getEtuNimi());
    	
    	sukunimi.setText(palaute.getAsiakas().getSukuNimi());
    	
    	sahkoposti.setText(palaute.getAsiakas().getSahkopostiOsoite());
    	
    	puhelinnumero.setText(palaute.getAsiakas().getPuhelinNumero());
    
    }
    
    @FXML
    private void palautesivu() throws IOException {
    	
    	if (kayttaja.tuoTyontekija() != null) {
    		App.setRoot("tyontekijaNakyma");
    	} else {
    		App.setRoot("palaute");
    	}
    }

    /**
     * näytetään palautteet
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
		try {

			ResourceBundle budle = App.getResourceBundle();

			luotuvastaus.setText(budle.getString("created.respond2"));

		

		} catch (Exception e) {
			System.err.println("Kieli resursseja ei löydetty!");
			System.exit(0);

		}
    	
    	
    	kayttaja = Kayttaja.getInstance();
    	
    	kayttaja.alustaPalauteSivuKontrolleri(this);
    
    	List<Palaute> data = kayttaja.getPalautteet();
    	
    	lista.addAll(data);
        if (lista.size() > 0) {
        	kuuntelija = new IPalaute() {
                public void onClickListener(Palaute palaute) {
                	valitsePalaute(palaute);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lista.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("palautevastaus.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PalauteVastausController itemController = fxmlLoader.getController();
                itemController.setData(lista.get(i),kuuntelija);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
