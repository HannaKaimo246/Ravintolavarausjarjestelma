package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.Koordinaatit;
import model.IKoordinaatit;
import view.App;

public class PoytaController {

	@FXML
	private AnchorPane poyta;
	
	private Koordinaatit poydantiedot;

	private IKoordinaatit kuuntelija;
	
    @FXML
    private ImageView kaanna;
    
    @FXML
    private Text poytatyyppi;
    
    @FXML
    private ImageView skaalaus;

    @FXML
    private ImageView poisto;

    @FXML
    private Text poytatunnus;
    
    @FXML
    private BorderPane valikot;
	
	private static final double pikselit = 10;
	private static final double liikkuvuus = 15;

	private boolean asiakas;
	
	boolean salliKaantyminen = false;
	
	boolean saalliSkaalaus = false;
	
	private static final int kaantoNopeus = 5;
	
	private enum Suunta {
		VASEN, OIKEA, ALAS, YLOS
	}

	private double edellinenXsuunta;

	private double edellinenYSijainti;

	private Suunta viimeisinSuunta = null;

	TranslateTransition transition = new TranslateTransition();
	
	private int kaantoakseli;
	
	private double skaalaa;
	
	private static final int skaalaaNopeus = 10;
	
	private ImageView kuva;
	
	private double alkuperainenKoko;
	
	private Bounds naytto;
	

	private String tedot;

	/**
	 * FXMLLoader kutsuu tätä metodia, kun alustus on valmis, tassa toteutetaan poytien kaantaminen
	 */
	@FXML // FXMLLoader kutsuu tätä metodia, kun alustus on valmi
	void initialize() {
		
		kuva = (ImageView) poyta.getChildren().get(0);
	
		kaanna.setVisible(false);
		
		poisto.setVisible(false);
		
		skaalaus.setVisible(false);
		
		// poytien skaalaus
		skaalaus.setOnMousePressed(e -> {
		
		if (e.isSecondaryButtonDown()) {
			
			System.out.println("skaalaus takaisin");
			
			poydantiedot.setKoko(alkuperainenKoko);
			skaalaa = alkuperainenKoko;
			kuva.setFitHeight(poydantiedot.getKoko());
			kuva.setFitWidth(poydantiedot.getKoko());
		}
		
			System.out.println("skaalaus On");
			
			saalliSkaalaus = true; 
		});
		
		
		skaalaus.setOnMouseReleased(e -> {
			
			System.out.println("skaalaus Off");
			
			saalliSkaalaus = false;
			
		});
		
		
		skaalaus.setOnMouseDragged(e -> {
			
			naytto = poyta.localToScene(poyta.getBoundsInLocal());
			
			if (saalliSkaalaus) {
				
				System.out.println("Aloitetaan skaalaamaan...");
				
				double nykyinenX = e.getSceneX();
			
				if (naytto.getMaxX() <= 690 && naytto.getMaxY() <= 545 && viimeisinSuunta != Suunta.OIKEA && nykyinenX - edellinenXsuunta > pikselit && !asiakas && saalliSkaalaus) {
					System.out.print("skaalaa isommaksi" + skaalaa);

					if (skaalaa <= 500) skaalaa = skaalaa + skaalaaNopeus;
					
					kuva.setFitHeight(skaalaa);
					kuva.setFitWidth(skaalaa);

					poydantiedot.setKoko(skaalaa);
					
					edellinenXsuunta = nykyinenX;

				}

				else if (naytto.getMinX() >= 10 && viimeisinSuunta != Suunta.VASEN && nykyinenX - edellinenXsuunta < -pikselit && !asiakas && saalliSkaalaus) {
					System.out.print("skaalaa pienemmaksi" + skaalaa);

					if (skaalaa >= 150) skaalaa = skaalaa - skaalaaNopeus;
					
					kuva.setFitHeight(skaalaa);
					kuva.setFitWidth(skaalaa);
					
					poydantiedot.setKoko(skaalaa);

					edellinenXsuunta = nykyinenX;

				
					
				}
				
				kuuntelija.paivitaSkaalaus(poydantiedot.getKoko());
				
			}
			
		});
		
		
		
		
		// poytien poistaminen
		poisto.setOnMousePressed(e -> {
			
			kuuntelija.poisto(poydantiedot, poyta);
			
		});
		
		// poytien kaantaminen
		kaanna.setOnMousePressed(e -> {
			
			salliKaantyminen = true;
			
			System.out.println("kaanna On");
			
			
		});
		
		kaanna.setOnMouseReleased(e -> {
			
			System.out.println("kaanna Off");
			
			salliKaantyminen = false;
			
		});
	
		
		
		
		kaanna.setOnMouseDragged(e -> {
			
			if (salliKaantyminen) {
				
				System.out.println("Aloitetaan kaantamaan...");
				
				double nykyinenX = e.getSceneX();
			
				if (viimeisinSuunta != Suunta.OIKEA && nykyinenX - edellinenXsuunta > pikselit && !asiakas && salliKaantyminen) {
					System.out.print("kaantyy oikea");

					kaantoakseli = kaantoakseli + kaantoNopeus;
					
					kuva.setRotate(kaantoakseli);

					poydantiedot.setKulma(kaantoakseli);
					
					edellinenXsuunta = nykyinenX;

				}

				else if (viimeisinSuunta != Suunta.VASEN && nykyinenX - edellinenXsuunta < -pikselit && !asiakas && salliKaantyminen) {
					System.out.print("kaantyy vasen");

					kaantoakseli = kaantoakseli - kaantoNopeus;
					
					kuva.setRotate(kaantoakseli);
					
					poydantiedot.setKulma(kaantoakseli);

					edellinenXsuunta = nykyinenX;

				}
				
			}
			
		});
		
		
		poyta.setOnMousePressed(e -> {
			
			edellinenXsuunta = e.getSceneX();
			edellinenYSijainti = e.getSceneY();

			kuuntelija.onClickListener(poydantiedot);
			
			if (!asiakas) {
		
				kaanna.setVisible(true);
				
				poisto.setVisible(true);
				
				skaalaus.setVisible(true);
				
				valikot.setVisible(true);
				
			} else {
				valitsePoyta();
			}
			
		});

		poyta.setOnMouseDragged(e -> {
		
			naytto = poyta.localToScene(poyta.getBoundsInLocal());

			double nykyinenX = e.getSceneX();
			double nykyinenY = e.getSceneY();
			poydantiedot.setKoordinaattiX(naytto.getMinX());
			poydantiedot.setKoordinaattiY(naytto.getMinY());

			if (naytto.getMaxX() <= 690 && viimeisinSuunta != Suunta.OIKEA && nykyinenX - edellinenXsuunta > pikselit && !asiakas && !salliKaantyminen && !saalliSkaalaus) {
				
				double oikea = poyta.getTranslateX() + liikkuvuus;

				System.out.print("oikea" + naytto.getMaxX());
				
				poyta.setTranslateX(oikea);

				edellinenXsuunta = nykyinenX;

			} 

			else if (naytto.getMinX() >= 10 && viimeisinSuunta != Suunta.VASEN && nykyinenX - edellinenXsuunta < -pikselit && !asiakas && !salliKaantyminen && !saalliSkaalaus) {
				
				double vasen = poyta.getTranslateX() - liikkuvuus;

				System.out.print("vasen" + naytto.getMinX());
				
				poyta.setTranslateX(vasen);

				edellinenXsuunta = nykyinenX;

			}

			else if (naytto.getMaxY() <= 545 && viimeisinSuunta != Suunta.ALAS && nykyinenY - edellinenYSijainti > pikselit && !asiakas && !salliKaantyminen && !saalliSkaalaus) {
				
				double alas = poyta.getTranslateY() + liikkuvuus;

				System.out.print("alas" + naytto.getMaxY());
				
				poyta.setTranslateY(alas);

				edellinenYSijainti = nykyinenY;

			} else if (naytto.getMinY() >= 90 && viimeisinSuunta != Suunta.YLOS && nykyinenY - edellinenYSijainti < -pikselit && !asiakas && !salliKaantyminen && !saalliSkaalaus) {
				

				double ylos = poyta.getTranslateY() - liikkuvuus;

				System.out.print("ylos" + naytto.getMinY());
				
				poyta.setTranslateY(ylos);

				edellinenYSijainti = nykyinenY;

			}
			

		});

	}

	public void setData(Koordinaatit poydantiedot, IKoordinaatit kuuntelija, boolean asiakas) {
		this.poydantiedot = poydantiedot;
		this.kuuntelija = kuuntelija;
		this.asiakas = asiakas;
		
		ImageView imageview = (ImageView) poyta.getChildren().get(0);
		
		imageview.setImage(new Image(new ByteArrayInputStream(poydantiedot.getPoyta().getKuva())));

		poytatyyppi.setText(poydantiedot.getPoyta().getPoydanTyyppi());
		
		kaantoakseli = poydantiedot.getKulma();
		
		kuva.setRotate(kaantoakseli);
		
		skaalaa = poydantiedot.getKoko();
		
		alkuperainenKoko = poydantiedot.getKoko();
		
		kuva.setFitHeight(skaalaa);
		kuva.setFitWidth(skaalaa);
		
		poytatunnus.setText(String.valueOf(poydantiedot.getKoordinaattiTunnus()));
		
	}

	
	
	private void valitsePoyta() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("varaus.fxml"), App.getResourceBundle());
			
			Parent parent = fxmlLoader.load();
			
			Scene scene = App.getScene();
			
			scene.setRoot(parent);
			
			varausController varauskontrolleri = fxmlLoader.getController();
	
			varauskontrolleri.setData(poydantiedot);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
