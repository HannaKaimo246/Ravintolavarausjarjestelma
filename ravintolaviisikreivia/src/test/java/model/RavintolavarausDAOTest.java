package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Asiakas;
import model.Palaute;
import model.RavintolavarausDAO;
import model.Tyontekija;
import model.Varaus;

public class RavintolavarausDAOTest {

	private static RavintolavarausDAO tietokanta;
	
	
	@BeforeAll
	public static void testDAO() throws Exception {
		tietokanta = new RavintolavarausDAO();
		
	}
	
	
	@Test
	@DisplayName("Luo Palaute pitäisi palauttaa false")
	public void testPalaute() {
		Palaute p = new Palaute();
		assertEquals(false, tietokanta.luoPalaute(p), "Palaute piti palauttaa false");
		
	}
	
	@Test
	@DisplayName("Luo Varaus pitäisi palauttaa false")
	public void testVaraus() {
		Varaus v = new Varaus();
		assertEquals(false, tietokanta.luoVaraus(v), "Varaus piti palauttaa false");
	
	}
	
	@Test
	@DisplayName("Luo Asiakas pitäisi palauttaa false")
	public void testAsiakas() {
		Asiakas a = new Asiakas();
		assertEquals(false, tietokanta.luoAsiakas(a), "Asiakas piti palauttaa false");
	
	}
	
	@Test
	@DisplayName("Luo tyontekija pitäisi palauttaa false")
	public void testTyontekija() {
		Tyontekija t = new Tyontekija();
		assertEquals(false, tietokanta.luoTyontekija(t), "tyontekija piti palauttaa false");
	
	}
	
}
