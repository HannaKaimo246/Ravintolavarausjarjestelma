package model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * Luokka toteuttaa varauksen
 * @author R27
 * @since 2021/12/17
 */

@Entity
@Table(name="varaus")
public class Varaus {
	/**
	 * varauksen yksilöivä ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="poytavaraus")
    @Column(name = "varausID")
	public int varausID;
	/**
	 * varauksen päivämäärä
	 */
	@Column(name = "paivamaara")
	public LocalDate paiva;
	/**
	 * varauksen alkamisajankohta
	 */
	@Column(name = "alkamisajankohta")
	public LocalTime alkamisAjankohta;
	/**
	 * varauksen paattymisajankohta
	 */
	@Column(name = "paattymisajankohta")
	public LocalTime paattymisAjankohta;
	/**
	 * varauksen henkilömäärä
	 */
	@Column(name = "henkilomaara")
	public int henkiloMaara;
	
	/**
	 * varauksen tarkemmat lisätiedot
	 */
	@Column(name = "lisatiedot")
	public String lisaTiedot;

	/**
	 * varaaja
	 */
	@ManyToOne
	@JoinColumn(name="AsiakasID")
	public Asiakas asiakas;
	
	@ManyToOne
	@JoinColumn(name="PoytaID")
	public Poyta poyta;
	
	@ManyToOne
	@JoinColumn(name="KoordinaattiID")
	public Koordinaatit koordinaatit;
	
	/**
	 * 
	 * @param varausID varausID
	 * @param paiva varauksen päivämäärä
	 * @param alkamisAjankohta varauksen akamis kellonaika
	 * @param paattymisAjankohta varauksen päättymiskellonaika
	 * @param henkiloMaara varauksen henkilömäärä
	 * @param asiakas varaajan tiedot
	 */
	public Varaus(int varausID, LocalDate paiva, LocalTime alkamisAjankohta, LocalTime paattymisAjankohta, int henkiloMaara, String tiedot, Asiakas asiakas, Poyta poyta, Koordinaatit koordinaatit) {
		this.varausID = varausID;
		this.paiva = paiva;
		this.alkamisAjankohta = alkamisAjankohta;
		this.paattymisAjankohta = paattymisAjankohta;
		this.henkiloMaara = henkiloMaara;
		this.lisaTiedot = tiedot;
		this.asiakas = asiakas;
		this.poyta = poyta;
		this.koordinaatit = koordinaatit;
		
	}

	public Varaus() {
		
	}
	
	

	public Varaus(int varausID, LocalDate paiva, LocalTime alkamisAjankohta, LocalTime paattymisAjankohta,
			int henkiloMaara, String lisaTiedot) {
		super();
		this.varausID = varausID;
		this.paiva = paiva;
		this.alkamisAjankohta = alkamisAjankohta;
		this.paattymisAjankohta = paattymisAjankohta;
		this.henkiloMaara = henkiloMaara;
		this.lisaTiedot = lisaTiedot;
		
	}

	/**
	 * getteri varausID:lle
	 * @return varausID
	 */
	public int getVarausID() {
		return varausID;
	}
	/**
	 * asettaa varausID:n
	 * @param varausID
	 */
	public void setVarausID(int varausID) {
		this.varausID = varausID;
	}
	/**
	 * getteri varauksen päivämäärälle
	 * @return paiva
	 */
	public LocalDate getPaiva() {
		return paiva;
	}
	/**
	 * asettaa varauksen päivämäärän
	 * @param paiva
	 */
	public void setPaiva(LocalDate paiva) {
		this.paiva = paiva;
	}
	/**
	 * getteri varauksen alkamiskellonajalle
	 * @return alkamisAjankohta
	 */
	public LocalTime getAlkamisAjankohta() {
		return alkamisAjankohta;
	}
	/**
	 * asettaa varauksen alkamiskellonajan
	 * @param alkamisAjankohta
	 */
	public void setAlkamisAjankohta(LocalTime alkamisAjankohta) {
		this.alkamisAjankohta = alkamisAjankohta;
	}
	/**
	 * getteri varauksen päättymiskellonajalle
	 * @return paattymisAjankohta
	 */
	public LocalTime getPaattymisAjankohta() {
		return paattymisAjankohta;
	}
	/**
	 * asettaa varauksen päättymiskellonajan
	 * @param paattymisAjankohta
	 */
	public void setPaattymisAjankohta(LocalTime paattymisAjankohta) {
		this.paattymisAjankohta = paattymisAjankohta;
	}
	/**
	 * getteri henkilömäärälle
	 * @return henkilömäärä
	 */
	public int getHenkiloMaara() {
		return henkiloMaara;
	}
	/**
	 * asettaa henkilömäärän
	 * @param henkiloMaara
	 */
	public void setHenkiloMaara(int henkiloMaara) {
		this.henkiloMaara = henkiloMaara;
	}
	/**
	 * getteri asiakasID:lle
	 * @return asiakas
	 */
	public Asiakas getAsiakasID() {
		return asiakas;
	}
	/**
	 * asettaa asiakasID:n
	 * @param asiakas
	 */
	public void setAsiakasID(Asiakas asiakas) {
		this.asiakas = asiakas;
	}


	/**
	 * getteri lisätiedoille
	 * @return lisatiedot
	 */
	public String getLisaTiedot() {
		return lisaTiedot;
	}


	/**
	 * asettaa lisätiedot
	 * @param lisaTiedot
	 */
	public void setLisaTiedot(String lisaTiedot) {
		this.lisaTiedot = lisaTiedot;
	}
	
	/**
	 * asettaa poydan
	 * @param poyta
	 */
	public void setPoyta(Poyta poyta) {
		this.poyta = poyta;
	}

	public Koordinaatit getKoordinaatit() {
		return koordinaatit;
	}
	
	/*
	@Override
	public String toString() {
		return "Varaus [varausID=" + varausID + ", paiva=" + paiva + ", alkamisAjankohta=" + alkamisAjankohta
				+ ", paattymisAjankohta=" + paattymisAjankohta + ", henkiloMaara=" + henkiloMaara + ", palaute="
				+ palaute + ", asiakasID=" + asiakasID + "]";
	}
	*/

	
}
