package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Asiakas;
import model.Palaute;
import model.Tyontekija;

class PalauteTest {

	private static Asiakas a;
	private static Palaute p;
	private static Palaute v;
	private static Tyontekija t;

	@BeforeAll
	public static void testVaraus() {
		a = new Asiakas(123, "Ville", "Kebab", "ville@mail.ru", "1234567", 
				"bc0552f", "vile");
		t = new Tyontekija(24, "pääsihteeri", "89aeb6d", "pena");
		v = new Palaute(2, "vastausaihe", "vastausviesti", null, null, t);
		p = new Palaute(1, "testiaihe", "testiviesti", a, v, t);
	}

	@Test
	@DisplayName("testataan aihe")
	public void testAihe() {
		assertEquals("testiaihe", p.getAihe(), "palautteen aihe väärin");
		p.setAihe("kissa");
		assertEquals("kissa", p.getAihe(), "palautteen aihe väärin");
	}

	@Test
	@DisplayName("testataan viesti")
	public void testViesti() {
		assertEquals("testiviesti", p.getViesti(), "palautteen viesti väärin");
		p.setViesti("kala");
		assertEquals("kala", p.getViesti(), "palautteen viesti väärin");
	}

	@Test
	@DisplayName("testataan asiakas")
	public void testAsiakas() {
		assertEquals("Ville", p.getNimi(), "palautteen asiakas väärin");
		Asiakas a2 = new Asiakas(5, "etu", "suku", "mail", "puh", "passu", "nick");
		p.setAsiakas(a2);
		assertEquals("etu", p.getNimi(), "palautteen asiakas väärin");
	}

	@Test
	@DisplayName("testataan työntekijä")
	public void testTyontekija() {
		assertEquals(24, p.getTyontekija().getTyontekijaID(), "palautteen työntekijä väärin");
		Tyontekija t2 = new Tyontekija(34, "rooli", "passu", "nimi");
		p.setTyontekija(t2);
		assertEquals(34, p.getTyontekija().getTyontekijaID(), "palautteen työntekijä väärin");
	}

	@Test
	@DisplayName("testataan vastaus")
	public void testVastaus() {
		assertEquals("vastausaihe", p.getVastaus().getAihe(), "palautteen vastaus väärin");
		Palaute v2 = new Palaute(4, "kissa", "kala", null, null, t);
		p.setVastaus(v2);
		assertEquals("kissa", p.getVastaus().getAihe(), "palautteen vastaus väärin");
	}

	@Test
	@DisplayName("testataan aika")
	public void testAika() {
		LocalDateTime aika = LocalDateTime.of(2020, 10, 7, 18, 0);
		p.setAika(aika);
		assertTrue(p.getAika().equals(aika));
		LocalDateTime aika2 = LocalDateTime.of(2020, 9, 15, 17, 30);
		p.setAika(aika2);
		assertTrue(p.getAika().equals(aika2));
	}

}
