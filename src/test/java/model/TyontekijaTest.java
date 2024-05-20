package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Ruoka;
import model.Tyontekija;

class TyontekijaTest {

	private Tyontekija tyontekija = new Tyontekija(1, "Tarjoilija", "Ammar@ammar12", "Ammar");

	@Test
	@DisplayName("Testataan konstruktoria")
	public void testRuoka() {
		assertEquals(1, tyontekija.getTyontekijaID());
		assertEquals("Tarjoilija", tyontekija.getRooli());
		assertEquals("Ammar@ammar12", tyontekija.getSalasana());
		assertEquals("Ammar",tyontekija.getTyontekijaNimiMerkki());
			
	}

	@Test
	@DisplayName("Testataan työntekijäID:n getter- methodi")
	public void testGetTyontekijaID() {
		assertEquals(1, tyontekija.getTyontekijaID(),"Tyontekijä- id:n get metodin arvo on väärä");
	}


	@Test
	@DisplayName("Testataan työntekijäID:n metodi")
	public void testSetTyonID() {
		tyontekija.setTyontekijaID(5);
		assertEquals(5, tyontekija.getTyontekijaID(),"Työtekijä- id:n set metodin arvo on väärä");
	}

	@Test
	@DisplayName("Testataan työtekijän rooli get-metodi")
	public void testGetRooli() {
		assertEquals("Tarjoilija", tyontekija.getRooli(),"Työntekijän rooli get metodin arvo on väärä");
	}

	@Test
	@DisplayName("Testataan työntekijä rooli set- metodi")
	public void testSetRooli() {
		tyontekija.setRooli("tarjoilija");
		assertEquals("tarjoilija", tyontekija.getRooli(), "Työtekijän roolin set metodin arvo on väärä");
		}


	@Test
	@DisplayName("Testataan työntekijän salasana get- metodi")
	public void testGetSalasana() {
		assertEquals("Ammar@ammar12", tyontekija.getSalasana(), "Työntekijän salasana get- metodi on väärä");
	}

	@Test
	@DisplayName("Työntekijän set- metdin tarkistaminen")
	public void testSetSalasana() {
		tyontekija.setSalasana("Ammar@ammar12");
		assertEquals("Ammar@ammar12", tyontekija.getSalasana() , "Työntekijän salasanan set- metodin arvo on väärä");
		}

	@Test
	@DisplayName("Testataan työntekijän nimimerkkin Get- metodin")
	public void testGetNimimerkki() {
		assertEquals("Ammar", tyontekija.getTyontekijaNimiMerkki() ,"Työntekijän nimimerkkin get metodin arvo on väärä");
		}


	@Test
	@DisplayName("Testataan työntekijän nimimerkkin set- metodi")
	public void testSetNimimerkki() {
		tyontekija.setTyontekijaNimiMerkki("Ammar");
		assertEquals("Ammar", tyontekija.getTyontekijaNimiMerkki(),"Työntekijän nimimerkkin set- metodin arvo väärä");
	}
	
}
