package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Asiakas;

class AsiakasTest {
	
	private static Asiakas asiakas;

	@BeforeAll
	public static void testAsiakas() {
		asiakas = new Asiakas(123, "Ville", "Kebab", "ville@mail.ru", "1234567", 
				"bc0552f", "vile");
	}
	
	@Test
	@DisplayName("testataan id")
	public void testId() {
		assertEquals(123, asiakas.getAsiakasID(), "asiakkaan id väärin");
		asiakas.setAsiakasID(456);
		assertEquals(456, asiakas.getAsiakasID(), "asiakkaan id väärin");
		
	}

	@Test
	@DisplayName("testataan etunimi")
	public void testEtunimi() {
		assertEquals("Ville", asiakas.getEtuNimi(), "asiakkaan etunimi väärin");
		asiakas.setEtuNimi("Matti");
		assertEquals("Matti", asiakas.getEtuNimi(), "asiakkaan etunimi väärin");
	}

	@Test
	@DisplayName("testataan sukunimi")
	public void testSukunimi() {
		assertEquals("Kebab", asiakas.getSukuNimi(), "asiakkaan sukunimi väärin");
		asiakas.setSukuNimi("Simonov");
		assertEquals("Simonov", asiakas.getSukuNimi(), "asiakkaan sukunimi väärin");
	}

	@Test
	@DisplayName("testataan sähköposti")
	public void testEmail() {
		assertEquals("ville@mail.ru", asiakas.getSahkopostiOsoite(), "asiakkaan sähköposti väärin");
		asiakas.setSahkopostiOsoite("matti@mail.ru");
		assertEquals("matti@mail.ru", asiakas.getSahkopostiOsoite(), "asiakkaan sähköposti väärin");
	}

	@Test
	@DisplayName("testataan puhelinnumero")
	public void testPuhelin() {
		assertEquals("1234567", asiakas.getPuhelinNumero(), "asiakkaan puhelinnumero väärin");
		asiakas.setPuhelinNumero("7654321");
		assertEquals("7654321", asiakas.getPuhelinNumero(), "asiakkaan puhelinnumero väärin");
	}

	@Test
	@DisplayName("testataan salasana")
	public void testSalasana() {
		assertEquals("bc0552f", asiakas.getSalasana(), "asiakkaan salasana väärin");
		asiakas.setSalasana("29a5bb6");
		assertEquals("29a5bb6", asiakas.getSalasana(), "asiakkaan salasana väärin");
	}

	@Test
	@DisplayName("testataan nimimerkki")
	public void testNimimerkki() {
		assertEquals("vile", asiakas.getAsiakasNimimerkki(), "asiakkaan nimimerkki väärin");
		asiakas.setAsiakasNimimerkki("taistelujaska");
		assertEquals("taistelujaska", asiakas.getAsiakasNimimerkki(), "asiakkaan nimimerkki väärin");
	}
	
	@Test
	@DisplayName("Testataan palautteen luominen")
	public void testPalaute() {
		asiakas.tietokanta = new RavintolavarausDAO();
		assertEquals(true, asiakas.luoPalaute(0, "Ruoka", "Ruoka oli jees"), "Palautetta ei voinut luoda.");
	}
	
	@Test
	@DisplayName("Testataan palautteen haku")
	public void testPalautteenHaku() {
		assertNotNull(asiakas.getPalautteet());
	}



}
