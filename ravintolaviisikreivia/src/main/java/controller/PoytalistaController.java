package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import model.IKoordinaatit;
import model.Kayttaja;
import model.Koordinaatit;
import model.Poyta;
import view.App;

public class PoytalistaController implements Initializable {

	private ToggleGroup ryhma = new ToggleGroup();

	private IKoordinaatit kuuntelija;

	private int listaKoko = 10;
	
    @FXML
    private TabPane editoripalkit;
	
	@FXML
	private Button takaisin;

	@FXML
	private Button julkaise;

	@FXML
	private TextField poytanimi;

	@FXML
	private ToolBar poydat;

	@FXML
	private AnchorPane poytaEditori;
	
	@FXML
	private TextField maksimipaikat;

	@FXML
	private Pane sisalto;

	private int valittuKohde;

	private byte[] kuva;

	private Kayttaja model;

	private boolean asiakas;
	
	private double skaalaa = 200;
	
	@FXML
	private Tab guide;
	
    @FXML
    private TextArea ohjeteksti;
	
	private void valitsePoyta(Koordinaatit poyta) {

		System.out.println("Poyta:" + poyta + " napsautettu...");
		
		for( Node node: poytaEditori.getChildren()) {

		    if( node instanceof AnchorPane) {
		        System.out.println( node);
		      
		        ((AnchorPane) node).getChildren().get(1).setVisible(false);
		      
		    }

		}
		
		
	}

	// poistetaan poyta
	private void poistaPoyta(Koordinaatit koordinaatti, AnchorPane poyta) {
		
		System.out.println("poisto");
		
		poytaEditori.getChildren().remove(poyta);
		
		model.poistaYksiPoytaListaan(koordinaatti);
		
		listaKoko++;
		
	}
 	
	@FXML
	private void julkaise() {

		System.out.println("viedaan poytalista kantaan...");

		if (model.julkaise()) {
			
			  try {
				  
				  ResourceBundle budle = App.getResourceBundle();
			
				Alert alert = new Alert(AlertType.CONFIRMATION, budle.getString("dialog3"), ButtonType.OK);
				alert.showAndWait();

				  } catch(Exception e) { System.err.println("Kieli resursseja ei löydetty!");
				  System.exit(0); }
			
		}

	}

	@FXML
	private void takaisin() throws IOException {
		
		if (asiakas) {
			App.setRoot("varaus");
		} else {
			App.setRoot("tyontekijaNakyma");
		}
		
	}
	
	private void sortList(List<Poyta> list) {
	        Collections.sort(list, new Comparator<Poyta>() {
	            public int compare(Poyta ideaVal1, Poyta ideaVal2) {
	                // avoiding NullPointerException in case name is null
	                Integer idea1 = new Integer(ideaVal1.getPoytaID());
	                Integer idea2 = new Integer(ideaVal2.getPoytaID());
	                return idea2.compareTo(idea1);
	            }
	        });
	}

	@FXML
	private void LuoPoyta(ActionEvent event) {

		try 
		{ 
		
		  ResourceBundle budle = App.getResourceBundle();
				
		   if (poytanimi != null && kuva != null && maksimipaikat != null) {
		  
			if (kuva != null && !poytanimi.getText().equals("") && (!maksimipaikat.getText().isEmpty())) {
				model.luoPoyta(kuva, poytanimi.getText(), Integer.parseInt(maksimipaikat.getText()));

				poytanimi.setText(null);
				maksimipaikat.setText(null);
				
				poydat.getItems().clear();
				
				model.nollaaPainikkeet();
				
				model.lisaaKaikkiPainikkeetPoytaEditoriin();

				List<Poyta> lista = model.tuoPainikkeet();
				
				sortList(lista);
				
			
				for (int i = 0; i < lista.size(); i++) {

					naytaPoytaNappulat(i);

				}
				
			} else {
				Alert alert = new Alert(AlertType.ERROR, budle.getString("dialog2"), ButtonType.OK);
				alert.showAndWait();
			}
				
		   } else {
				Alert alert = new Alert(AlertType.ERROR, budle.getString("dialog2"), ButtonType.OK);
				alert.showAndWait();
		   }
		
		}  
		catch (NumberFormatException e)  
		{ 
			System.out.println("not a valid integer"); 
		} 
	}

	@FXML
	private void tuoProjektiTietokanta() {

		System.out.println("Tuodaan projekti tietokannasta...");

		nollaaEditori();

		model.lisaaKaikkiPoytaListaan();

		model.naytaPoytalista();

	}

	@FXML
	void avaaTiedosto(ActionEvent event) {

		kuva = App.lataaKuva();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		  ohjeteksti.setVisible(false);
		
		  guide.selectedProperty().addListener((observable, oldValue, newValue) -> {
			  
		        if (newValue) {
		          
		        	System.out.println("guide on");
		        	
		        	ohjeteksti.setVisible(true);
		        	
		        	for( Node node: poytaEditori.getChildren()) {

		    		    if( node instanceof AnchorPane) {
		    		        System.out.println( node);
		    		      
		    		        ((AnchorPane) node).setVisible(false);
		    		    
		    		    }

		    		}
		        	
		        }
		        
		        else if (oldValue) {
			          
		        	System.out.println("guide off");
		        	
		        	ohjeteksti.setVisible(false);
		        	
		           	for( Node node: poytaEditori.getChildren()) {

		    		    if( node instanceof AnchorPane) {
		    		        System.out.println( node);
		    		      
		    		        ((AnchorPane) node).setVisible(true);
		    		    
		    		    }

		    		}
		        	
		        }
		        
		    });
	
		// force the field to be numeric only
		maksimipaikat.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (newValue != null) {
		        if (!newValue.matches("\\d*")) {
		        	maksimipaikat.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        
		    	}
		    }
		});
		
		model = Kayttaja.getInstance();

		model.alustaPoytalista();

		model.alustaPoytaEditoriPainikkeet();
		
		model.alustaPoytalistaKontrolleri(this);

		kuuntelija();
		
		if (model.onkoAsiakasSessio()) {
			
			asiakas = true;
			
			editoripalkit.setVisible(false);
			
			julkaise.setVisible(false);
			
			tuoProjektiTietokanta();
			
		} else {
			
			asiakas = false;
			tuoProjektiTietokanta();
			
		}
	
		sisalto.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				for (int i = 0; i < model.PoytaEditoriPainikkeetListaKoko(); i++) {
				
					if (valittuKohde == model.valitsePoyta(i).getPoytaID() && model.poytaListaKoko() <= listaKoko) {

						Koordinaatit koordinaatti = new Koordinaatit();

						koordinaatti.setKoordinaattiY(event.getSceneY());

						koordinaatti.setKoordinaattiX(event.getSceneX());

						koordinaatti.setKoko(skaalaa);
						
						koordinaatti.setPoyta(model.valitsePoyta(i));
						
						model.lisaaYksiPoytaListaan(koordinaatti);

						naytaPoyta();

					}

				}

			}
		});

		model.lisaaKaikkiPainikkeetPoytaEditoriin();

		List<Poyta> lista = model.tuoPainikkeet();
		
		sortList(lista);
		
	
		for (int i = 0; i < lista.size(); i++) {

			naytaPoytaNappulat(i);

		}

	}

	public void nollaaEditori() {

		poytaEditori.getChildren().removeIf(node -> node instanceof AnchorPane);

		model.nollaaPoytalista();

	}

	/**
	 * poydan valitseminen poytaa klikkaamalla
	 * @param arvo
	 */
	private void naytaPoytaNappulat(int arvo) {

		Poyta poyta = model.valitsePoyta(arvo);
		
		System.out.println("type: " + poyta.getPoydanTyyppi());

		ToggleButton nappi = new ToggleButton(poyta.getPoydanTyyppi());

		String tunnus = String.valueOf(poyta.getPoytaID());

		nappi.setId(tunnus);

		nappi.setToggleGroup(ryhma);

		nappi.setPrefWidth(134);

		nappi.setPrefHeight(48);

		nappi.setOnMouseClicked(event -> {

			if (nappi.isSelected() && event.getSource() instanceof ToggleButton) {

				ToggleButton nappula = (ToggleButton) event.getSource();
				
				if (nappula.getId() != null)
					valittuKohde = Integer.parseInt(nappula.getId());

			} else {
				valittuKohde = -1;
			}

		});

		poydat.getItems().add(nappi);

	}

	public void naytaPoyta() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(App.class.getResource("poyta.fxml"));
			AnchorPane anchorPane = fxmlLoader.load();
	
			Koordinaatit poyta = model.getPoytalistaIndeksi();

			PoytaController poytakontrolleri = fxmlLoader.getController();
			poytakontrolleri.setData(poyta, kuuntelija, asiakas);

			anchorPane.setLayoutX(poyta.getKoordinaattiX());
			
			anchorPane.setLayoutY(poyta.getKoordinaattiY());
			
			poytaEditori.getChildren().add(anchorPane);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void paivitaS(double koko) {
		
		this.skaalaa = koko;
		
	}

	private void kuuntelija() {

		kuuntelija = new IKoordinaatit() {
			public void onClickListener(Koordinaatit poyta) {
				valitsePoyta(poyta);
				
			}

			public void poisto(Koordinaatit koordinaatti, AnchorPane poyta) {
				poistaPoyta(koordinaatti, poyta);
			}

			public void paivitaSkaalaus(double koko) {
				
				paivitaS(koko);
				
			}

		};
		
	}

	public boolean ilmoitus() {
		
		  try {
		  
		  ResourceBundle budle = App.getResourceBundle();
	
		Alert alert = new Alert(AlertType.CONFIRMATION, budle.getString("dialog"), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		} 
		
		  } catch(Exception e) { System.err.println("Kieli resursseja ei löydetty!");
		  System.exit(0); }
		  
		  
		  
		  
		return false;
	
	}
	
	
}
