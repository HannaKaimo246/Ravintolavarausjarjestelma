package model;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class VarausTest {
	
	private static Asiakas a;
	private static Varaus v;
	private static Poyta p;
	private static Koordinaatit k;

	@BeforeAll
	public static void testVaraus() {
		a = new Asiakas(123, "Ville", "Kebab", "ville@mail.ru", "1234567", 
				"bc0552f", "vile");
		
		byte[] testi = new byte[1000000];
		
		p = new Poyta(1, 4, "poyta", testi);
		
		k = new Koordinaatit();
		
		v = new Varaus(1, LocalDate.parse("2021-10-15"), LocalTime.of(18, 0), 

		LocalTime.of(20, 0), 4, "ikkunan vieruspoyta", a, p, k);

	}

	@Test
	@DisplayName("testataan id")
	public void testID() {
		assertEquals(1, v.getVarausID(), "varauksen id väärin");
		v.setVarausID(55);
		assertEquals(55, v.getVarausID(), "varauksen id väärin");
	}

	@Test
	@DisplayName("testataan päivämäärä")
	public void testPaiva() {
		assertTrue(v.getPaiva().equals(LocalDate.parse("2021-10-15")), "varauksen päivä väärin");
		LocalDate d = LocalDate.parse("2020-02-02");
		v.setPaiva(d);
		assertTrue(v.getPaiva().equals(d), "varauksen päviä väärin");
	}
	
	@Test
	@DisplayName("testataan alkamisaika")
	public void testAlkamisaika() {
		assertTrue(v.getAlkamisAjankohta().equals(LocalTime.of(18, 0)), "varauksen alkamisaika väärin");
		LocalTime t = LocalTime.of(19, 15);
		v.setAlkamisAjankohta(t);
		assertTrue(v.getAlkamisAjankohta().equals(t), "varauksen alkamisaika väärin");
	}

	@Test
	@DisplayName("testataan päättymisaika")
	public void testPaattymisaika() {
		assertTrue(v.getPaattymisAjankohta().equals(LocalTime.of(20, 0)), "varauksen päättymisaika väärin");
		LocalTime t = LocalTime.of(15, 45);
		v.setPaattymisAjankohta(t);
		assertTrue(v.getPaattymisAjankohta().equals(t), "varauksen päättymisaika väärin");
	}

	@Test
	@DisplayName("testataan henkilömäärä")
	public void testHenkilot() {
		assertEquals(4, v.getHenkiloMaara(), "varauksen henkilömäärä väärin");
		v.setHenkiloMaara(6);
		assertEquals(6, v.getHenkiloMaara(), "varauksen henkilömäärä väärin");
	}

	@Test
	@DisplayName("testataan asiakasid")
	public void testAsiakasID() {
		assertEquals(123, v.getAsiakasID().getAsiakasID(), "varauksen asiakasid väärin");
		Asiakas a2 = new Asiakas(5, "etu", "suku", "mail", "puh", "passu", "nick");
		v.setAsiakasID(a2);
		assertEquals(5, v.getAsiakasID().getAsiakasID(), "varauksen asiakasid väärin");
	}
	
	@Test
	@DisplayName("testataan varauksen lisätiedot")
	public void testLisaTiedot() {
		assertEquals("ikkunan vieruspoyta", v.getLisaTiedot(), "varauksen lisatiedot väärin");
		v.setLisaTiedot("esteetön kulku");
		assertEquals("esteetön kulku", v.getLisaTiedot(), "varauksen lisatiedot väärin");
	}

}
