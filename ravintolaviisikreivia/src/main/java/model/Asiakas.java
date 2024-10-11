package model;


import javax.persistence.*;

import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Luokassa luodaan asiakas
 * Luokassa luodaan myös palaute ja varaus tietokantaan
 * @author R27
 * @since 2021/12/17
 *
 */
@Entity
@Table(name="asiakas")
public class Asiakas extends Kayttaja {
	/**
	 * Asiakkaan ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
    @Column(name = "AsiakasID")
	public int asiakasID;
	/**
	 * Asiakkaan nimimerkki
	 */
	@Column(name = "Asiakas_nimimerkki")
	public String asiakasNimimerkki;
	/**
	 * Asiakkaan etunimi
	 */
	@Column(name = "Etunimi")
	public String etuNimi;
	
	/**
	 * asiakkaan sukunimi
	 */
	@Column(name = "Sukunimi")
	public String sukuNimi;
	
	/**
	 * asiakkaan sähköpostiosoite
	 */
	@Column(name = "Sahkopostiosoite")
	public String sahkopostiOsoite;
	
	/**
	 * asiakkaan salasana
	 */
	@Column(name = "Asiakas_salasana")
	public String salasana;
	
	/**
	 * asiakkaan puhelinnumero
	 */
	@Column(name = "Puhelinnumero")
	public String puhelinNumero;
	
	/**
	 * 
	 * palautetaulukko
	 */
	@Transient
	public Palaute[] palautteet;

	
	public Asiakas() {
		
	}
	/**
	 * 
	 * @param asiakasID asiakasID
	 * @param etuNimi asiakkaan etunimi
	 * @param sukuNimi asiakkaan sukunimi
	 * @param sahkopostiOsoite asiakkaan sähköpostiosoite
	 * @param puhelinNumero asiakkaan puhelinnumero
	 * @param salasana asiakkaan salasana
	 * @param asiakasNimimerkki asiakkaan nimimerkki
	 */
	public Asiakas(int asiakasID, String etuNimi, String sukuNimi, String sahkopostiOsoite, String puhelinNumero, String salasana, String asiakasNimimerkki) {

		super();
		this.asiakasID = asiakasID;
		this.asiakasNimimerkki = asiakasNimimerkki;
		this.etuNimi = etuNimi;
		this.sukuNimi = sukuNimi;
		this.puhelinNumero = puhelinNumero;
		this.sahkopostiOsoite = sahkopostiOsoite;
		this.salasana = salasana;
	}
	
	/**
	 * getteri asiakasID:lle
	 * @return asiakasID
	 */
	public int getAsiakasID() {
		return asiakasID;
	}

	/**
	 * asettaa asiakasID:n
	 * @param asiakasID
	 */
	public void setAsiakasID(int asiakasID) {
		this.asiakasID = asiakasID;
	}

	/**
	 * getteri etunimelle
	 * @return etunimi
	 */
	public String getEtuNimi() {
		return etuNimi;
	}

	/**
	 * asettaa etunimen
	 * @param etuNimi
	 */
	public void setEtuNimi(String etuNimi) {
		this.etuNimi = etuNimi;
	}

	/**
	 * getteri sukunimelle
	 * @return sukunimi
	 */
	public String getSukuNimi() {
		return sukuNimi;
	}

	/**
	 * asettaa sukunimen
	 * @param sukuNimi
	 */
	public void setSukuNimi(String sukuNimi) {
		this.sukuNimi = sukuNimi;
	}

	/**
	 * getteri ähköpostiosoitteelle
	 * @return sahkopostiOsoite
	 */
	public String getSahkopostiOsoite() {
		return sahkopostiOsoite;
	}

	/**
	 * asettaa sähköpostiooitteen
	 * @param sahkopostiOsoite
	 */
	public void setSahkopostiOsoite(String sahkopostiOsoite) {
		this.sahkopostiOsoite = sahkopostiOsoite;
	}
	/**
	 * getteri puhelinnumerolle
	 * @return puhelinNumero
	 */
	public String getPuhelinNumero() {
		return puhelinNumero;
	}

	/**
	 * asettaa puhelinnumeron
	 * @param puhelinNumero
	 */
	public void setPuhelinNumero(String puhelinNumero) {
		this.puhelinNumero = puhelinNumero;

	}
	/**
	 * asettaa nimimerkin
	 * @param nimimerkki
	 */
	public void setAsiakasNimimerkki(String nimimerkki) {
		this.asiakasNimimerkki = nimimerkki;
	}
	/**
	 * getteri nimimerkille
	 * @return asiakasNimimerkki
	 */
	public String getAsiakasNimimerkki() {
		return asiakasNimimerkki;
		
	}
	/**
	 * asettaa salasanan
	 * @param salasana
	 */
	public void setSalasana(String salasana) {
		
		this.salasana = salasana;
		
	}
	/**
	 * getteri salasanalle
	 * @return
	 */
	public String getSalasana() {
		return salasana;
		
	}
	

	/**
	 * palauttaa String- muotoisen estyksen asiakkaasta
	 */
	@Override
	public String toString() {
		return "Asiakas [asiakasID=" + asiakasID + ", etuNimi=" + etuNimi + ", sukuNimi=" + sukuNimi
				+ ", sahkopostiOsoite=" + sahkopostiOsoite + ", salasana=" + salasana + ", puhelinNumero="
				+ puhelinNumero + "]";
	}
	
	/**
	 * luodaan palaute tietokantaan
	 * @param tunnus palautteen tunnus
	 * @param aihe palautteen aihe 
	 * @param viesti palautteen viesti
	 * @return luotu
	 */
	public boolean luoPalaute(int tunnus, String aihe, String viesti) {
		
		// Luodaan palaute olio
		Palaute palaute = new Palaute(0,aihe,viesti,this, null, null);
	
		// Viedään palaute tiedot dao luokkaan
		boolean luotu = tietokanta.luoPalaute(palaute);
	
	
		
		return luotu;
		
	}
	
	/**
	 * Luodaan varaus tietokantaan
	 * @param paiva varauksen päivämäärä
	 * @param alku varauksen alkamisajankohta
	 * @param loppu varauksen päättymisajankohta
	 * @param maara varauksen henkilömäärä
	 * @param tiedot varauksen tiedot
	 * @param valittuPoyta 
	 */
	public void luoVaraus(LocalDate paiva, LocalTime alku, LocalTime loppu, int maara, String tiedot, Poyta valittuPoyta, Koordinaatit koordinaatti) {
		
		Varaus varaus = new Varaus(0, paiva, alku, loppu, maara, tiedot, this, valittuPoyta, koordinaatti);
		
		tietokanta.luoVaraus(varaus);
	}
	
	/**
	 * Haetaan asiakkaan palautteet DAO:sta
	 */
	public List<Palaute> getPalautteet() {
		
		List<Palaute> lista = tietokanta.haePalautteetAsiakas(this.asiakasID);
	
		return lista;
		
	}
	
}
	

	