package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Poyta;

class PoytaTest {
	
	private Poyta p = new Poyta(123, 6, "ikkunapoyta", null);

	@Test
	@DisplayName("testataan id")
	public void testId() {
		assertEquals(123, p.getPoytaID(), "pöydän id väärin");
		p.setPoytaID(456);
		assertEquals(456, p.getPoytaID(), "pöydän id väärin");
	}
	
	/*
	@Test
	@DisplayName("Testataan poydän nimi")
	public void testNimi() {
		assertEquals("ikkunapoyta", p.getNimi(),"Pöydän nimi väärin");
		p.setNimi("wc:n läheisyydessa oleva poyta");
		assertEquals("wc:n läheisyydessa oleva poyta", p.getNimi(), "pöydän nimi väärin");
		
		}
	*/
	
	@Test
	@DisplayName("Testataan poydän tyyppi")
	public void testTyyppi() {
		assertEquals("ikkunapoyta", p.getPoydanTyyppi(),"Pöydän tyyppi väärin");
		p.setPoydanTyyppi("katiska");
		assertEquals("katiska", p.getPoydanTyyppi(), "pöydän tyyppi väärin");
		
		}

	@Test
	@DisplayName("testataan paikkamäärä")
	public void testMAX() {
		assertEquals(6, p.getMaksimipaikat(), "pöydän paikkamäärä väärin");
		p.setMaksimipaikat(4);
		assertEquals(4, p.getMaksimipaikat(), "pöydän paikkamäärä väärin");
	}

	@Test
	@DisplayName("testataan toString")
	public void testString() {
		assertEquals("Poyta [poytaID=123, MAX=6]", p.toString(), "pöydän toString väärin");
		
	}


}
