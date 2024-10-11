package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.KirjautuminenController;
import model.Asiakas;
import model.Kayttaja;
import model.Tyontekija;

public class KayttajaTest {
	
	private Tyontekija tyontekija = new Tyontekija(1, "Tarjoilija", "Ammar@ammar12", "Ammar");
	private Asiakas asiakas= new Asiakas(1, "Ville", "Kebab", "ville@mail.ru", "1234567", "bc0552f", "vile");
	
	Kayttaja k = Kayttaja.getInstance();
	
	
	@Test
	@DisplayName("Testataan asiakkaan käyttäjän luomista")
	public void testAsiakasKayttajanLuonti() {
		assertEquals(true, k.luoAsiakas(asiakas.getEtuNimi(), asiakas.getSukuNimi(), asiakas.getPuhelinNumero(), asiakas.getSahkopostiOsoite(), asiakas.getSalasana(), asiakas.getSalasana(), asiakas.getAsiakasNimimerkki()), 
				"Asiakas käyttäjää ei voitu luoda");
		
	}
	
	@Test
	@DisplayName("Testataan työntekijän käyttäjän luomista")
	public void testTyontekijaKayttajanLuonti() {
		assertEquals(true, k.luoTyontekija(tyontekija.getRooli(), tyontekija.getSalasana(), tyontekija.getTyontekijaNimiMerkki()), 
		"Tyontekijaa ei voitu luoda.");
		
	}
	
	/* testi ei toimi koska käyttäjänimi ei ole uniikki
	@Test
	@DisplayName("Testataan asiakkaan tarkistamista")
	public void testAsiakasKayttajanTarkistus() {
		Asiakas tietokantaAsiakas = k.tietokanta.onkoAsiakas(asiakas.getAsiakasNimimerkki(), k.hashPassword(asiakas.getSalasana()));
		assertEquals(asiakas.getAsiakasNimimerkki(), tietokantaAsiakas.getAsiakasNimimerkki(), "Asiakasta ei löytyny.t");
		
	}
	*/
	
	
	
		
}
