package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Ruoka;

class RuokaTest {

	private Ruoka ruokaAnnos = new Ruoka(1, "pitsa", "Napolin pitsa", "pizza", 10.90);

	@Test
	@DisplayName("Testataan konstruktoria")
	public void testRuoka() {
		assertEquals(1, ruokaAnnos.getRuokaID());
		assertEquals("pitsa", ruokaAnnos.getNimi());
		assertEquals("Napolin pitsa", ruokaAnnos.getKuvaus());
		assertEquals(10.90,ruokaAnnos.getHinta());
			
	}

	@Test
	@DisplayName("Testataan ruokaID:n getter- methodi")
	public void testGetRuokaID() {
		assertEquals(1, ruokaAnnos.getRuokaID(),"Ruoka- id:n get metodin arvo on väärä");
	
	}


	@Test
	@DisplayName("Testataan ruokaID:n metodi")
	public void testSetRuokaID() {
		ruokaAnnos.setRuokaID(20);
		assertEquals(20, ruokaAnnos.getRuokaID(),"Ruoka- id:n set metodin arvo on väärä");
	}

	@Test
	@DisplayName("Testataan ruoan nimen get-metodi")
	public void testGetNimi() {
		assertEquals("pitsa", ruokaAnnos.getNimi(),"Ruoan nimen get metodin arvo on väärä");
		
	}

	@Test
	@DisplayName("Testataan ruoan nimen set- metodi")
	public void testSetNimi() {
		ruokaAnnos.setNimi("Kebab");
		assertEquals("Kebab", ruokaAnnos.getNimi(),"Ruoan nimen get metodin arvo on väärä");
		
		}


	@Test
	@DisplayName("Testataan ruoan kuvauksen get- metodi")
	public void testGetKuvaus() {
		assertEquals("Napolin pitsa", ruokaAnnos.getKuvaus(), "Ruoan kuvauksen get- metodi on väärä");
	}

	@Test
	@DisplayName("Ruoan kuvauksen set- metodin tarkistaminen")
	public void testSetKuvaus() {
		ruokaAnnos.setKuvaus("Jauhelihapitsa");
		assertEquals("Jauhelihapitsa", ruokaAnnos.getKuvaus(),"Ruoan kuvauksen set- metodin arvo on väärä");
		
		}

	@Test
	@DisplayName("Testataan hinnan Get- metodin")
	public void testGetHinta() {
		assertEquals(10.90, ruokaAnnos.getHinta(),"Ruoan hinnan get metodin arvo on väärä");
		
		}


	@Test
	@DisplayName("Testataan ruoan hinnan set- metodi")
	public void testSetHinta() {
		ruokaAnnos.setHinta(11.20);
		assertEquals(11.20,ruokaAnnos.getHinta(),"Ruoan hinnan set- metodin arvo väärä");
	}
	
	@Test
	@DisplayName("testataan, että Id on int- muodossa")
	public void testIntMuoto(){
		assertEquals(1, ruokaAnnos.getRuokaID(),"id ei ole integer- muodossa");
	}

}
