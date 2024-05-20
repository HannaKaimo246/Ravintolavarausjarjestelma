package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Kayttaja;
import model.Tyontekija;
import model.Varaus;
import view.App;

public class VarausHallintaController implements Initializable {
	
	

    @FXML
    private Button takaisinBtn;
    
    @FXML
    private TableView<Varaus> varausHallintaTaulukko;
    
    @FXML
    private TableColumn<Varaus, LocalDate> paivamaaraSarake;
    
    @FXML
    private TableColumn<Varaus, LocalDate> alkamisaikaSarake;

    @FXML
    private TableColumn<Varaus, LocalDate> paattymisaikaSarake;

    @FXML
    private TableColumn<Varaus, Integer> henkilomaarasarake;

    @FXML
    private TableColumn<Varaus, String> lisatiedotsarake;
    
    @FXML
    private TableColumn<Varaus, String> varaajannimisarake;
    
    @FXML
    private Label dateLabel;
    

    @FXML
    private Button muokkaaBtn;

    @FXML
    private Button poistaBtn;
    
    
    private List<Varaus> list = new ArrayList<>();
    
    
    private Varaus valittuVaraus = null;
    
	
    @FXML
    private void takaisin() throws IOException {
        App.setRoot("tyontekijaNakyma");
    }
    /**
     * Varauksen luonti
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    		updateTaulukko();
		 }

  /**
     * 
     * FXML loader kutsuu tätä metodia, kun varausta halutaan muokata
     * @throws IOException
     */
    public void muokkaaBtnClicked() throws IOException {
    	if(valittuVaraus != null) {
	    	ResourceBundle bundle = App.getResourceBundle();
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/varausMuokkaus.fxml"), bundle);
	        //fxmlLoader.setLocation(getClass().getResource("/view/varausMuokkaus.fxml"));
	        BorderPane borderPane = fxmlLoader.load();
	        
	        VarausMuokkausController itemController = fxmlLoader.getController();
	        itemController.setVarausData(valittuVaraus);
	        
	        Scene scene = new Scene(borderPane);
	        Stage stage = (Stage) varausHallintaTaulukko.getScene().getWindow();
	        stage.setScene(scene);
    	}
    	
    }
    
    
    /**
     * Varauksen poisto
     */
    public void poistaBtnClicked() {
    	if(valittuVaraus != null) {
    		Kayttaja.getInstance().poistaVaraus(valittuVaraus);
    		updateTaulukko();
    	}
    }
    
    private void updateTaulukko() {
		Varaus varaus = null;
				
				List<Varaus> tulos = Kayttaja.tietokanta.readVaraukset(varaus);
				
					//kirjautuneena työntekijänä, näytetään kaikki varaukset
					
					    	varausHallintaTaulukko.getItems().clear();
					    	
					    	list = new ArrayList<>();
							list.addAll(tulos);
						
							final ObservableList<Varaus> varauslista = FXCollections.observableArrayList(list);
							
							paivamaaraSarake.setCellValueFactory(new PropertyValueFactory<Varaus,LocalDate>("paiva"));
							alkamisaikaSarake.setCellValueFactory(new PropertyValueFactory<Varaus,LocalDate>("alkamisAjankohta"));
							paattymisaikaSarake.setCellValueFactory(new PropertyValueFactory<Varaus,LocalDate>("paattymisAjankohta"));
							henkilomaarasarake.setCellValueFactory(new PropertyValueFactory<Varaus,Integer>("henkiloMaara"));
							lisatiedotsarake.setCellValueFactory(new PropertyValueFactory<Varaus,String>("lisaTiedot"));
							varaajannimisarake.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakasID().getEtuNimi() + " " + cellData.getValue().getAsiakasID().getSukuNimi()));
		
						    System.out.println("Taulukko" + varauslista);
		
						    varausHallintaTaulukko.setItems(varauslista);
						    
						    varausHallintaTaulukko.setOnMouseClicked((MouseEvent event) -> {
						        if (event.getButton().equals(MouseButton.PRIMARY)) {
						            int index = varausHallintaTaulukko.getSelectionModel().getSelectedIndex();
						            valittuVaraus = varausHallintaTaulukko.getItems().get(index);
						            System.out.println(valittuVaraus);
						        }
						    });

    }
}



